package com.rajesh.springbootfirst;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.junit.Test;

import org.junit.Assert;

public class HelloWebpageIT {

    @Test
    public void startWebDriver() {
        // System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");


        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        WebDriver driver = new ChromeDriver(chromeOptions);
        
        // WebDriver driver = new ChromeDriver();
        driver.get("http://34.93.172.88:8080");
        String title = driver.getTitle();
        System.out.println(driver.getTitle());
        Assert.assertEquals("hello selenium!", title);
        driver.quit();
    }
}