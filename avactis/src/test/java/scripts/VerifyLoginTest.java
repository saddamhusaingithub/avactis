package scripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import utility.Log;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.ExcelUtils;

public class VerifyLoginTest extends LoginTest {

	@DataProvider(name = "ValidAuthentication")
	public static Object[][] credentials() throws FileNotFoundException, IOException {
		return new Object[][] { { ExcelUtils.getCellValueByHeaderName("Credentials", "UserName", 1), ExcelUtils.getCellValueByHeaderName("Credentials", "Password", 1) } };
	}

	@DataProvider(name = "InvalidAuthentication")
	public static Object[][] invalidcredentials() throws FileNotFoundException, IOException {
		return new Object[][] { { ExcelUtils.getCellValueByHeaderName("Credentials", "UserName", 2), ExcelUtils.getCellValueByHeaderName("Credentials", "Password", 2) } };
	}

	@Test(dataProvider = "ValidAuthentication")
	public void verifyLoginAffirmative(String username, String password) {
		Log.startTestCase("verifyLoginAffirmative");
		
		objAvactisHomePage.clickOnSignIn().enterEmailAndPassword(username, password).clickOnSignInBtn();
		Log.info("Entering valid email and password in sign in Page");
		
		assertTrue(objAvactisHomePage.verifyLoginSuccessfull("Saddam husain"));
		Log.info("registered user successfully signed in avactic website");
		
		objAvactisHomePage.ClickOnSignOutBtn();
		Log.info("Signing out");
		
		Log.endTestCase("verifyLoginAffirmative");
	}

	@Test(dataProvider = "InvalidAuthentication")
	public void verifyLoginNegative(String username, String password) {
		Log.startTestCase("verifyLoginNegative");
		
		objAvactisHomePage.clickOnSignIn().enterEmailAndPassword(username, password).clickOnSignInBtn();
		Log.info("Entering Invalid email and password in sign in Page");
		
		assertEquals(objAvactisHomePage.getIncorrectPasswordMessage(),
				"Account and password could not be identified. Try again or create an account.");
		Log.info("Invalid user got error and not able to sign in avactic website");
	}
}
