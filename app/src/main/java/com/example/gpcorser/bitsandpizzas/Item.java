package com.example.gpcorser.bitsandpizzas;

/**
 * Created by gpcorser on 11/7/2016.
 */

public class Item {
    private String name;
    private int imageResourceId;

    public static final Item[] converterType = {
            new Item("weight converter", R.drawable.scale),
            new Item("volume converter", R.drawable.volume),
            new Item("cup converter", R.drawable.cup)
    };

    private Item(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
