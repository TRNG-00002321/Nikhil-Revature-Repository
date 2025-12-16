import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UITestWithScreenshots
{
    @Test
    @Description("Test with screenshot on failure")
    void loginTest_capturesScreenshotOnFailure() {
        try {
            boolean loginSuccess = performLogin("user", "wrong_password");

            if (!loginSuccess) {
                attachScreenshot("Login Failure Screenshot");
            }

            assertTrue(loginSuccess, "Login should succeed");

        } catch (AssertionError e) {
            attachScreenshot("Assertion Failure Screenshot");
            throw e;
        }
    }

    private boolean performLogin(String user, String pass) {
        return false;
    }

    @Attachment(value = "{name}", type = "image/png")
    public byte[] attachScreenshot(String name) {
        try {
            return Files.readAllBytes(Path.of("src/test/resources/placeholder.png"));
        } catch (Exception e) {
            return createPlaceholderImage();
        }
    }

    private byte[] createPlaceholderImage() {
        return new byte[]{
                (byte) 0x89, 0x50, 0x4E, 0x47,
                0x0D, 0x0A, 0x1A, 0x0A
        };
    }
}
