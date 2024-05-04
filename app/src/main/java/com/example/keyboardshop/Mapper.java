package com.example.keyboardshop;

import java.util.ArrayList;
import java.util.List;

public  class Mapper {
    public static List<KeyboardModel> entityToModel(List<KeyboardEntity> list) {
        List<KeyboardModel> result = new ArrayList<>();

        for (KeyboardEntity ke : list) {
            KeyboardModel km = new KeyboardModel(ke.getId(), ke.getQuantity(), ke.getName(), ke.getPrice(), ke.getPhotoId(), ke.getDiscount(), ke.getRating());
            result.add(km);
        }

        return result;
    }
}
