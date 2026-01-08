package sauceDemo_POM_Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import helper.BasePage;

public class CheckoutPage_POM extends BasePage{
	 private Logger log = LogManager.getLogger(CheckoutPage_POM.class);

	    @FindBy(id = "first-name")
	    private WebElement firstnameInput;

	    @FindBy(id = "last-name")
	    private WebElement lastnameInput;

	    @FindBy(id = "postal-code")
	    private WebElement postalcode;
	    
	    @FindBy(id = "continue")
	    private WebElement continueButton;
	    
	    public CheckoutPage_POM(WebDriver driver) {
	        super(driver);
	    }

	    public void enterFirstname(String fname) {
	        firstnameInput.clear();
	        firstnameInput.sendKeys(fname);
	        log.info("Entered First name: {}", fname);
	    }

	    public void enterLastname(String lname) {
	        lastnameInput.clear();
	        lastnameInput.sendKeys(lname);
	        log.info("Entered Last name: {}", lname);
	    }
	    
	    public void enterPostalCode(String PostalCode) {
	        postalcode.clear();
	        postalcode.sendKeys(PostalCode);
	        log.info("Entered Postal Code: {}", PostalCode);
	    }

	    public void clickContinue() {
	        continueButton.click();
	        log.info("Clicked Continue Button");
	    }

	    public void Checkout(String fname, String lname, String PostalCode) {
	       enterFirstname(fname);
	       enterLastname(lname);
	       enterPostalCode(PostalCode);
	       clickContinue();
	    }

	    
}
