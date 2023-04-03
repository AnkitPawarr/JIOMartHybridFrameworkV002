package com.JioMart.Base;

import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.JioMart.Util.ReadConfig;

public class base {

	ReadConfig readConfig = new ReadConfig();

	public String browser = readConfig.readbrowser();
	public String chromePath = readConfig.readbrowser();
	public String firefoxPath = readConfig.readbrowser();
	public String baseUrl = readConfig.readbrowser();
	public String signInUrl = readConfig.readbrowser();
	public String successfulLoginUrl = readConfig.readbrowser();
	public String custAccountUrl = readConfig.readbrowser();
	public String loginNumber = readConfig.readbrowser();
	public String registerNumber = readConfig.readbrowser();
	public String inValidNumber = readConfig.readbrowser();

	public static WebDriver driver;
	//public static Logger log;
	//public static WebDriverWait wait;
	
	@BeforeClass
	public void setUp() {
		//log = Logger.getLogger("JioMart");
		//PropertyConfigurator.configure("Log4j.properties");

		if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", chromePath);
			driver = new ChromeDriver();

		} else if (browser.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", firefoxPath);
			driver = new FirefoxDriver();

		} else if (browser.equals("Safari")) {
			driver = new SafariDriver();

		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		//wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	@BeforeMethod
	public void readMethodName(Method method) {
		System.out.println("***************************************************** Test Method Name : "
				+ method.getName() + " *******************************************************");
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
