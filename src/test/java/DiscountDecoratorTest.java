import com.balaur.dp.decorator.PercentageDiscountDecorator;
import com.balaur.dp.decorator.ValueDiscountDecorator;
import com.balaur.dp.factory.enums.GatewayType;
import com.balaur.model.Order;
import com.balaur.model.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DiscountDecoratorTest {
    private ShoppingCart cart;
    private Order order;

    @BeforeEach
    public void setUp() {
        cart = mock(ShoppingCart.class);
        when(cart.getTotalPrice()).thenReturn(100.0);
        order = new Order(cart, GatewayType.STRIPE);
    }

    @Test
    public void testPercentageDiscountDecorator() {
        new PercentageDiscountDecorator(order, 10); // 10% discount
        assertEquals(90.0, order.getTotalAmount(), 0.01);
    }

    @Test
    public void testValueDiscountDecorator() {
        new ValueDiscountDecorator(order, 15); // $15 discount
        assertEquals(85.0, order.getTotalAmount(), 0.01);
    }

    @Test
    public void testMultipleDiscountDecorators() {
        new PercentageDiscountDecorator(order, 10); // 10% discount
        new ValueDiscountDecorator(order, 5); // Additional $5 discount
        assertEquals(85.0, order.getTotalAmount(), 0.01); // 100 - 10% - 5 = 85
    }
}
