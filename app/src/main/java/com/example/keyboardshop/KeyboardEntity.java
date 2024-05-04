package com.example.keyboardshop;

import java.math.BigDecimal;

public class KeyboardEntity {
    int id;
    int quantity;
    String name;
    BigDecimal price;
    int discount;
    int rating;
    int photoId;

    public KeyboardEntity(int Id, String Name, BigDecimal Price, int Quantity, int Discount, int Rating, int PhotoId) {
        id = Id;
        quantity = Quantity;
        name = Name;
        price = Price;
        discount = Discount;
        rating = Rating;
        photoId = PhotoId;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public int getRating() {
        return rating;
    }

    public int getPhotoId() {
        return photoId;
    }
}
