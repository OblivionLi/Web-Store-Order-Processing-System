import com.balaur.dp.proxy.ProductImage;
import com.balaur.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductImageTest {
    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product("P001", "Laptop", 1200.00);
    }

    @Test
    public void testProductImageProxy() {
        // redirect System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ProductImage image = product.getProductImage();
        // image should not be loaded yet

        image.displayImage(); // first call should load the image
        assertTrue(outContent.toString().contains("Loading image"));

        outContent.reset();

        image.displayImage(); // second call should not load again
        assertTrue(outContent.toString().contains("Displaying image"));

        // reset System.out
        System.setOut(System.out);
    }
}
