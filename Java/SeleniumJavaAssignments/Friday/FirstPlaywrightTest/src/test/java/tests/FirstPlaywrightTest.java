package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * First Playwright test demonstrating basic operations.
 */
public class FirstPlaywrightTest
{

    // Shared across all tests in the class
    static Playwright playwright;
    static Browser browser;

    // Unique per test for isolation
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)  // Set to true for CI
                .setSlowMo(100));    // Slow down for visibility (remove in production)
    }

    @AfterAll
    static void closeBrowser() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void testNavigateToHomepage() {
        // Navigate to page
        page.navigate("https://the-internet.herokuapp.com/");

        // Playwright's web-first assertions automatically wait!
        assertThat(page).hasTitle("The Internet");

        // Find heading and assert
        Locator heading = page.locator("h1");
        assertThat(heading).containsText("Welcome to the-internet");
    }

    @Test
    void testClickNavigationLink() {
        page.navigate("https://the-internet.herokuapp.com/");

        // Click link - auto-waits for element to be actionable
        page.click("text=Form Authentication");

        // Assert URL changed
        assertThat(page).hasURL("https://the-internet.herokuapp.com/login");

        // Assert heading on new page
        assertThat(page.locator("h2")).hasText("Login Page");
    }

    @Test
    void testFormInteraction()
    {
        // Navigate to login page
        page.navigate("https://the-internet.herokuapp.com/login");

        // 1. Fill username field
        page.fill("#username", "tomsmith");

        // 2. Fill password field
        page.fill("#password", "SuperSecretPassword!");

        // 3. Click login button
        page.click("button[type='submit']");

        // 4. Assert redirect and success message
        assertThat(page)
                .hasURL(java.util.regex.Pattern.compile(".*/secure"));

        assertThat(page.locator("#flash"))
                .containsText("You logged into");
    }

    @Test
    void testAutoWaitBehavior() {
        page.navigate("https://the-internet.herokuapp.com/dynamic_loading/1");

        // Click start - element hidden initially
        page.click("#start button");

        // This automatically waits for element to be visible!
        // No explicit wait needed unlike Selenium
        Locator result = page.locator("#finish h4");

        // Web-first assertion waits until condition is met
        assertThat(result).hasText("Hello World!");
    }

    @Test
    void testLocatorStrategies()
    {
        page.navigate("https://the-internet.herokuapp.com/login");

        // By ID
        Locator byId = page.locator("#username");
        assertThat(byId).isVisible();

        // By CSS
        Locator byCss = page.locator("input[name='password']");
        assertThat(byCss).isVisible();

        // By text (scoped to button to avoid strict mode violation)
        Locator byText = page.locator("button:has-text('Login')");
        assertThat(byText).isVisible();

        // By role (BEST PRACTICE)
        Locator byRole = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Login")
        );
        assertThat(byRole).isVisible();

        // By placeholder (does not exist on this page)
        Locator byPlaceholder = page.getByPlaceholder("username");
        assertThat(byPlaceholder).hasCount(0);

        // By label
        Locator byLabel = page.getByLabel("Username");
        assertThat(byLabel).isVisible();
    }

}