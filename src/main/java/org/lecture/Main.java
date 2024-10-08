package org.lecture;

import Products.Product;
import org.lecture.ShippingSystem.ShippingStragegy;
import org.lecture.ShippingSystem.ShoppingCart;
import org.lecture.ShippingSystem.ShoppingSystem;
import org.lecture.User.UserManager;

import java.util.Scanner;
import java.util.regex.Pattern;

import static org.lecture.FileReader.loadProducts;
import static org.lecture.FileWriter.saveProducts;
import static org.lecture.User.UserManager.isValidEmail;

public class Main {
    public static String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static Pattern pattern = Pattern.compile(regex);

    public static void main(String[] args) throws Exception {
        //ShoppingSystem.getInstance();
        //ShoppingSystem sho2 = new ShoppingSystem();
        loadProducts();
        Scanner sc = new Scanner(System.in);

        boolean shouldRun = true;
        System.out.println("Welcome to our Website!\nPlease select one of the following:");

        //Registration and logging in
        loginOrRegister(shouldRun, sc);

        //Checking if the user admin
        checkifAdmin(sc);
        System.out.println("You can do a lot of things...\nPlease enter your choice.");

        //Main menu
        while (true) {
            System.out.println(Menus.menu);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    boolean run = true;
                    while (run) {
                        System.out.println("Please choose one of the following: ");
                        System.out.println(Menus.shoppingMenu);
                        int shoppingChoice = sc.nextInt();
                        switch (shoppingChoice) {
                            //Listing the products and add products to the cart
                            case 1:
                                int i = 0;
                                for (Product product : ShoppingSystem.products) {
                                    System.out.println(i + " - Product: " + product.getName() + ", Price: " + product.getPrice() + ", Category: " + product.getCategory());
                                    i++;
                                }
                                System.out.println("Select a product:");
                                int productChoice = sc.nextInt();
                                while (productChoice < 0 || productChoice > ShoppingSystem.products.size()) {
                                    System.out.println("Wrong choice please try again.");
                                    productChoice = sc.nextInt();
                                }
                                ShoppingCart.addItem(ShoppingSystem.products.get(productChoice));
                                System.out.println("Product " + ShoppingSystem.products.get(productChoice).getName() + " successful added to the cart.");
                                break;
                            //remove a product from the cart
                            case 2:
                                if (ShoppingCart.shoppingCart.isEmpty()) {
                                    System.out.println("Shopping Cart is empty.");
                                    break;
                                }
                                i = 0;
                                for (Product product : ShoppingCart.shoppingCart) {
                                    System.out.println(i + " - Product: " + product.getName() + ", Price: " + product.getPrice() + ", Category: " + product.getCategory());
                                    i++;
                                }
                                System.out.println("Select a product:");
                                System.out.println("To exit type -1.");
                                productChoice = sc.nextInt();
                                if (productChoice == -1) {
                                    break;
                                }
                                while (productChoice < 0 || productChoice > ShoppingCart.shoppingCart.size()) {
                                    System.out.println("Wrong choice please try again.");
                                    productChoice = sc.nextInt();
                                }
                                ShoppingCart.removeItem(ShoppingCart.shoppingCart.get(productChoice));
                                System.out.println("Product " + ShoppingCart.shoppingCart.get(productChoice).getName() + " successful removed from the cart.");
                                break;
                            //cart listing
                            case 3:
                                ShoppingCart.anzeige();
                                break;
                            //finish shopping
                            case 4:
                                finish(sc);
                                break;
                            //exit to login menu
                            case 5:
                                run = false;
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    }
                    break;
                //Show profile details
                case 2:
                    UserManager.viewProfile();
                    break;
                //updating profile details
                case 3:
                    UserManager.updateDetails(sc);
                    break;
                //exit to login menu
                case 4:
                    loginOrRegister(shouldRun, sc);
                    break;
            }
        }
    }

