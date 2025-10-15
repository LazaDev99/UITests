package UI.test.asserts;

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
        Assert.assertTrue(checkBoxLabel.isDisplayed(), "Checkbox label isn't displayed");

        softAssert.assertAll();
    }
}
