package by.epamlab.webdriver_advanced.test_condition;

import by.epamlab.webdriver_advanced.page.EmailPage;
import by.epamlab.webdriver_advanced.page.EmailSentPage;
import org.testng.annotations.BeforeClass;

public class MailSendingCondition extends CommonConditions {

    private int sentMailsBeforeSendingMail;

    @BeforeClass
    public void setSentMailsBeforeSendingMail() {
        EmailSentPage sentPage = new EmailPage().pressSentBtn();
        sentMailsBeforeSendingMail = sentPage.getSentMailsItems().size();
    }

    public int getSentMailsBeforeSendingMail() {
        return sentMailsBeforeSendingMail;
    }
}
