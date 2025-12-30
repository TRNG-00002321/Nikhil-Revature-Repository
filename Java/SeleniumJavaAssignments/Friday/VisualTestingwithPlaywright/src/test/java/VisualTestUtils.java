import com.microsoft.playwright.*;
import java.nio.file.*;
import java.util.Arrays;

/**
 * Utility methods for visual testing.
 */
public class VisualTestUtils
{

    private static final Path BASELINE_DIR = Paths.get("visual-baselines");
    private static final Path ACTUAL_DIR = Paths.get("visual-actual");

    /**
     * Compare current screenshot with baseline.
     *
     * @param page The page to screenshot
     * @param name The baseline name
     * @param options Screenshot options
     * @return true if matches baseline (or baseline was created)
     */
    public static boolean compareWithBaseline(
            Page page,
            String name,
            Page.ScreenshotOptions options) throws Exception {

        // Create directories if needed
        Files.createDirectories(BASELINE_DIR);
        Files.createDirectories(ACTUAL_DIR);

        Path baselinePath = BASELINE_DIR.resolve(name + ".png");
        Path actualPath = ACTUAL_DIR.resolve(name + ".png");

        // Capture screenshot
        byte[] screenshot = page.screenshot(options);
        Files.write(actualPath, screenshot);

        // Check for baseline
        if (!Files.exists(baselinePath)) {
            Files.write(baselinePath, screenshot);
            System.out.println("✓ Created baseline: " + name);
            return true;
        }

        // Compare
        byte[] baseline = Files.readAllBytes(baselinePath);
        boolean matches = Arrays.equals(baseline, screenshot);

        if (matches) {
            System.out.println("✓ Visual match: " + name);
        } else {
            System.out.println("✗ Visual mismatch: " + name);
            System.out.println("  Baseline: " + baselinePath);
            System.out.println("  Actual: " + actualPath);
        }

        return matches;
    }

    /**
     * Update baseline with current screenshot.
     */
    public static void updateBaseline(Page page, String name) throws Exception {
        Files.createDirectories(BASELINE_DIR);

        byte[] screenshot = page.screenshot();
        Path baselinePath = BASELINE_DIR.resolve(name + ".png");
        Files.write(baselinePath, screenshot);

        System.out.println("Updated baseline: " + name);
    }

    /**
     * Hide elements that contain dynamic content.
     */
    public static void hideDynamicElements(Page page, String... selectors) {
        for (String selector : selectors) {
            page.evaluate(
                    "(selector) => document.querySelectorAll(selector).forEach(el => el.style.visibility = 'hidden')",
                    selector
            );
        }
    }
}