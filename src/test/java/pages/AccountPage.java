package pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utlities.Constants;

public class AccountPage extends PageBase {
    By txt_Email = By.id("email_create");
    By btn_newAccount = By.xpath("//button[@id='SubmitCreate']/span");
    By btn_Gender = By.xpath("//input[@id='id_gender2']");
    By txt_firstName = By.id("customer_firstname");
    By txt_lastName = By.id("customer_lastname");
    By txt_Password = By.id("passwd");
    By txt_Address = By.id("address1");
    By txt_City = By.id("city");
    By txt_State = By.id("id_state");
    By txt_zipCode = By.id("postcode");
    By txt_Mobile = By.id("phone_mobile");
    By txt_addressAlias = By.id("alias");
    By btn_Register = By.xpath("//button[@id='submitAccount']/span");
    By txt_successMsg = By.xpath("//p[@class='info-account']");
    //By btn_signIn = By.xpath("//button[@id='SubmitLogin']/span");

    public AccountPage(WebDriver driver) {

        super(driver);
    }

    // enter random Email
    public void enterEmail(String email) {

        clearText(txt_Email);
        sendKeys(txt_Email, email + generateEmail() + Constants.DomainName);
        clickOnButton(btn_newAccount);

    }


    //create New Account
    public void createNewAccount(String firstname, String lastname,
                                 String password, String address, String city,
                                 String state, String code, String mobile,
                                 String alias) {

        clickOnButton(btn_Gender);
        sendText(txt_firstName, firstname);
        sendText(txt_lastName, lastname);
        sendText(txt_Password, password);
        sendText(txt_Address, address);
        sendText(txt_City, city);
        selectByVisibaleText(txt_State, state);
        sendText(txt_zipCode, code);
        sendText(txt_Mobile, mobile);
        clearText(txt_addressAlias);
        sendText(txt_addressAlias, alias);
        clickOnButton(btn_Register);

    }


// validate Success Message

    public String getSuccessMsg() {
        return getText(txt_successMsg);
    }


    public boolean isSuccessMsgDisplay() {

        return isDisplay(txt_successMsg);
    }


}
