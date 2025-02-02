import com.balaur.dp.factory.enums.GatewayType;
import com.balaur.dp.observer.EmailNotifier;
import com.balaur.dp.observer.SMSNotifier;
import com.balaur.dp.singleton.InventoryManager;
import com.balaur.model.Order;
import com.balaur.model.Product;
import com.balaur.model.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private ShoppingCart cart;
    private InventoryManager inventoryManager;

    @BeforeEach
    public void setUp() {
        inventoryManager = InventoryManager.getInstance();
        cart = new ShoppingCart();
        Product laptop = inventoryManager.getProduct("P001");
        Product mouse = inventoryManager.getProduct("P002");
        cart.addItem(laptop, 1);
        cart.addItem(mouse, 2);
    }

    @Test
    public void testCreateOrderWithStripePayment() {
        Order order = new Order(cart, GatewayType.STRIPE);
        assertNotNull(order.getOrderId());
        assertEquals(cart, order.getCart());
        assertTrue(order.getTotalAmount() > 0);
    }

    @Test
    public void testCreateOrderWithPayPalPayment() {
        Order order = new Order(cart, GatewayType.PAYPAL);
        assertNotNull(order.getOrderId());
        assertEquals(cart, order.getCart());
        assertTrue(order.getTotalAmount() > 0);
    }

    @Test
    public void testProcessPayment() {
        // redirect System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Order order = new Order(cart, GatewayType.STRIPE);
        order.processPayment();

        assertTrue(outContent.toString().contains("Stripe"));

        // reset System.out
        System.setOut(System.out);
    }

    @Test
    public void testOrderObserverNotification() {
        // redirect System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Order order = new Order(cart, GatewayType.STRIPE);
        EmailNotifier emailNotifier = new EmailNotifier();
        SMSNotifier smsNotifier = new SMSNotifier();
        order.addObserver(emailNotifier);
        order.addObserver(smsNotifier);

        order.setOrderStatus("Shipped");

        assertTrue(outContent.toString().contains("Email notification"));
        assertTrue(outContent.toString().contains("SMS notification"));

        // reset System.out
        System.setOut(System.out);
    }
}
