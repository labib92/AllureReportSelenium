package allure.reports.testCases;

import allure.reports.pages.SwagLabsLoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class AllureReportTest extends BaseTest {
    private SwagLabsLoginPage loginPage;

    @Test(priority = 1, description = "Verify Logo presence on Login Page")
    @Description("Verify Logo presence on Login Page")
    @Epic("EP001")
    @Feature("Feature1: Logo")
    @Story("Story: Logo Presence")
    @Step("Verifying log Presence")
    @Severity(SeverityLevel.MINOR)
    public void testLogoPresence() {
        loginPage = new SwagLabsLoginPage(driver);
        Assert.assertTrue(loginPage.checkLogo().isDisplayed(),
                "Logo is not presented in the website");
    }

    @Test(priority = 2)
    @Parameters({"username", "password"})
    @Description("Verify Login presence on Login Page")
    @Epic("EP001")
    @Feature("Feature2: Login")
    @Story("Story: Valid Login")
    @Step("Verifying login")
    @Severity(SeverityLevel.BLOCKER)
    public void testLogin(String username, String password) throws InterruptedException {
        loginPage = new SwagLabsLoginPage(driver);
        loginPage.loginToPage(username, password);
        sleep(5000);
       /* Assert.assertTrue(loginPage.getShoppingCartLogo().isDisplayed(),
                "Shopping cart logo is not displayed");*/
        Assert.assertEquals(driver.getTitle(), "Labib Labs",
                "Title is not equal to " + driver.getTitle());
    }

    @Test(priority = 3)
    @Description("Verify User registration")
    @Epic("EP001")
    @Feature("Feature3: Registration")
    @Story("Story: User Registration")
    @Severity(SeverityLevel.NORMAL)
    public void registrationTest() {
        throw new SkipException("Skipping this test");
    }
}
