package org.lecture.User;

import org.lecture.Menus;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

import static org.lecture.FileReader.loadUsers;
import static org.lecture.FileWriter.saveUser;
import static org.lecture.Main.pattern;

public class UserManager {
    public static HashMap<String, User> usersHashmap = new HashMap<>();
    public static User user;
    public static final Path path = Paths.get("src", "main", "resources", "users.json");

    public static Scanner sc = new Scanner(System.in);

    public static void viewProfile() {
        System.out.println("Username: " + user.getName() + "\n" +
                "Email: " + user.getEmail() + "\n" +
                "Shipping Address: " + user.getShippingAddress() + "\n");
    }

    public static boolean register(String username, String email, String password, String shippingAddress) {
        loadUsers();
        if (usersHashmap.containsKey(username)) {
            return false;
        } else {
            user = new User(username, email, password, shippingAddress);
            usersHashmap.put(username, user);
            saveUser();
            return true;
        }
    }

    public static boolean login(String username, String password) {
        loadUsers();
        user = usersHashmap.get(username);
        boolean userExists = false;
        for (var entry : usersHashmap.entrySet()) {
            if (entry.getKey().equals(username)) {
                userExists = true;
                break;
            }
        }
        if (user == null || !userExists) {
            return false;
        }

        return user.getPassword().equals(password);
    }

    public static void updateEmail() {
        System.out.println("Please enter a new email:");
        String email = sc.nextLine();
        while (!isValidEmail(email)) {
            System.out.println("Email is incorrect. Please try again.");
            email = sc.nextLine();
            isValidEmail(email);
        }
        user.setEmail(email);
        System.out.println("Email updated!");
        saveUser();
    }

    public static void updateAddress() {
        System.out.println("Please enter your new shipping address: ");
        String address = sc.nextLine();
        while (address.isEmpty()) {
            System.out.println("Address cannot be empty.");
            address = sc.nextLine();
        }
        user.setShippingAddress(address);
        System.out.println("Shipping address updated!");
        saveUser();
    }

    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static void updateDetails(Scanner sc) {
        System.out.println("Please choose one of the following:");
        System.out.println(Menus.update);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                updateEmail();
                break;
            case 2:
                updateAddress();
                break;
            default:
                System.out.println("There's no option for your choice.");
        }
    }
}
