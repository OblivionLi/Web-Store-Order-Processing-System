# Web Store Order Processing System

This Java project simulates a simplified order processing system for a web store. It demonstrates the implementation of several design patterns within a basic Model-View-Controller (MVC) architecture.

## Design Patterns Implemented

This project showcases the following design patterns:

*   **Model-View-Controller (MVC):** Organizes the application into three interconnected parts: the Model (data and business logic), the View (user interface), and the Controller (handles user input and updates the Model and View).
*   **Singleton:** Ensures that only one instance of the `InventoryManager` class exists, providing a global point of access to the product inventory.
*   **Factory:** The `PaymentGatewayFactory` creates instances of different payment gateways (e.g., `StripePaymentGateway`, `PayPalPaymentGateway`) without specifying their concrete classes.
*   **Adapter:** Adapts the interfaces of the different payment gateways to a common `PaymentGateway` interface, allowing the system to process payments through various providers.
*   **Observer:** Used for implementing a notification system for order status changes. While not fully implemented in the final version, the initial design included `EmailNotifier` and `SMSNotifier` to simulate sending notifications.
*   **Proxy:** The `ProductImageLoader` acts as a proxy for `RealProductImage`, demonstrating lazy loading of product images.
*   **Strategy:** The `ShippingCostCalculator` uses different strategies (e.g., `StandardShippingStrategy`, `ExpressShippingStrategy`) to calculate shipping costs based on the selected method.
*   **Facade:** The `OrderProcessingFacade` provides a simplified interface for the complex order processing workflow, hiding the underlying details.
*   **Decorator:** The `DiscountDecorator` and its concrete implementations (e.g., `PercentageDiscountDecorator`, `ValueDiscountDecorator`) allow adding discounts to orders dynamically.

## Example Usage

Here's an example of how the application works, demonstrating the interaction flow:

**1. Initial Display and Viewing the Empty Cart:**
````
Available Products:
P001: Laptop - $1200.0
P003: Keyboard - $75.0
P002: Mouse - $15.0
--------------------
1. Add to Cart
2. View Cart
3. Checkout
4. Exit

*User input: 2*

Your Cart:
Cart is empty.
````

**2. Adding Items to the Cart:**

````
--------------------
Available Products:
P001: Laptop - $1200.0
P003: Keyboard - $75.0
P002: Mouse - $15.0
--------------------
1. Add to Cart
2. View Cart
3. Checkout
4. Exit

*User input: 1*

Enter product ID to add: P001 // user input
Enter quantity: 4 // user input

Your Cart:
Laptop x 4 = $4800.0
Total: $4800.0
````

**3. Viewing the Cart with Items:**

````
--------------------
Available Products:
P001: Laptop - $1200.0
P003: Keyboard - $75.0
P002: Mouse - $15.0
--------------------
1. Add to Cart
2. View Cart
3. Checkout
4. Exit

*User input: 2*

Your Cart:
Laptop x 4 = $4800.0
Total: $4800.0
````

**4. Proceeding to Checkout:**

````
--------------------
Available Products:
P001: Laptop - $1200.0
P003: Keyboard - $75.0
P002: Mouse - $15.0
--------------------
1. Add to Cart
2. View Cart
3. Checkout
4. Exit

*User input: 3*

Select Payment Gateway:
1. STRIPE
2. PAYPAL

*User input: 1*

Processing payment of $4800.0 via Stripe...
Order Confirmed:
Order ID: 8160f9cb-7efb-43cb-846b-27813d251576
Total: $4800.0
Shipping Cost: $240.0
````

**5. Exiting the Application:**

````
--------------------
Available Products:
P001: Laptop - $1200.0
P003: Keyboard - $75.0
P002: Mouse - $15.0
--------------------
1. Add to Cart
2. View Cart
3. Checkout
4. Exit

*User input: 4*
 
Exiting...
````
## How to Run

1. **Clone the repository:**
    ```bash
    git clone https://github.com/OblivionLi/Web-Store-Order-Processing-System.git
    ```
2. **Navigate to the project directory:**
    ```bash
    cd Web-Store-Order-Processing
    ```
3. **Compile the Java files:**
    ```bash
    javac src/com/balaur/**/*.java
    ```
4. **Run the `Main` class:**
    ```bash
    java -cp src com.balaur.Main
    ```

## Dependencies

This project uses the following external libraries:

*   **JUnit 5:** For unit testing.
*   **Mockito:** For mocking objects in tests.

(Make sure these dependencies are included in your project's classpath if you're using an IDE or build tool like Maven or Gradle).

## Notes

*   This is a simplified simulation of a web store's order processing system. It's intended for educational purposes to demonstrate design patterns and MVC.
*   There is no actual database or external API integration.
*   The payment processing is simulated.
*   Error handling and input validation are minimal.

## Future Improvements

*   Implement a more robust error handling mechanism.
*   Add input validation to prevent invalid user input.
*   Integrate with a database to persist data.
*   Use a graphical user interface (GUI) instead of the console.
*   Implement a more realistic payment gateway integration.
*   Add more comprehensive unit tests.