package com.example.keyboardshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProfileOrderAdapter extends RecyclerView.Adapter<ProfileOrderAdapter.ProfileOrderViewHolder> {

    private List<KeyboardModel> itemList;

    public ProfileOrderAdapter(List<KeyboardModel> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ProfileOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_order, parent, false);
        return new ProfileOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileOrderViewHolder holder, int position) {
        KeyboardModel keyboard = itemList.get(position);
        holder.name.setText(keyboard.getName());
        holder.price.setText("$" + String.format("%.2f", keyboard.getSinglePrice()));
        holder.image.setImageResource(keyboard.getPhotoId());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ProfileOrderViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        ImageView image;

        public ProfileOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.shoppingItem);
            name = itemView.findViewById(R.id.itemNameTextView);
            price = itemView.findViewById(R.id.priceNumberTextView);
        }
    }
}
