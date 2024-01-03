package data.pageElements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
    public final Locator usernameInput;
    public final Locator passwordInput;
    public final Locator signInBtn;
    public final Locator signOutBtn;

    public LoginPage(Page page) {
        this.usernameInput = page.getByTestId("email-input");
        this.passwordInput = page.getByTestId("password-input");
        this.signInBtn = page.getByTestId("sign-in-button");
        this.signOutBtn = page.getByTestId("logout-link");
    }
}
