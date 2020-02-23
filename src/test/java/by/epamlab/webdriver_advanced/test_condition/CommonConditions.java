package by.epamlab.webdriver_advanced.test_condition;

import by.epamlab.webdriver_advanced.driver.DriverSingleton;
import by.epamlab.webdriver_advanced.utils.TestListener;
import org.testng.annotations.*;

@Listeners({TestListener.class})
public class CommonConditions {

    @AfterSuite(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}
