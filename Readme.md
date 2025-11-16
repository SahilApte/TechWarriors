# Testathon Project

This project contains automated UI tests for a web application, developed as part of a testathon. The tests are written in Java using Selenium WebDriver and the TestNG framework. The project is configured to run tests on BrowserStack for cross-browser testing.

## Frameworks and Libraries

*   **Java**: The programming language used to write the tests.
*   **Selenium WebDriver**: For automating browser actions.
*   **TestNG**: As the testing framework for test management and execution.
*   **Maven**: For project build and dependency management.
*   **BrowserStack**: For running tests on a cloud-based grid of browsers and devices.

## Project Structure

```
├── pom.xml
├── Readme.md
├── src
│   └── test
│       └── java
│           └── com
│               └── bs
│                   ├── BaseTest.java
│                   ├── HackathonTests.java
│                   └── HomePageTests.java
└── testng.xml
```

*   `pom.xml`: The Maven Project Object Model file, which defines project dependencies, plugins, and build settings.
*   `testng.xml`: The TestNG configuration file, used to create test suites and configure test execution parameters.
*   `src/test/java/com/bs/`: The main directory for the test source code.
    *   `BaseTest.java`: A base class for tests that handles the setup and teardown of the WebDriver, including BrowserStack configuration.
    *   `HomePageTests.java`: Contains end-to-end tests for the main application, including login, add to cart, and checkout flows.
    *   `HackathonTests.java`: Contains additional tests, such as a simple login test.

## Automated Tests

The project includes the following automated tests:

### `HomePageTests`

*   **`endToEndFLow()`**: This test covers the entire user journey from logging in, adding a product to the cart, and successfully placing an order.
*   **`validateShippingDetailsMandatoryFields()`**: This test validates that the shipping details form correctly handles mandatory fields.

### `HackathonTests`

*   **`loginWithValidCredentials()`**: A simple test to verify that a user can log in with valid credentials.

## How to Run Tests

### Prerequisites

*   Java Development Kit (JDK) 17 or higher.
*   Apache Maven.
*   A BrowserStack account with your username and access key.

### Configuration

1.  **BrowserStack Credentials**: The BrowserStack username and access key are loaded from environment variables (`BROWSERSTACK_USERNAME` and `BROWSERSTACK_ACCESS_KEY`) in `src/test/java/com/bs/BaseTest.java`. You need to set these environment variables before running the tests.

    For example, you can set them in your shell:
    ```bash
    export BROWSERSTACK_USERNAME="your_username"
    export BROWSERSTACK_ACCESS_KEY="your_access_key"
    ```

2.  **Test Suite Configuration**: The `testng.xml` file is used to configure which tests to run and on which browsers. You can enable or disable tests and test groups by editing this file.

### Running Tests with Maven

To run the tests, execute the following command from the root of the project:

```bash
mvn clean test
```

This command will compile the project, download the dependencies, and run the tests as configured in `testng.xml`.
