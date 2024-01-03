package tests;

import base.Setup;
import data.pageElements.LoginPage;
import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;

import static data.application.ApplicationCredentials.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends Setup {

    @Test
    public void LoginTest() {
        LoginPage loginPage = new LoginPage(page);

        // Expect a title
        assertThat(page).hasTitle(Pattern.compile("The Keyholding Company"));

        // Login
        loginPage.usernameInput.fill(EMAIL_ADDRESS);
        loginPage.passwordInput.fill(PASS);
        loginPage.signInBtn.click();

        // Logout
        loginPage.signOutBtn.click();
        assertThat(loginPage.signInBtn).isVisible();
    }
}