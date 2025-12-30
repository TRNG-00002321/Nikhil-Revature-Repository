package tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Tests demonstrating Playwright navigation features.
 */
public class NavigationTest
{
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void teardown() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void createPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closePage() {
        context.close();
    }

    @Test
    void testBackForwardNavigation()
    {
        // Navigate to home
        page.navigate("https://the-internet.herokuapp.com/");
        String homeUrl = page.url();

        // Navigate to another page
        page.click("text=Form Authentication");
        assertThat(page).hasURL("https://the-internet.herokuapp.com/login");

        // Go back
        page.goBack();
        assertThat(page).hasURL(homeUrl);

        // Go forward
        page.goForward();
        assertThat(page).hasURL("https://the-internet.herokuapp.com/login");
    }

    @Test
    void testNewTab()
    {
        // Navigate to the page with the link that opens a new window
        page.navigate("https://the-internet.herokuapp.com/windows");

        // Wait for the popup and click the link that opens it
        Page popup = page.waitForPopup(() -> {
            page.click("text=Click Here");
        });

        // Assert popup title
        assertThat(popup).hasTitle("New Window");

        // Assert popup content
        assertThat(popup.locator("h3")).hasText("New Window");

        // Close the popup
        popup.close();
    }

    @Test
    void testMultipleTabs()
    {
        // Open first page
        page.navigate("https://the-internet.herokuapp.com/");

        // Open a second page (new tab) in the SAME browser context
        Page page2 = context.newPage();
        page2.navigate("https://the-internet.herokuapp.com/login");

        // Both pages share the same context (cookies, localStorage, sessionStorage)
        assertThat(page).hasTitle("The Internet");
        assertThat(page2).hasURL("https://the-internet.herokuapp.com/login");

        // Optional: prove context sharing by logging in on page2
        page2.fill("#username", "tomsmith");
        page2.fill("#password", "SuperSecretPassword!");
        page2.click("button[type='submit']");
        assertThat(page2).hasURL(java.util.regex.Pattern.compile(".*/secure"));

        // Cleanup
        page2.close();
    }

    @Test
    void testWaitForNavigation() {
        page.navigate("https://the-internet.herokuapp.com/login");

        // Wait for navigation when clicking
        page.fill("#username", "tomsmith");
        page.fill("#password", "SuperSecretPassword!");

        // waitForURL waits for navigation to complete
        page.click("button[type='submit']");
        page.waitForURL("**/secure");

        assertThat(page.locator("#flash")).containsText("secure area");
    }

    @Test
    void testCaptureScreenshot()
    {
        page.navigate("https://the-internet.herokuapp.com/");

        // Full page screenshot
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(java.nio.file.Paths.get("screenshots/homepage.png"))
                .setFullPage(true));

        // Element screenshot
        Locator heading = page.locator("h1");
        heading.screenshot(new Locator.ScreenshotOptions()
                .setPath(java.nio.file.Paths.get("screenshots/heading.png")));

        // Verify files exist
        java.io.File fullPage = new java.io.File("screenshots/homepage.png");
        java.io.File element = new java.io.File("screenshots/heading.png");

        Assertions.assertTrue(fullPage.exists(), "Full page screenshot should exist");
        Assertions.assertTrue(element.exists(), "Element screenshot should exist");
    }
}
