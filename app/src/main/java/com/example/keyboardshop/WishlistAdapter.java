package com.example.keyboardshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishListItemViewHolder> {
    private List<KeyboardModel> itemList;
    private final WishlistActivity wishlistActivity;

    public WishlistAdapter(List<KeyboardModel> itemList, WishlistActivity wishlistActivity) {
        this.itemList = itemList;
        this.wishlistActivity = wishlistActivity;
    }

    @NonNull
    @Override
    public WishListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item, parent, false);
        return new WishListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListItemViewHolder holder, int position) {
        KeyboardModel keyboard = itemList.get(position);
        holder.name.setText(keyboard.getName());
        holder.price.setText("$" + String.format("%.2f", keyboard.getSinglePrice()));
        holder.image.setImageResource(keyboard.getPhotoId());
        holder.removeButton.setOnClickListener(v -> {
            if (position != RecyclerView.NO_POSITION) {
                wishlistActivity.removeFromWishlist(1, keyboard.getId(), position);
                itemList.remove(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class WishListItemViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        ImageView image, removeButton;

        public WishListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.keyboardItemImageView);
            name = itemView.findViewById(R.id.titleTextView);
            price = itemView.findViewById(R.id.totalCostTextView);
            removeButton = itemView.findViewById(R.id.removeFromWishlist);
        }
    }
}