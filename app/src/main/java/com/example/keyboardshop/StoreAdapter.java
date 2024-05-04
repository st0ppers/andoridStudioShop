package com.example.keyboardshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreItemViewHolder> {
    private final List<KeyboardModel> itemList;
    private final StoreActivity storeActivity;

    public StoreAdapter(List<KeyboardModel> itemList, StoreActivity storeActivity) {
        this.itemList = itemList;
        this.storeActivity = storeActivity;
    }

    @NonNull
    @Override
    public StoreItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_item, parent, false);
        return new StoreItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreItemViewHolder holder, int position) {
        KeyboardModel keyboard = itemList.get(position);
        holder.name.setText(keyboard.getName());
        holder.price.setText("$" + String.format("%.2f", keyboard.getSinglePrice()));
        holder.inStock.setText(String.valueOf(keyboard.getQuantity()));
        holder.discount.setText(keyboard.getDiscount() + "%");
        holder.ratingBar.setRating(keyboard.getRating());
        holder.image.setImageResource(keyboard.getPhotoId());

        holder.wishlistButton.setOnClickListener(v ->{
            if (position != RecyclerView.NO_POSITION) {
                storeActivity.insertIntoDatabase(1,keyboard.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public List<KeyboardModel> getList() {
        return itemList;
    }

    public static class StoreItemViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        TextView inStock;
        TextView discount;
        ImageView image, wishlistButton;
        RatingBar ratingBar;

        public StoreItemViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.shoppingItem);
            name = itemView.findViewById(R.id.itemNameTextView);
            inStock = itemView.findViewById(R.id.inStockNumber);
            price = itemView.findViewById(R.id.priceNumberTextView);
            discount = itemView.findViewById(R.id.discountNumber);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            wishlistButton = itemView.findViewById(R.id.wishlistButton);
        }
    }
}

