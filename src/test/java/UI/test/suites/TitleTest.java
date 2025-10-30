package UI.test.suites;

import UI.constants.ConstValues;
import UI.test.base.TestBase;
import environment.ConfigProvider;
import io.qameta.allure.Allure;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.*;

public class TitleTest extends TestBase
{
    @BeforeMethod(alwaysRun = true)
    public void prepareData()
    {
        //setup driver url
        driver.get(ConfigProvider.getBasicPageUrl());
    }

    @Test(groups = {"smoke"})
    @Description("Verify title name")
    public void getTitle()
    {
        Allure.step("INFO: Get page title");
        String pageTitle = driver.getTitle();
        Allure.step("PASS: Page title found");

        Allure.step("INFO: Assert page title");
        Assert.assertEquals(pageTitle, ConstValues.EXAMPLE_DOMAIN_TITLE, "Page title didn't match");
        Allure.step("PASS: Assert finished");
    }
}
