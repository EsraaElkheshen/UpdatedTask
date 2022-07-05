package pages;


import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends PageBase {


    By btn_signInMenu = By.xpath("//a[@class='login']");
    By btn_signOut = By.xpath("//a[@class='logout']");
    By txt_Msg = By.xpath("//h3[text()='Create an account']");


    public HomePage(WebDriver driver) {

        super(driver);
    }

    // sign in  to page
    public void signIn() {

        clickOnButton(btn_signInMenu);
    }

    // log out from page
    public void signOut() {

        clickOnButton(btn_signOut);
    }

    // validate sign in btn display
    public boolean isSignInDisplay() {
        return isDisplay(btn_signInMenu);
    }

    public String getMsgText() {
        return getText(txt_Msg);

    }

}
