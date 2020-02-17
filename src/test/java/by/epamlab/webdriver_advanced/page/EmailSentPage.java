package by.epamlab.webdriver_advanced.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EmailSentPage extends AbstractPage {

    @FindBy(xpath = "//*[@class='stop-animate']/ancestor::a[contains(@href, '/sent/')]")
    private List<WebElement> mailsList;

    public int getMailsAmountInFolder() {
        return mailsList.size();
    }
}
