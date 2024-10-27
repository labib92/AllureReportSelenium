package allure.reports.pages;

import allure.reports.load.LoadablePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SwagLabsLoginPage implements LoadablePage {
    private final WebDriver driver;
    private static final By LOGIN_LOGO = By.xpath(".//div[@class='login_logo']");
    private static final By USERNAME_INPUT = By.xpath(".//input[@id='user-name']");
    private static final By PASSWORD_INPUT = By.xpath(".//input[@id='password']");
    private static final By LOGIN_BUTTON = By.xpath(".//input[@id='login-button']");

    public SwagLabsLoginPage(WebDriver driver) {
        this.driver = driver;
        checkPage();
    }

    @Override
    public void checkPage() {
        elementIsLocated(LOGIN_LOGO);
        elementIsLocated(USERNAME_INPUT);
        elementIsLocated(PASSWORD_INPUT);
        elementIsLocated(LOGIN_BUTTON);
    }

    private WebElement elementIsLocated(By locator) {
        return (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Login with username and password")
    public void loginToPage(String username, String password) {
        elementIsLocated(USERNAME_INPUT).sendKeys(username);
        elementIsLocated(PASSWORD_INPUT).sendKeys(password);
        elementIsLocated(LOGIN_BUTTON).click();
    }

    @Step("Checking if the logo displayed")
    public WebElement checkLogo() {
        return elementIsLocated(LOGIN_LOGO);
    }
}
