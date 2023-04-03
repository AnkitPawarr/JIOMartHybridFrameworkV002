package com.JioMart.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.JioMart.Resources.ReadConfig;
import com.JioMart.Resources.XLUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class base {

	ReadConfig readConfig = new ReadConfig();

	// public String browser = readConfig.readbrowser();
	public String chromePath = readConfig.readChromePath();
	public String firefoxPath = readConfig.readFirefoxPath();
	public String baseUrl = readConfig.readbaseUrl();
	public String signInUrl = readConfig.readSignInUrl();
	public String successfulLoginUrl = readConfig.readSuccessfulLoginUrl();
	public String custAccountUrl = readConfig.readCustomerAcUrl();
	public String loginNumber = readConfig.readLoginNumber();
	public String registerNumber = readConfig.readRegisterNumber();
	public String inValidNumber = readConfig.readInvalidNumber();
	public String homePageTitle = readConfig.readHomePageTitle();
	public String loginPageTitle = readConfig.readLoginPageTitle();

	public static ChromeOptions co;
	public static WebDriver driver;
	public static Logger log;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setUp(String br) throws Exception {

		log = Logger.getLogger("JioMart");
		PropertyConfigurator.configure("Log4j.properties");

		if (br.equals("Chrome")) {
			co = new ChromeOptions();
			co.addArguments("--icognito");
			// co.setHeadless(true);

			driver = WebDriverManager.chromedriver().capabilities(co).create();

			/*
			 * System.setProperty("webdriver.chrome.driver", chromePath); option = new
			 * ChromeOptions(); option.addArguments("--incognito"); driver = new
			 * ChromeDriver(option);
			 */

		} else if (br.equals("Firefox")) {
			driver = WebDriverManager.firefoxdriver().create();

			/*
			 * System.setProperty("webdriver.gecko.driver", firefoxPath); driver = new
			 * FirefoxDriver();
			 */

		} else if (br.equals("Safari")) {
			driver = WebDriverManager.safaridriver().create();

			// driver = new SafariDriver();

		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);

		js = (JavascriptExecutor) driver;

	}

	@BeforeMethod(alwaysRun = true)
	public void readMethodName(Method method) {
		System.out.println("***************************************************** TEST METHOD NAME : "
				+ method.getName() + " *******************************************************");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	public String getScreenshot(WebDriver driver, String testCaseName) throws IOException {

		String date = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		TakesScreenshot ss = (TakesScreenshot) driver;
		File source = ss.getScreenshotAs(OutputType.FILE);

		String ssPath = System.getProperty("user.dir") + "//FailedScreenshots//" + testCaseName + " " + date + ".png";
		File destination = new File(ssPath);
		FileUtils.copyFile(source, destination);

		return ssPath;

	}
}
