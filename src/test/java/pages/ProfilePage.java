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

    @FindBy(id = "logOut")
    WebElement logOutButton;

    @FindBy(id = "field")
    WebElement fieldField;

    @FindBy(id = "phonenr")
    WebElement phonenrField;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "makeReservation")
    WebElement makeReservationField;

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

    public ProfilePage submitValidLogIn(){
        logInButton.click();
        return PageFactory.initElements(driver, ProfilePage.class);
    }

    public void submitInValidLogIn() {
        logInButton.click();
    }

    /// LOGGED IN ///

    public HomePage submitLogOut(){
        logOutButton.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

    public void setFieldField(String field){
        fieldField.clear();
        fieldField.sendKeys(field);
    }

    public boolean hasStickyField(String field){
        return field.equals(fieldField.getAttribute("value"));
    }

    public void setPhonenrField(String phonenr){
        phonenrField.clear();
        phonenrField.sendKeys(phonenr);
    }

    public boolean hasStickyPhonenr(String phonenr){
        return phonenr.equals(phonenrField.getAttribute("value"));
    }

    public void setEmailField(String email){
        emailField.clear();
        emailField.sendKeys(email);
    }

    public boolean hasStickyEmail(String email){
        return email.equals(emailField.getAttribute("value"));
    }

    public YourReservationsPage submitValidField(){
        makeReservationField.click();
        return PageFactory.initElements(driver, YourReservationsPage.class);
    }

    public void submitInValidField(){
        makeReservationField.click();
    }

}
