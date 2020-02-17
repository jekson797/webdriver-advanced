package by.epamlab.webdriver_advanced.driver;

import by.epamlab.webdriver_advanced.service.ConfigReader;
import by.epamlab.webdriver_advanced.service.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverSingleton {

    private static WebDriver driver;
    private static final int IMPLICIT_TIMEOUT = Integer.parseInt(ConfigReader.get(Constants.IMPLICIT_WAIT));
    private static final String GRID_URL = ConfigReader.get(Constants.WEBDRIVER_GRID_URL);

    private DriverSingleton() {
        throw new AssertionError("Cannot be instantiated directly.");
    }

    public static WebDriver getDriver() {
        if (null == driver) {
            try {
                return setDriver();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    private static WebDriver setDriver() throws MalformedURLException {
        switch (System.getProperty("browser", "chrome")){
            case "firefox": {
                driver = createFirefoxDriver();
                break;
            }
            case "chrome": {
                driver = createChromeDriver();
                break;
            }
            default: {
                throw new AssertionError("Browser is not correct");
            }
        }
        driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return getDriver();
    }

    private static WebDriver createFirefoxDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        driver = new RemoteWebDriver(new URL(GRID_URL), capabilities);
        return driver;
    }

    private static WebDriver createChromeDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL(GRID_URL), capabilities);
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
