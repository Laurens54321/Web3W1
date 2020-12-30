import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import pages.ProfilePage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LogInTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Servlet";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\laure\\Documents\\school\\Web 3\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"?command=Profile");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Login_CorrectCredentials_Success()  {
        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);
        assertEquals("Sign In", profilePage.getTitle());

        profilePage.setUserid("admin");
        profilePage.setPassword("t");
        profilePage.submitValidLogIn();

        assertEquals("Profile", profilePage.getTitle());
    }

    @Test
    public void test_Login_UseridNotFilledIn_Fail()  {
        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);
        assertEquals("Sign In", profilePage.getTitle());

        profilePage.setPassword("t");
        profilePage.submitInValidLogIn();

        assertEquals("Sign In", profilePage.getTitle());
        assertTrue(profilePage.hasErrorMessage("No userid given"));
    }

    @Test
    public void test_Login_PasswordNotFilledIn_HasStickyUserid_Fail()  {
        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);
        assertEquals("Sign In", profilePage.getTitle());

        profilePage.setUserid("admin");
        profilePage.submitInValidLogIn();

        assertEquals("Sign In", profilePage.getTitle());
        assertTrue(profilePage.hasErrorMessage("No password given"));
    }

    @Test
    public void test_Login_InvalidUsername_Fail()  {
        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);
        assertEquals("Sign In", profilePage.getTitle());

        profilePage.setUserid("qmsldkfjqsmlkfjqsmldkfj");
        profilePage.setPassword("t");
        profilePage.submitInValidLogIn();

        assertEquals("Sign In", profilePage.getTitle());
        assertTrue(profilePage.hasErrorMessage("Username not found"));
    }

    @Test
    public void test_Login_InvalidPassword_Fail()  {
        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);
        assertEquals("Sign In", profilePage.getTitle());

        profilePage.setUserid("admin");
        profilePage.setPassword("qsdmfkjqsmdlfkjqsdmlfkjqsd");
        profilePage.submitInValidLogIn();

        assertEquals("Sign In", profilePage.getTitle());
        assertTrue(profilePage.hasErrorMessage("Password Incorrect"));
    }

    @Test
    public void test_Login_Logout_Succes(){
        test_Login_CorrectCredentials_Success();
        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);
        HomePage homePage = profilePage.submitLogOut();
        assertEquals("Home", homePage.getTitle());
    }
}
