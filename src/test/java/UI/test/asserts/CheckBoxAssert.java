package UI.test.asserts;

import UI.constants.ConstValues;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class CheckBoxAssert
{
    public SoftAssert softAssert = new SoftAssert();

    public void assertCheckBox(WebElement checkBoxLabel, WebElement checkBoxInput)
    {
        Assert.assertTrue(checkBoxInput.isEnabled(), "Checkbox is not enabled");
        Assert.assertFalse(checkBoxInput.isSelected(), "Checkbox is already selected");

        softAssert.assertAll();
    }
}
