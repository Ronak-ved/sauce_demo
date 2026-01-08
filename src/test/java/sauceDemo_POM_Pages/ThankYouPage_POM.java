package sauceDemo_POM_Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import helper.BasePage;

public class ThankYouPage_POM extends BasePage{
	private Logger log = LogManager.getLogger(LoginPage_POM.class);

    @FindBy(id = "back-to-products")
    private WebElement BackHomeButton;
    
    @FindBy(className = "complete-header")
    WebElement thankYouHeader;

    public boolean isOrderSuccessful() {
        return thankYouHeader.isDisplayed();
    }

	
    public ThankYouPage_POM(WebDriver driver) {
        super(driver);
    }
    public void clickBackHomeButton() {
        BackHomeButton.click();
        log.info("Clicked Back Home button");
    }

    public void BackToHome() {
       clickBackHomeButton();
    }

}
