package com.example.keyboardshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private static final BigDecimal deliveryFee = BigDecimal.valueOf(19.99);
    DbHelper dbHelper;
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
        dbHelper = new DbHelper(this);
        items = InMemoryCart.getCart();
        checkoutButton = findViewById(R.id.chekcoutButton);
        checkoutButton.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        checkoutButton.setOnClickListener(v -> {
            for (KeyboardModel km : items) {
                List<KeyboardEntity> list = dbHelper.getKeyboard(km.getId());
                if (list.get(0).getQuantity() < km.getQuantity()){
                    Toast.makeText(this, "Don't have enough in stock.", Toast.LENGTH_SHORT).show();
                    continue;
                }
                dbHelper.addToOrders(1, km.getId(), km.getQuantity());
                Toast.makeText(this, "Successfully ordered.", Toast.LENGTH_SHORT).show();
            }
            items.clear();
        });

        cartItemsRecyclerView = findViewById(R.id.cartItemsRecyclerView);
        deliveryTextView = findViewById(R.id.deliveryTextViewNumber);
        subTotalTextView = findViewById(R.id.subTotalPriceTextViewNumber);
        totalPriceTextView = findViewById(R.id.totalPriceTextViewNumber);

        subTotalTextView.setText("$" + String.format("%.2f", CalculateSubTotalPrice()));

        BigDecimal totalPrice = CalculateSubTotalPrice();
        totalPrice = totalPrice.add(deliveryFee);

        if (items.isEmpty()) {
            deliveryTextView.setText("$" + String.format("%.2f", BigDecimal.valueOf(0)));
            totalPriceTextView.setText("$" + String.format("%.2f", BigDecimal.valueOf(0)));
        } else {
            deliveryTextView.setText("$" + String.format("%.2f", deliveryFee));
            totalPriceTextView.setText("$" + String.format("%.2f", totalPrice));
        }

        itemAdapter = new ItemAdapter(items);
        cartItemsRecyclerView.setAdapter(itemAdapter);
        cartItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        bottomNavigationListeners();
    }


    private void bottomNavigationListeners() {
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

    private BigDecimal CalculateSubTotalPrice() {
        BigDecimal price = BigDecimal.valueOf(0);
        for (KeyboardModel keyboard : items) {
            price = price.add(keyboard.totalPrice);
        }
        return price;
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
