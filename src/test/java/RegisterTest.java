import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.*;

public class RegisterTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Servlet";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\laure\\Documents\\school\\Web 3\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"?command=Register");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Register_AllFieldsFilledInCorrectly_UserIsRegistered() throws InterruptedException {
        String useridRandom = generateRandomUseridInOrderToRunTestMoreThanOnce("jakke");

        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        assertEquals("Sign Up", registerPage.getTitle());
        registerPage.setUserid(useridRandom);
        registerPage.setFirstName("Jans");
        registerPage.setLastName("Janssens");
        registerPage.setEmail("jan.janssens@hotmail.com");
        registerPage.setPassword("A1a&wateenwachtwoordzeg");
        Thread.sleep(1000);
        PersonOverviewPage personOverviewPage = registerPage.submitValid();

        assertEquals("Overview", personOverviewPage.getTitle());
        Thread.sleep(1000);
        assertTrue(personOverviewPage.containsUserWithUserid(useridRandom));

    }

    @Test
    public void test_Register_UseridNotFilledIn_ErrorMessageGivenForUseridAndOtherFieldsValueKept(){

        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setFirstName("Jan");
        registerPage.setLastName("Janssens");
        registerPage.setEmail("jan.janssens@hotmail.com");
        registerPage.setPassword("A1a&wateenwachtwoordzeg");

        registerPage.submitInvalid();
        assertEquals("Sign Up",registerPage.getTitle());
        assertTrue(registerPage.hasStickyFirstName("Jan"));
        assertTrue(registerPage.hasStickyLastName("Janssens"));
        assertTrue(registerPage.hasStickyEmail("jan.janssens@hotmail.com"));

    }

    @Test
    public void test_Register_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept(){
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setUserid("janneke");
        registerPage.setLastName("Janssens");
        registerPage.setEmail("jan.janssens@hotmail.com");
        registerPage.setPassword("A1a&wateenwachtwoordzeg");

        registerPage.submitInvalid();
        assertEquals("Sign Up",registerPage.getTitle());
        assertTrue(registerPage.hasStickyLastName("Janssens"));
        assertTrue(registerPage.hasStickyEmail("jan.janssens@hotmail.com"));
        assertTrue(registerPage.hasErrorMessage("No first name given"));
    }

    @Test
    public void test_Register_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept(){
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setUserid("janneke");
        registerPage.setFirstName("Jan");
        registerPage.setEmail("jan.janssens@hotmail.com");
        registerPage.setPassword("A1a&wateenwachtwoordzeg");

        registerPage.submitInvalid();
        assertEquals("Sign Up",registerPage.getTitle());
        assertTrue(registerPage.hasStickyUserid("janneke"));
        assertTrue(registerPage.hasStickyFirstName("Jan"));
        assertTrue(registerPage.hasStickyEmail("jan.janssens@hotmail.com"));
        assertTrue(registerPage.hasErrorMessage("No last name given"));
    }

    @Test
    public void test_Register_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setUserid("janneke");
        registerPage.setFirstName("Jan");
        registerPage.setLastName("Janssens");
        registerPage.setPassword("A1a&wateenwachtwoordzeg");

        registerPage.submitInvalid();
        assertEquals("Sign Up",registerPage.getTitle());
        assertTrue(registerPage.hasStickyUserid("janneke"));
        assertTrue(registerPage.hasStickyFirstName("Jan"));
        assertTrue(registerPage.hasStickyLastName("Janssens"));
        assertTrue(registerPage.hasErrorMessage("No email given"));
    }

    @Test
    public void test_Register_EmailInvalid_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        assertEquals("Sign Up", registerPage.getTitle());
        registerPage.setUserid("jakke");
        registerPage.setFirstName("Jans");
        registerPage.setLastName("Janssens");
        registerPage.setEmail("email");
        registerPage.setPassword("A1a&wateenwachtwoordzeg");
        registerPage.submitInvalid();
        assertTrue(registerPage.hasErrorMessage("Email is not valid"));
    }

    @Test
    public void test_Register_PasswordNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setUserid("janneke");
        registerPage.setFirstName("Jan");
        registerPage.setLastName("Janssens");
        registerPage.setEmail("jan.janssens@hotmail.com");

        registerPage.submitInvalid();
        assertEquals("Sign Up",registerPage.getTitle());
        assertTrue(registerPage.hasStickyUserid("janneke"));
        assertTrue(registerPage.hasStickyFirstName("Jan"));
        assertTrue(registerPage.hasStickyEmail("jan.janssens@hotmail.com"));
        assertTrue(registerPage.hasErrorMessage("No password given"));
    }

    @Test
    public void test_Register_PasswordInvalid_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        assertEquals("Sign Up", registerPage.getTitle());
        registerPage.setUserid("jakke");
        registerPage.setFirstName("Jans");
        registerPage.setLastName("Janssens");
        registerPage.setEmail("email");
        registerPage.setPassword("t");
        registerPage.submitInvalid();
        assertTrue(registerPage.hasErrorMessage("Password is not strong enough"));
    }

    @Test
    public void test_Register_UserAlreadyExists_ErrorMessageGiven() throws InterruptedException {
        String useridRandom = generateRandomUseridInOrderToRunTestMoreThanOnce("jakke");

        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        assertEquals("Sign Up", registerPage.getTitle());
        registerPage.setUserid(useridRandom);
        registerPage.setFirstName("Jans");
        registerPage.setLastName("Janssens");
        registerPage.setEmail("jan.janssens@hotmail.com");
        registerPage.setPassword("A1a&wateenwachtwoordzeg");
        Thread.sleep(1000);

        PersonOverviewPage personOverviewPage = registerPage.submitValid();
        assertTrue(personOverviewPage.containsUserWithUserid(useridRandom));

        registerPage = personOverviewPage.navigateToRegister();
        assertEquals("Sign Up", registerPage.getTitle());
        registerPage.setUserid(useridRandom);
        registerPage.setFirstName("Jans");
        registerPage.setLastName("Janssens");
        registerPage.setEmail("jan.janssens@hotmail.com");
        registerPage.setPassword("A1a&wateenwachtwoordzeg");
        registerPage.submitInvalid();
        assertTrue(registerPage.hasErrorMessage("User with that username already exists"));
    }

    private String generateRandomUseridInOrderToRunTestMoreThanOnce(String component) {
        int random = (int)(Math.random() * 1000 + 1);
        return component+random;
    }

    private void fillOutField(String name,String value) {
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void submitForm(String userid, String firstName,String lastName, String email, String password) {
        fillOutField("userid", userid);
        fillOutField("firstName", firstName);
        fillOutField("lastName",lastName);
        fillOutField("email", email);
        fillOutField("password", password);

        WebElement button=driver.findElement(By.id("signUp"));
        button.click();
    }
}