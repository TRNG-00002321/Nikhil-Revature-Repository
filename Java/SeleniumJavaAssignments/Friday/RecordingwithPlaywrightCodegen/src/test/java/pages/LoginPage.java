package pages;

import com.microsoft.playwright.*;

public class LoginPage
{

    private final Page page;

    private static final String USERNAME = "#username";
    private static final String PASSWORD = "#password";
    private static final String LOGIN_BUTTON = "button[type='submit']";
    private static final String FLASH = "#flash";
    private static final String LOGOUT = "a.button";

    public LoginPage(Page page)
    {
        this.page = page;
    }

    public LoginPage navigate()
    {
        page.navigate("https://the-internet.herokuapp.com/login");
        return this;
    }

    public LoginPage login(String user, String pass)
    {
        page.fill(USERNAME, user);
        page.fill(PASSWORD, pass);
        page.click(LOGIN_BUTTON);
        return this;
    }

    public Locator flashMessage()
    {
        return page.locator(FLASH);
    }

    public void logout()
    {
        page.click(LOGOUT);
    }
}

