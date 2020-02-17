package by.epamlab.webdriver_advanced.test;

import by.epamlab.webdriver_advanced.page.EmailDraftPage;
import by.epamlab.webdriver_advanced.form.MailSendingForm;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class EmailDraftContentTest extends CommonConditions {

    @Parameters({"email-addressee", "email-subject", "email-body"})
    @Test(description = "Check that content of draft mail meets the requirements", dependsOnGroups = {"save-mail"})
    public void testDraftMailContent(String expectedAddressee, String expectedSubject, String expectedBody) {
        Map<String, String> expectedContent =
                createMapWithMailContent(expectedAddressee, expectedSubject, expectedBody);
        Map<String, String> actualContent = getDraftMailContent();
        Assert.assertEquals(expectedContent, actualContent);
    }

    private Map<String, String> createMapWithMailContent(String addressee, String subject, String body) {
        Map<String, String> mailContent = new HashMap<>();
        mailContent.put("Addressee", addressee);
        mailContent.put("Subject", subject);
        mailContent.put("Body", body);
        return mailContent;
    }

    private Map<String, String> getDraftMailContent() {
        MailSendingForm sendingForm = new EmailDraftPage().openLastAddedMail();
        String addressee = sendingForm.getAddresseeFieldValue();
        String subject = sendingForm.getSubjectValue();
        String body = cutMailSignature(sendingForm.getMessageFieldValue());
        sendingForm.pressCloseBtn();
        return createMapWithMailContent(addressee, subject, body);
    }

    private String cutMailSignature(String stringWithSignature) {
        String regexForMailSignature = "\\s+ПОДПИСЬ";
        return stringWithSignature.split(regexForMailSignature)[0];
    }
}
