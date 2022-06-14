package scripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import objectRepository.CheckoutPage;
import objectRepository.OrderPlacedPage;
import objectRepository.ProductListPage;
import utility.ExcelUtils;
import utility.Log;

public class VerifyCartProductTest extends LoginTest {

	ProductListPage objProductListPage;
	CheckoutPage objCheckoutPage;
	OrderPlacedPage objOrderPlacedPage;

	static String productCategory1;
	static String productSubCategory1;
	static String productCategory2;
	static String productCategory3;
	static String product1;
	static String product2;
	static String product3;
	static int productQty1;
	static int productQty2;
	static int productQty3;
	static String product1Price;
	static String product2Price;
	static String product3Price;
	static String shippingOption = "Ground Shipping";
	static String paymentMethod = "CashOnDelivery";
	
	@DataProvider(name = "ProductDetails")
	public static Object[][] productDetails() throws FileNotFoundException, IOException {
		productCategory1 = ExcelUtils.getCellValueByHeaderName("ProductDetails", "ProductCategory", 1);
		productSubCategory1 = ExcelUtils.getCellValueByHeaderName("ProductDetails", "ProductSubCategory", 1);
		product1 = ExcelUtils.getCellValueByHeaderName("ProductDetails", "Product", 1);
		productCategory2 = ExcelUtils.getCellValueByHeaderName("ProductDetails", "ProductCategory", 2);
		product2 = ExcelUtils.getCellValueByHeaderName("ProductDetails", "Product", 2);
		productCategory3 = ExcelUtils.getCellValueByHeaderName("ProductDetails", "ProductCategory", 3);
		product3 = ExcelUtils.getCellValueByHeaderName("ProductDetails", "Product", 3);
		productQty1 = Integer.parseInt(ExcelUtils.getCellValueByHeaderName("ProductDetails", "Quantity", 1));
		productQty2 = Integer.parseInt(ExcelUtils.getCellValueByHeaderName("ProductDetails", "Quantity", 2));
		productQty3 = Integer.parseInt(ExcelUtils.getCellValueByHeaderName("ProductDetails", "Quantity", 3));
		return new Object[][] { { productCategory1, productSubCategory1, product1, productCategory2, product2,
				productCategory3, product3, productQty1, productQty2, productQty3 } };
	}

	@DataProvider(name = "UserCheckoutDetails")
	public static Object[][] userCheckoutDetails() throws FileNotFoundException, IOException {
		return new Object[][] { { ExcelUtils.getCellValueByHeaderName("UserDetails", "FirstName", 1),
				ExcelUtils.getCellValueByHeaderName("UserDetails", "LastName", 1),
				ExcelUtils.getCellValueByHeaderName("UserDetails", "EmailAddress", 1),
				ExcelUtils.getCellValueByHeaderName("UserDetails", "Country", 1),
				ExcelUtils.getCellValueByHeaderName("UserDetails", "ZipCode", 1),
				ExcelUtils.getCellValueByHeaderName("UserDetails", "State", 1),
				ExcelUtils.getCellValueByHeaderName("UserDetails", "City", 1),
				ExcelUtils.getCellValueByHeaderName("UserDetails", "AddressLine1", 1),
				ExcelUtils.getCellValueByHeaderName("UserDetails", "AddressLine2", 1),
				ExcelUtils.getCellValueByHeaderName("UserDetails", "Phone Number", 1) } };
	}

	@Test(dataProvider = "ProductDetails")
	public void verifyAddedProductsInUserCart(String productCategory1, String productSubCategory1, String product1,
			String productCategory2, String product2, String productCategory3, String product3, int productQty1,
			int productQty2, int productQty3) throws InterruptedException {
		// objAvactisHomePage.clickOnSignIn().enterEmailAndPassword("saddamali13786@gmail.com",
		// "9759064799")
		// .clickOnSignInBtn(); //Comment this step for guest user

		Log.startTestCase("verifyAddedProductsInUserCart");
		
		Log.info("Add First product and get its price");
		product1Price = objAvactisHomePage.hoverOnProductCategories(productCategory1)
				.clickOnProductSubCategories(productSubCategory1).addProductInAddToCart(product1, productQty1)
				.getPriceOfAddedCartProducts(product1);

		Log.info("Add second product and get its price");
		product2Price = objAvactisHomePage.clickOnProductCategories(productCategory2)
				.addProductInAddToCart(product2, productQty2).getPriceOfAddedCartProducts(product2);
		
		Log.info("Add third product and get its price");
		objProductListPage = objAvactisHomePage.clickOnProductCategories(productCategory3)
				.addProductInAddToCart(product3, productQty3);
		product3Price = objProductListPage.getPriceOfAddedCartProducts(product3);
		
		Log.info("Click on Cart button showing total count of added products");
		objProductListPage.clickOnTotalCartCounts();

		Log.info("Verify added products in user cart");
		assertTrue(objProductListPage.verifyAddedProductInCart(product1), product1 + " fails to add in cart");
		assertTrue(objProductListPage.verifyAddedProductInCart(product2), product2 + " fails to add in cart");
		assertTrue(objProductListPage.verifyAddedProductInCart(product3), product3 + " fails to add in cart");
		
		Log.endTestCase("verifyAddedProductsInUserCart");
	}

