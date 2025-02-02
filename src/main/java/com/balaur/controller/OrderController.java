package com.balaur.controller;

import com.balaur.dp.facade.OrderProcessingFacade;
import com.balaur.dp.factory.enums.GatewayType;
import com.balaur.dp.singleton.InventoryManager;
import com.balaur.dp.strategy.StandardShippingStrategy;
import com.balaur.model.Order;
import com.balaur.model.Product;
import com.balaur.model.ShoppingCart;
import com.balaur.view.OrderView;

import java.util.Map;

public class OrderController {
    private OrderProcessingFacade orderFacade;
    private InventoryManager inventoryManager;
    private OrderView orderView;

    public OrderController(OrderView orderView) {
        this.orderFacade = new OrderProcessingFacade(new StandardShippingStrategy());
        this.inventoryManager = InventoryManager.getInstance();
        this.orderView = orderView;
    }

    public void displayAvailableProducts() {
        Map<String, Product> products = inventoryManager.getAllProducts();
        orderView.showAvailableProducts(products);
    }

    public void handleAddToCart(String productId, int quantity) {
        Product product = inventoryManager.getProduct(productId);
        if (product != null) {
            ShoppingCart cart = orderView.getShoppingCart(); // Assuming the view manages the cart
            cart.addItem(product, quantity);
            orderView.showCart(cart);
        } else {
            orderView.showErrorMessage("Product not found.");
        }
    }

    public void handleCheckout(GatewayType gatewayType) {
        ShoppingCart cart = orderView.getShoppingCart();
        Order order = orderFacade.processOrder(cart, gatewayType);
        if (order != null) {
            orderView.showOrderConfirmation(order);
            cart.clear(); // Clear the cart after successful order
        } else {
            orderView.showErrorMessage("Order processing failed.");
        }
    }

    public void handleViewCart() {
        orderView.showCart(orderView.getShoppingCart());
    }
}
