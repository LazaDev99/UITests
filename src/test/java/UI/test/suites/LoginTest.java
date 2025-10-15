package UI.test.suites;

import UI.constants.ConstValues;
import UI.utils.ScreenshotHelpper;
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

    @BeforeMethod
    public void prepareData()
    {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(ConfigProvider.getLoginTestUrl());

        successfulLoginID = ConfigProvider.getLoginTestSuccessfullID();
    }

    @Test
    @Description("Verify login with valid credentials")
    public void loginWithValidCredentials()
    {
        login(ConstValues.LOGIN_USERNAME, ConstValues.LOGIN_PASSWORD);

        WebElement message = driver.findElement(By.id(successfulLoginID));
        String messageText = message.getText();

        Assert.assertTrue(messageText.contains("You logged into a secure area!"), "Success message not displayed");
    }

    @Test
    @Description("Verify login with invalid credentials")
    public void loginWithInvalidCredentials()
    {
        login(ConstValues.LOGIN_INVALID_USERNAME, ConstValues.LOGIN_INVALID_PASSWORD);

        WebElement message = driver.findElement(By.id(successfulLoginID));
        String messageText = message.getText();

        Assert.assertTrue(messageText.contains("Changed for screenshot test") || messageText.contains("Your password is invalid!"), "Expected error message not displayed");
    }

    //Helper method to fill and submit the login form
    //This helper method should be created outside this class!!!!!!!!!!!!!
    private void login(String username, String password)
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

    @AfterMethod
    public void quitDriver(ITestResult result)
    {
        if (ITestResult.FAILURE == result.getStatus())
        {
            ScreenshotHelpper.captureScreenshot(driver, result.getName());
        }

        if (driver != null)
            driver.quit();
    }
}
