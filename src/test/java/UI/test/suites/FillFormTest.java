package UI.test.suites;

import UI.constants.ConstValues;
import UI.test.asserts.FillFormAssert;
import environment.ConfigProvider;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FillFormTest
{
    String fullNameId, emailCssSelector, currentAddressXPath, permanentAddressXpath, submitCssSelector;
    String outputFullNameId, outputEmailCss, outputCurrentAddressXpath, outputPermanentAddressXpath;
    WebDriver driver;
    FillFormAssert softAssert;

    @BeforeMethod
    public void prepareData()
    {
        //setup driver
        driver = new EdgeDriver();
        driver.get(ConfigProvider.getFillFormUrl());
        driver.manage().window().maximize();

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

    @Test
    @Description("Verify fill form with valid data")
    public void fillFormWithValidData()
    {
        WebElement fullName = driver.findElement(By.id(fullNameId));
        fullName.sendKeys(ConstValues.FILL_FORM_FULL_NAME);

        WebElement email = driver.findElement(By.cssSelector(emailCssSelector));
        email.sendKeys(ConstValues.FILL_FORM_EMAIL);

        WebElement currentAddress = driver.findElement(By.xpath(currentAddressXPath));
        currentAddress.sendKeys(ConstValues.FILL_FORM_CURRENT_ADDRESS);

        WebElement permanentAddress = driver.findElement(By.xpath(permanentAddressXpath));
        permanentAddress.sendKeys(ConstValues.FILL_FORM_PERMANENT_ADDRESS);

        WebElement submitButton = driver.findElement(By.cssSelector(submitCssSelector));
        ((JavascriptExecutor) driver).executeScript(ConstValues.SCROLL_CONST, submitButton);
        submitButton.click();

        try { Thread.sleep(1000); }
        catch (InterruptedException ignored) {}

        //get output elements
        WebElement nameOutput = driver.findElement(By.id(outputFullNameId));
        WebElement emailOutput = driver.findElement(By.id(outputEmailCss));
        WebElement currentAddressOutput = driver.findElement(By.xpath(outputCurrentAddressXpath));
        WebElement permanentAddressOutput = driver.findElement(By.xpath(outputPermanentAddressXpath));

        //assert output
        softAssert.assertFillFormWithValidData(nameOutput, emailOutput, currentAddressOutput, permanentAddressOutput);
    }

    @AfterMethod
    public void quitDriver()
    {
        if (driver != null)
            driver.quit();
    }
}

//Also, we can create FillFormPage to create better approach.
