package UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage
{
    WebDriver driver;
    private static final String usernameId = "username";
    private static final String passwordId = "password";
    private static final String buttonCss = "button[type='submit']";

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void login (String username, String password)
    {
        driver.findElement(By.id(usernameId)).clear();
        driver.findElement(By.id(usernameId)).sendKeys(username);
        driver.findElement(By.id(passwordId)).clear();
        driver.findElement(By.id(passwordId)).sendKeys(password);

        driver.findElement(By.cssSelector(buttonCss)).click();
    }
}
