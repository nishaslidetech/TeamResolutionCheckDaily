package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SliPages extends BaseClass {

	@DataProvider
	public Object[][] windowResolution() {

		return new Object[][] { // { 1536, 864 } - getting 1.73
				{ 1280, 720 }, { 1366, 768 }, { 2560, 1440 }, { 1920, 1080 }, { 1920, 1200 }, { 1440, 900 },
				{ 1680, 1050 }, };
	}

	@Test(dataProvider = "windowResolution", enabled = false)
	public void checkResolutionForSliPages(int w, int h) throws InterruptedException {
		setDriver(w, h);
		System.out.println("Resolution = " + w + "*" + h);
		driver.get(config.getProperty("testsiteurl"));
		System.out.println("strategy pages");
		WebElement strategy = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("strategy"))));
		strategy.click();
		Thread.sleep(3000);
		log.info("strategy is successfully clicked");
		BaseClass.checkResolutionSliPages(driver);

		System.out.println("proposals pages");
		WebElement proposals = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("proposals"))));
		proposals.click();
		Thread.sleep(3000);
		log.info("proposals is successfully clicked");
		BaseClass.checkResolutionSliPages(driver);

		System.out.println("kpiDashooard pages");
		WebElement kpiDashooard = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("kpiDashooard"))));
		kpiDashooard.click();
		Thread.sleep(3000);
		log.info("kpiDashooard is successfully clicked");
		BaseClass.checkResolutionSliPages(driver);

		System.out.println("professional pages");
		WebElement professional = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("professional"))));
		professional.click();
		Thread.sleep(3000);
		log.info("professional is successfully clicked");
		BaseClass.checkResolutionSliPages(driver);

		System.out.println("management pages");
		WebElement management = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("management"))));
		management.click();
		Thread.sleep(3000);
		log.info("management is successfully clicked");
		BaseClass.checkResolutionSliPages(driver);

		System.out.println("businessproposals pages");
		WebElement businessproposals = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("businessproposals"))));
		businessproposals.click();
		Thread.sleep(3000);
		log.info("businessproposals is successfully clicked");
		BaseClass.checkResolutionSliPages(driver);

		System.out.println("orgCharts pages");
		WebElement orgCharts = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("orgCharts"))));
		orgCharts.click();
		Thread.sleep(3000);
		log.info("orgCharts is successfully clicked");
		BaseClass.checkResolutionSliPages(driver);

		System.out.println("education pages");
		WebElement education = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("education"))));
		education.click();
		Thread.sleep(3000);
		log.info("education is successfully clicked");
		BaseClass.checkResolutionSliPages(driver);

		System.out.println("digitalMarketing pages");
		WebElement digitalMarketing = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("digitalMarketing"))));
		digitalMarketing.click();
		Thread.sleep(3000);
		log.info("digitalMarketing is successfully clicked");
		BaseClass.checkResolutionSliPages(driver);

		driver.close();
	}
}
