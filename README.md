# Self-Healing Test Automation Framework

## Project Overview
This project is a Java-based Selenium test automation framework that demonstrates self-healing locator functionality.

When the original web element locator fails, the framework tries predefined alternative locators to locate the element.

## Technologies Used
- Java
- Selenium WebDriver
- TestNG
- Maven
- ExtentReports
- Log4j
- WebDriverManager
- Page Object Model (POM)

## Key Features
- Self-healing locator mechanism
- Alternative locator fallback
- Automated login testing
- Positive and negative test cases
- Screenshot capture
- HTML test reports
- Logging
- Maven test execution

## Project Structure
```text
Self-Healing-Test-Automation-Framework/
├── src/
│   ├── main/java/healing/
│   ├── test/java/
│   └── test/resources/
├── reports/
├── screenshots/
├── logs/
├── pom.xml
├── testng.xml
└── README.md
```

## Test Website
https://www.saucedemo.com/

## Test Results
- Total test cases: 5
- Passed: 5
- Failed: 0
- Errors: 0

## How to Run
1. Install Java and Maven.
2. Clone this repository.
3. Open the project folder in Terminal.
4. Run:

```bash
mvn clean test
```

## Reports and Screenshots
- HTML report: `reports/ExtentReport.html`
- Screenshots: `screenshots/`

## Note
The self-healing mechanism uses predefined alternative locators. It is a rule-based implementation and does not use AI-based locator discovery.

## Author
Vedanshi Chaudhary
</EOF>

