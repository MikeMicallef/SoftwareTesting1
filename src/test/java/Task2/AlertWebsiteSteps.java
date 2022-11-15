package Task2;


import API.AlertsManager;
import API.ApiManager;
import API.LogInApi;
import Task1ScreenScrape.ScrapeElements;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertWebsiteSteps {

    protected static WebDriver ChromeDriver;
    protected AlertsManager AlertsManager;
    protected LogInApi loginPageObject;
    String validLogin = "548ac34f-7c6d-491f-99ec-5387bf312e84";
    protected ApiManager ApiManager;
    String testAlertJson;
    API.objects.api api;


    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/Users/mikemicallef/Desktop/webdriver/chromedriver");
        ChromeDriver = new ChromeDriver();
        ChromeDriver.get("https://www.marketalertum.com/");
        loginPageObject = new LogInApi(ChromeDriver);
        AlertsManager = new AlertsManager(ChromeDriver);
        ScrapeElements.wait(1);
    }


    @Given("I am a user of marketalertum$")
    public void iAmAUserOfMarketalertum() { Assertions.assertEquals("Home Page - MarketAlertUM", ChromeDriver.getTitle());
    }

    @Given("I am a user of marketalertumm$")
    public void iAmAUserOfMarketalertumm() { Assertions.assertEquals("- MarketAlertUM", ChromeDriver.getTitle());
    }

    @When("I login using valid credentials$")
    public void iLoginUsingValidCredentials() throws Exception{
        loginPageObject.logIn(validLogin);
    }

    @Then("I should see my alerts$")
    public void iShouldSeeMyAlerts() {
        Assertions.assertEquals("Latest alerts for Mike Micallef", ChromeDriver.findElement(By.xpath("//main[@class='pb-3']//h1")).getText());
    }

    @When("I login using invalid credentials$")
    public void iLoginUsingInvalidCredentials() throws Exception{
        loginPageObject.logIn("000-00-0");
    }

    @Then("I should see the login screen again$")
    public void iShouldSeeTheLoginScreenAgain() {
        Assertions.assertEquals("https://www.marketalertum.com/Alerts/Login", ChromeDriver.getCurrentUrl());
    }

    @Given("I am an administrator of the website and I upload (\\d+) alerts$")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int noOfAlerts) throws Exception {
        loginPageObject.logIn(validLogin);
        assert(AlertsManager.CheckLogIn());
        ScrapeElements.wait(2);
        ApiManager = new ApiManager();

        switch(noOfAlerts){
            case 1:
                int Status1 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 1, "548ac34f-7c6d-491f-99ec-5387bf312e84", "MAZDA MX5 ", "URL", 10000, "MAZDA MX5 TA MALTA", "https://upload.wikimedia.org/wikipedia/commons/9/95/Mazda_Roadster_%28MX-5%29_by_Negawa_Bridge_%28cropped%29.jpg");
                Assert.assertEquals(201,Status1);
                break;
            case 2:
                Status1 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 1, "548ac34f-7c6d-491f-99ec-5387bf312e84", "MAZDA MX5 ", "URL", 10000, "MAZDA MX5 TA MALTA", "https://upload.wikimedia.org/wikipedia/commons/9/95/Mazda_Roadster_%28MX-5%29_by_Negawa_Bridge_%28cropped%29.jpg");
                Assert.assertEquals(201,Status1);
                int Status2 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 2, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Boat ", "URL", 230000, "Capelli Tempest", "https://www.cantiericapelli.com/nautica/wp-content/uploads/2020/09/TEMPEST-750-SPORT-11.jpg");
                Assert.assertEquals(201,Status2);
                break;
            case 3:
                Status1 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 1, "548ac34f-7c6d-491f-99ec-5387bf312e84", "MAZDA MX5 ", "URL", 10000, "MAZDA MX5 TA MALTA", "https://upload.wikimedia.org/wikipedia/commons/9/95/Mazda_Roadster_%28MX-5%29_by_Negawa_Bridge_%28cropped%29.jpg");
                Assert.assertEquals(201,Status1);
                Status2 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 2, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Boat ", "URL", 230000, "Capelli Tempest", "https://www.cantiericapelli.com/nautica/wp-content/uploads/2020/09/TEMPEST-750-SPORT-11.jpg");
                Assert.assertEquals(201,Status2);
                int Status3 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 3, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Rent ", "URL", 200000, "Rent an apartment in Xemxija", "https://images.pexels.com/photos/439391/pexels-photo-439391.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                Assert.assertEquals(201,Status3);
                break;
            case 4:
                Status1 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 1, "548ac34f-7c6d-491f-99ec-5387bf312e84", "MAZDA MX5 ", "URL", 10000, "MAZDA MX5 TA MALTA", "https://upload.wikimedia.org/wikipedia/commons/9/95/Mazda_Roadster_%28MX-5%29_by_Negawa_Bridge_%28cropped%29.jpg");
                Assert.assertEquals(201,Status1);
                Status2 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 2, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Boat ", "URL", 230000, "Capelli Tempest", "https://www.cantiericapelli.com/nautica/wp-content/uploads/2020/09/TEMPEST-750-SPORT-11.jpg");
                Assert.assertEquals(201,Status2);
                Status3 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 3, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Rent ", "URL", 200000, "Rent an apartment in Xemxija", "https://images.pexels.com/photos/439391/pexels-photo-439391.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                Assert.assertEquals(201,Status3);
                int Status4 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 4, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Buy ", "URL", 10000, "Buy house in Qormi", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgYzJvMy9qE2q2f7_VO8RLhpQe-NJb48p4ue9wWWcx&s");
                Assert.assertEquals(201,Status4);
                break;
            case 5:
                Status1 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 1, "548ac34f-7c6d-491f-99ec-5387bf312e84", "MAZDA MX5 ", "URL", 10000, "MAZDA MX5 TA MALTA", "https://upload.wikimedia.org/wikipedia/commons/9/95/Mazda_Roadster_%28MX-5%29_by_Negawa_Bridge_%28cropped%29.jpg");
                Assert.assertEquals(201,Status1);
                Status2 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 2, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Boat ", "URL", 230000, "Capelli Tempest", "https://www.cantiericapelli.com/nautica/wp-content/uploads/2020/09/TEMPEST-750-SPORT-11.jpg");
                Assert.assertEquals(201,Status2);
                Status3 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 3, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Rent ", "URL", 200000, "Rent an apartment in Xemxija", "https://images.pexels.com/photos/439391/pexels-photo-439391.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                Assert.assertEquals(201,Status3);
                Status4 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 4, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Buy ", "URL", 10000, "Buy house in Qormi", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgYzJvMy9qE2q2f7_VO8RLhpQe-NJb48p4ue9wWWcx&s");
                Assert.assertEquals(201,Status4);
                int Status5 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 5, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Toy ", "URL", 4500, "Buy a Toy Car", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVT6dYCNJxuM12c1yjUO4hbLVG_VqRjwos66BXgdy8BA&s");
                Assert.assertEquals(201,Status5);
                break;
            case 6:
                Status1 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 1, "548ac34f-7c6d-491f-99ec-5387bf312e84", "MAZDA MX5 ", "URL", 10000, "MAZDA MX5 TA MALTA", "https://upload.wikimedia.org/wikipedia/commons/9/95/Mazda_Roadster_%28MX-5%29_by_Negawa_Bridge_%28cropped%29.jpg");
                Assert.assertEquals(201,Status1);
                Status2 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 2, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Boat ", "URL", 230000, "Capelli Tempest", "https://www.cantiericapelli.com/nautica/wp-content/uploads/2020/09/TEMPEST-750-SPORT-11.jpg");
                Assert.assertEquals(201,Status2);
                Status3 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 3, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Rent ", "URL", 200000, "Rent an apartment in Xemxija", "https://images.pexels.com/photos/439391/pexels-photo-439391.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                Assert.assertEquals(201,Status3);
                Status4 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 4, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Buy ", "URL", 10000, "Buy house in Qormi", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgYzJvMy9qE2q2f7_VO8RLhpQe-NJb48p4ue9wWWcx&s");
                Assert.assertEquals(201,Status4);
                Status5 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 5, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Toy ", "URL", 4500, "Buy a Toy Car", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVT6dYCNJxuM12c1yjUO4hbLVG_VqRjwos66BXgdy8BA&s");
                Assert.assertEquals(201,Status5);
                int Status6 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 6, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Electronics ", "URL", 60000, "LG SMART TV", "https://vitelmalta.com/wp-content/uploads/wpsc/product_images/LG_32LQ630B6LA.jpeg");
                Assert.assertEquals(201,Status6);
                break;
            default:
                Status1 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 1, "548ac34f-7c6d-491f-99ec-5387bf312e84", "MAZDA MX5 ", "URL", 10000, "MAZDA MX5 TA MALTA", "https://upload.wikimedia.org/wikipedia/commons/9/95/Mazda_Roadster_%28MX-5%29_by_Negawa_Bridge_%28cropped%29.jpg");
                Assert.assertEquals(201,Status1);
                Status2 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 2, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Boat ", "URL", 230000, "Capelli Tempest", "https://www.cantiericapelli.com/nautica/wp-content/uploads/2020/09/TEMPEST-750-SPORT-11.jpg");
                Assert.assertEquals(201,Status2);
                Status3 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 3, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Rent ", "URL", 200000, "Rent an apartment in Xemxija", "https://images.pexels.com/photos/439391/pexels-photo-439391.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                Assert.assertEquals(201,Status3);
                Status4 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 4, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Buy ", "URL", 10000, "Buy house in Qormi", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgYzJvMy9qE2q2f7_VO8RLhpQe-NJb48p4ue9wWWcx&s");
                Assert.assertEquals(201,Status4);
                Status5 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 5, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Toy ", "URL", 4500, "Buy a Toy Car", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVT6dYCNJxuM12c1yjUO4hbLVG_VqRjwos66BXgdy8BA&s");
                Assert.assertEquals(201,Status5);
                Status6 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 6, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Electronics ", "URL", 60000, "LG SMART TV", "https://vitelmalta.com/wp-content/uploads/wpsc/product_images/LG_32LQ630B6LA.jpeg");
                Assert.assertEquals(201,Status6);
                System.out.println("Maximum  number of alert is 6");

        }
    }


    @When("I view a list of alerts$")
    public void iViewAListOfAlerts() {
        Assertions.assertEquals("Latest alerts for Mike Micallef", ChromeDriver.findElement(By.xpath("//main[@class='pb-3']//h1")).getText());
    }

    @Then("each alert should contain an icon$")
    public void eachAlertShouldContainAnIcon() {
        int alertCount = AlertsManager.AlertsNum();
        String[] AlertInclIcon = new String[alertCount];

        for(int i=0; i<alertCount; i++){
            AlertInclIcon[i] = AlertsManager.getIcon(i);
            Assertions.assertNotEquals(AlertInclIcon[i],null);
        }
    }

    @And("each alert should contain a heading$")
    public void eachAlertShouldContainAHeading() {
        int alertCount = AlertsManager.AlertsNum();
        String[] AlertInclHeading = new String[alertCount];

        for (int i = 0; i < alertCount; i++) {
            AlertInclHeading[i] = AlertsManager.getHeading();
            Assertions.assertNotEquals(AlertInclHeading[i],null);
        }
    }

    @And("each alert should contain a description$")
    public void eachAlertShouldContainADescription() {
        int alertCount = AlertsManager.AlertsNum();
        String[] alertDescription = new String[alertCount];

        for (int i = 0; i < alertCount; i++) {
            alertDescription[i] = AlertsManager.getDescription();
            Assertions.assertNotEquals(alertDescription[i],null);
        }
    }

    @And("each alert should contain an image$")
    public void eachAlertShouldContainAnImage() {
        int alertCount = AlertsManager.AlertsNum();
        String[] AlertInclImage = new String[alertCount];

        for (int i = 0; i < alertCount; i++) {
            AlertInclImage[i] = AlertsManager.getImage();
            Assertions.assertNotEquals(AlertInclImage[i],null);
        }
    }

    @And("each alert should contain a price$")
    public void eachAlertShouldContainAPrice() {
        int alertCount = AlertsManager.AlertsNum();
        String[] AlertInclPrice = new String[alertCount];

        for (int i = 0; i < alertCount; i++) {
            AlertInclPrice[i] = AlertsManager.getPrice();
            Assertions.assertNotEquals(AlertInclPrice[i],null);

        }
    }

    @And("each alert should contain a link to the original product website$")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {
        int alertCount = AlertsManager.AlertsNum();
        String[] AlertInclLink = new String[alertCount];

        for (int i = 0; i < alertCount; i++) {
            AlertInclLink[i] = AlertsManager.getLink();
            Assertions.assertNotEquals(AlertInclLink[i],null);
        }

    }

    @Given("I am an administrator of the website and I upload more than 5 alerts$")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts() throws Exception {
        loginPageObject.logIn(validLogin);
        ApiManager = new ApiManager();
        api = Mockito.mock(API.objects.api.class);
        Mockito.when(api.getApi()).thenReturn(api.AVAILABLE);
        ApiManager.setapistatus(api);

        int Status1 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 1, "548ac34f-7c6d-491f-99ec-5387bf312e84", "MAZDA MX5 ", "URL", 10000, "MAZDA MX5 TA MALTA", "https://upload.wikimedia.org/wikipedia/commons/9/95/Mazda_Roadster_%28MX-5%29_by_Negawa_Bridge_%28cropped%29.jpg");
        Assert.assertEquals(201,Status1);
        int Status2 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 2, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Boat ", "URL", 230000, "Capelli Tempest", "https://www.cantiericapelli.com/nautica/wp-content/uploads/2020/09/TEMPEST-750-SPORT-11.jpg");
        Assert.assertEquals(201,Status2);
        int Status3 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 3, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Rent ", "URL", 200000, "Rent an apartment in Xemxija", "https://images.pexels.com/photos/439391/pexels-photo-439391.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        Assert.assertEquals(201,Status3);
        int Status4 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 4, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Buy ", "URL", 10000, "Buy house in Qormi", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgYzJvMy9qE2q2f7_VO8RLhpQe-NJb48p4ue9wWWcx&s");
        Assert.assertEquals(201,Status4);
        int Status5 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 5, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Toy ", "URL", 4500, "Buy a Toy Car", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVT6dYCNJxuM12c1yjUO4hbLVG_VqRjwos66BXgdy8BA&s");
        Assert.assertEquals(201,Status5);
        int Status6 = ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 6, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Electronics ", "URL", 60000, "LG SMART TV", "https://vitelmalta.com/wp-content/uploads/wpsc/product_images/LG_32LQ630B6LA.jpeg");
        Assert.assertEquals(201,Status6);


    }

    @When("I view a list of alerts I should see 5 alerts$")
    public void iViewAListOfAlertsIShouldSeeAlerts() {
        Assertions.assertEquals(5, AlertsManager.AlertsNum());
    }

    @Given("I am an administrator of the website and I upload an alert of type {int}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfTypeAlertType(int alertType) throws Exception {
        loginPageObject.logIn(validLogin);
        ApiManager = new ApiManager();
        api = Mockito.mock(API.objects.api.class);
        Mockito.when(api.getApi()).thenReturn(api.AVAILABLE);
        ApiManager.setapistatus(api);

        ApiManager = new ApiManager();
        switch(alertType){
            case 1:
                ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 1, "548ac34f-7c6d-491f-99ec-5387bf312e84", "MAZDA MX5 ", "URL", 10000, "MAZDA MX5 TA MALTA", "https://upload.wikimedia.org/wikipedia/commons/9/95/Mazda_Roadster_%28MX-5%29_by_Negawa_Bridge_%28cropped%29.jpg");
                break;
            case 2:
                ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 2, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Boat ", "URL", 230000, "Capelli Tempest", "https://www.cantiericapelli.com/nautica/wp-content/uploads/2020/09/TEMPEST-750-SPORT-11.jpg");
                break;
            case 3:
                ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 3, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Rent ", "URL", 200000, "Rent an apartment in Xemxija", "https://images.pexels.com/photos/439391/pexels-photo-439391.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                break;
            case 4:
                ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 4, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Buy ", "URL", 10000, "Buy house in Qormi", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgYzJvMy9qE2q2f7_VO8RLhpQe-NJb48p4ue9wWWcx&s");
                break;
            case 5:
                ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 5, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Toy ", "URL", 4500, "Buy a Toy Car", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVT6dYCNJxuM12c1yjUO4hbLVG_VqRjwos66BXgdy8BA&s");
                break;
            default:
                ScrapeElements.postAlert("https://api.marketalertum.com/Alert", 6, "548ac34f-7c6d-491f-99ec-5387bf312e84", "Electronics ", "URL", 60000, "LG SMART TV", "https://vitelmalta.com/wp-content/uploads/wpsc/product_images/LG_32LQ630B6LA.jpeg");

                break;
        }

        api = Mockito.mock(API.objects.api.class);
        Mockito.when(api.getApi()).thenReturn(API.objects.api.AVAILABLE);
        ApiManager.setapistatus(api);

    }

    @Then("I should see (\\d+) alerts$")
    public void iShouldSeeAlerts(int arg0) {
        Assertions.assertNotEquals(0, AlertsManager.AlertsNum());
    }

    @Then("I should get an icon name with icon-car.png")
    public void i_should_get_an_icon_name_with_icon_car_png() {
        AlertsManager.reloadPage();
        String iconType = AlertsManager.getIcon(0);
        Assert.assertTrue(iconType.contains("icon-car.png"));
    }

    @Then("I should get an icon name with icon-boat.png")
    public void i_should_get_an_icon_name_with_icon_boat_png() {
        AlertsManager.reloadPage();
        String iconType = AlertsManager.getIcon(1);
        Assert.assertTrue(iconType.contains("icon-boat.png"));
    }

    @Then("I should get an icon name with icon-property-rent.jpg")
    public void i_should_get_an_icon_name_with_icon_property_rent_jpg() {
        AlertsManager.reloadPage();
        String iconType = AlertsManager.getIcon(4);
        Assert.assertTrue(iconType.contains("icon-property-rent.jpg"));
    }

    @Then("I should get an icon name with icon-property-sale.jpg")
    public void i_should_get_an_icon_name_with_icon_property_sale_jpg() {
        AlertsManager.reloadPage();
        String iconType = AlertsManager.getIcon(3);
        Assert.assertTrue(iconType.contains("icon-property-sale.jpg"));
    }

    @Then("I should get an icon name with icon-toys.png")
    public void i_should_get_an_icon_name_with_icon_toys_png() {
        AlertsManager.reloadPage();
        String iconType = AlertsManager.getIcon(2);
        Assert.assertTrue(iconType.contains("icon-toys.png"));
    }

    @Then("I should get an icon name with icon-electronics.png")
    public void i_should_get_an_icon_name_with_icon_electronics_png() {
        AlertsManager.reloadPage();
        String iconType = AlertsManager.getIcon(1);
        Assert.assertTrue(iconType.contains("icon-electronics.png"));
    }

    @After
    public void tearDown() {
        ChromeDriver.quit();
    }

    @AfterEach
    public void tearDownApi(){
        ApiManager = null;
        testAlertJson = null;
    }

}

