package com.balaur.view;

import com.balaur.model.Order;
import com.balaur.model.Product;
import com.balaur.model.ShoppingCart;

import java.util.Map;
import java.util.Scanner;

public class OrderView {
    private ShoppingCart cart;
    private Scanner scanner;

    public OrderView() {
        cart = new ShoppingCart();
        scanner = new Scanner(System.in);
    }

    public void showAvailableProducts(Map<String, Product> products) {
        System.out.println("Available Products:");
        products.values().forEach(product ->
                System.out.println(product.getId() + ": " + product.getName() + " - $" + product.getPrice()));
        System.out.println("--------------------");
    }

    public void showCart(ShoppingCart cart) {
        System.out.println("Your Cart:");
        if (cart.getItems().isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            cart.getItems().forEach((product, quantity) ->
                    System.out.println(product.getName() + " x " + quantity + " = $" + (product.getPrice() * quantity)));
            System.out.println("Total: $" + cart.getTotalPrice());
        }
        System.out.println("--------------------");
    }

    public void showOrderConfirmation(Order order) {
        System.out.println("Order Confirmed:");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Total: $" + order.getTotalAmount());
        System.out.println("Shipping Cost: $" + order.getShippingCost());
        System.out.println("--------------------");
    }

    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
        System.out.println("--------------------");
    }

    public ShoppingCart getShoppingCart() {
        return cart;
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // consume the non-int input
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }
}
