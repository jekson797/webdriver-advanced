package by.epamlab.webdriver_advanced.test;

import by.epamlab.webdriver_advanced.page.EmailDraftPage;
import by.epamlab.webdriver_advanced.page.EmailPage;
import by.epamlab.webdriver_advanced.form.MailSendingForm;
import by.epamlab.webdriver_advanced.page.EmailSentPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmailSendDraftMailTest extends MailSendingCondition {

    @Test(description = "Send mail and check its disappearing from draft folder", dependsOnGroups = {"save-mail"})
    public void testDraftFolderAfterMailSending() {
        EmailDraftPage draftPage = new EmailPage().pressDraftBtn();
        int draftMailsBeforeMailSending = draftPage.getMailsAmountInFolder();
        sendLastSavedMailInDraftFolder(draftPage);
        int draftMailsAfterMailSending = draftPage.getMailsAmountInFolder();
        Assert.assertTrue(draftMailsBeforeMailSending > draftMailsAfterMailSending);
    }

    @Test(description = "Check that mail after sending presents in sent folder",
            dependsOnMethods = {"testDraftFolderAfterMailSending"})
    public void testLetterPresenceInSentFolderAfterItsSending() {
        EmailSentPage sentPage = new EmailPage().pressSentBtn();
        int sentMailsAfterMailSending = sentPage.getMailsAmountInFolder();
        Assert.assertTrue(sentMailsAfterMailSending > getSentMailsBeforeSendingMail());
    }

    private void sendLastSavedMailInDraftFolder(EmailDraftPage draftPage) {
        MailSendingForm sendingForm = draftPage.openLastAddedMail();
        sendingForm.pressSendBtn();
        draftPage.refreshPage();
    }
}
