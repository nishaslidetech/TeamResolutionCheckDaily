package testCases;

import static org.testng.Assert.assertTrue;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class rough extends BaseClass {

	public void checkResolution() {

		List<WebElement> listofImages = driver.findElements(By.xpath(OR.getProperty("images1")));
		System.out.println("Size  = " + listofImages.size());
		for (int i = 0; i < listofImages.size(); i++) {
			float width = listofImages.get(i).getSize().getWidth();
			float hight = listofImages.get(i).getSize().getHeight();

			float roundedValue = width / hight;
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.DOWN);
			// System.out.println(df.format(roundedValue));
			float f = Float.parseFloat(df.format(roundedValue));
			System.out.println(i + "--" + f + " = float value");

			System.out.println(
					"URL = " + driver.getCurrentUrl() + "\n" + "PPtName = " + listofImages.get(i).getAttribute("title")
							+ " -" + width + "-" + hight + "\n" + df.format(roundedValue));
		}
	}

	@Test
	public void test() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();

		driver = new ChromeDriver(options);
		// driver.manage().window().setSize(new Dimension(1536, 864));

		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
		driver.get("https://www.slideteam.net/professional-powerpoint-templates");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String url = driver.getCurrentUrl();
		String URL = "https://www.slideteam.net/professional-powerpoint-templates?p=3";
		
		for (int second = 0;; second++) {
		js.executeScript("window.scrollBy(0,1200)", "");
		
		Thread.sleep(3000);
		checkResolution();
		
		if (!(driver.findElements(By.xpath("//em[normalize-space()='Loading - please wait...']")).isEmpty()))
		{
			Thread.sleep(10000);
		

		}
		
		else if (url.equals(URL))
		{
			break;
			
		}
		
		}
	}

}