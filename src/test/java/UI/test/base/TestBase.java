package UI.test.base;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.io.ByteArrayInputStream;

public class TestBase
{
    public WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void prepareDataForAllClasses()
    {
        //driver setup
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void quitDriver()
    {
        if (driver != null)
            driver.quit();
    }
}
