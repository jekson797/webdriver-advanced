package by.epamlab.webdriver_advanced.page;

import by.epamlab.webdriver_advanced.driver.DriverSingleton;
import by.epamlab.webdriver_advanced.service.ConfigReader;
import by.epamlab.webdriver_advanced.service.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    private WebDriver driver = DriverSingleton.getDriver();
    private int explicitTimeout = Integer.parseInt(ConfigReader.get(Constants.COMMON_TIMEOUT));
    private WebDriverWait wait = new WebDriverWait(driver, explicitTimeout);
    private Actions action = new Actions(driver);
    private JavascriptExecutor js = (JavascriptExecutor) driver;

    protected AbstractPage() {
        PageFactory.initElements(driver, this);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    protected void dragElementToAnotherElement(WebElement draggableElement, WebElement droppableElement) {
        highlightElement(draggableElement);
        action.clickAndHold(draggableElement).moveToElement(droppableElement).release().perform();
        unHighlightElement(draggableElement);
    }

    protected void waitUntilPageIsLoadedWithJs() {
        wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
    }

    protected void waitUntilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitUntilVisibilityOfElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitUntilInvisibilityOfElementLocated(By element) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    protected void openPage(String pageUrl) {
        driver.get(pageUrl);
        waitUntilPageIsLoadedWithJs();
    }

    protected void clickElement(WebElement element) {
        waitUntilVisibilityOfElement(element);
        waitUntilElementToBeClickable(element);
        highlightElement(element);
        element.click();
        unHighlightElement(element);
    }

    protected void hoverAndClick(WebElement element) {
        highlightElement(element);
        action.moveToElement(element).click(element).perform();
        unHighlightElement(element);
    }

    protected void clickElementAction(WebElement element) {
        highlightElement(element);
        action.click(element).build().perform();
        unHighlightElement(element);
    }

    protected void clickElementWithJs(WebElement element) {
        waitUntilElementToBeClickable(element);
        highlightElement(element);
        js.executeScript("arguments[0].click();", element);
        unHighlightElement(element);
    }

    protected void pressEnterOnElement(WebElement element) {
        highlightElement(element);
        element.sendKeys(Keys.ENTER);
        unHighlightElement(element);
    }

    protected void setValue(WebElement element, String text) {
        highlightElement(element);
        waitUntilElementToBeClickable(element);
        element.sendKeys(text);
        unHighlightElement(element);
    }

    protected void setValueAction(WebElement element, String text) {
        waitUntilElementToBeClickable(element);
        highlightElement(element);
        action.sendKeys(element, text).perform();
        unHighlightElement(element);
    }

    protected String getElementText(WebElement element) {
        return element.getText();
    }

    protected String getAttributeValue(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    protected void highlightElement(WebElement element) {
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
                element);
    }

    protected void unHighlightElement(WebElement element) {
        js.executeScript("arguments[0].setAttribute('style', '');", element);
    }
}
