package by.epamlab.webdriver_advanced.page;

import by.epamlab.webdriver_advanced.form.MailSendingForm;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailPage extends AbstractPage {

    @FindBy(xpath = "//a[@href='/inbox/']")
    private WebElement inboxBtn;

    @FindBy(css = ".compose-button__wrapper")
    private WebElement writeLetterBtn;

    @FindBy(xpath = "//a[@href='/drafts/']")
    private WebElement draftBtn;

    @FindBy(xpath = "//a[@href='/sent/']")
    private WebElement sentBtn;

    @FindBy(xpath = "//a[@href='/trash/']")
    private WebElement trashBtn;

    @FindBy(xpath = "//a[contains(@href, 'logout')]")
    private WebElement logoutBtn;

    public EmailPage pressInboxBtn() {
        clickElementWithJs(inboxBtn);
        return this;
    }

    public String getUrl() {
        waitUntilVisibilityOfElement(inboxBtn);
        return getPageUrl();
    }

    public EmailDraftPage pressDraftBtn() {
        clickElementWithJs(draftBtn);
        return new EmailDraftPage();
    }

    public EmailSentPage pressSentBtn() {
        clickElementWithJs(sentBtn);
        return new EmailSentPage();
    }

    public EmailTrashPage pressTrashBtn() {
        clickElementWithJs(trashBtn);
        return new EmailTrashPage();
    }

    public MailSendingForm pressWriteLetterBtn() {
        clickElement(writeLetterBtn);
        return new MailSendingForm();
    }

    public void pressLogout() {
        clickElement(logoutBtn);
    }

    protected WebElement getTrashBtnElement() {
        return trashBtn;
    }
}
