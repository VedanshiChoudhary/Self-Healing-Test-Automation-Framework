package com.selfhealing.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.selfhealing.base.BaseTest;
import com.selfhealing.healing.SelfHealingLocator;

public class SelfHealingTest extends BaseTest {

    @Test
    public void verifyAlternativeLocatorWorks() {

        driver.get(baseUrl);

        System.out.println("Testing Self-Healing Locator...");

        WebElement username = SelfHealingLocator.findElement(
                driver,
                By.id("wrong-username-id"),
                By.id("user-name")
        );

        Assert.assertTrue(username.isDisplayed());

        System.out.println(
                "SUCCESS: Alternative locator found the username field!"
        );
    }
}
