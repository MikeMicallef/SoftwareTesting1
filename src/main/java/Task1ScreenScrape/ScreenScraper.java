package Task1ScreenScrape;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ScreenScraper {

    WebDriver driver;

    public int ScreenScraper(WebDriver ChromeDriver) throws Exception {
        ScrapeElements scraper = new ScrapeElements();
        this.driver = ChromeDriver;
        //my user id
        final String USER_ID = "548ac34f-7c6d-491f-99ec-5387bf312e84";
        //API
        final String API = "https://api.marketalertum.com/Alert";
        //Product Search item
        final String ITEM = "Gaming Chair";

        scraper.search(ChromeDriver, ITEM);

       // Fetching Store item name, details, price, picture & URL
        List<WebElement> itemNames = scraper.getItemName(ChromeDriver);
        List<WebElement> ItemDesc= scraper.getItemDetails(ChromeDriver);
        List<WebElement> ItemPrices = scraper.getItemPrice(ChromeDriver);
        List<WebElement> ItemPics = scraper.getItemPic(ChromeDriver);
        List<WebElement> ItemURLs = scraper.getItemURL(ChromeDriver);

        final int TOT_PRODUCTS = 5; //Tot number of products
        int STATUS = 0;
        final int ALERT_TYPE = 6; //Alert type is 6 since AToZ is an electronics store

    //Looping through Products
        for (int c = 0; c < TOT_PRODUCTS; c++) {

            String ItemName = itemNames.get(c).getText();
            String ItemURL = ItemURLs.get(c).getAttribute("href");
            int ItemPrice = Integer.parseInt(ItemPrices.get(c).getText().replace("Black Friday Price:", "").replace("€", "").replace("Inc VAT", "").replace(".", "").replace(" ", ""));
            String ItemDetails = ItemDesc.get(c).getText().replace("\"", "");
            String ItemPic = ItemPics.get(c).getAttribute("src");

            STATUS = scraper.postAlert(API,ALERT_TYPE,USER_ID,ItemName,ItemURL,ItemPrice,ItemDetails,ItemPic);


        }
        return STATUS;

    }
}


