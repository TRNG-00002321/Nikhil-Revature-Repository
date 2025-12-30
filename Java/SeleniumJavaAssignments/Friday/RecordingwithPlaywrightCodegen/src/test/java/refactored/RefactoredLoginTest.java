package refactored;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;
import pages.LoginPage;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RefactoredLoginTest
{

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeAll
    void launchBrowser()
    {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(true)
        );
    }

    @AfterAll
    void closeBrowser()
    {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void createContext()
    {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext()
    {
        context.close();
    }

    @Test
    @DisplayName("Login and logout successfully")
    void loginLogoutFlow()
    {
        page.navigate("https://the-internet.herokuapp.com/");

        page.getByRole(AriaRole.LINK,
                        new Page.GetByRoleOptions().setName("Form Authentication"))
                .click();

        LoginPage loginPage = new LoginPage(page);

        loginPage.login("tomsmith", "SuperSecretPassword!");

        assertThat(page).hasURL(Pattern.compile(".*/secure"));
        assertThat(loginPage.flashMessage())
                .containsText("You logged into a secure area!");

        loginPage.logout();

        assertThat(page).hasURL(Pattern.compile(".*/login"));
    }
}

