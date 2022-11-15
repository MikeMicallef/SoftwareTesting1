package API;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertsManager {
    static WebDriver ChromeDriver;

    public AlertsManager(WebDriver browser){
        AlertsManager.ChromeDriver = browser;
    }

    public boolean CheckLogIn(){ //Making Sure user is logged in by searching for log out button
        By logOutBtn = By.xpath("//a[@href='/Home/Logout']");
        boolean loggedIn= ChromeDriver.findElements(logOutBtn).size() != 0;
        return loggedIn;
    }

    public int AlertsNum(){ // Number of Alerts
        return ChromeDriver.findElements(By.xpath("//table")).size();
    }

    public String getIcon(int index){ //Fetch Icon for Alert
        System.out.println(ChromeDriver.findElement(By.xpath("//table[" + (index+1) + "]//tr[1]//td//h4//img")).getAttribute("src").substring(7));
        return ChromeDriver.findElement(By.xpath("//img[contains(@src,'icon')]")).getAttribute("src").substring(7);
    }

    public String getHeading(){ // Fetching Alert Name
        return ChromeDriver.findElement(By.xpath("//h4")).getText();
    }

    public String getDescription(){ // Fetching Alert details
        return ChromeDriver.findElement(By.xpath("//div[@class='container']/main//table//tbody//following::tr[3]/td")).getText();
    }

    public String getImage(){ //Fetching Alert Image
        return ChromeDriver.findElement(By.xpath("//td[@rowspan='4']/img")).getAttribute("src");
    }

    public String getLink(){ // Fetching Alert Item Link
        return ChromeDriver.findElement(By.linkText("Visit item")).getAttribute("href");
    }

    public String getPrice(){ // Fetching Alert Price
        return ChromeDriver.findElement(By.xpath("//tr[3]")).getText();
    }

    public void reloadPage(){
        ChromeDriver.navigate().refresh();
    } //Page refresh

}
