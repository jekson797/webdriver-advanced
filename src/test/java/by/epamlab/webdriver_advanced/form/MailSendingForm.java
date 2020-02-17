package by.epamlab.webdriver_advanced.form;

import by.epamlab.webdriver_advanced.page.AbstractPage;
import by.epamlab.webdriver_advanced.page.EmailPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailSendingForm extends AbstractPage {

    @FindBy(xpath = "//div[@data-name='to']//input")
    private WebElement addresseeFieldInput;

    @FindBy(xpath = "//div[@data-type='to']//button/preceding-sibling::span")
    private WebElement addresseeFieldValue;

    @FindBy(name = "Subject")
    private WebElement subjectField;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement messageField;

    @FindBy(xpath = "//span[@data-title-shortcut='Ctrl+S']")
    private WebElement saveBtn;

    @FindBy(xpath = "//span[@data-title-shortcut='Ctrl+Enter']")
    private WebElement sendBtn;

    @FindBy(xpath = "//button[@tabindex='700']")
    private WebElement closeBtn;

    public MailSendingForm fillAddresseeField(String addressee) {
        setValue(addresseeFieldInput, addressee);
        return this;
    }

    public MailSendingForm fillSubjectField(String subject) {
        setValue(subjectField, subject);
        return this;
    }

    public MailSendingForm fillMessageField(String message) {
        setValue(messageField, message);
        return this;
    }

    public MailSendingForm pressSaveBtn() {
        clickElement(saveBtn);
        return this;
    }

    public void pressCloseBtn() {
        pressEnterOnElement(closeBtn);
    }

    public String getAddresseeFieldValue() {
        return getElementText(addresseeFieldValue);
    }

    public String getSubjectValue() {
        String subjectValueAttributeName = "value";
        return getAttributeValue(subjectField, subjectValueAttributeName);
    }

    public String getMessageFieldValue() {
        return getElementText(messageField);
    }

    public void pressSendBtn() {
        clickElementAction(sendBtn);
        String sendingFormXpath = "//div[@class='compose-app__compose']";
        waitUntilInvisibilityOfElementLocated(By.xpath(sendingFormXpath));
    }
}
