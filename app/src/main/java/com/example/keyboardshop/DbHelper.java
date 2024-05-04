package com.example.keyboardshop;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "keyboard.db";
    public static final int DBVERSION = 6;
    private SQLiteDatabase _myDB;

    public static String DBCREATEKEYBOARD = "" + "CREATE TABLE keyboard(" + "ID integer PRIMARY KEY AUTOINCREMENT, " + "Name text not null, " + "Price numeric not null, " + "Quantity integer not null," + "Discount integer not null," + "Rating real not null," + "PhotoId integer not null" + ")";

    public static String DBCREATECUSTOMERS = "" + "CREATE TABLE customers(" + "ID integer PRIMARY KEY AUTOINCREMENT, " + "Name text not null, " + "Password text not null, " + "Email text not null, " + "Phone text not null " + ")";

    public static String DBCREATEORDERS = "" + "CREATE TABLE orders(" + "ID integer PRIMARY KEY AUTOINCREMENT, " + "CustomerId integer not null, " + "KeyboardId integer not null, " + "OrdertDate text not null " + ")";

    public static String DBCREATEWISHLIST = "" + "CREATE TABLE wishlist(" + "ID integer PRIMARY KEY AUTOINCREMENT, " + "CustomerId integer not null, " + "KeyboardId integer not null " + ")";

    public DbHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
        _myDB = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
        populateKeyboards(db);
        addTestCustomer(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            dropTables(db);
            createTables(db);
            populateKeyboards(db);
            addTestCustomer(db);
        }
    }

    private void createTables(SQLiteDatabase db) {
        db.execSQL(DBCREATEKEYBOARD);
        db.execSQL(DBCREATECUSTOMERS);
        db.execSQL(DBCREATEORDERS);
        db.execSQL(DBCREATEWISHLIST);
    }

    private void dropTables(SQLiteDatabase db) {
        db.execSQL("DROP TABLE keyboard; ");
        db.execSQL("DROP TABLE customers; ");
        db.execSQL("DROP TABLE orders; ");
        db.execSQL("DROP TABLE wishlist; ");
    }

    private void populateKeyboards(SQLiteDatabase db) {
        int gmmk = R.drawable.gmmk;
        int sofle = R.drawable.sofle;
        int chocofi = R.drawable.chocofi;
        int alic = R.drawable.alice;

        db.execSQL("INSERT INTO keyboard (Name, Price, Quantity, Discount , Rating, PhotoId) VALUES " + "('GMMK Pro', '55.00', 10, 1, 4, ?), " + "('Sofle', '165.00', 8, 0, 3, ?), " + "('Alice 66', '200.00', 13, 0, 2, ?), " + "('Chocofi', '89.00', 5, 10, 5, ?)", new Object[]{gmmk, sofle, alic, chocofi});
    }

    private void addTestCustomer(SQLiteDatabase db) {
        db.execSQL("INSERT INTO customers(Name, Password , Email , Phone) VALUES " + "('test', '123', 'test@mail.com', 1231231234)");
    }

    private Cursor getOrdersByCustomerId(int customerId) {
        return _myDB.rawQuery("SELECT * FROM orders WHERE CustomerId = ?", new String[]{String.valueOf(customerId)});
    }

    private Cursor getWishlistByCustomerIdInternal(int customerId) {
        return _myDB.rawQuery("SELECT * FROM wishlist WHERE CustomerId = ?", new String[]{String.valueOf(customerId)});
    }

    private Cursor getKeyboardByIdInternal(int id) {
        return _myDB.rawQuery("SELECT * FROM keyboard WHERE ID = ?", new String[]{String.valueOf(id)});
    }

    private static int mapInt(Cursor c) {
        int index = c.getColumnIndex("KeyboardId");
        return c.getInt(index);
    }

    private static KeyboardEntity mapKeyboard(Cursor c) {
        int idIndex = c.getColumnIndex("ID");
        int nameIndex = c.getColumnIndex("Name");
        int priceIndex = c.getColumnIndex("Price");
        int quantityIndex = c.getColumnIndex("Quantity");
        int discountIndex = c.getColumnIndex("Discount");
        int ratingIndex = c.getColumnIndex("Rating");
        int photoIndex = c.getColumnIndex("PhotoId");

        int keyboardId = c.getInt(idIndex);
        String name = c.getString(nameIndex);
        BigDecimal price = new BigDecimal(c.getString(priceIndex));
        int quantity = c.getInt(quantityIndex);
        int discount = c.getInt(discountIndex);
        int rating = c.getInt(ratingIndex);
        int photoId = c.getInt(photoIndex);

        return new KeyboardEntity(keyboardId, name, price, quantity, discount, rating, photoId);
    }

    private <T> List<T> test(Cursor c, Function<Cursor, T> func) {
        List<T> result = new ArrayList<>();

        if (c != null && c.moveToFirst()) {
            do {
                T item = func.apply(c);
                result.add(item);
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }
        return result;
    }

    public boolean deleteFromWishlist(int customerId, int keyboardId) {
        boolean success = false;
        try {
            String selection = "CustomerId = ? AND KeyboardId = ?";
            String[] selectionArgs = {String.valueOf(customerId), String.valueOf(keyboardId)};
            int rowsDeleted = _myDB.delete("wishlist", selection, selectionArgs);
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (Exception ignored) {
        } finally {
            _myDB.close();
        }

        return success;
    }

    public Cursor getAllKeyboards() {
        return _myDB.rawQuery("SELECT * FROM keyboard", null);
    }

    public void addToWishList(int customerId, int keyboardId) {
        _myDB.execSQL("INSERT INTO wishlist(CustomerId, KeyboardId) " + "VALUES(?, ?)", new Object[]{customerId, keyboardId});
    }

    public Cursor getCustomerByUsername(String username) {
        return _myDB.rawQuery("SELECT * FROM customers WHERE Name  = ?", new String[]{username});
    }

    public List<Integer> getKeyboardsFromWishlistByCustomerId(int customerId) {
        Cursor c = getWishlistByCustomerIdInternal(customerId);
        return test(c, DbHelper::mapInt);
    }

    public List<KeyboardEntity> getAll() {
        Cursor c = getAllKeyboards();
        return test(c, DbHelper::mapKeyboard);
    }

    public List<KeyboardEntity> getAllById(List<Integer> ids) {
        List<KeyboardEntity> list = new ArrayList<>();
        for (int id : ids) {
            Cursor c = getKeyboardByIdInternal(id);
            list.addAll(test(c, DbHelper::mapKeyboard));
        }
        return list;
    }

    public List<KeyboardEntity> getOrders(int customerId) {
        Cursor c = getOrdersByCustomerId(customerId);
        return test(c, DbHelper::mapKeyboard);
    }
}
