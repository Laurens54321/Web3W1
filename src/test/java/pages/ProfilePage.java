package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.color.ProfileDataException;

public class ProfilePage extends Page {

    @FindBy(id = "userid")
    WebElement useridField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "logIn")
    WebElement logInButton;

    public ProfilePage(WebDriver driver) {
        super(driver);
        driver.get(getPath() + "?command=Profile");
    }

    public void setUserid(String userid) {
        useridField.clear();
        useridField.sendKeys(userid);
    }

    public boolean hasStickyUserid(String userid){
        return userid.equals(useridField.getAttribute("value"));
    }

    public void setPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public ProfilePage submitValid(){
        logInButton.click();
        return PageFactory.initElements(driver, ProfilePage.class);
    }



}
