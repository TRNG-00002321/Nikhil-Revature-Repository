package generated;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class GeneratedLoginTest
{
    public static void main(String[] args)
    {
        try (Playwright playwright = Playwright.create())
        {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("https://the-internet.herokuapp.com/");
            page.getByRole(AriaRole.LINK,
                    new Page.GetByRoleOptions().setName("Form Authentication")).click();

            page.locator("#username").click();
            page.locator("#username").fill("tomsmith");

            page.locator("#password").click();
            page.locator("#password").fill("SuperSecretPassword!");

            page.getByRole(AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName("Login")).click();

            page.getByRole(AriaRole.LINK,
                    new Page.GetByRoleOptions().setName("Logout")).click();
        }
    }
}
