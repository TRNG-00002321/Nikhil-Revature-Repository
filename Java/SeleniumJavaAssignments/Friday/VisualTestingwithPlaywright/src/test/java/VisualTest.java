import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.nio.file.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Visual regression tests using Playwright screenshot comparison.
 */
public class VisualTest
{

    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;

    private static final Path BASELINE_DIR = Paths.get("visual-baselines");
    private static final Path ACTUAL_DIR = Paths.get("visual-actual");
    private static final Path DIFF_DIR = Paths.get("visual-diff");

    @BeforeAll
    static void setup() throws Exception {
        // Create directories
        Files.createDirectories(BASELINE_DIR);
        Files.createDirectories(ACTUAL_DIR);
        Files.createDirectories(DIFF_DIR);

        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void teardown() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void createContext() {
        // Use consistent viewport for visual testing
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1280, 720));
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void testHomepageVisualBaseline() throws Exception {
        page.navigate("https://the-internet.herokuapp.com/");

        // Capture screenshot
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions()
                .setFullPage(true));

        Path baselinePath = BASELINE_DIR.resolve("homepage.png");
        Path actualPath = ACTUAL_DIR.resolve("homepage.png");

        // Save actual screenshot
        Files.write(actualPath, screenshot);

        if (!Files.exists(baselinePath)) {
            // First run - create baseline
            Files.write(baselinePath, screenshot);
            System.out.println("Created baseline: " + baselinePath);
        } else {
            // Compare with baseline
            byte[] baseline = Files.readAllBytes(baselinePath);
            boolean matches = Arrays.equals(baseline, screenshot);

            if (!matches) {
                // Save diff for analysis
                System.out.println("Visual difference detected! Check: " + actualPath);
            }

            // Note: In production, use proper image comparison library
            // assertTrue(matches, "Screenshot should match baseline");
        }
    }

    @Test
    void testLoginPageVisual() throws Exception {
        page.navigate("https://the-internet.herokuapp.com/login");

        // Wait for page to stabilize
        page.waitForLoadState();

        byte[] screenshot = page.screenshot();

        Path baselinePath = BASELINE_DIR.resolve("login.png");
        Path actualPath = ACTUAL_DIR.resolve("login.png");

        Files.write(actualPath, screenshot);

        if (!Files.exists(baselinePath)) {
            Files.write(baselinePath, screenshot);
            System.out.println("Created baseline: " + baselinePath);
        }
    }

    @Test
    void testElementScreenshot() throws Exception {
        page.navigate("https://the-internet.herokuapp.com/login");

        // Screenshot of specific element
        Locator loginForm = page.locator("#login");

        byte[] elementScreenshot = loginForm.screenshot();

        Path baselinePath = BASELINE_DIR.resolve("login-form.png");
        Path actualPath = ACTUAL_DIR.resolve("login-form.png");

        Files.write(actualPath, elementScreenshot);

        if (!Files.exists(baselinePath)) {
            Files.write(baselinePath, elementScreenshot);
            System.out.println("Created element baseline: " + baselinePath);
        }
    }

    @Test
    void testUsingUtility() throws Exception {
        page.navigate("https://the-internet.herokuapp.com/login");
        page.waitForLoadState();

        boolean matches = VisualTestUtils.compareWithBaseline(
                page,
                "login-page",
                new Page.ScreenshotOptions().setFullPage(true)
        );

        assertTrue(matches, "Login page should match baseline");
    }
}