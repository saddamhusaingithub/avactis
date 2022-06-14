package utility;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

import objectRepository.BasePage;

public class SetDriverUtils extends LoadableComponent<BasePage>{

	private WebDriver driver;
	
	public WebDriver InitializeDriver(){
		System.setProperty("webdriver.chrome.driver", "F:\\KetanTraining\\AvactisProjectWS\\avactis\\src\\test\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(Constants.URL);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
		return driver;
	}

	@Override
	protected void isLoaded() throws Error {
		assertEquals(getTitleBeforesLogin(), Constants.Title);
	}

	public String getTitleBeforesLogin() {
		String titlebefore = driver.getTitle();
		return titlebefore;
	}

	@Override
	protected void load() {
	}
}
