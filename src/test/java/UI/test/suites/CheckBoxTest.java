package UI.test.suites;

import UI.constants.ConstValues;
import UI.test.asserts.CheckBoxAssert;
import UI.test.asserts.FillFormAssert;
import environment.ConfigProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CheckBoxTest
{
    WebDriver driver;
    CheckBoxAssert softAssert;
    String checkBoxLabelCss, checkBoxInputId;

    @BeforeMethod
    public void prepareData()
    {
        //driver setup
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(ConfigProvider.getCheckBoxTestUrl());

        //assert setup
        softAssert = new CheckBoxAssert();

        //checkBox config setup
        checkBoxInputId = ConfigProvider.getCheckBoxIputID();
        checkBoxLabelCss = ConfigProvider.getCheckBoxLabelCSS();
    }

    @Test
    public void verifyCheckboxStates()
    {
        //find elements
        WebElement checkboxLabel = driver.findElement(By.cssSelector(checkBoxLabelCss));
        WebElement checkboxInput = driver.findElement(By.id(checkBoxInputId));

        //soft assert before click
        softAssert.assertCheckBox(checkboxLabel, checkboxInput);

        //click
        ((JavascriptExecutor) driver).executeScript(ConstValues.SCROLL_CONST, checkboxLabel);
        checkboxLabel.click();

        //assert after click
        Assert.assertTrue(checkboxInput.isSelected(), "Checkbox was not selected after click");
    }

    @AfterMethod
    public void quitDriver()
    {
        if (driver != null)
            driver.quit();
    }
}
