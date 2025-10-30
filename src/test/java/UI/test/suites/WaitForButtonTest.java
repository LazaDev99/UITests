package UI.test.suites;

import UI.constants.ConstValues;
import UI.test.base.TestBase;
import environment.ConfigProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.time.Duration;

public class WaitForButtonTest extends TestBase
{
    String buttonId;

    @BeforeMethod(alwaysRun = true)
    public void prepareData()
    {
        //setup driver url
        driver.get(ConfigProvider.getButtonTestUrl());

        buttonId = ConfigProvider.getButtonID();
    }

    @Test(groups = {"regression", "integration"})
    @Description("Verify button to be clickable")
    public void waitForButtonToBeClickableAndClick()
    {
        Allure.step("INFO: Wait for button to be clickable");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id(buttonId)));
        Allure.step("PASS: Button is clickable");
        ((JavascriptExecutor) driver).executeScript(ConstValues.SCROLL_CONST, button);

        Allure.step("INFO: Click button");
        button.click();
        Allure.step("PASS: Button clicked");
    }
}
