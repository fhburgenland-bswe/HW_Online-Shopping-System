package org.lecture.ShippingSystem;

import Products.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingSystem {
    private static ShoppingSystem instance;
    public static List<Product> products = new ArrayList<>();

    public static synchronized ShoppingSystem getInstance() {
        if (instance == null) {
            instance = new ShoppingSystem();
        }
        return instance;
    }

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static void removeProduct(Product product) {
        products.remove(product);
    }

}
