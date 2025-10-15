package UI.test.suites;

import UI.constants.ConstValues;
import environment.ConfigProvider;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TitleTest
{
    WebDriver driver;
    String url;

    @BeforeMethod
    public void prepareData()
    {
        driver = new EdgeDriver();
        url = ConfigProvider.getBasicPageUrl();
    }

    @Test
    @Description("Verify title name")
    public void getTitle()
    {
        driver.get(url);
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, ConstValues.EXAMPLE_DOMAIN_TITLE, "Page title didn't match");
    }

    @AfterMethod
    public void quitDriver()
    {
        if (driver != null)
            driver.quit();
    }
}
