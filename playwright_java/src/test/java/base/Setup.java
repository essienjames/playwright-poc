package base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Path;
import java.nio.file.Paths;

import static common.Actions.timeStampName;

public class Setup {
    static Playwright playwright;
    static Browser browser;
    public Page page;
    public Path path = Path.of("src/test/reporting/videos");
    BrowserContext context;

    @BeforeAll
    public static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
    }

    @BeforeEach
    public void createContextAndPage() {
        context = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(path)
                .setRecordVideoSize(800, 600));

        // Start tracing
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        // Open new web browser page
        page = context.newPage();

        // Login to client platform
        navigateToLoginPage();
    }

    @AfterEach
    public void closeContext() {
        // Stop tracing and export trace to a zip archive.
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("src/test/reporting/trace_files/" + timeStampName() + "trace.zip")));

        context.close();
    }

    @AfterAll
    public static void closeBrowser() {
        playwright.close();
    }

    public void navigateToLoginPage() {
        String env = System.getProperty("env");
        switch (env) {
            case "staging" -> page.navigate("https://staging.keyholding.com/home");
            case "testing" -> page.navigate("https://testing.keyholding.com/home");
        }
    }
}
