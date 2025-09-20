package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {
    private WebDriver driver;
    private final Scanner scanner = new Scanner(System.in);

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Headless in CI
        if (System.getenv("CI") != null || Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/"); // Demo e-commerce site for testing

        pause("Browser opened and navigated to login page.");
    }

    @Test
    @Description("Valid login redirects to inventory page and URL contains inventory.html")
    void validLogin() {
        LoginPage loginPage = new LoginPage(driver);

        pause("Ready to perform login?");
        loginPage.login("standard_user", "secret_sauce");

        // Capture a screenshot right after login for the report
        attachScreenshot("After login");

        pause("Login attempted. Check the browser.");

        try{
            assertThat(driver.getCurrentUrl())
                    .as("URL after successful login should contain inventory.html")
                    .contains("inventory.html");
        } catch (AssertionError ae) {
            // On failure, attach another screenshot to help debugging
            attachScreenshot("Failure – URL assertion");
            throw ae;
        }

        pause("Assertion done. Test will be finished.");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            pause("Test finished. Browser will be closed.");
            driver.quit();
        }
    }

    private void pause(String message) {
        try {
            // If running headless (e.g., in CI), fall back to a short sleep so tests don’t hang forever
            if (GraphicsEnvironment.isHeadless()) {
                System.out.println("[HEADLESS] " + message + " (auto-continue in 5s)");
                Thread.sleep(Duration.ofSeconds(5).toMillis());
                return;
            }

            // Show a modal dialog that blocks until you click OK (or close the dialog)
            int result = JOptionPane.showConfirmDialog(
                    null,
                    message + "\n\nClick OK to continue or Cancel to abort.",
                    "Pause",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE
            );

            if (result != JOptionPane.OK_OPTION) {
                throw new RuntimeException("Execution cancelled by user.");
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Pause interrupted", ie);
        }
    }

    private void attachScreenshot(String name) {
        if (driver instanceof TakesScreenshot ts) {
            byte[] bytes = ts.getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(name, new ByteArrayInputStream(bytes));
        } else {
            System.out.println("Driver does not support screenshots.");
        }
    }

}