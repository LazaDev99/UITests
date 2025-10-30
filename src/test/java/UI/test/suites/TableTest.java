package UI.test.suites;

import UI.constants.ConstValues;
import UI.test.base.TestBase;
import environment.ConfigProvider;
import io.qameta.allure.Allure;
import jdk.jfr.Description;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class TableTest extends TestBase
{
    String tableCompanyXPath;

    @BeforeMethod(alwaysRun = true)
    public void prepareData()
    {
        //setup driver url
        driver.get(ConfigProvider.getTableTestUrl());

        //config setup
        tableCompanyXPath = ConfigProvider.getTableCompanyXpath();
    }

    @Test(groups = {"regression"})
    @Description("Verify company names and validate")
    public void extractCompanyNamesAndValidate()
    {
        Allure.step("INFO: Find company cells");
        List<WebElement> companyCells = driver.findElements(By.xpath(tableCompanyXPath));
        Allure.step("PASS: Cells are found");

        //go through all cells and get list of company names
        List<String> companyNames = new ArrayList<>();
        for (WebElement cell : companyCells)
        {
            companyNames.add(cell.getText().trim());
        }

        Allure.step("INFO: Assert company name");
        Assert.assertTrue(companyNames.contains(ConstValues.COMPANY_ERNEST_HANDEL), "company name isn't matching");
        Allure.step("PASS: Assert finished");
    }
}
