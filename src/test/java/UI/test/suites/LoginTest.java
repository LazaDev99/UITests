package UI.test.suites;

import UI.constants.ConstValues;
import UI.test.base.TestBase;
import UI.utils.ScreenshotHelper;
import environment.ConfigProvider;
import io.qameta.allure.Allure;
import jdk.jfr.Description;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;


public class LoginTest extends TestBase
{
    String successfulLoginID;

    @BeforeMethod(alwaysRun = true)
    public void prepareData()
    {
        //setup driver url
        driver.get(ConfigProvider.getLoginTestUrl());

        successfulLoginID = ConfigProvider.getLoginTestSuccessfullID();
    }

    @Test(groups = {"smoke","regression","integration"})
    @Description("Verify login with valid credentials")
    public void loginWithValidCredentials()
    {
        Allure.step("INFO: Login user with valid data");
        login(ConstValues.LOGIN_USERNAME, ConstValues.LOGIN_PASSWORD);
        Allure.step("PASS: Login finished");

        Allure.step("INFO: Find response text");
        WebElement message = driver.findElement(By.id(successfulLoginID));
        String messageText = message.getText();
        Allure.step("PASS: Response text found");

        Allure.step("INFO: Assert login");
        Assert.assertTrue(messageText.contains("You logged into a secure area!"), "Success message not displayed");
        Allure.step("PASS: Assert finished");
    }

    @Test(groups = {"regression", "knownBugs"})
    @Description("Verify login with invalid credentials")
    public void loginWithInvalidCredentials()
    {
        Allure.step("INFO: Login user with invalid data");
        login(ConstValues.LOGIN_INVALID_USERNAME, ConstValues.LOGIN_INVALID_PASSWORD);
        Allure.step("PASS: Login finished");

        Allure.step("INFO: Find response text");
        WebElement message = driver.findElement(By.id(successfulLoginID));
        String messageText = message.getText();
        Allure.step("PASS: Response text found");

        Allure.step("INFO: Assert login");
        Assert.assertTrue(messageText.contains("Changed for screenshot test") || messageText.contains("Your password is invalid!"), "Expected error message not displayed");
        Allure.step("PASS: Assert finished");
    }

    @AfterMethod(alwaysRun = true)
    public void takeScreenshot(ITestResult result)
    {
        if (ITestResult.FAILURE == result.getStatus())
        {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(result.getName() + " - Screenshot", new ByteArrayInputStream(screenshot));
        }
    }

    public void login(String username, String password)
    {
        WebElement userField = driver.findElement(By.id("username"));
        WebElement passField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        userField.clear();
        userField.sendKeys(username);

        passField.clear();
        passField.sendKeys(password);

        loginButton.click();
    }
}
