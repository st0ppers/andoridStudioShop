package com.example.keyboardshop;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    List<KeyboardModel> orders;
    ImageView profileImageView;
    TextView nameTextView, emailTextView;
    RecyclerView orderListRecyclerView;
    ImageView shopButton, cartButton, wishlistButton, profileButton;
    ProfileOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        InitBottomNavigation();

        profileImageView = findViewById(R.id.profileImageView);
        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        orderListRecyclerView = findViewById(R.id.orderListRecyclerView);

        orders = GetBoughtItems();
        adapter = new ProfileOrderAdapter(orders);
        orderListRecyclerView.setAdapter(adapter);
        orderListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        OnClickListeners();
    }

    private List<KeyboardModel> GetBoughtItems() {
        List<KeyboardModel> list = new ArrayList<>();
        list.add(new KeyboardModel(1, "Alice 66", 89, R.drawable.alice, 65, 0, 2));
        return list;
    }
    private void OnClickListeners() {
        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, CartActivity.class);
            startActivity(intent);
        });
        wishlistButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, WishlistActivity.class);
            startActivity(intent);
        });
        shopButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, StoreActivity.class);
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
