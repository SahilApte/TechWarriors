package com.bs;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePageTests extends BaseTest {

    private WebDriverWait wait;

    /**
     * TC-183: End to End Flow Test
     */
    @Test
    public void endToEndFLow() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        login();
        addToCartAndCheckout();
        enterShippingDetails();
        String msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[normalize-space()='Your Order has been successfully placed.']"))).getText();
        Assert.assertEquals(msg, "Your Order has been successfully placed.");
    }

    /**
     * TC-181: Method to validate Shipping details mandatory fields
     */
    @Test
    public void validateShippingDetailsMandatoryFields() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        login();
        addToCartAndCheckout();
        type(By.xpath("//input[@id='firstNameInput']"), generateRandomString());
        type(By.xpath("//input[@id='lastNameInput']"), generateRandomString());
        String urlBeforeSubmit=driver.getCurrentUrl();
        clickOnElement(By.xpath("//button[@id='checkout-shipping-continue']"));
        String urlAfterSubmit=driver.getCurrentUrl();
        Assert.assertEquals(urlBeforeSubmit,urlAfterSubmit);
    }

    /* Helper methods*/

    public void login() {
        try {
            clickOnElement(By.xpath("//a[@id='Sign In']"));
            clickOnElement(By.id("username"));
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
            clickOnElement(By.id("password"));
            actions.sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
            clickOnElement(By.id("login-btn"));
        } catch (Exception e) {
            Assert.fail("Login failed: " + e.getMessage());
        }
    }

    public void addToCartAndCheckout() {
        clickOnElement(By.xpath("//div[@id='1']//div[@class='shelf-item__buy-btn' and normalize-space()='Add to cart']"));
        clickOnElement(By.xpath("//div[@class='buy-btn']"));
    }

    public void enterShippingDetails() {
        try {
            type(By.xpath("//input[@id='firstNameInput']"), generateRandomString());
            type(By.xpath("//input[@id='lastNameInput']"), generateRandomString());
            type(By.xpath("//input[@id='addressLine1Input']"), generateRandomString());
            type(By.xpath("//input[@id='provinceInput']"), generateRandomString());
            type(By.xpath("//input[@id='postCodeInput']"), generateRandomString());
            clickOnElement(By.xpath("//button[@id='checkout-shipping-continue']"));
        } catch (Exception e) {
            Assert.fail("Entering shipping details failed: " + e.getMessage());
        }
    }

    public void type(By by, CharSequence data) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(data);
    }

    public void clickOnElement(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public String getTextMessage(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).getText().trim();
    }

    public String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        try{
            int length = 5;
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            randomString.append(characters.charAt(index));
        }}
        catch (Exception e) {
            Assert.fail("Random string generation failed: " + e.getMessage());
        }
        return randomString.toString();
    }
}