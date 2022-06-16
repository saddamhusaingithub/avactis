package scripts;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import objectRepository.RegisterPage;
import utility.Log;

public class VerifyRegisterationTest extends LoginTest {
	WebDriver driver;

	RegisterPage objRegisterPage;

	@Test
	public void verifyRegisterationAffirmative() {
		Log.startTestCase("verifyRegisterationAffirmative");
		
		Log.info("Entering valid user details for successfull registeration");
		objAvactisHomePage.clickOnMyAccount().clickOnRegisterButton().enterEmail("Sameer@gmail.com")
				.enterPassword("9759064799").enterReTypePassword("9759064799").enterFirstName("Sameer")
				.enterLastName("Khan").selectCountry("India").selectState("Gujarat").enterZipCode("380055")
				.enterCity("Ahmedabad").enteraddressLine1(" c-101, Al arsh-3").enteraddressLine2("juhapura")
				.enterPhoneNumber("9759064799").clickOnRegisterBtn();
		
		Log.info("Verify Registeration successfull message");
		assertEquals(objAvactisHomePage.getAccountCreatedMessage(),
				"Account created successfully. You are now registered.");
		
		objAvactisHomePage.ClickOnSignOutBtn();
		
		Log.endTestCase("verifyRegisterationAffirmative");
	}

	@Test
	public void verifyRegisterationNegative() {
		Log.startTestCase("verifyRegisterationNegative");
		
		Log.info("Entering invalid user details for successfull registeration");
		objRegisterPage = objAvactisHomePage.clickOnMyAccount().clickOnRegisterButton();
		objRegisterPage.enterEmail("zia2208@gmail.com").enterPassword("9759064799").enterReTypePassword("9627841412")
				.enterFirstName("Mumtaz").enterLastName("Husain").selectCountry("India").selectState("Gujarat")
				.enterZipCode("380055").enterCity("Ahmedabad").enteraddressLine1(" c-101, Al arsh-3")
				.enteraddressLine2("juhapura").enterPhoneNumber("9759064799").clickOnRegisterBtn();
		
		Log.info("Verify registeration failed message");
		assertEquals(objRegisterPage.getIncorrectPasswordMessage(),
				"The password you entered is incorrect. Please enter the correct password.");
		
		Log.endTestCase("verifyRegisterationNegative");
	}
}
