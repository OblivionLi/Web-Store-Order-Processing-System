package com.balaur.dp.facade;

import com.balaur.dp.factory.enums.GatewayType;
import com.balaur.dp.singleton.InventoryManager;
import com.balaur.dp.strategy.ShippingCostCalculator;
import com.balaur.dp.strategy.ShippingStrategy;
import com.balaur.model.Order;
import com.balaur.model.ShoppingCart;

public class OrderProcessingFacade {
    private InventoryManager inventoryManager;
    private ShippingCostCalculator shippingCostCalculator;

    public OrderProcessingFacade(ShippingStrategy shippingStrategy) {
        this.inventoryManager = InventoryManager.getInstance();
        this.shippingCostCalculator = new ShippingCostCalculator(shippingStrategy);
    }

    public OrderProcessingFacade(ShippingStrategy shippingStrategy, InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
        this.shippingCostCalculator = new ShippingCostCalculator(shippingStrategy);
    }

    public Order processOrder(ShoppingCart cart, GatewayType paymentGatewayType) {
        // 1. check inventory
        if (!checkInventory(cart)) {
            return null; // or throw exception indicating insufficient inventory
        }

        // 2. create the order
        Order order = new Order(cart, paymentGatewayType);

        // 3. calculate shipping cost
        double shippingCost = shippingCostCalculator.calculateShippingCost(order);
        order.setShippingCost(shippingCost);

        // 4. process payment
        order.processPayment();

        // 5. update inventory
        // updateInventory(cart);

        return order;
    }

    private boolean checkInventory(ShoppingCart cart) {
        // basic check for demonstration - in a real app, this method will have a more robust logic
        return cart.getItems().entrySet().stream().allMatch(entry ->
                inventoryManager.isProductAvailable(entry.getKey().getId(), entry.getValue()));
    }

    private void updateInventory(ShoppingCart cart) {
        // in a real app, you would update the inventory quantities here
        System.out.println("Inventory updated (simulated).");
    }
}
