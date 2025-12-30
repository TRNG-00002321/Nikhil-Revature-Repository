import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import java.nio.file.*;

/**
 * Visual testing with dynamic content handling.
 */
public class DynamicVisualTest
{

    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;

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
    void createContext() {
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1280, 720));
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void testWithHiddenDynamicElements() throws Exception {
        page.navigate("https://the-internet.herokuapp.com/dynamic_content");

        // Hide dynamic elements before screenshot
        page.evaluate("document.querySelectorAll('.large-2 img').forEach(el => el.style.visibility = 'hidden')");

        byte[] screenshot = page.screenshot();

        Path path = Paths.get("visual-baselines/dynamic-hidden.png");
        Files.createDirectories(path.getParent());
        Files.write(path, screenshot);
    }

    @Test
    void testWithMaskedRegions() throws Exception {
        page.navigate("https://the-internet.herokuapp.com/");

        // Use clip to exclude dynamic regions
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions()
                .setClip(0, 0, 1280, 300));  // Only capture header

        Path path = Paths.get("visual-baselines/header-only.png");
        Files.createDirectories(path.getParent());
        Files.write(path, screenshot);
    }

    @Test
    void testWithDateReplacement() throws Exception {
        page.navigate("https://the-internet.herokuapp.com/");

        // Replace dynamic dates/times with static values
        page.evaluate("""
            document.querySelectorAll('[class*="date"], [class*="time"]')
                .forEach(el => el.textContent = 'STATIC_DATE');
        """);

        byte[] screenshot = page.screenshot();

        Path path = Paths.get("visual-baselines/static-dates.png");
        Files.createDirectories(path.getParent());
        Files.write(path, screenshot);
    }

    @Test
    void testWaitForAnimationsComplete() throws Exception {
        page.navigate("https://the-internet.herokuapp.com/");

        // Wait for all animations to complete
        page.waitForFunction("() => !document.querySelector(':scope *:is(:animating)')");

        // Alternative: wait fixed time for animations
        page.waitForTimeout(500);

        byte[] screenshot = page.screenshot();

        Path path = Paths.get("visual-baselines/post-animation.png");
        Files.createDirectories(path.getParent());
        Files.write(path, screenshot);
    }

    @Test
    void testResponsiveVisuals() throws Exception {
        String[] viewports = {"desktop", "tablet", "mobile"};
        int[][] sizes = {{1920, 1080}, {768, 1024}, {375, 667}};

        for (int i = 0; i < viewports.length; i++) {
            // Create context with specific viewport
            BrowserContext responsiveContext = browser.newContext(
                    new Browser.NewContextOptions()
                            .setViewportSize(sizes[i][0], sizes[i][1])
            );
            Page responsivePage = responsiveContext.newPage();

            responsivePage.navigate("https://the-internet.herokuapp.com/");

            byte[] screenshot = responsivePage.screenshot();

            Path path = Paths.get("visual-baselines/responsive-" + viewports[i] + ".png");
            Files.createDirectories(path.getParent());
            Files.write(path, screenshot);

            responsiveContext.close();
        }
    }
}