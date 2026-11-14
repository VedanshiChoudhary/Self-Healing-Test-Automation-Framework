package com.selfhealing.healing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SelfHealingLocator {

    private static final Logger logger =
            LogManager.getLogger(SelfHealingLocator.class);

    public static WebElement findElement(
            WebDriver driver,
            By originalLocator,
            By... alternativeLocators) {

        logger.info("Trying original locator: {}", originalLocator);

        List<WebElement> elements =
                driver.findElements(originalLocator);

        if (!elements.isEmpty()) {
            logger.info("Original locator worked!");
            return elements.get(0);
        }

        logger.warn("Original locator failed. Trying alternative locators.");

        for (By alternative : alternativeLocators) {

            logger.info("Trying alternative locator: {}", alternative);

            List<WebElement> alternativeElements =
                    driver.findElements(alternative);

            if (!alternativeElements.isEmpty()) {

                logger.warn(
                        "Self-healing successful! Alternative locator worked: {}",
                        alternative
                );

                return alternativeElements.get(0);
            }
        }

        logger.error("All locator attempts failed!");

        throw new RuntimeException(
                "Unable to find element using original or alternative locators."
        );
    }
}
