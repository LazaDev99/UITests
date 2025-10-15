package UI.suites;

import UI.constants.ConstValues;
import environment.ConfigProvider;
import jdk.jfr.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class TableTest
{
    WebDriver driver;
    String tableCompanyXPath;

    @BeforeMethod
    public void prepareData()
    {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(ConfigProvider.getTableTestUrl());

        tableCompanyXPath = ConfigProvider.getTableCompanyXpath();
    }

    @Test
    @Description("Verify company names and validate")
    public void extractCompanyNamesAndValidate()
    {
        List<WebElement> companyCells = driver.findElements(By.xpath(tableCompanyXPath));

        List<String> companyNames = new ArrayList<>();
        for (WebElement cell : companyCells)
        {
            companyNames.add(cell.getText().trim());
        }

        Assert.assertTrue(companyNames.contains(ConstValues.COMPANY_ERNEST_HANDEL), "company name isn't matching");
    }

    @AfterMethod
    public void quitDriver()
    {
        if (driver != null)
            driver.quit();
    }
}
