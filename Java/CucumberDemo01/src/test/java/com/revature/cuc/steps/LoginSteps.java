package com.revature.cuc.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps
{
    private WebDriver driver;
    private final String BASE_URL = "https://the-internet.herokuapp.com/login";

    @Before
    public void setUp()
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }
    @Given("the application is running")
    public void theApplicationIsRunning() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        driver.get(BASE_URL);
        Thread.sleep(5);
        assertTrue(driver.getTitle().contains("The Internet"));
    }

    @And("the test database is already seeded with users")
    public void theTestDatabaseIsAlreadySeededWithUsers() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("The test database is already seeded with users");
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        // Write code here that turns the phrase above into concrete actions
        driver.get(BASE_URL);
    }

    @When("the user enters username {string}")
    public void theUserEntersUsername(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.id("username")).sendKeys(arg0);
    }

    @And("the user enters password {string}")
    public void theUserEntersPassword(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.id("password")).sendKeys(arg0);
    }

    @And("the user clicks the login button")
    public void theUserClicksTheLoginButton()
    {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.className("radius")).click();
        //driver.findElement(By.id("login")).click();
    }

    @Then("the user should be redirected to the secure area")
    public void theUserShouldBeRedirectedToTheSecureArea() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(driver.getCurrentUrl().endsWith("/secure"));
    }

    @And("the page should display a message containing {string}")
    public void thePageShouldDisplayAMessageContaining(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        String actualMessage = driver.findElement(By.id("flash")).getText();
        assertTrue(actualMessage.contains(arg0));
    }
}
