package com.bs;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {
    public static WebDriver driver;

    @Parameters({"browser","os"})
    @BeforeMethod
    public void setBrowser(String browser, String os, Method method) throws MalformedURLException {
        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", System.getenv("BROWSERSTACK_USERNAME"));
        bstackOptions.put("accessKey", System.getenv("BROWSERSTACK_ACCESS_KEY"));
//        bstackOptions.put("geoLocation", "IN");
        bstackOptions.put("projectName", "Testathon Demo 5");
        bstackOptions.put("buildName", "Sauce Demo v2");
        bstackOptions.put("sessionName", method.getName()+"_"+browser+"_"+os);
       // bstackOptions.put("sessionName", "loginTest");

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("bstack:options", bstackOptions);

        switch (browser) {
            case "chrome_win10":
                setBrowserCapabilities(capabilities, "Chrome", "141", "Windows", "10");
                break;
            case "chrome_win11":
                setBrowserCapabilities(capabilities, "Chrome", "latest", "Windows", "11");
                break;
            case "firefox":
                setBrowserCapabilities(capabilities, "Firefox", "latest", "Windows", "10");
                break;
            case "edge":
                setBrowserCapabilities(capabilities, "Edge", "latest", "Windows", "10");
                break;
            case "safari_tahoe":
                setBrowserCapabilities(capabilities, "Safari", "latest", "OS X", "tahoe");
                break;
            case "safari_sonoma":
                setBrowserCapabilities(capabilities, "Safari", "latest", "OS X", "Sonoma");
                break;
        }

        //driver = new ChromeDriver();
        
        String username = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub"), capabilities);
        driver.get("https://testathon.live/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private void setBrowserCapabilities(MutableCapabilities capabilities, String browserName, String browserVersion, String os, String osVersion) {
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", browserVersion);
        if (os != null && osVersion != null) {
            HashMap<String, Object> bstackOptions = (HashMap<String, Object>) capabilities.getCapability("bstack:options");
            bstackOptions.put("os", os);
            bstackOptions.put("osVersion", osVersion);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
