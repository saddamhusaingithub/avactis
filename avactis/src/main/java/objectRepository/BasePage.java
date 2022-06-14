package objectRepository;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Constants;

public class BasePage extends LoadableComponent<BasePage> {

	private WebDriver driver;
	public WebDriverWait wait;
	public Actions actions;
	

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		actions = new Actions(driver);
	}

	public void click(String xpathExpression) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression))).click();
	}

	public void sendKeys(String xpathExpression, String text) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression))).sendKeys(text);
	}

	public String getText(String xpathExpression) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression))).getText();
	}

	public void moveToElement(String xpathExpression) {
		actions.moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression))))
				.perform();
	}

	public void selectByVisibleText(String xpathExpression, String visibleText) {
		Select dropdown = new Select(
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression))));
		dropdown.selectByVisibleText(visibleText);
	}

	public boolean isSelected(String xpathExpression) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression))).isSelected();
	}

	public List<WebElement> waitFor(String xpathExpression) {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathExpression)));
	}

	@Override
	protected void isLoaded() throws Error {
		assertEquals(driver.getTitle(), Constants.Title);
	}

	

	@Override
	protected void load() {
	}

	public void closeBrowser() {
		driver.quit();
	}
}