    private static void checkifAdmin(Scanner sc) {
        if (UserManager.user.getName().equals("admin")) {
            boolean run = true;
            while (run) {
                System.out.println(Menus.admin);
                System.out.println("Please choose: ");
                int adminchoice = sc.nextInt();
                switch (adminchoice) {
                    //Add new product to the system
                    case 1:
                        System.out.println("Please add a product to your shop: ");
                        System.out.println("Name: ");
                        //type = sc.nextLine() ist 2mal definiziert, da wenn ich nur einmal definiziere,es gibt ein Bug und kann das Program sich abstürtzt
                        String name = sc.nextLine();
                        name = sc.nextLine();
                        System.out.println("Price:");
                        double price = sc.nextDouble();
                        System.out.println("Electronic");
                        System.out.println("Clothing");
                        System.out.println("Book");
                        System.out.println("Select the type: ");
                        //type = sc.nextLine() ist 2mal definiziert, da wenn ich nur einmal definiziere,es gibt ein Bug und kann das Program sich abstürtzt
                        String type = sc.nextLine();
                        type = sc.nextLine();
                        ShoppingSystem.addProduct(ProductFactory.createProduct(name, price, type));
                        System.out.println("Product successful added.");
                        saveProducts();
                        break;
                    //remove a product from the system
                    case 2:
                        if (ShoppingSystem.products.isEmpty()) {
                            System.out.println("There is not any product in the list.");
                            break;
                        }
                        int i = 0;
                        for (Product product : ShoppingSystem.products) {
                            System.out.println(i + " - Product: " + product.getName() + ", Price: " + product.getPrice() + ", Category: " + product.getCategory());
                            i++;
                        }
                        System.out.println("Select a product:");
                        System.out.println("Type in -1 to exit to the menu");
                        int productChoice = sc.nextInt();
                        if (productChoice == -1) {
                            break;
                        }
                        System.out.println("Product " + ShoppingSystem.products.get(productChoice).getName() + " successful removed from the cart.");
                        ShoppingSystem.removeProduct(ShoppingSystem.products.get(productChoice));
                        saveProducts();
                        break;
                    //back to main menu
                    case 3:
                        run = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }

    private static void finish(Scanner sc) {
        if (ShoppingCart.shoppingCart.isEmpty()) {
            System.out.println("You cannot finish your shopping without any items in your cart. Add some items.");
        } else {
            double shippingprice;
            if (ShoppingCart.getTotal() > 150) {
                shippingprice = 0;
                System.out.println("The Shipping method is - " + ShippingStragegy.freeShipping);
                getTotalPriceWithShipping(shippingprice);
                System.exit(0);
            }
            System.out.println("Do you want to have express shipping for €15? Yes/No");
            String choice = sc.nextLine();
            while (!choice.equalsIgnoreCase("Yes") && !choice.equalsIgnoreCase("No")) {
                System.out.println("Wrong answer.. Yes/No");
                choice = sc.nextLine();
            }
            if (choice.equalsIgnoreCase("yes")) {
                System.out.println("The Shipping method is - " + ShippingStragegy.expressShipping);
                shippingprice = ShippingStragegy.expressShippingPrice;
            } else {
                System.out.println("The Shipping method is - " + ShippingStragegy.standardShipping);
                shippingprice = ShippingStragegy.standardShippingPrice;
            }
            getTotalPriceWithShipping(shippingprice);
            System.exit(0);
        }
    }

    private static void getTotalPriceWithShipping(double shippingprice) {
        System.out.println("Price: ");
        for (Product product : ShoppingCart.shoppingCart) {
            System.out.println("Product: " + product.getName() + "  ---  €" + product.getPrice());
        }
        System.out.println("\nTotal price (Product): €" + ShoppingCart.getTotal());
        System.out.println("Shipping price: " + shippingprice);
        System.out.println("Total: €" + (ShoppingCart.getTotal() + shippingprice));
    }

    private static void loginOrRegister(boolean shouldRun, Scanner sc) {
        while (shouldRun) {
            System.out.println(Menus.mainMenu);
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                //Registration
                case 1:
                    System.out.println("Enter username: ");
                    String userName = sc.nextLine();
                    while (userName.isEmpty()) {
                        System.out.println("Username is incorrect. Please try again.");
                        userName = sc.nextLine();
                    }
                    System.out.println("Enter email:");
                    String email = sc.nextLine();
                    while (!isValidEmail(email)) {
                        System.out.println("Email is incorrect. Please try again.");
                        email = sc.nextLine();
                        isValidEmail(email);
                    }
                    System.out.println("Enter password: ");
                    String password = sc.nextLine();
                    while (password.isEmpty()) {
                        System.out.println("Password is incorrect. Please try again.");
                        password = sc.nextLine();
                    }
                    System.out.println("Please enter your shipping address: ");
                    String shippingAddress = sc.nextLine();

                    if (UserManager.register(userName, email, password, shippingAddress)) {
                        System.out.println("Registration successful!");
                    } else {
                        System.out.println("Username already exists.");
                    }
                    break;
                //Logging in
                case 2:
                    System.out.println("Enter username: ");
                    String loginUserName = sc.nextLine();
                    System.out.println("Enter password: ");
                    String loginPassword = sc.nextLine();

                    if (UserManager.login(loginUserName, loginPassword)) {
                        System.out.println("Login successful!");
                        shouldRun = false;
                    } else {
                        System.out.println("Invalid username of password.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}