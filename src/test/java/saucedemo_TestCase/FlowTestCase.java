package saucedemo_TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import helper.BaseTest;
import sauceDemo_POM_Pages.CartPage_POM;
import sauceDemo_POM_Pages.CheckoutPage_POM;
import sauceDemo_POM_Pages.HomePage_POM;
import sauceDemo_POM_Pages.LoginPage_POM;
import sauceDemo_POM_Pages.OrderSummaryPage_POM;
import sauceDemo_POM_Pages.ThankYouPage_POM;
import utilities_classes.Excel_File_Reader;
import utilities_classes.ScreenShot_Utils;

public class FlowTestCase extends BaseTest {
    private Logger log = LogManager.getLogger(FlowTestCase.class);

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        String path = System.getProperty("user.dir") + "/src/test/resources/testdata/LoginData.xlsx";
        Excel_File_Reader reader = new Excel_File_Reader(path);
        //return reader.getTestData(); //return the all row present in the excel
        return reader.getSingleRowData();//return only first row in the excel
    }
    
    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() {
        String path = System.getProperty("user.dir") + "/src/test/resources/testdata/CheckoutData.xlsx";
        Excel_File_Reader reader = new Excel_File_Reader(path);
        //return reader.getTestData();//return the all row present in the excel
        return reader.getSingleRowData();//return only first row in the excel
    }

    @Test(priority = 1, dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedResult) {

        LoginPage_POM login = new LoginPage_POM(driver);
        log.info("Login attempt for user: {}", username);
        testReporter.info("Attempting login for " + username);

        login.login(username, password);

        boolean isLoggedIn = driver.getCurrentUrl().contains("inventory.html");

        if (expectedResult.equalsIgnoreCase("SUCCESS")) {

            if (!isLoggedIn) {
                ScreenShot_Utils.takeScreenshot(driver, "unexpected_fail_" + username);
                testReporter.fail("Expected SUCCESS but login failed");
            }

            Assert.assertTrue(isLoggedIn, "Expected login to succeed");
            testReporter.pass("Login SUCCESS as expected");

        } else {

            if (isLoggedIn) {
                ScreenShot_Utils.takeScreenshot(driver, "unexpected_success_" + username);
                testReporter.fail("Expected FAILURE but login succeeded");
            }

            Assert.assertFalse(isLoggedIn, "Expected login to fail");
            testReporter.pass("Login FAILED as expected");
        }
    }
  //user is on home page and try to add item in cart & open cart
    @Test(priority = 2, dependsOnMethods ="loginTest")
    public void addTocartTest() {

        HomePage_POM home = new HomePage_POM(driver);
        log.info("User try to add items to cart");
        testReporter.info("Attempting to add item to cart ");

        home.AddItemToCart();
         
        Assert.assertTrue(
                driver.getPageSource().contains("Remove"),
                "Item not added to cart"
            );
    }
    
  //user is cart page and see the added items and click on checkout button
    @Test(priority = 3, dependsOnMethods ="addTocartTest")
    public void CartTest() throws InterruptedException {

        CartPage_POM cart = new CartPage_POM(driver);
        log.info("user try to move on checkout page");
        testReporter.info("Attempting to move to checkout page ");

        cart.MoveToCheckoutPage();
     
        // ✅ wait for navigation
        Thread.sleep(1500);
        
        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-one"),
                "Not navigated to checkout page"
            );

    }
    //user is on checkout page & filling the user details
    @Test(priority = 4, dependsOnMethods ="CartTest", dataProvider = "checkoutData")
    public void checkoutTest(String fname, String lname, String postalcode) {

        CheckoutPage_POM checkout = new CheckoutPage_POM(driver);
        log.info("Checkout for user: {}", fname);
        testReporter.info("Attempting checkout for " + fname);

        checkout.Checkout(fname, lname, postalcode);
        
        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-two"),
                "Checkout details not submitted"
            );
}
    //user is on order summary page and clicks on finish button to place order
    @Test(priority = 5, dependsOnMethods ="checkoutTest")
    public void OrderSummaryTest() throws InterruptedException {

        OrderSummaryPage_POM order = new OrderSummaryPage_POM(driver);
        log.info("user clicks on finish button");
        testReporter.info("Attempting to click on finish button ");

        order.PlaceOrder();
        
        ThankYouPage_POM thankYou = new ThankYouPage_POM(driver);

        Assert.assertTrue(
            thankYou.isOrderSuccessful(),
            "Order confirmation not displayed"
        );
        
    }
    
    //user is on thank you page and clicks on back home button to back to home page
    @Test(priority = 6, dependsOnMethods ="OrderSummaryTest")
    public void ThankYouTest() throws InterruptedException {

        ThankYouPage_POM thank = new ThankYouPage_POM(driver);
        log.info("user clicks on back home button");
        testReporter.info("Attempting to click on back home button ");

        thank.BackToHome();
        
    }
    
    //user is home page and try to logout
    @Test(priority = 7, dependsOnMethods ="loginTest")
    public void LogoutTest() {

        HomePage_POM Logout = new HomePage_POM(driver);
        log.info("user clicks on logout Button");
        testReporter.info("Attempting to click on logout button");

        Logout.Logout();
        
        Assert.assertTrue(
        	    driver.getCurrentUrl().contains("saucedemo.com"),
        	    "Logout failed"
        	);
    }
    
        
}