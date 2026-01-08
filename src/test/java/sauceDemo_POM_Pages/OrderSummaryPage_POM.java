package sauceDemo_POM_Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import helper.BasePage;

public class OrderSummaryPage_POM extends BasePage {
	 private Logger log = LogManager.getLogger(OrderSummaryPage_POM.class);

	    @FindBy(id = "finish")
	    private WebElement finishButton;
	    
	    public OrderSummaryPage_POM(WebDriver driver) {
	        super(driver);
	    }
	    
	    public void clickFinish() {
	        finishButton.click();
	        log.info("Clicked Finish button");
	    }

	    public void PlaceOrder() {
	        clickFinish();
	    }


}
