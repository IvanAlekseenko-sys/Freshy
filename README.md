# Freshy - UI Test Automation Framework 🚀

![Java](https://img.shields.io/badge/Java-17-blue.svg?style=for-the-badge&logo=openjdk)
![Selenium](https://img.shields.io/badge/Selenium-4-green.svg?style=for-the-badge&logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-7.10-orange.svg?style=for-the-badge&logo=testng)
![Gradle](https://img.shields.io/badge/Gradle-8.8-blue.svg?style=for-the-badge&logo=gradle)

A scalable and robust UI Test Automation Framework built with a modern Java-based technology stack. It provides a solid foundation for writing clean, readable, and maintainable automated tests for web applications.

## 🛠️ Tech Stack & Key Features

* **Selenium WebDriver**: For state-of-the-art browser automation.
* **TestNG**: As a powerful testing framework for test management, execution, and assertions.
* **Gradle**: For dependency management and building the project using a Gradle Wrapper for consistency.
* **Page Object Model (POM)**: As the core design pattern for enhancing test maintenance and reducing code duplication.
* **Detailed Logging**: Integrated **SLF4J + Log4j2** for clear test execution traceability.
* **Automatic Screenshots**: Utilizes **TestNG Listeners** to automatically capture screenshots on test failure for easier debugging.
* **Externalized Configuration**: Key parameters (e.g., `baseUrl`) are managed in `.properties` files, allowing for easy switching between environments.
* **Cross-Platform**: Designed for seamless execution across Windows, macOS, and Linux environments.

## 📂 Project Structure

```
Freshy/
├── .gradle/
├── build/
├── gradle/
├── src/
│   ├── main/java/          # Page Objects (BasePage, LoginPage, etc.)
│   └── test/
│       ├── java/           # Test classes (LoginTest, ExceptionsTest, etc.)
│       └── resources/      # Configuration files (test.properties, log4j2.xml)
├── .gitignore
├── build.gradle
└── gradlew
```

## 🚀 Getting Started

### Prerequisites

To run this project, you need the following installed on your machine:
1.  **Java Development Kit (JDK)** version 17 or higher.
2.  **Google Chrome** browser (latest version).

### How to Run Tests

1.  Clone the repository:
    ```bash
    git clone <your-repository-url>
    ```
2.  Navigate to the project directory:
    ```bash
    cd Freshy
    ```
3.  (For Linux/macOS only) Make the Gradle Wrapper executable:
    ```bash
    chmod +x gradlew
    ```
4.  Run all tests using the following command:
    ```bash
    # For Windows
    gradlew test

    # For Linux/macOS
    ./gradlew test
    ```
Tests can also be executed directly from IntelliJ IDEA by right-clicking on a test class or method and selecting 'Run'.

## 📊 Test Reports

This framework is ready to be integrated with the **Allure Framework** to generate rich, interactive test reports. Once Allure is configured, the report can be generated and served with the following command:

```bash
# This command will work after Allure integration is complete.
./gradlew allureServe
```