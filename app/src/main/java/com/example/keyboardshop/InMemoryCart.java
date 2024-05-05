package com.example.keyboardshop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryCart {
    private static List<KeyboardModel> cart = new ArrayList<>();

    public static List<KeyboardModel> getCart() {
        return cart;
    }

    public static boolean addToCart(List<KeyboardModel> keyboard) {
        return cart.addAll(keyboard);
    }

    public static void incrementQuantity(int id) {
        cart = cart.stream()
                .filter(x -> x.id == id)
                .peek(x -> {
                    int q = x.getQuantity() + 1;
                    x.setQuantity(q);
                })
                .collect(Collectors.toList());
    }

    public static void decrementQuantity(int id) {
        cart = cart.stream()
                .filter(x -> x.id == id)
                .map(x -> {
                    if (x.quantity == 0) {
                        return x;
                    }
                    x.quantity--;
                    return x;
                })
                .filter(x -> x.quantity != 0)
                .collect(Collectors.toList());
    }
}
