import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import services.BrowsersService;

public class SeleniumTests {

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
    public void Electricfloor() throws InterruptedException {
        driver.get("https://kermi-fko.ru/raschety/Calc-Rehau-Solelec.aspx");
        Thread.sleep(2000);

        WebElement width = driver.findElement(By.id("el_f_width"));
        WebElement lenght = driver.findElement(By.id("el_f_lenght"));
        WebElement roomType = driver.findElement(By.id("room_type"));
        WebElement heatingType = driver.findElement(By.id("heating_type"));
        WebElement heatLoss = driver.findElement(By.id("el_f_losses"));
        WebElement button = driver.findElement(By.name("button"));
        WebElement cablePower = driver.findElement(By.id("floor_cable_power"));
        WebElement specificPower = driver.findElement(By.id("spec_floor_cable_power"));

        width.sendKeys("3");
        width.sendKeys(Keys.TAB);
        lenght.sendKeys("4");
        Select selectSpace = new Select(roomType);
        selectSpace.selectByVisibleText("Ванная");
        Select selectHeating = new Select(heatingType);
        selectHeating.selectByVisibleText("Подогрев для комфорта");
        heatLoss.sendKeys("120");
        button.click();
        Thread.sleep(2000);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cablePower.getAttribute("value"), 67);
        softAssert.assertEquals(specificPower.getAttribute("value"), 6);

        softAssert.assertAll();
    }
    @Test
    public void validateSKF() throws InterruptedException {
        driver.get("https://bymed.top/calc/%D1%81%D0%BA%D1%84-2148");
        Thread.sleep(2000);

        driver.switchTo().frame(1);
        WebElement age = driver.findElement(By.cssSelector("#age"));
        WebElement gender = driver.findElement(By.id("sex"));
        WebElement creatinin = driver.findElement(By.id("cr"));
        WebElement creatininUnit = driver.findElement(By.id("cr-size"));
        WebElement race = driver.findElement(By.id("race"));
        WebElement weight = driver.findElement(By.id("mass"));
        WebElement height = driver.findElement(By.id("grow"));
        WebElement button = driver.findElement(By.xpath("//*[@id=\"input-data\"]/p[7]/button"));
        WebElement mdrd = driver.findElement(By.id("mdrd_res"));
        WebElement ckdEpi = driver.findElement(By.id("ckd_epi_res"));
        WebElement cockroftGaut = driver.findElement(By.id("cge_res"));


        age.sendKeys("38");
        Select selectGender = new Select(gender);
        selectGender.selectByVisibleText("Женский");
        creatinin.sendKeys("80");
        Select selectUnit = new Select(creatininUnit);
        selectUnit.selectByVisibleText("ммоль/л");
        Select selectRace = new Select(race);
        selectRace.selectByVisibleText("Другая");
        weight.sendKeys("55");
        weight.sendKeys(Keys.TAB);
        height.sendKeys("163");
        button.click();
        Thread.sleep(2000);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(mdrd.getText(), "0.02");
        softAssert.assertEquals(ckdEpi.getText(), "0.02");
        softAssert.assertEquals(cockroftGaut.getText(), "0.07");

        softAssert.assertAll();
    }

    @Test
    public void laminateCalculator() throws InterruptedException {
        driver.get("https://calc.by/building-calculators/laminate.html");
        Thread.sleep(2000);

        WebElement lenght = driver.findElement(By.id("ln_room_id"));
        WebElement width = driver.findElement(By.id("wd_room_id"));
        WebElement panelLenght = driver.findElement(By.id("ln_lam_id"));
        WebElement panelWidth = driver.findElement(By.id("wd_lam_id"));
        WebElement methodOfLaying = driver.findElement(By.id("laying_method_laminate"));
        WebElement layingDirection = driver.findElement(By.xpath("//*[@id=\"t3-content\"]/div[3]/article/section/div[2]/div[2]/div[1]/div[9]/div[2]"));
        WebElement button = driver.findElement(By.xpath("//*[@id=\"t3-content\"]/div[3]/article/section/div[2]/div[2]/div[2]/a"));

        lenght.clear();
        lenght.sendKeys("500");
        width.clear();
        width.sendKeys("400");
        panelLenght.clear();
        panelLenght.sendKeys("2000");
        panelWidth.clear();
        panelWidth.sendKeys("200");
        Select selectmethod = new Select(methodOfLaying);
        selectmethod.selectByVisibleText("с использованием отрезанного элемента");
        Select selectDirection = new Select(layingDirection);
        selectDirection.selectByVisibleText("по ширине комнаты°");
        button.click();
        Thread.sleep(3000);

        WebElement numOfBoards = driver.findElement(By.cssSelector("span[style='padding:5px 0;font-size:22px; color:#C80303; font-weight:bold;']"));
        WebElement numOfPackages = driver.findElement(By.cssSelector("span[style='padding:5px 0;font-size:18px; color:#0E8C19; font-weight:bold;']"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(numOfBoards.getText(),"53");
        softAssert.assertEquals(numOfPackages.getText(),"7");
        softAssert.assertAll();

    }
}
