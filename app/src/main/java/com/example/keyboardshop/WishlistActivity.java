package com.example.keyboardshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {
    ImageView shopButton, cartButton, wishlistButton, profileButton;
    RecyclerView wishlistRecyleView;
    WishlistAdapter wishlistAdapter;
    List<KeyboardModel> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlist);
        InitBottomNavigation();
        OnClickListeners();

        wishlistRecyleView = findViewById(R.id.wishlistRecyleView);

        items = GetWishlistItems();
        wishlistAdapter = new WishlistAdapter(items);
        wishlistRecyleView.setAdapter(wishlistAdapter);
        wishlistRecyleView.setLayoutManager(new LinearLayoutManager(this));
        //Use shopping list item
    }

    private List<KeyboardModel> GetWishlistItems() {
        List<KeyboardModel> list = new ArrayList<>();
        list.add(new KeyboardModel(2, "GMMK Pro", 55, R.drawable.gmmk, 1, 1, 4));
        list.add(new KeyboardModel(1, "Sofle", 156, R.drawable.sofle, 10, 0, 3));
        list.add(new KeyboardModel(4, "Chocofi", 200, R.drawable.chocofi, 22, 10, 5));
        return list;
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
}
