import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.ProfilePage;
import pages.YourReservationsPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReservationTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Servlet";

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\laure\\Documents\\school\\Web 3\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"?command=Profile");

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);
        profilePage.setUserid("admin");
        profilePage.setPassword("t");
        profilePage.submitValidLogIn();
        assertEquals("Profile", profilePage.getTitle());
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Reservation_AllFieldFilledInCorrectly_Succes() {
        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);
        profilePage.setFieldField("1");
        profilePage.setPhonenrField("045678910");
        profilePage.setEmailField("jan.janssens@hotmail.com");
        YourReservationsPage yourReservationsPage = profilePage.submitValidField();
        assertEquals("Your Reservations", yourReservationsPage.getTitle());
        assertTrue(yourReservationsPage.containsReservationWithName("admin"));
    }

    @Test
    public void test_Reservation_FieldNotFilledIn_OtherFieldsAreSticky_Fail() {
        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);
        profilePage.setPhonenrField("045678910");
        profilePage.setEmailField("jan.janssens@hotmail.com");
        profilePage.submitInValidField();
        assertEquals("Profile", profilePage.getTitle());
        assertTrue(profilePage.hasErrorMessage("No field given"));
    }

    @Test
    public void test_Reservation_PhonenrNotFilledIn_OtherFieldsAreSticky_Fail() {
        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);
        profilePage.setFieldField("1");
        profilePage.setEmailField("jan.janssens@hotmail.com");
        profilePage.submitInValidField();
        assertEquals("Profile", profilePage.getTitle());
        assertTrue(profilePage.hasErrorMessage("No phonenr given"));
    }

    @Test
    public void test_Reservation_EmailNotFilledIn_OtherFieldsAreSticky_Fail() {
        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);
        profilePage.setFieldField("1");
        profilePage.setPhonenrField("045678910");
        profilePage.submitInValidField();
        assertEquals("Profile", profilePage.getTitle());
        assertTrue(profilePage.hasErrorMessage("No email given"));
    }

}
