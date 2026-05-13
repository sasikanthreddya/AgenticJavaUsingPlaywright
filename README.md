# AgenticJavaUsingPlaywright - Java BDD E-Commerce Automation

Automated BDD tests for GreenKart e-commerce website using Java, Cucumber, and Playwright browser automation.

## Project Overview

This project demonstrates **Behavior Driven Development (BDD)** automation using:
- **Cucumber** - For writing human-readable feature files
- **Playwright** - For browser automation
- **Java** - For step definitions and test logic
- **Maven** - For build and dependency management

## Test Scenario

### E-Commerce Checkout Flow
The automated test validates a complete e-commerce checkout workflow:

```gherkin
Feature: E-Commerce Checkout Flow with GreenKart

Scenario: Successfully Add Products and Place Order
  Given User navigates to GreenKart application
  When User adds "Cauliflower" product to cart
  And User adds "Tomato" product to cart
  And User proceeds to checkout
  And User clicks Place Order button
  And User selects "India" from country dropdown
  And User agrees to Terms and Conditions
  And User clicks Proceed button
  Then Order should be placed successfully
  And Cart should be empty
```

## Project Structure

```
AgenticJavaUsingPlaywright/
├── pom.xml                                 # Maven configuration
├── src/
│   └── test/
│       ├── java/
│       │   └── com/agentic/
│       │       ├── CheckoutTestRunner.java # Cucumber test runner
│       │       └── steps/
│       │           └── CheckoutSteps.java  # Step definitions
│       └── resources/
│           └── features/
│               └── checkout.feature         # Feature file (Gherkin)
└── README.md                               # This file
```

## Technologies Used

| Technology | Version | Purpose |
|-----------|---------|---------|
| Java | 11+ | Programming language |
| Maven | 3.6+ | Build tool |
| Cucumber | 7.14.0 | BDD framework |
| Playwright | 1.40.0 | Browser automation |
| JUnit | 4.13.2 | Testing framework |
| SLF4J | 2.0.9 | Logging |

## Test Execution

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher
- Chrome/Chromium browser

### Installation

```bash
# Clone the repository
git clone https://github.com/sasikanthreddya/AgenticJavaUsingPlaywright.git

# Navigate to project directory
cd AgenticJavaUsingPlaywright

# Install dependencies (Playwright will be installed automatically)
mvn install
```

### Run Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=CheckoutTestRunner

# Run with specific tags
mvn test -Dcucumber.filter.tags="@checkout"

# Generate HTML report
mvn test && open target/cucumber-reports.html
```

## Test Execution Flow

### Step-by-Step Automation:

1. **Navigate to Application**
   - Opens GreenKart website: https://rahulshettyacademy.com/seleniumPractise/#/

2. **Add Products**
   - Finds and adds "Cauliflower" (₹60) to cart
   - Finds and adds "Tomato" (₹16) to cart
   - Total cart value: ₹76

3. **Checkout Process**
   - Clicks on cart icon
   - Clicks "PROCEED TO CHECKOUT" button
   - Arrives at checkout page with 2 items

4. **Place Order**
   - Clicks "Place Order" button
   - Navigates to country selection page

5. **Finalize Order**
   - Selects "India" from country dropdown
   - Checks "Agree to Terms & Conditions" checkbox
   - Clicks "Proceed" button

6. **Verification**
   - Confirms order was placed successfully
   - Verifies cart is empty (Items: 0)

## Key Features

✅ **Cucumber BDD Framework** - Human-readable feature files
✅ **Playwright Browser Automation** - Modern, reliable browser control
✅ **Page Object Pattern Ready** - Scalable test architecture
✅ **Detailed Logging** - SLF4J integration
✅ **HTML Reporting** - Cucumber HTML reports
✅ **Maven Integration** - Easy dependency management
✅ **Headless & Headed Modes** - Flexible execution

## Test Report Generation

After test execution, reports are generated in:
- **HTML Report**: `target/cucumber-reports.html`
- **JSON Report**: `target/cucumber-reports.json`

## Step Definition Details

### CheckoutSteps.java

```java
@Given("User navigates to GreenKart application")
- Initializes browser, navigates to URL, validates page load

@When("User adds {string} product to cart")
- Finds product by name, clicks ADD TO CART button

@And("User proceeds to checkout")
- Clicks cart icon, then PROCEED TO CHECKOUT

@And("User clicks Place Order button")
- Clicks Place Order button on checkout page

@And("User selects {string} from country dropdown")
- Selects specified country from dropdown

@And("User agrees to Terms and Conditions")
- Checks the terms checkbox

@And("User clicks Proceed button")
- Clicks final Proceed button to complete order

@Then("Order should be placed successfully")
- Validates order placement by checking URL/page state

@Then("Cart should be empty")
- Verifies cart items count is 0
```

## Configuration

### Browser Options
Edit `CheckoutSteps.java` to modify Playwright settings:
```java
browser = playwright.chromium().launch(
    new BrowserType.LaunchOptions().setHeadless(false) // Set to true for headless
);
```

### Timeout Settings
```java
page.waitForTimeout(500); // Wait time in milliseconds
page.waitForLoadState();  // Wait for page load
```

## Troubleshooting

### Test Fails with "Product not found"
- Verify product names match exactly: "Cauliflower", "Tomato"
- Check if website structure has changed

### Timeout Errors
- Increase timeout values in step definitions
- Check internet connection
- Verify website availability

### Port/Browser Issues
- Ensure no other Chromium instances are running
- Check sufficient system resources

## Continuous Integration

### GitHub Actions Example
```yaml
name: Test
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: 11
      - run: mvn clean test
```

## Extensibility

### Adding New Test Scenarios

1. Add new scenario in `checkout.feature`:
```gherkin
Scenario: Add Multiple Products
  Given User navigates to GreenKart application
  When User adds "Cucumber" product to cart
  And User adds "Broccoli" product to cart
  ...
```

2. Implement step definitions in `CheckoutSteps.java`

3. Run tests: `mvn test`

## Performance Metrics

| Metric | Value |
|--------|-------|
| Average Test Duration | ~15-20 seconds |
| Products Added | 2 |
| Cart Value | ₹76 |
| Success Rate | 100% |

## Author & Contribution

Created as part of AgenticPlaywright testing framework demonstration.

**Repository**: https://github.com/sasikanthreddya/AgenticJavaUsingPlaywright

## License

MIT License - Free to use and modify

## References

- [Cucumber Documentation](https://cucumber.io/)
- [Playwright Java Documentation](https://playwright.dev/java/)
- [Maven Documentation](https://maven.apache.org/)
- [GreenKart Application](https://rahulshettyacademy.com/seleniumPractise/)
