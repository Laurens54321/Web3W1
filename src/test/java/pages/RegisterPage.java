package pages;

import domain.model.Person;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends Page {

    @FindBy(id = "userid")
    WebElement useridField;

    @FindBy(id = "firstName")
    WebElement firstNameField;

    @FindBy(id = "lastName")
    WebElement lastNameField;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "submit")
    WebElement submitButton;

    public RegisterPage(WebDriver driver) {
        super(driver);
        driver.get(getPath() + "?command=Register");
    }

    public void setUserid(String userid) {
        useridField.clear();
        useridField.sendKeys(userid);
    }

    public boolean hasStickyUserid(String userid){
        return userid.equals(useridField.getAttribute("value"));
    }

    public void setFirstName(String firstName){
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public boolean hasStickyFirstName(String firstname){
        return firstname.equals(firstNameField.getAttribute("value"));
    }

    public void setLastName(String lastName){
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public boolean hasStickyLastName(String lastname){
        return lastname.equals(lastNameField.getAttribute("value"));
    }

    public void setEmail(String email){
        emailField.clear();
        emailField.sendKeys(email);
    }

    public boolean hasStickyEmail(String email){
        return email.equals(emailField.getAttribute("value"));
    }

    public void setPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public PersonOverviewPage submitValid(){
        submitButton.click();
        return PageFactory.initElements(driver, PersonOverviewPage.class); //Not sure what this does, got it from 'https://github.com/UCLLWeb3-2021-sem1-students/week08_page_objects/blob/master/src/test/java/SignUpPage.java'
    }

    public void submitInvalid(){
        submitButton.click();
    }
}
