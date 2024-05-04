package com.example.keyboardshop;

import static com.example.keyboardshop.Mapper.entityToModel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WishlistActivity extends AppCompatActivity {
    DbHelper dbHelper;
    ImageView shopButton, cartButton, wishlistButton, profileButton;
    RecyclerView wishlistRecyleView;
    WishlistAdapter wishlistAdapter;
    List<KeyboardModel> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DbHelper(this);
        setContentView(R.layout.wishlist);
        InitBottomNavigation();
        OnClickListeners();

        wishlistRecyleView = findViewById(R.id.wishlistRecyleView);

        items = GetWishlistItems();
        wishlistAdapter = new WishlistAdapter(items, this);
        wishlistRecyleView.setAdapter(wishlistAdapter);
        wishlistRecyleView.setLayoutManager(new LinearLayoutManager(this));
    }

    public List<KeyboardModel> GetWishlistItems() {
        List<Integer> keyboardIds = dbHelper.getKeyboardsFromWishlistByCustomerId(1);//todo get customerId
        List<KeyboardEntity> entities = dbHelper.getAllById(keyboardIds);

        return entityToModel(entities);
    }

    private void OnClickListeners() {
        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(WishlistActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(WishlistActivity.this, CartActivity.class);
            startActivity(intent);
        });
        wishlistButton.setOnClickListener(v -> {
            Intent intent = new Intent(WishlistActivity.this, WishlistActivity.class);
            startActivity(intent);
        });
        shopButton.setOnClickListener(v -> {
            Intent intent = new Intent(WishlistActivity.this, StoreActivity.class);
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

    public void removeFromWishlist(int customerId, int keyboardId,int position) {
        boolean success = dbHelper.deleteFromWishlist(customerId, keyboardId);
        if (success) {
            wishlistAdapter.notifyItemRemoved(position);
            Toast.makeText(this, "Successfully remove keyboard from wishlist", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Failed to remove keyboard from wishlist", Toast.LENGTH_SHORT).show();
    }
}
