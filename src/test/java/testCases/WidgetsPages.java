package testCases;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WidgetsPages extends BaseClass {

	@DataProvider
	public Object[][] windowResolution() {

		return new Object[][] { { 2560, 1440 }, { 1920, 1080 }, { 1280, 720 }, { 1536, 864 }, { 1366, 768 },
				{ 1920, 1200 }, { 1440, 900 }, {1680,1050}};
	}

	@Test(dataProvider = "windowResolution", enabled = true)
	public void checkResolutionForWidgets(int w, int h) throws InterruptedException {
		try {
			setDriver(w, h);
			System.out.println("Resolution = " + w + "*"+ h );
			driver.get(config.getProperty("testsiteurl"));
			Thread.sleep(5000);
			List<WebElement> listofImages = driver.findElements(By.xpath(OR.getProperty("EmarsysImages")));
			System.out.println("Number of elements:" + listofImages.size());

			for (int i = 0; i < listofImages.size(); i++) {

				float width = listofImages.get(i).getSize().getWidth();
				float hight = listofImages.get(i).getSize().getHeight();
			//	System.out.println(listofImages.get(i).getAttribute("title") + " -" + width + "-" + hight + "Resolutio = " + w + "*" + h );
				float roundedValue = width / hight;
				//System.out.println((roundedValue) + "roundedValue");
				DecimalFormat df = new DecimalFormat("#.##");
				df.setRoundingMode(RoundingMode.DOWN);
				float f = Float.parseFloat(df.format(roundedValue));
				if ((f <= 1.74) || f >= 1.78) {
					System.out.println("URL = " + driver.getCurrentUrl() + "\n" + "PPtName = "
							+ listofImages.get(i) + " -" + width + "-" + hight + "\n"
							+ df.format(roundedValue) + "Resolution = " + w + "*" + h);

				}
				assertTrue(df.format(roundedValue).equals("1.77") || df.format(roundedValue).equals("1.76") || df.format(roundedValue).equals("1.75"), "image is not displayed properly");

			}
			driver.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}