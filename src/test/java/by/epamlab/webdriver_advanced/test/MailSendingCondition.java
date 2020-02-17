package by.epamlab.webdriver_advanced.test;

import by.epamlab.webdriver_advanced.page.EmailPage;
import by.epamlab.webdriver_advanced.page.EmailSentPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class MailSendingCondition extends CommonConditions {

    private int sentMailsBeforeSendingMail;

    @BeforeClass
    public void setSentMailsBeforeSendingMail() {
        EmailSentPage sentPage = new EmailPage().pressSentBtn();
        sentMailsBeforeSendingMail = sentPage.getMailsAmountInFolder();
    }

    @AfterClass
    public void logOff() {
        new EmailPage().pressLogout();
    }

    public int getSentMailsBeforeSendingMail() {
        return sentMailsBeforeSendingMail;
    }
}
