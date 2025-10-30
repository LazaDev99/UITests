package UI.test.suites;

import UI.constants.ConstValues;
import UI.test.asserts.CheckBoxAssert;
import UI.test.base.TestBase;
import environment.ConfigProvider;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class CheckBoxTest extends TestBase
{
    CheckBoxAssert softAssert;
    String checkBoxLabelCss, checkBoxInputId;

    @BeforeMethod(alwaysRun = true)
    public void prepareData()
    {
        //setup driver url
        driver.get(ConfigProvider.getCheckBoxTestUrl());

        //assert setup
        softAssert = new CheckBoxAssert();

        //checkBox config setup
        checkBoxInputId = ConfigProvider.getCheckBoxIputID();
        checkBoxLabelCss = ConfigProvider.getCheckBoxLabelCSS();
    }

    @Test(groups = {"regression"})
    public void verifyCheckboxStates()
    {
        //find elements
        Allure.step("INFO: Find checkbox elements");
        WebElement checkboxLabel = driver.findElement(By.cssSelector(checkBoxLabelCss));
        WebElement checkboxInput = driver.findElement(By.id(checkBoxInputId));
        Allure.step("PASS: Elements are found");

        //soft assert before click
        Allure.step("INFO: Assert before click");
        softAssert.assertCheckBox(checkboxLabel, checkboxInput);
        Allure.step("PASS: Assert before click finished");

        //click
        Allure.step("INFO: Click");
        ((JavascriptExecutor) driver).executeScript(ConstValues.SCROLL_CONST, checkboxLabel);
        checkboxLabel.click();
        Allure.step("PASS: Click is finished");

        //assert after click
        Allure.step("INFO: Assert after click");
        Assert.assertTrue(checkboxInput.isSelected(), "Checkbox was not selected after click");
        Allure.step("PASS: Assert after click is finished");
    }
}
