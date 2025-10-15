package UI.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHelper
{
    public static void captureScreenshot(WebDriver driver, String testName)
    {
        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        //take screenshot
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        //path to destination
        String filename = "screenshots/" + testName + "_" + time + ".png";
        File destinationPath = new File(filename);

        //create file if it's not created
        destinationPath.getParentFile().mkdirs();

        try
        {
            Files.copy(src.toPath(), destinationPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved");
        }
        catch (IOException e)
        {
            System.err.println("Screenshot failed");
        }
    }
}
