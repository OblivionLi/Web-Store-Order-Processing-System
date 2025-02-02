package com.balaur.dp.singleton;

import com.balaur.model.Product;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private static InventoryManager instance;
    private Map<String, Product> products;

    private InventoryManager() {
        products = new HashMap<>();

        products.put("P001", new Product("P001", "Laptop", 1200.00));
        products.put("P002", new Product("P002", "Mouse", 15.00));
        products.put("P003", new Product("P003", "Keyboard", 75.00));
    }

    public static InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    public boolean isProductAvailable(String productId, int quantity) {
        Product product = getProduct(productId);
        // here in a real world project you would also track the product qty
        return product != null;
    }

    public Map<String, Product> getAllProducts() {
        return products;
    }
}
