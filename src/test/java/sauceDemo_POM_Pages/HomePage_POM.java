package sauceDemo_POM_Pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.BasePage;

public class HomePage_POM extends BasePage {
	private Logger log = LogManager.getLogger(HomePage_POM.class);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	 @FindBy(id = "add-to-cart-sauce-labs-backpack")
	    private WebElement addToCartButton;

	    @FindBy(className ="shopping_cart_link")
	    private WebElement cartButton;
	   
	    @FindBy(id = "react-burger-menu-btn")
	    private WebElement menuButton;

	    @FindBy(id ="logout_sidebar_link")
	    private WebElement LogoutButton;
	   
	    
	    public HomePage_POM(WebDriver driver) {
	        super(driver);
	    }
       
	    public void AddItem() {
	        addToCartButton.click();
	        log.info("Item added to cart");
	    }
	    
	    public void OpenCart() {
	        cartButton.click();
	        log.info("Cart is opened");
	    }

	    public void AddItemToCart() {
	        AddItem();
	        OpenCart();
	    }
	    
	    //logging out
	    public void ClickMenu() {
	        menuButton.click();
	        log.info("Clicked on Menu Button");
	    }
	    
	    public void ClickLogoutButton() {
	        LogoutButton.click();
	        log.info("Clicked on Logout Button");
	    }
	    
	    public void Logout() {
	    	    menuButton.click();
	    	    log.info("Clicked on Menu Button");

	    	    wait.until(ExpectedConditions.visibilityOf(LogoutButton));
	    	    LogoutButton.click();
	    	    log.info("Clicked Logout Button");
	    }
	    
}
