package com.bs;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HackathonTests extends BaseTest{
    public void type(By by, String data) {
        driver.findElement(by).sendKeys(data);
    }

    public void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    public String getTextMessage(By by) {
        return driver.findElement(by).getText().trim();
    }

    @Test
    public void loginWithValidCredentials() throws InterruptedException {
        type(By.cssSelector("[placeholder=\"Username\"]"),"performance_glitch_user");
        type(By.cssSelector("[data-test=\"password\"]"),"secret_sauce");
        clickOnElement(By.id("login-button"));
        Assert.assertEquals(getTextMessage(By.cssSelector(".title")), "Products");
    }





}
