package by.epamlab.webdriver_advanced.test;

import by.epamlab.webdriver_advanced.page.EmailPage;
import by.epamlab.webdriver_advanced.test_condition.CommonConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class EmailDragMailFromSentToTrashFolderTest extends CommonConditions {

    private EmailPage emailPage = new EmailPage();

    @Test(description = "Drag mail from sent folder to trash folder", dependsOnGroups = {"login", "mail-sending"})
    public void testDraggingMailFromSentToTrashFolder() {
        int mailsInTrashFolderBeforeDragging = getMailAmountInTrashFolder();
        dragSentMailFromSentFolderToTrashFolder();
        emailPage.refreshPage();
        int mailsInTrashFolderAfterDragging = getMailAmountInTrashFolder();
        Assert.assertTrue(mailsInTrashFolderAfterDragging > mailsInTrashFolderBeforeDragging);
    }

    @AfterClass
    public void logout() {
        new EmailPage().pressLogout();
    }

    private int getMailAmountInTrashFolder() {
        return emailPage.pressTrashBtn().getTrashMailItems().size();
    }

    private void dragSentMailFromSentFolderToTrashFolder() {
        emailPage.pressSentBtn().dragFirstMailItemToTrashBtn();
    }
}
