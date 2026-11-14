
package com.selfhealing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.selfhealing.healing.SelfHealingLocator;

public class LoginPage {

    private WebDriver driver;

    // Original and backup locators
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Enter username
    public void enterUsername(String username) {

        WebElement element = SelfHealingLocator.findElement(
                driver,
                usernameField,
                By.name("user-name"),
                By.cssSelector("input#user-name")
        );

        element.clear();
        element.sendKeys(username);
    }

    // Enter password
    public void enterPassword(String password) {

        WebElement element = SelfHealingLocator.findElement(
                driver,
                passwordField,
                By.name("password"),
                By.cssSelector("input#password")
        );

        element.clear();
        element.sendKeys(password);
    }

    // Click login button
    public void clickLogin() {

        WebElement element = SelfHealingLocator.findElement(
                driver,
                loginButton,
                By.cssSelector("input[type='submit']"),
                By.cssSelector("input.btn_action")
        );

        element.click();
    }

    // Complete login process
    public void login(String username, String password) {

        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Read login error message
    public String getErrorMessage() {

        return driver.findElement(
                By.cssSelector("[data-test='error']")
        ).getText();
    }
}
