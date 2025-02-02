package com.balaur.model;

import com.balaur.dp.proxy.ProductImage;
import com.balaur.dp.proxy.ProductImageLoader;

public class Product {
    private String id;
    private String name;
    private double price;
    private ProductImage productImage;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productImage = new ProductImageLoader("image_" + id + ".jpg");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ProductImage getProductImage() {
        return productImage;
    }
}
