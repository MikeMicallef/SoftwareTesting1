package API;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInApi {
    static WebDriver ChromeDriver;

    public LogInApi(WebDriver browser){
        LogInApi.ChromeDriver = browser;
    }
    //Logging into API
    public void logIn(String uniqueId) throws InterruptedException {
        By postedby = By.id("UserId");
        By LogInBTN = By.xpath("//a[@href='/Alerts/Login']");

        ChromeDriver.findElement(LogInBTN).click();
        ChromeDriver.findElement(postedby).sendKeys(uniqueId);
        ChromeDriver.findElement(postedby).submit();
    }
}
