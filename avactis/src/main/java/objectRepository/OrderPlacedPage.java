package objectRepository;

import org.openqa.selenium.WebDriver;

public class OrderPlacedPage extends BasePage {

	WebDriver driver;

	public OrderPlacedPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public String getOrderDetailsForGivenFields(String orderFieldName) {
		return getText("//label[text()='" + orderFieldName + ":']/following-sibling::div");
	}
}
