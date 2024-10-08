package org.lecture;

import Products.Category;
import Products.Product;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ProductFactory {
    public static final Path path = Paths.get("src", "main", "resources", "products.json");

    public static Product createProduct(String name, double price, String type) {
        return switch (type) {
            case "Electronic" -> new Product(name, price, Category.Electronic);
            case "Clothing" -> new Product(name, price, Category.Clothing);
            case "Book" -> new Product(name, price, Category.Book);
            default -> throw new IllegalArgumentException("Unknown product type");
        };
    }
}
