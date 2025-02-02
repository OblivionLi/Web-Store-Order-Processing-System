package com.balaur;

import com.balaur.controller.OrderController;
import com.balaur.dp.factory.enums.GatewayType;
import com.balaur.view.OrderView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OrderView orderView = new OrderView();
        OrderController controller = new OrderController(orderView);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            controller.displayAvailableProducts();
            System.out.println("1. Add to Cart");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product ID to add: ");
                    String productId = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    controller.handleAddToCart(productId, quantity);
                    break;
                case 2:
                    controller.handleViewCart();
                    break;
                case 3:
                    System.out.println("Select Payment Gateway:");
                    System.out.println("1. STRIPE");
                    System.out.println("2. PAYPAL");
                    int paymentChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    GatewayType gatewayType = (paymentChoice == 1) ?
                            GatewayType.STRIPE : GatewayType.PAYPAL;
                    controller.handleCheckout(gatewayType);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}