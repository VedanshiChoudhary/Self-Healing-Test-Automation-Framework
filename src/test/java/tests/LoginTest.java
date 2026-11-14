package com.selfhealing.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import com.selfhealing.base.BaseTest;
import com.selfhealing.pages.LoginPage;

public class LoginTest extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(LoginTest.class);

    @Test
    public void verifySuccessfulLogin() {

        logger.info("Starting successful login test");

        driver.get(baseUrl);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(explicitWait)
        );

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='title']")
        ));

        String heading = driver.findElement(
                By.cssSelector("[data-test='title']")
        ).getText();

        Assert.assertEquals(heading, "Products");

        logger.info("Successful login test passed");
    }

    @Test
    public void verifyInvalidLogin() {

        logger.info("Starting invalid login test");

        driver.get(baseUrl);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("wrong_user", "wrong_password");

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertTrue(
                errorMessage.contains("Username and password do not match")
        );

        logger.info("Invalid login test passed");
    }

    @Test
    public void verifyEmptyLogin() {

        logger.info("Starting empty login test");

        driver.get(baseUrl);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertTrue(
                errorMessage.contains("Username is required"),
                "Empty login validation message was not displayed!"
        );

        logger.info("Empty login test passed");
    }

    @Test
    public void verifyLockedOutUser() {

        logger.info("Starting locked-out user test");

        driver.get(baseUrl);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertTrue(
                errorMessage.contains("locked out"),
                "Locked-out user error message was not displayed!"
        );

        logger.info("Locked-out user test passed");
    }
}
