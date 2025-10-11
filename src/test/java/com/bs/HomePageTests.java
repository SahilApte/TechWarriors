package com.bs;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends  BaseTest {

    @FindBy(xpath = "//a[@class='Navbar_logo__26S5Y']//*[name()='svg']")
    public WebElement browserStackDemoLogo;

    @FindBy(xpath = "//strong[normalize-space()='Offers']")
    public WebElement offersLink;

    @FindBy(xpath = "//strong[normalize-space()='Orders']")
    public WebElement ordersLink;

    @FindBy(xpath = "//strong[normalize-space()='Favourites']")
    public WebElement favouritesLink;

    @FindBy(xpath = "//small[@class=\"products-found\"]//span")
    public WebElement productsCount;

    @FindBy(xpath = "//span[@class='bag bag--float-cart-closed']")
    public WebElement cartIcon;

    @FindBy(xpath = "//div[@id='1']//div[@class='shelf-item__buy-btn'][normalize-space()='Add to cart']")
    public WebElement addToCartButton;

    @FindBy(xpath ="//div[@data-sku=\"iPhone12-device-info.png\" and @id=\"3\"]//button")
    public WebElement getFavouritesLink;

    @FindBy(xpath = "//div[@class='buy-btn']")
    public WebElement checkout;


    @FindBy(id = "username")
    public WebElement usernameDropdown;

    @FindBy(id = "password")
    public WebElement passwordDropdown;

    @FindBy(id="login-btn")
    public WebElement loginButton;

    @FindBy(xpath ="//input[@id='firstNameInput']")
    public WebElement firstNameInput;

    @FindBy(xpath ="///input[@id='lastNameInput']")
    public WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='addressLine1Input']")
    public WebElement addressLine1Input;

    @FindBy(xpath = "//input[@id='addressLine1Input']")
    public  WebElement addressLine2Input;

    @FindBy(xpath = "//input[@id='postCodeInput']")
    public WebElement postCodeInput;

    @FindBy(xpath = "//button[@id='checkout-shipping-continue']")
    public WebElement continueButton;

    @FindBy(xpath = "//button[@id='checkout-shipping-continue']")
    public WebElement orderSuccessMessage;

    @FindBy(xpath = "//button[normalize-space()='Continue Shopping »']")
    public WebElement continueShoppingButton;

    @FindBy(xpath = "//button[@id='checkout-shipping-continue']")
    public WebElement submit;


    @FindBy(xpath = "//a[@id='logout']")
    public WebElement logoutButton;

    @FindBy(xpath = "//a[@id='Sign In']")
    public WebElement signIn;

    public String generateRandomString() {
        int length = 5;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            randomString.append(characters.charAt(index));
        }
        return randomString.toString();
    }

    public void voidLogin() {
        driver.get("https://testathon.live/");


//        signIn.click();
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", signIn);
        usernameDropdown.click();
        usernameDropdown.sendKeys(Keys.RETURN);

        passwordDropdown.click();
        passwordDropdown.sendKeys(Keys.RETURN);

        loginButton.click();
    }
    public void addtoCartAndCheckout(){
        voidLogin();
        addToCartButton.click();
        checkout.click();
    }

    public void enterShippingDetails(){
        firstNameInput.sendKeys(generateRandomString());
        lastNameInput.sendKeys(generateRandomString());
        addressLine1Input.sendKeys(generateRandomString());
        addressLine2Input.sendKeys(generateRandomString());
        postCodeInput.sendKeys(generateRandomString());
        submit.click();
    }

    @Test
    public void endTOEndFLow() throws InterruptedException{
        voidLogin();
        addtoCartAndCheckout();
        enterShippingDetails();
        orderSuccessMessage.isDisplayed();
        Assert.assertEquals(orderSuccessMessage.getText(),"Your Order has been successfully placed.");
    }











































}
