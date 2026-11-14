package com.selfhealing.base;

import com.selfhealing.config.ConfigReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {

    protected WebDriver driver;
    protected String baseUrl;
    protected int explicitWait;

    @BeforeMethod
    public void setUp() {

        baseUrl = ConfigReader.getProperty("baseUrl");
        explicitWait = ConfigReader.getIntProperty("explicitWait");

        String browser = ConfigReader.getProperty("browser");

        if (!"chrome".equalsIgnoreCase(browser)) {
            throw new RuntimeException(
                "Currently supported browser: Chrome only"
            );
        }

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        options.setBinary(
            "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"
        );

        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);

        System.out.println("Browser: " + browser);
        System.out.println("Website: " + baseUrl);
        System.out.println("Explicit Wait: " + explicitWait);
        System.out.println("Chrome Browser Launched Successfully!");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getName());
        }

        if (driver != null) {
            driver.quit();
            System.out.println("Browser Closed Successfully!");
        }
    }

    public void captureScreenshot(String testName) {

        try {

            File screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat(
                    "yyyyMMdd_HHmmss"
            ).format(new Date());

            Path folder = Paths.get("screenshots");

            Files.createDirectories(folder);

            Path destination = folder.resolve(
                    testName + "_" + timestamp + ".png"
            );

            Files.copy(screenshot.toPath(), destination);

            System.out.println(
                    "Screenshot Saved: " + destination
            );

        } catch (Exception e) {

            System.out.println(
                    "Screenshot Capture Failed: " + e.getMessage()
            );
        }
    }
}
