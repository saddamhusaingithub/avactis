package objectRepository;

import org.openqa.selenium.WebDriver;

public class AvactisHomePage extends BasePage {

	private WebDriver driver;

	public AvactisHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	SignInPage objSignInPage;
	ProductListPage objProductListPage;

	String myAccount = "//a[text()='My Account']";

	String accountCreatedMessage = "//div[contains(@class,'note-success')][1]";

	String signInBtn = "//a[text()='Sign In']";

	String signOutBtn = "//a[text()='Sign Out']";
	
	String welcomeUsernameHeader = "//span[@class='header_wel']/parent::span";

	String invalidLoginMessage = "//div[contains(@class,'note-danger')]";

	public SignInPage clickOnMyAccount() {
		click(myAccount);
		return new SignInPage(driver);
	}

	public SignInPage clickOnSignIn() {
		click(signInBtn);
		return new SignInPage(driver);
	}

	public String getAccountCreatedMessage() {
		return getText(accountCreatedMessage);
	}

	public boolean verifyLoginSuccessfull(String userName) {
		if (getText(welcomeUsernameHeader).contains(userName))
			return true;
		else
			return false;
	}

	public void ClickOnSignOutBtn(){
		click(signOutBtn);
	}
	
	public String getIncorrectPasswordMessage() {
		return getText(invalidLoginMessage);
	}

	public AvactisHomePage hoverOnProductCategories(String categoryName) {
		moveToElement("//div[@class='header-navigation']/ul/li/a[text()='" + categoryName + "'][1]");
		return this;
	}

	public ProductListPage clickOnProductSubCategories(String subCategoryName) {
		click("//ul[@class='dropdown-menu']/li/a[text()='" + subCategoryName + "']");
		return objProductListPage = new ProductListPage(driver);
	}

	public ProductListPage clickOnProductCategories(String categoryName) {
		click("//div[@class='header-navigation']/ul/li/a[text()='" + categoryName + "']");
		return objProductListPage = new ProductListPage(driver);
	}
}
