package com.herokuapp.uitests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver");
        driver = new ChromeDriver();
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @Test
    public void successfulLoginTest() {
        loginPage.open();
        loginPage.login("locked_out_use", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("/secure"), "Login failed");
    }

    @Test
    public void unsuccessfulLoginTest() {
        loginPage.open();
        loginPage.login("invalidUsername", "invalidPassword");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Your username is invalid!"), "Error message is incorrect");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}