package com.example.keyboardshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {
    EditText searchEditText;
    RecyclerView itemsRecyclerView;
    List<KeyboardModel> keyboardModelList;
    StoreAdapter storeAdapter;
    ImageView shopButton, cartButton, wishlistButton, profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);
        InitBottomNavigation();

        searchEditText = findViewById(R.id.searchEditText);
        itemsRecyclerView = findViewById(R.id.itemsRecyclerView);

        keyboardModelList = MockData();
        storeAdapter = new StoreAdapter(keyboardModelList);
        itemsRecyclerView.setAdapter(storeAdapter);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        OnClickListeners();

//        searchEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // Filter items when text changes
//                storeAdapter.filter(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//        });
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

    private List<KeyboardModel> MockData() {
        List<KeyboardModel> list = new ArrayList<>();
        list.add(new KeyboardModel(2, "GMMK Pro", 55, R.drawable.gmmk, 1, 1, 4));
        list.add(new KeyboardModel(1, "Sofle", 156, R.drawable.sofle, 10, 0, 3));
        list.add(new KeyboardModel(4, "Chocofi", 200, R.drawable.chocofi, 22, 10, 5));
        list.add(new KeyboardModel(1, "Alice 66", 89, R.drawable.alice, 65, 0, 2));
        return list;
    }
}
