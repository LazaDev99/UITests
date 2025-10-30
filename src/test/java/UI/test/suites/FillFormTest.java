package UI.test.suites;

import UI.constants.ConstValues;
import UI.test.asserts.FillFormAssert;
import UI.test.base.TestBase;
import environment.ConfigProvider;
import io.qameta.allure.Allure;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

public class FillFormTest extends TestBase
{
    String fullNameId, emailCssSelector, currentAddressXPath, permanentAddressXpath, submitCssSelector;
    String outputFullNameId, outputEmailCss, outputCurrentAddressXpath, outputPermanentAddressXpath;
    FillFormAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void prepareData()
    {
        //setup driver url
        driver.get(ConfigProvider.getFillFormUrl());


        //setup assertion
        softAssert = new FillFormAssert();

        //setup config data
        fullNameId = ConfigProvider.getFillFormFullNameID();
        emailCssSelector = ConfigProvider.getFillFormEmailCSS();
        currentAddressXPath = ConfigProvider.getFillFormCurrentAddressXPath();
        permanentAddressXpath = ConfigProvider.getFillFormPermanentAddressXPath();
        submitCssSelector = ConfigProvider.getFillFormSubmitCSS();

        //setup output config data
        outputFullNameId = ConfigProvider.getOutputFillFormFullNameID();
        outputEmailCss = ConfigProvider.getOutputFillFormEmailCSS();
        outputCurrentAddressXpath = ConfigProvider.getOutputFillFormCurrentAddressXPath();
        outputPermanentAddressXpath = ConfigProvider.getOutputFillFormPermanentAddressXPath();
    }

    @Test(groups = {"smoke","regression","integration"})
    @Description("Verify fill form with valid data")
    public void fillFormWithValidData()
    {
        Allure.step("INFO: Fill full name");
        WebElement fullName = driver.findElement(By.id(fullNameId));
        fullName.sendKeys(ConstValues.FILL_FORM_FULL_NAME);
        Allure.step("PASS: Full name filled");

        Allure.step("INFO: Fill email");
        WebElement email = driver.findElement(By.cssSelector(emailCssSelector));
        email.sendKeys(ConstValues.FILL_FORM_EMAIL);
        Allure.step("PASS: Email filled");


        Allure.step("INFO: Fill current address");
        WebElement currentAddress = driver.findElement(By.xpath(currentAddressXPath));
        currentAddress.sendKeys(ConstValues.FILL_FORM_CURRENT_ADDRESS);
        Allure.step("PASS: Current address filled");


        Allure.step("INFO: Fill permanent address");
        WebElement permanentAddress = driver.findElement(By.xpath(permanentAddressXpath));
        permanentAddress.sendKeys(ConstValues.FILL_FORM_PERMANENT_ADDRESS);
        Allure.step("PASS: Permanent address filled");


        Allure.step("INFO: Click on submit button");
        WebElement submitButton = driver.findElement(By.cssSelector(submitCssSelector));
        ((JavascriptExecutor) driver).executeScript(ConstValues.SCROLL_CONST, submitButton);
        submitButton.click();
        Allure.step("PASS: Submit button clicked");

        try { Thread.sleep(1000); }
        catch (InterruptedException ignored) {}


        // Validate output
        Allure.step("INFO: Find output elements");
        WebElement nameOutput = driver.findElement(By.id(outputFullNameId));
        WebElement emailOutput = driver.findElement(By.id(outputEmailCss));
        WebElement currentAddressOutput = driver.findElement(By.xpath(outputCurrentAddressXpath));
        WebElement permanentAddressOutput = driver.findElement(By.xpath(outputPermanentAddressXpath));
        Allure.step("PASS: All output elements are found");


        //assert
        Allure.step("INFO: Assert fill form");
        softAssert.assertFillFormWithValidData(nameOutput, emailOutput, currentAddressOutput, permanentAddressOutput);
        Allure.step("PASS: assert finished");
    }
}

//Also, we can create FillFormPage to create better approach.
