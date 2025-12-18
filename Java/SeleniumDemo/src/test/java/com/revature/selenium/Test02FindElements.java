package com.revature.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Find Elements Test")
public class Test02FindElements
{
    private WebDriver driver;
    private final String BASE_URL = "https://the-internet.herokuapp.com";

    @BeforeEach
    public void setUp()
    {
        // Setup WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }

    @DisplayName("Test by Id")
    @Test
    public void testFindById() throws InterruptedException
    {
        driver.get(BASE_URL + "/login");
        WebElement userName = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
    }

    // Try by name
    @DisplayName("Test by Name")
    @Test
    public void testFindByName() throws InterruptedException
    {
        driver.get(BASE_URL + "/");
        WebElement searchBox = driver.findElement(By.name("username"));
    }

    // Try by tag name
    @DisplayName("Test by Tag name")
    @Test
    public void testFindBytagName() throws InterruptedException
    {
        driver.get(BASE_URL + "/");
        WebElement header = driver.findElement(By.tagName("h1"));
    }

    @DisplayName("Test by Class name")
    @Test
    public void testFindByClassName() throws InterruptedException
    {
        driver.get(BASE_URL + "/");
        WebElement example = driver.findElement(By.className("radius"));
    }

    // Write an absolute path to find "available examples" header
    @DisplayName("Test by Absolute XPath")
    @Test
    public void testFindByAbsoluteXPath() throws InterruptedException
    {
        driver.get(BASE_URL + "/");
        WebElement header = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]"));
    }

    @DisplayName("Test by Relative XPath Login Username")
    @Test
    public void testFindByRelativeXPathLoginUserName() throws InterruptedException
    {
        driver.get(BASE_URL + "/login");
        WebElement header = driver.findElement(By.xpath("//input[@id='username']"));
    }

    @DisplayName("Test by Absolute XPath Login Username")
    @Test
    public void testFindByAbsoluteXPathLoginUserName() throws InterruptedException
    {
        driver.get(BASE_URL + "/login");
        WebElement header = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/input[1]"));
    }

    @DisplayName("Test by Relative XPath Login Password")
    @Test
    public void testFindByRelativeXPathLoginPassword() throws InterruptedException
    {
        driver.get(BASE_URL + "/login");
        WebElement header = driver.findElement(By.xpath("//input[@id='password']"));
    }

    @DisplayName("Test by Absolute XPath Login Password")
    @Test
    public void testFindByAbsoluteXPathLoginPassword() throws InterruptedException
    {
        driver.get(BASE_URL + "/login");
        WebElement header = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/input[1]"));
    }

    @Test
    @DisplayName("Complete login form interaction")
    void completeForm_loginFlow()
    {
        driver.get(BASE_URL + "/login");

        // Find elements
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.className("radius"));
        // Verify elements are displayed and enabled
        assertTrue(loginButton.isDisplayed());
        assertTrue(loginButton.isEnabled());
        assertTrue(usernameInput.isDisplayed());
        assertTrue(usernameInput.isEnabled());
        assertTrue(passwordInput.isDisplayed());
        assertTrue(passwordInput.isEnabled());
        // Clear and enter credentials
        usernameInput.clear();
        passwordInput.clear();
        usernameInput.sendKeys("tomsmith");
        passwordInput.sendKeys("SuperSecretPassword!");
        // Verify input values
        assertTrue(usernameInput.getAttribute("value").equals("tomsmith"));
        assertTrue(passwordInput.getAttribute("value").equals("SuperSecretPassword!"));
        // Click login
        loginButton.click();
        // Verify success (check for success message or URL)
        WebElement flashMessage = driver.findElement(By.id("flash"));
        String flash = flashMessage.getText();
        assertTrue(flash.contains("You logged into a secure area!") ||
                driver.getCurrentUrl().contains("secure"));
    }
}