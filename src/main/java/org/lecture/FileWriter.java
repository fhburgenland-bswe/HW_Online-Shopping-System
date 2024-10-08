package org.lecture;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.lecture.ShippingSystem.ShoppingSystem;
import org.lecture.User.UserManager;

import java.io.IOException;
import java.io.Writer;

public class FileWriter {
    public static void saveUser() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try (Writer writer = new java.io.FileWriter(String.valueOf(UserManager.path), false)) {
            gson.toJson(UserManager.usersHashmap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveProducts() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try (Writer writer = new java.io.FileWriter(String.valueOf(ProductFactory.path), false)) {
            gson.toJson(ShoppingSystem.products, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
