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
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\laure\\Documents\\school\\Web 3\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"?command=Register");
    }

    @After
    public void clean() {
        //driver.quit();
    }

    @Test
    public void test_Register_AllFieldsFilledInCorrectly_UserIsRegistered() throws InterruptedException {
        String useridRandom = generateRandomUseridInOrderToRunTestMoreThanOnce("jakke");

        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setUserid(useridRandom);
        registerPage.setFirstName("Jans");
        registerPage.setLastName("Janssens");
        registerPage.setEmail("jan.janssens@hotmail.com");
        registerPage.setPassword("A1a&wateenwachtwoordzeg");
        Thread.sleep(20000);
        PersonOverviewPage personOverviewPage = registerPage.submitValid();

        assertEquals("Overview", personOverviewPage.getTitle());
        Thread.sleep(2000);
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
        assertEquals("Sign up",registerPage.getTitle());
        assertTrue(registerPage.hasStickyFirstName("Jan"));
        assertTrue(registerPage.hasStickyLastName("Janssens"));
        assertTrue(registerPage.hasStickyEmail("jan.janssens@hotmail.com"));

    }

    @Test
    public void test_Register_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept(){
        submitForm("jakke", "", "Janssens", "jan.janssens@hotmail.com", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No firstname given", errorMsg.getText());

        WebElement fieldUserid=driver.findElement(By.id("userid"));
        assertEquals("jakke",fieldUserid.getAttribute("value"));

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept(){
        submitForm("jakke", "Jan", "", "jan.janssens@hotmail.com", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No last name given", errorMsg.getText());

        WebElement fieldUserid=driver.findElement(By.id("userid"));
        assertEquals("jakke",fieldUserid.getAttribute("value"));

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        submitForm("jakke", "Jan", "Janssens", "", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No email given", errorMsg.getText());

        WebElement fieldUserid=driver.findElement(By.id("userid"));
        assertEquals("jakke",fieldUserid.getAttribute("value"));

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("",fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_PasswordNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        submitForm("jakke", "Jan", "Janssens", "jan.janssens@hotmail.com", "");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No password given", errorMsg.getText());

        WebElement fieldUserid=driver.findElement(By.id("userid"));
        assertEquals("jakke",fieldUserid.getAttribute("value"));

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_UserAlreadyExists_ErrorMessageGiven(){
        String useridRandom = generateRandomUseridInOrderToRunTestMoreThanOnce("pierke");
        submitForm(useridRandom, "Pieter", "Pieters", "pieter.pieters@hotmail.com", "1234");

        driver.get(path+"?command=Register");

        submitForm(useridRandom, "Pieter", "Pieters", "pieter.pieters@hotmail.com", "1234");

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("User with that username already exists", errorMsg.getText());

        WebElement fieldUserid=driver.findElement(By.id("userid"));
        assertEquals(useridRandom,fieldUserid.getAttribute("value"));

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Pieter",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Pieters",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("pieter.pieters@hotmail.com",fieldEmail.getAttribute("value"));
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