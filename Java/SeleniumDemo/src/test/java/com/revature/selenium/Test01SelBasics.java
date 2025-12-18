package com.revature.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Basic Selenium Test")
public class Test01SelBasics
{
    private WebDriver driver;

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

    @Test
    public void testBasic() throws InterruptedException
    {
        //Navigate to selenium.dev
        driver.get("https://www.selenium.dev/");
        //Thread.sleep(5000);
        //Get the title of the page
        String title = driver.getTitle();
        System.out.println("Page Title: " + title);
        assertTrue(title.contains("Selenium"));
    }

    @Test
    public void testContainsDocumentation() throws InterruptedException
    {
        //Navigate to selenium.dev
        driver.get("https://www.selenium.dev/documentation/");
        //Thread.sleep(5000);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Page Title: " + currentUrl);
        assertTrue(currentUrl.contains("documentation"));
    }
}
