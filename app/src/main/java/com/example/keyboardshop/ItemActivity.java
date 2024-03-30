package com.example.keyboardshop;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemActivity extends AppCompatActivity {
    ImageView itemImageView;
    TextView itemNameTextView, itemPriceTextView, itemDiscountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_item);

        itemImageView = findViewById(R.id.shoppingItem);
        itemNameTextView = findViewById(R.id.itemNameTextView);
        itemPriceTextView = findViewById(R.id.itemPriceTextView);
        itemDiscountTextView = findViewById(R.id.itemDiscountTextView);
    }
}
