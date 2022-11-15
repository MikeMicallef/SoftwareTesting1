package Task1ScreenScrape;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ScrapeElements {

    //Searching For Product
    public void search(WebDriver ChromeDriver, String FindProducts) throws Exception{

        WebElement searchField = ChromeDriver.findElement(By.name("Search"));
        searchField.sendKeys(FindProducts);
        WebElement searchButton = ChromeDriver.findElement(By.name("Submit"));
        searchButton.submit();
        ChromeDriver.switchTo().activeElement().sendKeys(Keys.ENTER);
        Thread.sleep(1000);
    }


    //Waiting
    public static void wait(int sec) {
        try {
            Thread.sleep(sec*1000);
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
    }

    //Fetching Methods to Scrape and Post Alert onto API
    //public int  postAlert(String API,int alertType,String USER_ID,String ItemName, String ItemURL,int ItemPrice, String ItemDetails, String ItemPic)
    public List<WebElement> getItemName(WebDriver driver) throws InterruptedException {
        List<WebElement> ItemName = driver.findElements(By.cssSelector("a[class='v-product__title productnamecolor colors_productname']"));
        return ItemName;
    }
    public List<WebElement> getItemDetails(WebDriver driver) throws InterruptedException {
        List<WebElement> ItemDetails = driver.findElements(By.cssSelector("a[class='v-product__title productnamecolor colors_productname']"));
        return ItemDetails;
    }
    public List<WebElement> getItemURL(WebDriver driver) throws InterruptedException {
        List<WebElement> ItemURL = driver.findElements(By.cssSelector("a[class='v-product__title productnamecolor colors_productname']"));
        return ItemURL;
    }

    public List<WebElement> getItemPrice(WebDriver driver) throws InterruptedException {
        List<WebElement> ItemPrice = driver.findElements(By.cssSelector("div[class='product_productprice']"));
        return ItemPrice;
    }

    public List<WebElement> getItemPic(WebDriver driver) throws InterruptedException {
        List<WebElement> ItemPic = driver.findElements(By.cssSelector("a[class='v-product__img']"));
        return ItemPic;
    }

    //Post Alert
    public static int postAlert(String api, int alert_type, String user_id, String itemName, String itemURL, int itemPrice, String itemDetails, String itemPic) throws Exception{
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(api)).header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\n" + "    \"alertType\": " + alert_type + ",\n" + "    \"postedBy\": \"" + user_id + "\",\n" + "    \"heading\": \"" + itemName + "\",\n" + "    \"url\": \"" + itemURL + "\",\n" + "    \"priceInCents\": " + itemPrice + ",\n" + "    \"description\": \"" + itemDetails + "\",\n" + "    \"imageUrl\": \"" + itemPic + "\"\n" + "    }\n")).build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.statusCode());

        return response.statusCode();
    }
    //Delete Alert
    public String DeleteAlert() throws Exception{
        HttpClient client = HttpClient.newHttpClient();
        //Make a delete request to the server
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.marketalertum.com/Alert?userId=548ac34f-7c6d-491f-99ec-5387bf312e84"))
                .header("Content-Type", "application/json").DELETE().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }




}
