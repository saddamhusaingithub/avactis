package objectRepository;

import org.openqa.selenium.WebDriver;

public class SignInPage extends BasePage {

	private WebDriver driver;

	public SignInPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	RegisterPage objRegisterPage;
	ProductListPage objProductListPage;

	String registerBtn = "//button[text()='Register']";

	String emailTxt = "//input[@id='account_sign_in_form_email_id']";

	String passwordTxt = "//input[@name='passwd']";

	String signInBtn = "//input[@value='Sign In']";

	public RegisterPage clickOnRegisterButton() {
		click(registerBtn);
		objRegisterPage = new RegisterPage(driver);
		return objRegisterPage;
	}

	public SignInPage enterEmailAndPassword(String email, String password) {
		sendKeys(emailTxt, email);
		sendKeys(passwordTxt, password);
		return this;
	}

	public void clickOnSignInBtn() {
		click(signInBtn);
	}
}