	@Test(dataProvider = "UserCheckoutDetails", dependsOnMethods = { "verifyAddedProductsInUserCart" })
	public void VerifyCheckoutBillingAddress(String firstName, String lastName, String emailAddress, String country,
			String zip, String state, String city, String addressLine1, String addressLine2, String phoneNumber) {
		
		Log.startTestCase("VerifyCheckoutBillingAddress");
		objCheckoutPage = objProductListPage.clickOnCheckout();

		Log.info("Enter Billing Address");
		objCheckoutPage.enterFirstName(firstName).enterLastName(lastName).enterEmail(emailAddress)
				.selectCountry(country).enterZipCode(zip).selectState(state).enterCity(city)
				.enterAddressLine1(addressLine1).enterAddressLine2(addressLine2).enterPhoneNumber(phoneNumber)
				.clickOnShippingCheckBox().ClickonContinueCheckoutBtn();

		Log.info("Verify Billing Address after checkout");
		assertTrue(objCheckoutPage.verifyBillingAddressAfterCheckout(addressLine1),
				"Address is not saved or coming incorrect");
		
		Log.endTestCase("VerifyCheckoutBillingAddress");
	}

	@Test(dependsOnMethods = { "VerifyCheckoutBillingAddress" })
	public void verifyFinalPriceOfAddedProducts() {
		
		Log.startTestCase("verifyFinalPriceOfAddedProducts");
		
		Log.info("Choose shipping option as Ground and checkout");
		objCheckoutPage.chooseShippingOptions(shippingOption).ClickonContinueCheckoutBtnOnBillingPage();

		Log.info("Verify Final Products with Price and quantity");
		assertEquals(productQty1, objCheckoutPage.getProductQuantity(product1));
		assertEquals(productQty2, objCheckoutPage.getProductQuantity(product2));
		assertEquals(productQty3, objCheckoutPage.getProductQuantity(product3));
		assertEquals(product1Price, objCheckoutPage.getProductCheckoutSalePrice(product1));
		assertEquals(product2Price, objCheckoutPage.getProductCheckoutSalePrice(product2));
		assertEquals(product3Price, objCheckoutPage.getProductCheckoutSalePrice(product3));

		Log.endTestCase("verifyFinalPriceOfAddedProducts");
	}

	@Test(dependsOnMethods = { "verifyFinalPriceOfAddedProducts" })
	public void verifyFinalOrderPlaced() {
		Log.startTestCase("verifyFinalOrderPlaced");
		
		Log.info("Place final order");
		objOrderPlacedPage = objCheckoutPage.clickOnPlaceOrder();

		Log.info("Verify Oder Id, Payment and Shipping method");
		assertTrue(!objOrderPlacedPage.getOrderDetailsForGivenFields("Order Id").isEmpty());
		assertEquals(objOrderPlacedPage.getOrderDetailsForGivenFields("Payment Method"), paymentMethod);
		assertEquals(objOrderPlacedPage.getOrderDetailsForGivenFields("Shipping Method"), shippingOption);

		Log.info("Verify Final Products with Price and quantity");
		assertEquals(productQty1, objCheckoutPage.getProductQuantity(product1));
		assertEquals(productQty2, objCheckoutPage.getProductQuantity(product2));
		assertEquals(productQty3, objCheckoutPage.getProductQuantity(product3));
		assertEquals(product1Price, objCheckoutPage.getProductCheckoutSalePrice(product1));
		assertEquals(product2Price, objCheckoutPage.getProductCheckoutSalePrice(product2));
		assertEquals(product3Price, objCheckoutPage.getProductCheckoutSalePrice(product3));
		
		Log.endTestCase("verifyFinalOrderPlaced");
	}
}
