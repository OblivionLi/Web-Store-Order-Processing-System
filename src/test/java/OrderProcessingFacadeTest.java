import com.balaur.dp.facade.OrderProcessingFacade;
import com.balaur.dp.factory.enums.GatewayType;
import com.balaur.dp.singleton.InventoryManager;
import com.balaur.dp.strategy.StandardShippingStrategy;
import com.balaur.model.Order;
import com.balaur.model.Product;
import com.balaur.model.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderProcessingFacadeTest {
    private OrderProcessingFacade facade;
    private InventoryManager inventoryManager;
    private ShoppingCart cart;
    private Product product;

    @BeforeEach
    public void setUp() {
        // mock the InventoryManager
        inventoryManager = mock(InventoryManager.class);

        // inject the mocked InventoryManager into the Facade
        facade = new OrderProcessingFacade(new StandardShippingStrategy(), inventoryManager);

        cart = mock(ShoppingCart.class);
        product = mock(Product.class);

        // mock product data
        when(product.getId()).thenReturn("P001");
    }

    @Test
    public void testProcessOrderSuccess() {
        // mock shopping cart behavior
        Map<Product, Integer> items = new HashMap<>();
        items.put(product, 2);
        when(cart.getItems()).thenReturn(items);
        when(cart.getTotalPrice()).thenReturn(100.0);

        // mock inventory check to return true
        when(inventoryManager.isProductAvailable("P001", 2)).thenReturn(true);

        // process the order
        Order order = facade.processOrder(cart, GatewayType.STRIPE);

        // assertions to verify successful order processing
        assertNotNull(order);
        assertEquals(100.0, order.getTotalAmount(), 0.01);
        assertEquals(5.0, order.getShippingCost(), 0.01); // Assuming 5% for standard shipping

        // verify that inventory check was called
        verify(inventoryManager).isProductAvailable("P001", 2);
    }

    @Test
    public void testProcessOrderInventoryFailure() {
        // mock shopping cart behavior to have one item
        Map<Product, Integer> items = new HashMap<>();
        items.put(product, 2);
        when(cart.getItems()).thenReturn(items);

        // mock inventoryManager to return false for isProductAvailable
        when(inventoryManager.isProductAvailable(anyString(), anyInt())).thenReturn(false);

        // process the order
        Order order = facade.processOrder(cart, GatewayType.STRIPE);

        // assert that the order is null due to inventory check failure
        assertNull(order);
    }
}
