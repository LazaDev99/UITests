package UI.test.suites;

import environment.ConfigProvider;
import jdk.jfr.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.time.Duration;

public class WaitForButtonTest
{
    WebDriver driver;
    String buttonId;

    @BeforeMethod
    public void prepareData()
    {
        //driver setup
        driver = new EdgeDriver();
        driver.get(ConfigProvider.getButtonTestUrl());
        driver.manage().window().maximize();

        //button setup
        buttonId = ConfigProvider.getButtonID();
    }

    @Test
    @Description("Verify button to be clickable")
    public void waitForButtonToBeClickableAndClick()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id(buttonId)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        button.click();
    }

    @AfterMethod
    public void quitDriver()
    {
        if (driver != null)
            driver.quit();
    }
}
