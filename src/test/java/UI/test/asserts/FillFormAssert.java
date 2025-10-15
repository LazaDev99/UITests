package UI.test.asserts;

import UI.constants.ConstValues;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class FillFormAssert
{
    public SoftAssert softAssert = new SoftAssert();

    public void assertFillFormWithValidData(WebElement name, WebElement email, WebElement currentAddress, WebElement permanentAddress)
    {
        softAssert.assertTrue(name.getText().contains(ConstValues.FILL_FORM_FULL_NAME), " didn't match");
        softAssert.assertTrue(email.getText().contains(ConstValues.FILL_FORM_EMAIL), "Email didn't match");
        softAssert.assertTrue(currentAddress.getText().contains(ConstValues.FILL_FORM_CURRENT_ADDRESS), "Current address didn't match");
        softAssert.assertTrue(permanentAddress.getText().contains(ConstValues.FILL_FORM_PERMANENT_ADDRESS), "Permanent address didn't match");

        softAssert.assertAll();
    }
}
