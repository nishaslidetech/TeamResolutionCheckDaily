package testCases;

import static org.testng.Assert.assertTrue;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class A4Pages extends BaseClass {
	@DataProvider
	public Object[][] windowResolution() {

		return new Object[][] { { 2560, 1440 }, { 1920, 1200 }, { 1280, 720 }, { 1536, 864 }, { 1366, 768 },
				{ 1920, 1080 }, { 1440, 900 }, { 1680, 1050 }

		};

	}

	@Test(dataProvider = "windowResolution", enabled = false)
	public void checkResolutionForA4Pages(int w, int h) throws InterruptedException {
		setDriver(w, h);
		System.out.println("Resolution for A4 Pages = " + w + "*" + h);
		driver.get(config.getProperty("App_url"));
		WebElement onePager = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("onePager"))));
		onePager.click();
		log.info("onePager is successfully clicked");
		Thread.sleep(3000);

		BaseClass.checkResolutionForA4Pages1(driver, w, h);
		driver.close();

	}
}
