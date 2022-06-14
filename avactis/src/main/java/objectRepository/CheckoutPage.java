package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
	private WebDriver driver;

	String CheckoutPageHeading = "//div[@class='checkout_rule step_1_active']/span[text()='1. Billing and Shipping Addresses']";
	String firstNameField = "//input[@name='billingInfo[Firstname]']";
	String lastNameField = "//input[@name='billingInfo[Lastname]']";
	String emailField = "//input[@name='billingInfo[Email]']";
	String selectCountry = "//select[@name='billingInfo[Country]']";
	String zipcodeField = "//input[@name='billingInfo[Postcode]']";
	String selectState = "//select[@name='billingInfo[Statemenu]']";
	String cityText = "//input[@name='billingInfo[City]']";
	String addressLine1Text = "//input[@name='billingInfo[Streetline1]']";
	String addressLine2Text = "//input[@name='billingInfo[Streetline2]']";
	String phoneNumberTextField = "//input[@name='billingInfo[Phone]']";
	String clickOnShippingChkBox = "//input[@name='checkbox_shipping_same_as_billing']";
	String continueCheckoutBtn = "//input[@value='Continue Checkout'][1]";
	String continueCheckoutBtnOnBillingPage = "//input[@onclick='submitStep(2);']";
	String savedBillingAddress = "//div[contains(@class, 'billing_form')]/div[@class='form-group']";
	String productSalesPriceCheckoutList = "//table[@class='order_items without_images']/tbody/tr/td[@class='product_sale_price']/span";
	String clickOnPlaceOrder = "//input[@value='Place Order']";
	String KeyOrderDetails = "//div[contains(@class, 'billing_form')]/div[@class='form-group']";

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public CheckoutPage enterFirstName(String firstName) {
		sendKeys(firstNameField, firstName);
		return this;
	}

	public CheckoutPage enterLastName(String lastName) {
		sendKeys(lastNameField, lastName);
		return this;
	}

	public CheckoutPage enterEmail(String email) {
		sendKeys(emailField, email);
		return this;
	}

	public CheckoutPage selectCountry(String country) {
		selectByVisibleText(selectCountry, country);
		return this;
	}

	public CheckoutPage enterZipCode(String zipCode) {
		sendKeys(zipcodeField, zipCode);
		return this;
	}

	public CheckoutPage selectState(String state) {
		selectByVisibleText(selectState, state);
		return this;
	}

	public CheckoutPage enterCity(String city) {
		sendKeys(cityText, city);
		return this;
	}

	public CheckoutPage enterAddressLine1(String addressLine1) {
		sendKeys(addressLine1Text, addressLine1);
		return this;
	}

	public CheckoutPage enterAddressLine2(String addressLine2) {
		sendKeys(addressLine2Text, addressLine2);
		return this;
	}

	public CheckoutPage enterPhoneNumber(String phoneNumber) {
		sendKeys(phoneNumberTextField, phoneNumber);
		return this;
	}

	public CheckoutPage clickOnShippingCheckBox() {
		if (!isSelected(clickOnShippingChkBox))
			click(clickOnShippingChkBox);
		return this;
	}

	public void ClickonContinueCheckoutBtn() {
		click(continueCheckoutBtn);
	}

	public OrderPlacedPage clickOnPlaceOrder() {
		click(clickOnPlaceOrder);
		return new OrderPlacedPage(driver);
	}

	public boolean verifyIdAfterPlaceOrder(String Id) {
		if (getText(KeyOrderDetails).contains(Id))
			return true;
		else
			return false;
	}

	public boolean verifyBillingAddressAfterCheckout(String address) {
		if (getText(savedBillingAddress).contains(address))
			return true;
		else
			return false;
	}

	public void ClickonContinueCheckoutBtnOnBillingPage() {
		click(continueCheckoutBtnOnBillingPage);
	}

	public CheckoutPage chooseShippingOptions(String shippingOptions) {
		click("//label[contains(text(),'" + shippingOptions + "')]/input");
		return this;
	}

	public String getProductCheckoutSalePrice(String productName) {
		return getText("//table[@class='order_items without_images']/tbody/tr/td[@class='product_data']/a[text()='"
				+ productName + "']/parent::td/following-sibling::td[@class='product_sale_price']/span");
	}

	public int getProductQuantity(String productName) {
		return Integer.parseInt(getText("//table[@class='order_items without_images']/tbody/tr/td[@class='product_data']/a[text()='"
				+ productName + "']/parent::td/following-sibling::td[@class='product_quantity_selector']"));
	}
}
