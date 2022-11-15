package um;

import Task1ScreenScrape.ScrapeElements;
import Task1ScreenScrape.ScreenScraper;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Tests {
    WebDriver ChromeDriver;
    ScreenScraper scraper = new ScreenScraper();
    ScrapeElements ScrapeElements = new ScrapeElements();
    final String PRODUCTSEARCH = "gaming chair";

    @BeforeEach
    public void testOpenDriver() throws Exception {
        System.setProperty("webdriver.chrome.driver","/Users/mikemicallef/Desktop/webdriver/chromedriver");
        ChromeDriver = new ChromeDriver();

        //NAVIGATE TO WEBSITE
        ChromeDriver.get("https://www.atoz.com.mt/");
        ChromeDriver.switchTo().activeElement().sendKeys(Keys.TAB);
        ChromeDriver.switchTo().activeElement().sendKeys(Keys.TAB);
        ChromeDriver.switchTo().activeElement().sendKeys(Keys.ENTER);

    //test
        String title = ChromeDriver.getTitle();
        Assertions.assertEquals("Malta's Choice for Appliances & Gadgets | AtoZ Electronics",title);

    }



    //Test to check if getProductHeading function works
    @Test
    public void testGetItemName() throws Exception{
        ScrapeElements.search(ChromeDriver, PRODUCTSEARCH);
        ScrapeElements.getItemName(ChromeDriver);
        //test
        Assert.assertNotEquals(null,ChromeDriver);
    }

    @Test //Get Product Description
    public void testGetItemDescription() throws Exception{
        ScrapeElements.search(ChromeDriver, PRODUCTSEARCH);
        ScrapeElements.getItemDetails(ChromeDriver);
        //test
        Assert.assertNotEquals(null,ScrapeElements.getItemDetails(ChromeDriver));
    }

    @Test //Get Product Price
    public void testGetItemPrices() throws Exception{
        ScrapeElements.search(ChromeDriver, PRODUCTSEARCH);
        ScrapeElements.getItemPrice(ChromeDriver);
        //test
        Assert.assertNotEquals(null,ScrapeElements.getItemPrice(ChromeDriver));

    }

    @Test //Get Image url
    public void testGetItemImgUrl() throws Exception{
        ScrapeElements.search(ChromeDriver, PRODUCTSEARCH);
        ScrapeElements.getItemPic(ChromeDriver);
        //test
        Assert.assertNotEquals(null,ScrapeElements.getItemPic(ChromeDriver));
    }


    @Test //Get Product Link
    public void testGetItemLink() throws Exception{
        ScrapeElements.search(ChromeDriver, PRODUCTSEARCH);
        ScrapeElements.getItemURL(ChromeDriver);
    //test
        Assert.assertNotEquals(null,ScrapeElements.getItemURL(ChromeDriver));
    }

    @Test //Post Alert test
    public void testPostAlert() throws Exception{
        int statusCode;
        statusCode = scraper.ScreenScraper(ChromeDriver);
        //test
        Assertions.assertEquals(201,statusCode);
    }

    @Test //remove Alert
    public void testRemoveAlert() throws Exception{
        ScrapeElements.DeleteAlert();
    }

    //close browser
    @AfterEach
    public void teardown(){
        ChromeDriver.quit();
    }

}
