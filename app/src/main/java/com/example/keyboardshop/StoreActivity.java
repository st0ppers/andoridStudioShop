package com.example.keyboardshop;


import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {
    DbHelper dbHelper;
    EditText searchEditText;
    RecyclerView itemsRecyclerView;
    List<KeyboardModel> keyboardModelList;
    StoreAdapter storeAdapter;
    ImageView shopButton, cartButton, wishlistButton, profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DbHelper(this);
        setContentView(R.layout.store);
        InitBottomNavigation();

        searchEditText = findViewById(R.id.searchEditText);
        itemsRecyclerView = findViewById(R.id.itemsRecyclerView);

        List<KeyboardEntity> dbKeyboards = dbHelper.getAll();
        keyboardModelList = entityToModel(dbKeyboards);

        storeAdapter = new StoreAdapter(keyboardModelList, this);
        itemsRecyclerView.setAdapter(storeAdapter);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        OnClickListeners();
    }

    private void OnClickListeners() {
        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(StoreActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(StoreActivity.this, CartActivity.class);
            startActivity(intent);
        });
        wishlistButton.setOnClickListener(v -> {
            Intent intent = new Intent(StoreActivity.this, WishlistActivity.class);
            startActivity(intent);
        });
        shopButton.setOnClickListener(v -> {
            Intent intent = new Intent(StoreActivity.this, StoreActivity.class);
            startActivity(intent);
        });
    }

    private void InitBottomNavigation() {
        shopButton = findViewById(R.id.shopImageView);
        cartButton = findViewById(R.id.cartImageView);
        wishlistButton = findViewById(R.id.wishlistImageView);
        profileButton = findViewById(R.id.profileImageView);

        shopButton.setImageResource(R.drawable.hsop);
        cartButton.setImageResource(R.drawable.cart);
        wishlistButton.setImageResource(R.drawable.whishlist);
        profileButton.setImageResource(R.drawable.profile);
    }

    public void insertIntoDatabase(int customerId, int keyboardId) {
        //TODO Get CustomerId
        List<Integer> ids = dbHelper.getKeyboardsFromWishlistByCustomerId(customerId);
        if (ids.contains(keyboardId)) {
            Toast.makeText(this, "Keyboard is already in your wishlist", Toast.LENGTH_SHORT).show();
            return;
        }
        dbHelper.addToWishList(customerId, keyboardId);
        Toast.makeText(this, "Successfully added to your wishlist", Toast.LENGTH_SHORT).show();
    }

    public void addToCart(int keyboardId) {
        List<KeyboardEntity> entities = dbHelper.getKeyboard(keyboardId);
        List<KeyboardModel> model = entityToModel(entities);

        if (InMemoryCart.addToCart(model)) {
            Toast.makeText(this, "Successfully added to your cart", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Failed to added to your cart", Toast.LENGTH_SHORT).show();
    }

    private static List<KeyboardModel> entityToModel(List<KeyboardEntity> list) {
        List<KeyboardModel> result = new ArrayList<>();
        for (KeyboardEntity ke : list) {
            KeyboardModel km = new KeyboardModel(ke.getId(), 1, ke.getName(), ke.getPrice(), ke.getPhotoId(), ke.getDiscount(), ke.getRating());
            result.add(km);
        }
        return result;
    }
}
