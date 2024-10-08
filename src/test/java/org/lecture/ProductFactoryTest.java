package org.lecture;

import Products.Category;
import Products.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;

class ProductFactoryTest {

    @Test
    void createProduct() throws Exception {
        Product product = new Product("Tablet",250, Category.Electronic);
        Product tablet = ProductFactory.createProduct("Tablet",250,"Electronic");
        Assertions.assertEquals(product,tablet);
    }
    @Test
    public void TestPath() {

        boolean richtig = Files.exists(ProductFactory.path);
        boolean expected = true;

        Assertions.assertEquals(expected,richtig);
    }
}