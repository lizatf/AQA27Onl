package services;

import configuration.ReadProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.sql.DriverManager;

public class BrowsersService {
    private WebDriver driver = null;

    public BrowsersService() {

        switch (ReadProperties.browserName().toLowerCase()) {
            case "chrome":
                DriverManagerType driverManagerType = DriverManagerType.CHROME;
                WebDriverManager.getInstance(driverManagerType).clearDriverCache().setup();
                driver = new ChromeDriver(getChromeOptions());
                break;
            default:
                System.out.println("Browser " + ReadProperties.browserName() + " not supported");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gru");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--silent");
        options.addArguments("--start-maximized");
        options.addArguments("--private");

        if (ReadProperties.isHeadless()) {
            options.addArguments("--headless");
        }

        return options;
    }
}
