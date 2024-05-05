package com.example.keyboardshop;


import static com.example.keyboardshop.Mapper.entityToModel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    DbHelper dbHelper;
    List<KeyboardModel> orders;
    ImageView profileImageView;
    TextView nameTextView, emailTextView;
    RecyclerView orderListRecyclerView;
    ImageView shopButton, cartButton, wishlistButton, profileButton;
    ProfileOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DbHelper(this);
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
        List<KeyboardEntity> entities = dbHelper.getOrders(1);
        return entityToModel(entities);
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
