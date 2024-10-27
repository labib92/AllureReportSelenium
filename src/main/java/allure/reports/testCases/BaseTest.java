package allure.reports.testCases;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class BaseTest {
    protected static WebDriver driver;

    private static File takeScreenshot(WebDriver driver, String testName) {
        String screenshotPath;
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);

        try {
            screenshotPath = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_screenshot.png";
            FileUtils.copyFile(file, new File(screenshotPath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to capture screenshot " + e);
        }
        return file;
    }

    @BeforeClass
    protected void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    protected static synchronized void updateTestStatus(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            File screenshot = takeScreenshot(driver, result.getName());
            Allure.addAttachment("Page screenshot", FileUtils.openInputStream(screenshot));
        }
    }

    @AfterClass
    protected void tearDown() {
        driver.quit();
    }
}
