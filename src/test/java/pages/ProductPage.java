package pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends PageBase {
    By btn_Woman = By.xpath("//Li//a[text()='Women']");
    By txt_blouseResult = By.xpath("//span[@class='cat-name']");
    By btn_viewScreen = By.xpath("//img[@title='Blouse']");
    By btn_addCart = By.xpath("//div[@class='button-container']//span[text()='Add to cart']");
    By btn_proceedCheckOut = By.xpath("//a[@title='Proceed to checkout']");
    By txt_summaryMsg = By.xpath("//h1[@id='cart_title']");
    By btn_firstProceedToCheckOut = By.xpath("//a[@href='http://automationpractice.com/index.php?controller=order&step=1']");
    By txt_addressMsg = By.xpath(" //h1[@class='page-heading']");
    By btn_secondProceedToCheckOut = By.xpath("//button[@name='processAddress']");
    By txt_shippingMsg = By.xpath("//div[@class='delivery_options_address']//p[@class='carrier_title']");
    By btn_agreeTermService = By.xpath("//div[@id='uniform-cgv']");
    By btn_thirdProceedToCheckOut = By.xpath("//button[@name='processCarrier']");
    By txt_paymentMsg = By.xpath("//div[@id='center_column']//h1[@class='page-heading']");
    By btn_payBankWire = By.partialLinkText("Pay by bank wire ");
    By txt_orderSummary = By.xpath("//div//h1[@class='page-heading']");
    By btn_confirmOrder = By.xpath("//button[@type='submit' and @class='button btn btn-default button-medium']");
    By txt_orderConfirmationMsg = By.xpath("//p//strong[@class='dark']");
    By txt_orderHistoryMessage = By.xpath("//h1[@class='page-heading bottom-indent']");
    By btn_backOrder = By.xpath("//a[@title='Back to orders']//i");
    By txt_result = By.xpath("//tbody//tr[@class='first_item ']");


    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private By getProduct(String productName) {
        return By.xpath("//li//a[text()='" + productName + "']");

    }

    // Select “Blouses” Subcategory in “Women” Category
    public void selectWomanSubCategory(String product) {
        hoverMouse(btn_Woman);
        clickOnButton(getProduct(product));

    }

    public boolean isBlouseDisplay() {
        return isDisplay(txt_blouseResult);
    }

    public String getBlouseText() {
        return getText(txt_blouseResult);
    }


    // Follow checkout procedure
    public void addToCart() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(btn_viewScreen)));
        scrollViewElement(btn_viewScreen);
        hoverMouse(btn_viewScreen);
        clickOnButton(btn_addCart);
    }


    public void proceedCheckoutProcces() {
        clickOnButton(btn_proceedCheckOut);
    }

    public String getSummaryMsg() {
        return getText(txt_summaryMsg);
    }

    public boolean isSummaryMsgDisplay() {
        return isDisplay(txt_summaryMsg);
    }

    public void proceedFirstCheckOutProcces() {
        clickOnButton(btn_firstProceedToCheckOut);
    }

    public String getAddressMsg() {
        return getText(txt_addressMsg);
    }

    public boolean isAddressMsgDisplay() {
        return isDisplay(txt_addressMsg);
    }

    public String getShippingMsg() {
        return getText(txt_shippingMsg);
    }

    public boolean isShippingMsgDisplay() {
        return isDisplay(txt_shippingMsg);
    }

    public void proceedSecondCheckOutProcces() {
        clickOnButton(btn_secondProceedToCheckOut);

    }

    public void proceedThirdCheckOutProcces() {
        scrollViewElement(btn_agreeTermService);
        clickOnButton(btn_agreeTermService);
        clickOnButton(btn_thirdProceedToCheckOut);
    }

    public String getPaymentMsg() {
        return getText(txt_paymentMsg);
    }

    public boolean isPaymentMsgDisplay() {
        return isDisplay(txt_paymentMsg);
    }

    // pay with BankWire and confirm order
    public void payOrder() {
        clickOnButton(btn_payBankWire);


    }

    public String getOrderSummaryMsg() {
        return getText(txt_orderSummary);
    }

    public boolean isOrderSummaryMsgDisplay() {
        return isDisplay(txt_orderSummary);
    }

    public void confirmOrder() {
        clickOnButton(btn_confirmOrder);
    }

    public String getOrderConfirmationMsg() {
        return getText(txt_orderConfirmationMsg);
    }

    public boolean isOrderConfirmationMsgDisplay() {
        return isDisplay(txt_orderConfirmationMsg);
    }

    public String getOrderHistoryMsg() {
        return getText(txt_orderHistoryMessage);
    }

    public boolean isOrderHistoryMsgDisplay() {
        return isDisplay(txt_orderHistoryMessage);
    }


    public boolean getResultText(String value) {
        return getText(txt_result).contains(value);
    }


    //Validate order was placed from order history page.
    public void backToOrder() {
        scrollViewElement(btn_backOrder);
        clickOnButton(btn_backOrder);
    }

}





