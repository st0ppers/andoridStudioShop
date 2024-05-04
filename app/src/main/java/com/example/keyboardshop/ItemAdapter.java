package com.example.keyboardshop;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<KeyboardModel> itemList;

    public ItemAdapter(List<KeyboardModel> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        KeyboardModel keyboard = itemList.get(position);
        holder.title.setText(keyboard.getName());
        holder.count.setText(String.valueOf(keyboard.getQuantity()));
        holder.totalPrice.setText("$" + String.format("%.2f", keyboard.getTotalPrice()));
        holder.photo.setImageResource(keyboard.getPhotoId());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView count;
        TextView totalPrice;
        ImageView photo;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTextView);
            totalPrice = itemView.findViewById(R.id.totalCostTextView);
            count = itemView.findViewById(R.id.itemCountTextView);
            photo = itemView.findViewById(R.id.keyboardItemImageView);
        }
    }
}
