package org.lecture;

import Products.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.lecture.ShippingSystem.ShoppingSystem;
import org.lecture.User.User;
import org.lecture.User.UserManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

public class FileReader {
    public static HashMap<String, User> loadUsers() {
        Gson gson2 = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try (java.io.FileReader reader = new java.io.FileReader(UserManager.path.toFile())) {
            Type type = new TypeToken<HashMap<String, User>>() {
            }.getType();
            UserManager.usersHashmap = gson2.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new HashMap<>();
    }

    public static ArrayList<Product> loadProducts() throws IOException {
        Gson gson2 = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try (java.io.FileReader reader = new java.io.FileReader(ProductFactory.path.toFile())) {
            Type type = new TypeToken<ArrayList<Product>>() {
            }.getType();
            ShoppingSystem.products = gson2.fromJson(reader, type);

        } catch (FileNotFoundException e) {
            System.out.println("File not found, starting with an empty list.");
            Files.createFile(ProductFactory.path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
