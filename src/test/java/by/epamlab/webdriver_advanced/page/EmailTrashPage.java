package by.epamlab.webdriver_advanced.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EmailTrashPage extends EmailPage {

    @FindBy(xpath = "//*[@class='stop-animate']/ancestor::a[contains(@href, '/trash/')]")
    private List<WebElement> trashMailItems;

    public List<WebElement> getTrashMailItems() {
        return trashMailItems;
    }
}
