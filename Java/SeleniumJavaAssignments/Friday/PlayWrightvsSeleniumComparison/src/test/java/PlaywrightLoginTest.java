import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Login test implemented with Playwright.
 */
public class PlaywrightLoginTest
{

    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;
    private static final String BASE_URL = "https://the-internet.herokuapp.com";

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                // .setHeadless(true)
        );
    }

    @AfterAll
    static void closeBrowser() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void createContext() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void testCompleteLoginLogoutFlow() {
        // 1. Navigate to login page
        page.navigate(BASE_URL + "/login");

        // 2. Enter credentials - NO element finding needed!
        page.fill("#username", "tomsmith");
        page.fill("#password", "SuperSecretPassword!");

        // 3. Click login
        page.click("button[type='submit']");

        // 4. Verify navigation - NO explicit wait needed!
        assertThat(page).hasURL(java.util.regex.Pattern.compile(".*/secure"));

        // 5. Verify welcome message - AUTO-WAITS until visible!
        assertThat(page.locator("#flash")).containsText("You logged into a secure area!");

        // 6. Click logout
        page.click("a.button");

        // 7. Verify returned to login - AUTO-WAITS!
        assertThat(page).hasURL(java.util.regex.Pattern.compile(".*/login"));

        // 8. Verify logout message - AUTO-WAITS!
        assertThat(page.locator("#flash")).containsText("You logged out of the secure area!");
    }

    @Test
    void testDynamicContentWithAutoWait() {
        page.navigate(BASE_URL + "/dynamic_loading/1");

        // Click start
        page.click("#start button");

        // NO explicit wait needed - auto-waits for visibility!
        assertThat(page.locator("#finish h4")).hasText("Hello World!");
    }
}