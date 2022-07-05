package suites;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.HomePage;
import pages.ProductPage;
import utlities.Constants;

public class FlowPurchaseTest extends TestBase {
    HomePage homePage;
    AccountPage accountPage;
    ProductPage productPage;

    @BeforeClass
    public void beforeClass() {
        homePage = new HomePage(driver);

    }

    @Test(priority = 1)
    public void verifyValidRegisterUser() {
        homePage.signIn();
        accountPage = new AccountPage(driver);
        testDataReader.selectDataSet(Constants.SheetName);
        accountPage.enterEmail(testDataReader.getDataCollector().get("email"));
        accountPage.createNewAccount(testDataReader.getDataCollector().get("firstname"),
                testDataReader.getDataCollector().get("lastname"),
                testDataReader.getDataCollector().get("password"),
                testDataReader.getDataCollector().get("address"),
                testDataReader.getDataCollector().get("city"),
                testDataReader.getDataCollector().get("state"),
                testDataReader.getDataCollector().get("code"),
                testDataReader.getDataCollector().get("mobile"),
                testDataReader.getDataCollector().get("alias"));
        System.out.println("User login Successfully");
        System.out.println("Message :" + accountPage.getSuccessMsg());
        Assert.assertTrue(accountPage.isSuccessMsgDisplay());

    }

    @Test(priority = 2, dependsOnMethods = {"verifyValidRegisterUser"})
    public void verifyPurchaseFlow() {
        productPage = new ProductPage(driver);
        productPage.selectWomanSubCategory(testDataReader.getDataCollector().get("productName"));
        Assert.assertTrue(productPage.isBlouseDisplay());
        System.out.println("Message :" + productPage.getBlouseText());
        productPage.addToCart();
        productPage.proceedCheckoutProcces();
        Assert.assertTrue(productPage.isSummaryMsgDisplay());
        System.out.println("Message :" + productPage.getSummaryMsg());
        productPage.proceedFirstCheckOutProcces();
        Assert.assertTrue(productPage.isAddressMsgDisplay());
        System.out.println("Message :" + productPage.getAddressMsg());
        productPage.proceedSecondCheckOutProcces();
        Assert.assertTrue(productPage.isShippingMsgDisplay());
        System.out.println("Message :" + productPage.getShippingMsg());
        productPage.proceedThirdCheckOutProcces();
        Assert.assertTrue(productPage.isPaymentMsgDisplay());
        System.out.println("Message :" + productPage.getPaymentMsg());
        productPage.payOrder();
        Assert.assertTrue(productPage.isOrderSummaryMsgDisplay());
        System.out.println("Message :" + productPage.getOrderSummaryMsg());
        productPage.confirmOrder();
        Assert.assertTrue(productPage.isOrderConfirmationMsgDisplay());
        System.out.println("Message :" + productPage.getOrderConfirmationMsg());
        productPage.backToOrder();
        Assert.assertTrue(productPage.isOrderHistoryMsgDisplay());
        System.out.println("Message :" + productPage.getOrderHistoryMsg());
        Assert.assertTrue(productPage.getResultText("Bank wire"));
        homePage.signOut();
        Assert.assertTrue(homePage.isSignInDisplay());
        System.out.println("User Sign Out Successfully");
        System.out.println("Message :" + homePage.getMsgText());
    }

}
