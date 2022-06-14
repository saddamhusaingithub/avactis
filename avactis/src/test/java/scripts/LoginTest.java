package scripts;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import objectRepository.AvactisHomePage;
import utility.SetDriverUtils;
import org.apache.commons.io.FileUtils;

public class LoginTest {

	AvactisHomePage objAvactisHomePage;
	WebDriver driver;
	SetDriverUtils objInitializeDriver;

	@BeforeClass
	public void beforeClassSetUp() {
		objInitializeDriver = new SetDriverUtils();
		driver = objInitializeDriver.InitializeDriver();
		objAvactisHomePage = new AvactisHomePage(driver);
	}

	@AfterMethod
	public void screenShot(ITestResult result) {
		// using ITestResult.FAILURE is equals to result.getStatus then it enter
		// into if condition
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				// To create reference of TakesScreenshot
				TakesScreenshot screenshot = (TakesScreenshot) driver;
				// Call method to capture screenshot
				File src = screenshot.getScreenshotAs(OutputType.FILE);
				// Copy files to specific location
				// result.getName() will return name of test case so that
				// screenshot name will be same as test case name
				FileUtils.copyFile(src, new File("F:\\KetanTraining\\AvactisProjectWS\\avactis\\test-output\\screenshotsOnFailures\\" + result.getName() + ".png"));
				System.out.println("Successfully captured a screenshot");
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}
	}

	@AfterClass
	public void afterMethod() {
		objAvactisHomePage.closeBrowser();
	}
}
