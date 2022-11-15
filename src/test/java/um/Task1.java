package um;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Task1 {

    WebDriver ChromeDriver;
    String API = "https://api.marketalertum.com/Alert";
    int TOT_PRODUCTS = 5;
    String USER_ID = "548ac34f-7c6d-491f-99ec-5387bf312e84";
    final String ITEM = "Gaming Chair";

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver","/Users/mikemicallef/Desktop/webdriver/chromedriver");
        ChromeDriver = new ChromeDriver();

        ChromeDriver.get("https://www.atoz.com.mt/");
        ChromeDriver.switchTo().activeElement().sendKeys(Keys.TAB);
        ChromeDriver.switchTo().activeElement().sendKeys(Keys.TAB);
        ChromeDriver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    @AfterEach
    public void teardown() {
        ChromeDriver.quit();
    }

    @Test
    public void screenScraper() throws Exception {

        WebElement searchField = ChromeDriver.findElement(By.name("Search"));
        searchField.sendKeys(ITEM);

        ChromeDriver.switchTo().activeElement().sendKeys(Keys.ENTER);
        Thread.sleep(5000);

//Get All Products into a List
        List<WebElement> productHeading = ChromeDriver.findElements(By.cssSelector("a[class='v-product__title productnamecolor colors_productname']"));
        List<WebElement> productPriceList = ChromeDriver.findElements(By.cssSelector("div[class='product_productprice']"));
        Assertions.assertTrue(productPriceList.size() > 0);
        List<WebElement> productImageUrl = ChromeDriver.findElements(By.cssSelector("a[class='v-product__img']"));
        Assertions.assertTrue(productImageUrl.size() > 0);
        List<WebElement> productDescription = ChromeDriver.findElements(By.cssSelector("a[class='v-product__title productnamecolor colors_productname']"));
        Assertions.assertTrue(productDescription.size() > 0);
        List<WebElement> productLink = ChromeDriver.findElements(By.cssSelector("a[class='v-product__title productnamecolor colors_productname']"));
        Assertions.assertTrue(productLink.size() > 0);

        //electronics store
        int alertType = 6;
        //Declaring ARRAYS to store product details
        String[] productNames = new String[TOT_PRODUCTS];
        String[] heading = new String[TOT_PRODUCTS];
        String[] urls = new String[TOT_PRODUCTS];
        int[] prices = new int[TOT_PRODUCTS];
        String[] descriptions = new String[TOT_PRODUCTS];
        String[] imagesUrls = new String[TOT_PRODUCTS];



        for (int c = 0; c < TOT_PRODUCTS; c++) {
            productNames[c] = productHeading.get(c).getText();
            heading[c] = productNames[c];
            urls[c] = productLink.get(c).getAttribute("href");
            prices[c] = Integer.parseInt(productPriceList.get(c).getText().replace("Black Friday Price:","").replace("€", "").replace("Inc VAT","").replace(".", "").replace(" ",""));
            descriptions[c] = productDescription.get(c).getText().replace("\"", "");
            imagesUrls[c] = productImageUrl.get(c).getAttribute("src");


            //New Alert for a product
              HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\n" + "    \"alertType\": " + alertType + ",\n" + "    \"postedBy\": \"" + USER_ID + "\",\n" + "    \"heading\": \"" + heading[c] + "\",\n" + "    \"url\": \"" + urls[c]  + "\",\n" + "    \"priceInCents\": " + prices[c] + ",\n" + "    \"description\": \"" + descriptions[c] + "\",\n" + "    \"imageUrl\": \"" + imagesUrls[c] + "\"\n" + "    }\n"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            Assertions.assertEquals(201, response.statusCode());

        }
    }
}
