package by.epamlab.webdriver_advanced.test;

import by.epamlab.webdriver_advanced.page.EmailDraftPage;
import by.epamlab.webdriver_advanced.page.EmailPage;
import by.epamlab.webdriver_advanced.form.MailSendingForm;
import by.epamlab.webdriver_advanced.test_condition.CommonConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EmailSaveMailTest extends CommonConditions {

    @Parameters({"email-addressee", "email-subject", "email-body"})
    @Test(description = "Save mail as a draft", groups = {"save-mail"}, dependsOnGroups = {"login"})
    public void testSavingMailAsDraft(String addressee, String subject, String body) {
        EmailDraftPage draftPage = new EmailDraftPage();
        int draftMailsBeforeCreatingNewOne = draftPage.pressDraftBtn().getDraftMailItems().size();
        writeMail(addressee, subject, body).saveMailAsDraft();
        draftPage.pressDraftBtn().refreshPage();
        int draftMailsAfterCreatingNewOne = draftPage.getDraftMailItems().size();
        Assert.assertTrue(draftMailsAfterCreatingNewOne > draftMailsBeforeCreatingNewOne);
    }

    private EmailSaveMailTest writeMail(String addressee, String subject, String body) {
        new EmailPage().pressWriteLetterBtn().
                fillAddresseeField(addressee).fillSubjectField(subject).fillMessageField(body);
        return this;
    }

    private void saveMailAsDraft() {
        new MailSendingForm().pressSaveBtn().pressCloseBtn();
    }
}
