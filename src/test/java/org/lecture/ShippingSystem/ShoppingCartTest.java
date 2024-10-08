package org.lecture.ShippingSystem;

import Products.Category;
import Products.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    void addItem() {
        Product product = new Product("iPhone",500, Category.Electronic);
        Product product1 = new Product("iPhone12",700, Category.Electronic);
        Product product2 = new Product("iPhone13",850, Category.Electronic);

        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product1);
        ShoppingCart.addItem(product);
        ShoppingCart.addItem(product1);

        Assertions.assertEquals(products,ShoppingCart.shoppingCart);
        ShoppingCart.shoppingCart.clear();
    }

    @Test
    void removeItem() {
        Product product = new Product("iPhone",500, Category.Electronic);
        Product product1 = new Product("iPhone12",700, Category.Electronic);
        Product product2 = new Product("iPhone13",850, Category.Electronic);

        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product1);
        ShoppingCart.shoppingCart.add(product);
        ShoppingCart.shoppingCart.add(product1);
        ShoppingCart.shoppingCart.add(product2);
        ShoppingCart.removeItem(product2);

        Assertions.assertEquals(products,ShoppingCart.shoppingCart);
        ShoppingCart.shoppingCart.clear();
    }

    @Test
    void getTotal() {
        Product product = new Product("iPhone",500, Category.Electronic);
        Product product1 = new Product("iPhone12",700, Category.Electronic);
        Product product2 = new Product("iPhone13",850, Category.Electronic);
        ShoppingCart.shoppingCart.add(product);
        ShoppingCart.shoppingCart.add(product1);
        ShoppingCart.shoppingCart.add(product2);
        double total = product.getPrice()+product1.getPrice()+product2.getPrice();
        double total_expected = ShoppingCart.getTotal();

        Assertions.assertEquals(total,total_expected);
    }
}