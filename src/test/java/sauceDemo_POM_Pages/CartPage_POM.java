package sauceDemo_POM_Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import helper.BasePage;

public class CartPage_POM extends BasePage {
	private Logger log = LogManager.getLogger(CartPage_POM.class);

    @FindBy(id = "checkout")
    private WebElement CheckoutButton;
  
    public CartPage_POM(WebDriver driver) {
        super(driver);
    }
    
    public void clickCheckoutButton() {
        CheckoutButton.click();
        log.info("Clicked on Checkout Button");
    }

    public void MoveToCheckoutPage() {
       clickCheckoutButton();
    }
}
