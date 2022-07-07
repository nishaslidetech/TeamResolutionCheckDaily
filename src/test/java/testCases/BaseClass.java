package testCases;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileInputStream;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	public static String local_chrome;
	public static String local_FFbrowser;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();

	public static Logger log = Logger.getLogger(BaseClass.class);

	public static JavascriptExecutor js;
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public static WebDriver driver;

	@BeforeSuite
	public void setUp() {

		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\teamProperties\\log4j.properties");

		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\teamProperties\\Config.properties");

			config.load(fis);
			log.info("Config file loaded !!!");
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\teamProperties\\OR.properties");
			OR.load(fis);
			log.info("OR file loaded !!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void setDriver(int w, int h) throws InterruptedException {

		local_chrome = config.getProperty("local_chrome");
		local_FFbrowser = config.getProperty("local_FFbrowser");
		// on source lab setup

		System.out.println(" local_FFbrowser= " + local_FFbrowser);

		System.out.println("local_chrome = " + local_chrome);

		if ((local_chrome.equals("yes"))) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			// options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			options.addArguments("--incognito"); // DesiredCapabilities object
			DesiredCapabilities c = DesiredCapabilities.chrome(); // set capability to
			c.setCapability(ChromeOptions.CAPABILITY, options);

			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(w, h));
			driver.manage().window().maximize();

			// driver.get(AppURL);
			driver.manage().timeouts().implicitlyWait(9000, TimeUnit.MILLISECONDS);
			driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 30);
			js = (JavascriptExecutor) driver;
		}
		// if (browser.equalsIgnoreCase("firefox"))

		// if (browser.equalsIgnoreCase("chrome"))
		else if ((local_FFbrowser.equals("yes"))) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(9000, TimeUnit.MILLISECONDS);
			driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 30);
			js = (JavascriptExecutor) driver;

			Thread.sleep(1000);
		} else {

			System.out.println("platform does not provide");
		}

	}

	public static void checkResolutionSliPages(WebDriver driver) throws InterruptedException {

		List<WebElement> listofImages = driver.findElements(By.xpath(OR.getProperty("SliImages")));
		System.out.println("Number of elements:" + listofImages.size());

		for (int i = 0; i < listofImages.size(); i++) {

			float width = listofImages.get(i).getSize().getWidth();
			float hight = listofImages.get(i).getSize().getHeight();

			// System.out.println(listofImages.get(i).getAttribute("title") + " -" + width +
			// "-" + hight);

			float roundedValue = width / hight;
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.DOWN);
			// System.out.println(df.format(roundedValue));
			float f = Float.parseFloat(df.format(roundedValue));

			// System.out.println(f + " = float value");
			if ((f < 1.75) || f > 1.79) {
				System.out.println("URL = " + driver.getCurrentUrl() + "\n" + "PPtName = "
						+ listofImages.get(i).getAttribute("title") + " -" + width + "-" + hight + "\n"
						+ df.format(roundedValue));

			}

			assertTrue(df.format(roundedValue).equals("1.77") || df.format(roundedValue).equals("1.79")
					|| df.format(roundedValue).equals("1.76") || df.format(roundedValue).equals("1.78")
					|| df.format(roundedValue).equals("1.77"), "image is not displayed properly");

			/*
			 * System.out.println(f + " = float value"); if ((f > 1.79) || (f <= 1.47) || (f
			 * >= 1.49 && f <= 1.74)) { System.out.println("URL = " + driver.getCurrentUrl()
			 * + "\n" + "PPtName = " + i + " -" + width + "-" + hight + "\n" +
			 * df.format(roundedValue));
			 * 
			 * }
			 * 
			 * 
			 * assertTrue( df.format(roundedValue).equals("1.77") ||
			 * df.format(roundedValue).equals("1.79") ||
			 * df.format(roundedValue).equals("1.76") ||
			 * df.format(roundedValue).equals("1.78"), "image is not displayed properly");
			 */

		}
	}

	public static void checkResolutionForNewlyAndPopular(WebDriver driver, int w, int h) throws InterruptedException {

		try {
			List<WebElement> listofImages = driver.findElements(By.xpath(OR.getProperty("images1")));
//	System.out.println("Number of elements:" + listofImages.size());

			for (int i = 0; i < listofImages.size(); i++) {

				float width = listofImages.get(i).getSize().getWidth();
				float hight = listofImages.get(i).getSize().getHeight();

				// System.out.println(listofImages.get(i).getAttribute("title") + " -" + width +
				// "-" + hight);

				float roundedValue = width / hight;
				DecimalFormat df = new DecimalFormat("#.##");
				df.setRoundingMode(RoundingMode.DOWN);
				// System.out.println(df.format(roundedValue));
				float f = Float.parseFloat(df.format(roundedValue));
				System.out.println(i + "--f = " + f);
				if (f >= 1.78 || f <= 1.47 || f >= 1.49 && f <= 1.74) {
					System.out.println("URL = " + driver.getCurrentUrl() + "\n" + "PPtNumber = " + i + " -" + width
							+ "-" + hight + "\n" + df.format(roundedValue) + "Resolution = " + w + "*" + h);

				}

				assertTrue(
						df.format(roundedValue).equals("1.77") || df.format(roundedValue).equals("1.75")
								|| df.format(roundedValue).equals("1.76") || df.format(roundedValue).equals("1.48")
								|| df.format(roundedValue).equals("1.74") || df.format(roundedValue).equals("1.73"),
						"image is not displayed properly");

			}
		} catch (NumberFormatException e) {

			e.printStackTrace();
		}

	}

	public static void checkResolutionForA4Pages1(WebDriver driver, int w, int h) {

		try {
			List<WebElement> listofImages = driver.findElements(By.xpath(OR.getProperty("A4images")));
			System.out.println("Number of elements:" + listofImages.size());

			for (int i = 0; i < listofImages.size(); i++) {

				float width = listofImages.get(i).getSize().getWidth();
				float hight = listofImages.get(i).getSize().getHeight();
				float roundedValue = width / hight;
				DecimalFormat df = new DecimalFormat("#.##");
				df.setRoundingMode(RoundingMode.DOWN);
				// System.out.println(df.format(roundedValue));
				float f = Float.parseFloat(df.format(roundedValue));
				System.out.println(i + "--" + f + " = float value");
				if (f >= 0.70 || f <= 0.68) {
					System.out.println("URL = " + driver.getCurrentUrl() + "\n" + "PPtName = " + i + " -" + width + "-"
							+ hight + "\n" + df.format(roundedValue) + "Resolution = " + w + "*" + h);

				}
				assertTrue(df.format(roundedValue).equals("0.69"), "image is not displayed properly");

			}
		} catch (NumberFormatException e) {

			e.printStackTrace();
		}
	}

}
