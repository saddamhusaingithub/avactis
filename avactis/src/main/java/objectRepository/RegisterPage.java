package objectRepository;

import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
	WebDriver driver;

	String registerPageHeading = "//h1[text()='Registration Form']";
	String emailTxt = "//input[@name='customer_info[Customer][Email]']";
	String passwordTxt = "//input[@name='customer_info[Customer][Password]']";
	String reTypePasswordTxt = "//input[@name='customer_info[Customer][RePassword]']";
	String firstNameTxt = "//input[@name='customer_info[Customer][FirstName]']";
	String lastNameTxt = "//input[@name='customer_info[Customer][LastName]']";
	String selectCountry = "//select[@id='customer_info_Customer_Country']";
	String selectState = "//select[@id='customer_info_Customer_State']";
	String zipCodeTxt = "//input[@name='customer_info[Customer][ZipCode]']";
	String cityTxt = "//input[@name='customer_info[Customer][City]']";
	String addressLine1Txt = "//input[@name='customer_info[Customer][Streetline1]']";
	String addressLine2Txt = "//input[@name='customer_info[Customer][Streetline2]']";
	String phoneNumberTxt = "//input[@name='customer_info[Customer][Phone]']";
	String registerBtn = "//input[@value='Register']";
	String incorrectPasswordMessage = "//div[contains(@class,'note-danger')]";

	public RegisterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public RegisterPage enterEmail(String email) {
		sendKeys(emailTxt, email);
		return this;
	}

	public RegisterPage enterPassword(String password) {
		sendKeys(passwordTxt, password);
		return this;
	}

	public RegisterPage enterReTypePassword(String reTypePassword) {
		sendKeys(reTypePasswordTxt, reTypePassword);
		return this;
	}

	public RegisterPage enterFirstName(String firstName) {
		sendKeys(firstNameTxt, firstName);
		return this;
	}

	public RegisterPage enterLastName(String lastName) {
		sendKeys(lastNameTxt, lastName);
		return this;
	}

	public RegisterPage selectCountry(String country) {
		selectByVisibleText(selectCountry, country);
		return this;
	}

	public RegisterPage selectState(String state) {
		selectByVisibleText(selectState, state);
		return this;
	}

	public RegisterPage enterZipCode(String zipCode) {
		sendKeys(zipCodeTxt, zipCode);
		return this;
	}

	public RegisterPage enterCity(String city) {
		sendKeys(cityTxt, city);
		return this;
	}

	public RegisterPage enteraddressLine1(String addressLine1) {
		sendKeys(addressLine1Txt, addressLine1);
		return this;
	}

	public RegisterPage enteraddressLine2(String addressLine2) {
		sendKeys(addressLine2Txt, addressLine2);
		return this;
	}

	public RegisterPage enterPhoneNumber(String phoneNumber) {
		sendKeys(phoneNumberTxt, phoneNumber);
		return this;
	}

	public void clickOnRegisterBtn() {
		click(registerBtn);
	}

	public String getIncorrectPasswordMessage() {
		return getText(incorrectPasswordMessage);
	}

}
