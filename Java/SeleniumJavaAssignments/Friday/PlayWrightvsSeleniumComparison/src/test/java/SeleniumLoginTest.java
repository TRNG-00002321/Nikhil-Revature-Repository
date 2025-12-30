import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Login test implemented with Selenium.
 */
public class SeleniumLoginTest
{

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "https://the-internet.herokuapp.com";

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testCompleteLoginLogoutFlow()
    {
        // 1. Navigate to login page
        driver.get(BASE_URL + "/login");

        // 2. Enter credentials
        // Must find elements first
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");

        // 3. Click login
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // 4. Wait for navigation - EXPLICIT WAIT REQUIRED
        wait.until(ExpectedConditions.urlContains("/secure"));

        // 5. Verify welcome message - EXPLICIT WAIT REQUIRED
        WebElement flashMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("flash"))
        );
        assertTrue(flashMessage.getText().contains("You logged into a secure area!"),
                "Should see success message");

        // 6. Click logout
        WebElement logoutButton = driver.findElement(By.cssSelector("a.button"));
        logoutButton.click();

        // 7. Wait and verify returned to login
        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"),
                "Should be back on login page");

        // 8. Verify logout message - EXPLICIT WAIT REQUIRED
        WebElement logoutMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("flash"))
        );
        assertTrue(logoutMessage.getText().contains("You logged out of the secure area!"),
                "Should see logout message");
    }

    @Test
    void testDynamicContentWithWaits()
    {
        driver.get(BASE_URL + "/dynamic_loading/1");

        // Click start
        driver.findElement(By.cssSelector("#start button")).click();

        // MUST wait explicitly for hidden element to become visible
        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish h4"))
        );

        assertEquals("Hello World!", result.getText());
    }
}