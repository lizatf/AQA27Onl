import configuration.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import services.BrowsersService;

public class LocatorsTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new BrowsersService().getDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void locatorsTests() throws InterruptedException {
        driver.get(ReadProperties.getUrl());

        //По id
        WebElement userName = driver.findElement(By.id("user-name"));
        //По name
        WebElement password = driver.findElement(By.name("password"));
        //По className
        WebElement loginButton = driver.findElement(By.className("submit-button"));

        userName.sendKeys(ReadProperties.username());
        password.sendKeys(ReadProperties.password());
        Thread.sleep(2000);
        loginButton.click();

        //По tagName
        WebElement select = driver.findElement(By.tagName("select"));
        //По linkText
        WebElement link = driver.findElement(By.linkText("Twitter"));
        link.isEnabled();

        //xpath
        //По атрибуту
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='title']")).isDisplayed());
        //По тексту
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Swag Labs']")).isDisplayed());
        //По parent
        driver.findElement(By.xpath("//select/parent::span")).isEnabled();
        //По ancestor
        Assert.assertTrue(driver.findElement(By.xpath("//a/ancestor::footer")).isEnabled());
        //По descendant
        Assert.assertTrue(driver.findElement(By.xpath("//ul/descendant::li[@class='social_linkedin']")).isEnabled());
        //По частичному совпадению атрибута
        driver.findElement(By.xpath("//button[contains(@id,'react-burger-menu-btn')]")).click();
        //По частичному совпадению текста
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(), 'All')]")).isEnabled());
        //По following
        Assert.assertTrue(driver.findElement(By.xpath("//a[@id='logout_sidebar_link']/following::a[@id='reset_sidebar_link']")).isEnabled());
        //По preceding
        Assert.assertTrue(driver.findElement(By.xpath("//a[@id='about_sidebar_link']/preceding::a")).isEnabled());
        //Поиск элемента с условием AND
        Assert.assertTrue(driver.findElement(By.xpath("//*[@data-test='add-to-cart-sauce-labs-backpack' and @id='add-to-cart-sauce-labs-backpack']")).isEnabled());

        //По partialLinkText
        WebElement partialLink = driver.findElement(By.partialLinkText("State"));
        partialLink.click();

        //css
        //По #id
        driver.findElement(By.cssSelector("#react-burger-cross-btn")).click();
        //По .class
        Assert.assertTrue(driver.findElement(By.cssSelector(".title")).isDisplayed());
        //По .class1 .class2
        Assert.assertTrue(driver.findElement(By.cssSelector(".header_secondary_container .title")).isDisplayed());
        //По tagname
        Assert.assertTrue(driver.findElement(By.cssSelector("footer")).isEnabled());
        //По tagname.class
        Assert.assertTrue(driver.findElement(By.cssSelector("span.title")).isDisplayed());
        //По [attribute=value]
        driver.findElement(By.cssSelector("[id='add-to-cart-sauce-labs-bike-light']")).click();
        //По [attribute$=value]
        driver.findElement(By.cssSelector("[id$='bolt-t-shirt']")).click();
        //По .class1.class2
        Assert.assertTrue(driver.findElement(By.cssSelector(".btn.btn_primary")).isEnabled());
        //По [attribute~=value]
        Assert.assertTrue(driver.findElement(By.cssSelector("[class~='title']")).isDisplayed());
        //По [attribute|=value]
        Assert.assertTrue(driver.findElement(By.cssSelector("[id|='add']")).isEnabled());
        //По [attribute*=value]
        Assert.assertTrue(driver.findElement(By.cssSelector("[name*='onesie']")).isEnabled());
        //По [attribute^=value]
        driver.findElement(By.cssSelector("[id^='add-to-cart-sauce-labs-fleece']")).click();
        Thread.sleep(2000);

        //Работа с корзиной
        driver.findElement(By.className("shopping_cart_link")).click();
        WebElement sauceLabsBike = driver.findElement(By.xpath("//div[text()= 'Sauce Labs Bike Light']"));
        WebElement sauceLabsBikePrice = driver.findElement(By.xpath("//button[@name='remove-sauce-labs-bike-light']/preceding::div[@data-test='inventory-item-price']"));
        WebElement sauceLabsBolt = driver.findElement(By.xpath("//div[text()= 'Sauce Labs Bolt T-Shirt']"));
        WebElement sauceLabsBoltPrice = driver.findElement(By.xpath("//div[text()= '15.99']"));
        WebElement sauceLabsFleece = driver.findElement(By.xpath("//div[text()= 'Sauce Labs Fleece Jacket']"));
        WebElement sauceLabsFleecePrice = driver.findElement(By.xpath("//div[text()= '49.99']"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(sauceLabsBike.getText(),"Sauce Labs Bike Light");
        softAssert.assertEquals(sauceLabsBikePrice.getText(),"$9.99");
        softAssert.assertEquals(sauceLabsBolt.getText(),"Sauce Labs Bolt T-Shirt");
        softAssert.assertEquals(sauceLabsBoltPrice.getText(),"$15.99");
        softAssert.assertEquals(sauceLabsFleece.getText(),"Sauce Labs Fleece Jacket");
        softAssert.assertEquals(sauceLabsFleecePrice.getText(),"$49.99");
        Thread.sleep(3000);

        softAssert.assertAll();

    }
}
