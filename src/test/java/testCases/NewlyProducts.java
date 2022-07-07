package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewlyProducts extends BaseClass {
	@DataProvider
	public Object[][] windowResolution() {

		return new Object[][] { { 2560, 1440 }, { 1920, 1080 }, { 1280, 720 }, { 1920, 1200 }, { 1440, 900 },
				{ 1680, 1050 }
				// {1536, 864},{1680, 1050},{ 1366, 768 }
		};

	}

	@Test(dataProvider = "windowResolution", enabled = true)
	public void checkResolutionForNewlyProducts(int w, int h) throws InterruptedException {
		try {
			setDriver(w, h);
			System.out.println("Resolution = " + w + "*" + h);
			driver.get(config.getProperty("App_url")); //
			log.info("Url is successfully opened");
			Thread.sleep(3000);
			WebElement newlyAdded = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("NewlyAdded"))));

			js.executeScript("arguments[0].click();", newlyAdded);
			log.info("newlyAdded is successfully clicked");
			Thread.sleep(3000);
			checkResolutionForNewlyAndPopular(driver, w, h);
			driver.get("https://www.slideteam.net/new-powerpoint-templates/?p=2"); //
			checkResolutionForNewlyAndPopular(driver, w, h);
			driver.close();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		
	}
}