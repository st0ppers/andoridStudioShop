package com.example.keyboardshop;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private static final double deliveryFee = 19.99;
    RecyclerView cartItemsRecyclerView;
    Button checkoutButton;
    TextView totalPriceTextView, deliveryTextView, subTotalTextView;
    ImageView shopButton, cartButton, wishlistButton, profileButton;
    ItemAdapter itemAdapter;
    List<KeyboardModel> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);
        InitBottomNavigation();
        items = GetItemsInCart();

        checkoutButton = findViewById(R.id.chekcoutButton);
        checkoutButton.setBackgroundColor(ContextCompat.getColor(this, R.color.green));

        cartItemsRecyclerView = findViewById(R.id.cartItemsRecyclerView);
        deliveryTextView = findViewById(R.id.deliveryTextViewNumber);
        deliveryTextView.setText("$" + String.format("%.2f", deliveryFee));
        subTotalTextView = findViewById(R.id.subTotalPriceTextViewNumber);
        subTotalTextView.setText("$" + String.format("%.2f", CalculateSubTotalPrice()));
        totalPriceTextView = findViewById(R.id.totalPriceTextViewNumber);
        double totalPrice = CalculateSubTotalPrice();
        totalPriceTextView.setText("$" + String.format("%.2f", (totalPrice + deliveryFee)));

        itemAdapter = new ItemAdapter(items);
        cartItemsRecyclerView.setAdapter(itemAdapter);
        cartItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        OnClickListeners();
    }

    private void OnClickListeners() {
        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, CartActivity.class);
            startActivity(intent);
        });
        wishlistButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, WishlistActivity.class);
            startActivity(intent);
        });
        shopButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, StoreActivity.class);
            startActivity(intent);
        });
    }

    private double CalculateSubTotalPrice() {
        double price = 0;
        for (KeyboardModel keyboard : items) {
            price += keyboard.totalPrice;
        }
        return price;
    }

    private List<KeyboardModel> GetItemsInCart() {
        List<KeyboardModel> list = new ArrayList<>();
        list.add(new KeyboardModel(1, "Alice 66", 89, R.drawable.alice, 65, 0, 2));
        return list;
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
