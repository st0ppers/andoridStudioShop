package com.example.keyboardshop;

import java.math.BigDecimal;

public class KeyboardModel {
    int id;
    int quantity;
    String name;
    BigDecimal singlePrice;
    BigDecimal totalPrice;
    int photoId;
    int discount;
    float rating;

    public KeyboardModel(int Id, int Quantity, String Name, BigDecimal SinglePrice, int PhotoId, int Discount, float Raiting) {
        id = Id;
        quantity = Quantity;
        name = Name;
        singlePrice = SinglePrice;
        photoId = PhotoId;
        totalPrice = singlePrice.multiply(BigDecimal.valueOf(quantity));
        discount = Discount;
        rating = Raiting;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int i) {
        quantity = i;
        totalPrice = singlePrice.multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal getSinglePrice() {
        return singlePrice;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getPhotoId() {
        return photoId;
    }

    public int getDiscount() {
        return discount;
    }

    public float getRating() {
        return rating;
    }

}