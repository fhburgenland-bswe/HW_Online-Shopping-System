package org.lecture.ShippingSystem;

import Products.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements ShippingStragegy {
    public static List<Product> shoppingCart = new ArrayList<>();

    public static void anzeige() {
        if (shoppingCart.isEmpty()) {
            System.out.println("Shopping cart is emtpy.");
        } else {
            for (Product product : shoppingCart) {
                System.out.println("Product: " + product.getName() + " | " +
                        "Price: " + product.getPrice() + " | " +
                        " Category: " + product.getCategory());
            }
            System.out.println("Total Price: " + getTotal());
        }
    }

    public static void addItem(Product product) {
        shoppingCart.add(product);
    }

    public static void removeItem(Product product) {
        shoppingCart.remove(product);
    }

    public static double getTotal() {
        return shoppingCart.stream().mapToDouble(Product::getPrice).sum();
    }
}
