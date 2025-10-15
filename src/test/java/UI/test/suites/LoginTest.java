package UI.test.suites;

import UI.constants.ConstValues;
import UI.pages.LoginPage;
import UI.utils.ScreenshotHelper;
import environment.ConfigProvider;
import jdk.jfr.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class LoginTest
{
    WebDriver driver;
    String successfulLoginID;
    LoginPage loginPage;

    @BeforeMethod
    public void prepareData()
    {
        //driver setup
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(ConfigProvider.getLoginTestUrl());

        //config setup
        successfulLoginID = ConfigProvider.getLoginTestResponseID();

        //login setup
        loginPage = new LoginPage(driver);
    }

    @Test
    @Description("Verify login with valid credentials")
    public void loginWithValidCredentials()
    {
        //login to the page
        loginPage.login(ConstValues.LOGIN_USERNAME, ConstValues.LOGIN_PASSWORD);

        //get login message
        WebElement message = driver.findElement(By.id(successfulLoginID));
        String messageText = message.getText();

        //login assert
        Assert.assertTrue(messageText.contains(ConstValues.LOGIN_ASSERT_MESSAGE_SUCCESSFUL), "Success message not displayed");
    }

    @Test
    @Description("Verify login with invalid credentials and take screenshot when test fail")
    public void loginWithInvalidCredentials()
    {
        //login to the page
        loginPage.login(ConstValues.LOGIN_INVALID_USERNAME, ConstValues.LOGIN_INVALID_PASSWORD);

        //get login message
        WebElement message = driver.findElement(By.id(successfulLoginID));
        String messageText = message.getText();

        //login assert
        Assert.assertTrue(messageText.contains(ConstValues.LOGIN_ASSERT_MESSAGE_SUCCESSFUL) || messageText.contains("Your password is invalid!"), "Expected error message not displayed");
    }

    @AfterMethod
    public void quitDriver(ITestResult result)
    {
        //take a screenshot if test fail
        if (ITestResult.FAILURE == result.getStatus())
            ScreenshotHelper.captureScreenshot(driver, result.getName());

        if (driver != null)
            driver.quit();
    }
}
