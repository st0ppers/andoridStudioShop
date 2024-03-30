package com.example.keyboardshop;

public class KeyboardModel {
    int count;
    String name;
    double singlePrice;
    double totalPrice;
    int photoId;
    int inStock;
    int discount;
    float rating;

    public KeyboardModel(int Count, String Name, double SinglePrice, int PhotoId, int InStock, int Discount, float Raiting) {

        count = Count;
        name = Name;
        singlePrice = SinglePrice;
        photoId = PhotoId;
        totalPrice = singlePrice * count;
        inStock = InStock;
        discount = Discount;
        rating = Raiting;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(double singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
