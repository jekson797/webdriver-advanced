package by.epamlab.webdriver_advanced.page;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EmailSentPage extends EmailPage {

    @FindBy(xpath = "//*[@class='stop-animate']/ancestor::a[contains(@href, '/sent/')]")
    private List<WebElement> sentMailItems;

    @FindBy(css = ".stop-animate .checkbox")
    private List<WebElement> selectMailButtons;

    public List<WebElement> getSentMailsItems() {
        return sentMailItems;
    }

    public void dragFirstMailItemToTrashBtn() {
        pressSelectFirstMailButton();
        try {
            dragElementToAnotherElement(getFirstMailItem(), getTrashBtnElement());
        } catch (StaleElementReferenceException e) {
            System.err.println("Stale Element Reference Exception cause by hover on element");
        }
    }

    private void pressSelectFirstMailButton() {
        if (selectMailButtons.isEmpty()) {
            throw new AssertionError("Sent folder is empty");
        }
        hoverAndClick(selectMailButtons.get(0));
    }

    private WebElement getFirstMailItem() {
        if (sentMailItems.isEmpty()) {
            throw new AssertionError("Sent folder is empty");
        }
        return sentMailItems.get(0);
    }
}
