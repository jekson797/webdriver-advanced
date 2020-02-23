package by.epamlab.webdriver_advanced.test;

import by.epamlab.webdriver_advanced.page.EmailDraftPage;
import by.epamlab.webdriver_advanced.page.EmailPage;
import by.epamlab.webdriver_advanced.form.MailSendingForm;
import by.epamlab.webdriver_advanced.page.EmailSentPage;
import by.epamlab.webdriver_advanced.test_condition.MailSendingCondition;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmailSendDraftMailTest extends MailSendingCondition {

    @Test(description = "Send mail and check its disappearing from draft folder",
            groups = {"mail-sending"}, dependsOnGroups = {"save-mail"})
    public void testDraftFolderAfterMailSending() {
        EmailDraftPage draftPage = new EmailDraftPage().pressDraftBtn();
        int draftMailsBeforeMailSending = draftPage.getDraftMailItems().size();
        sendLastSavedMailInDraftFolder(draftPage);
        int draftMailsAfterMailSending = draftPage.getDraftMailItems().size();
        Assert.assertTrue(draftMailsBeforeMailSending > draftMailsAfterMailSending);
    }

    @Test(description = "Check that mail after sending presents in sent folder",
            groups = {"mail-sending"}, dependsOnMethods = {"testDraftFolderAfterMailSending"})
    public void testLetterPresenceInSentFolderAfterItsSending() {
        EmailSentPage sentPage = new EmailPage().pressSentBtn();
        int sentMailsAfterMailSending = sentPage.getSentMailsItems().size();
        Assert.assertTrue(sentMailsAfterMailSending > getSentMailsBeforeSendingMail());
    }

    private void sendLastSavedMailInDraftFolder(EmailDraftPage draftPage) {
        MailSendingForm sendingForm = draftPage.pressOnFirstMailItem();
        sendingForm.pressSendBtn();
        draftPage.refreshPage();
    }
}
