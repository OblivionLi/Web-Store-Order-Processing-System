import com.balaur.dp.factory.enums.GatewayType;
import com.balaur.dp.strategy.ExpressShippingStrategy;
import com.balaur.dp.strategy.ShippingCostCalculator;
import com.balaur.dp.strategy.StandardShippingStrategy;
import com.balaur.model.Order;
import com.balaur.model.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShippingStrategyTest {
    private Order order;
    private ShoppingCart cart;

    @BeforeEach
    public void setUp() {
        cart = mock(ShoppingCart.class);
        when(cart.getTotalPrice()).thenReturn(100.0);

        order = new Order(cart, GatewayType.STRIPE);
    }

    @Test
    public void testSetShippingCost() {
        Order order = new Order(cart, GatewayType.STRIPE);
        order.setShippingCost(5.0);
        assertEquals(5.0, order.getShippingCost(), 0.01);
    }

    @Test
    public void testStandardShippingStrategy() {
        when(cart.getTotalPrice()).thenReturn(100.0);
        StandardShippingStrategy strategy = new StandardShippingStrategy();
        ShippingCostCalculator calculator = new ShippingCostCalculator(strategy);
        double cost = calculator.calculateShippingCost(order);
        System.out.println(cost);
        assertEquals(5.0, cost, 0.01); // assuming 5% of the total
    }

    @Test
    public void testExpressShippingStrategy() {
        when(cart.getTotalPrice()).thenReturn(100.0);
        ExpressShippingStrategy strategy = new ExpressShippingStrategy();
        ShippingCostCalculator calculator = new ShippingCostCalculator(strategy);
        double cost = calculator.calculateShippingCost(order);
        assertEquals(10.0, cost, 0.01); // assuming 10% of the total
    }
}
