package objectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductListPage extends BasePage {

	WebDriver driver;

	String cartCount = "//a[@class='top-cart-info-count']";
	String cartProductList = "//div[@class='top-cart-content']/ul/li/strong/a";
	String checkoutBtn = "//a[text()='Checkout']";

	public ProductListPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public ProductListPage addProductInAddToCart(String productName, int productQty) {
		for(int i = 1; i<= productQty; i++)
			click("//h3[text()='" + productName + "']/parent::div//parent::a/following-sibling::div[2]/input");
		return new ProductListPage(driver);
	}

	public String getPriceOfAddedCartProducts(String productName) {
		return getText("//h3[text()='" + productName + "']/parent::div//parent::a/following-sibling::div[1]/span[1]");
	}

	public ProductListPage clickOnTotalCartCounts() {
		click(cartCount);
		return new ProductListPage(driver);
	}

	public boolean verifyAddedProductInCart(String productName) {
		List<WebElement> cartProductListElement = waitFor(cartProductList);
		for (int i = 0; i < cartProductListElement.size(); i++) {
			if (productName.equalsIgnoreCase(cartProductListElement.get(i).getText())) {
				return true;
			}
		}
		return false;
	}

	public CheckoutPage clickOnCheckout() {
		click(checkoutBtn);
		return new CheckoutPage(driver);
	}

}
