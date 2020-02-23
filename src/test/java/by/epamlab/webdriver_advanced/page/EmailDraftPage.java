package by.epamlab.webdriver_advanced.page;

import by.epamlab.webdriver_advanced.form.MailSendingForm;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EmailDraftPage extends EmailPage {

    @FindBy(xpath = "//*[@class='stop-animate']/ancestor::a[contains(@href, '/drafts/')]")
    private List<WebElement> draftMailItems;

    public List<WebElement> getDraftMailItems() {
        return draftMailItems;
    }

    public MailSendingForm pressOnFirstMailItem() {
        clickElement(getFirstMailItem());
        return new MailSendingForm();
    }

    private WebElement getFirstMailItem() {
        if(draftMailItems.isEmpty()) {
            throw new AssertionError("Draft folder is empty");
        }
        return draftMailItems.get(0);
    }
}
