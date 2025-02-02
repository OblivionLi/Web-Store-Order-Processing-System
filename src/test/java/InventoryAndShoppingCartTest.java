import com.balaur.dp.adapter.PaymentGateway;
import com.balaur.dp.adapter.PaypalPaymentGateway;
import com.balaur.dp.adapter.StripePaymentGateway;
import com.balaur.dp.factory.PaymentGatewayFactory;
import com.balaur.dp.factory.enums.GatewayType;
import com.balaur.dp.singleton.InventoryManager;
import com.balaur.model.Product;
import com.balaur.model.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryAndShoppingCartTest {
    private InventoryManager inventoryManager;
    private ShoppingCart cart;

    @BeforeEach
    public void setUp() {
        inventoryManager = InventoryManager.getInstance();
        cart = new ShoppingCart();
    }

    @Test
    public void testInventoryManagerSingleton() {
        InventoryManager anotherInstance = InventoryManager.getInstance();
        assertSame(inventoryManager, anotherInstance, "InventoryManager should be a singleton");
    }

    @Test
    public void testGetProductFromInventory() {
        Product laptop = inventoryManager.getProduct("P001");
        assertNotNull(laptop, "Product should be found in inventory");
        assertEquals("Laptop", laptop.getName(), "Product name should match");
    }

    @Test
    public void testGetNonExistentProductFromInventory() {
        Product nonExistentProduct = inventoryManager.getProduct("P999");
        assertNull(nonExistentProduct, "Non-existent product should return null");
    }

    @Test
    public void testAddProductToShoppingCart() {
        Product laptop = inventoryManager.getProduct("P001");
        cart.addItem(laptop, 2);

        Map<Product, Integer> items = cart.getItems();
        assertEquals(1, items.size(), "Cart should contain 1 item type");
        assertEquals(2, (int) items.get(laptop), "Cart should have 2 laptops");
    }

    @Test
    public void testRemoveProductFromShoppingCart() {
        Product laptop = inventoryManager.getProduct("P001");
        cart.addItem(laptop, 3);
        cart.removeItem(laptop);

        Map<Product, Integer> items = cart.getItems();
        assertFalse(items.containsKey(laptop), "Cart should not contain the removed product");
    }

    @Test
    public void testGetTotalPriceOfShoppingCart() {
        Product laptop = inventoryManager.getProduct("P001");
        Product mouse = inventoryManager.getProduct("P002");
        cart.addItem(laptop, 1);
        cart.addItem(mouse, 3);

        double expectedTotalPrice = laptop.getPrice() + (mouse.getPrice() * 3);
        assertEquals(expectedTotalPrice, cart.getTotalPrice(), "Total cart price should be calculated correctly");
    }

    @Test
    public void testStripePaymentGateway() {
        PaymentGateway stripeGateway = PaymentGatewayFactory.createPaymentGateway(GatewayType.STRIPE);
        assertNotNull(stripeGateway);
        assertInstanceOf(StripePaymentGateway.class, stripeGateway);
        // in a real test with mocked dependencies, you would verify that the processPayment() method interacts with the Stripe API correctly
        stripeGateway.processPayment(100.00); // just call the method to see the output for now
    }

    @Test
    public void testPayPalPaymentGateway() {
        PaymentGateway payPalGateway = PaymentGatewayFactory.createPaymentGateway(GatewayType.PAYPAL);
        assertNotNull(payPalGateway);
        assertInstanceOf(PaypalPaymentGateway.class, payPalGateway);
        // similar to the Stripe test, in a real scenario with mocks, you would verify the interaction with PayPal's API
        payPalGateway.processPayment(50.00); // call the method to see the output
    }
}
