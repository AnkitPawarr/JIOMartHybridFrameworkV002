package com.JioMart.Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	private FileInputStream fis;
	private Properties prop;

	public ReadConfig() {
		try {
			fis = new FileInputStream(".//Configuration//config.properties");
			prop = new Properties();
			prop.load(fis);

		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
		}
	}

	public String readbrowser() {
		String value = prop.getProperty("browser");
		if (value != null)
			return value;
		else
			throw new RuntimeException("Browser not specified in Config File.");
	}

	public String readChromePath() {
		String value = prop.getProperty("chromePath");
		if (value != null)
			return value;
		else
			throw new RuntimeException("Chrome Path not specified in Config File.");
	}

	public String readFirefoxPath() {
		String value = prop.getProperty("firefoxPath");
		if (value != null)
			return value;
		else
			throw new RuntimeException("Firefox Path not specified in Config File.");
	}

	public String readbaseUrl() {
		String value = prop.getProperty("baseURL");
		if (value != null)
			return value;
		else
			throw new RuntimeException("Base URL not specified in Config File.");
	}

	public String readSignInUrl() {
		String value = prop.getProperty("signInURL");
		if (value != null)
			return value;
		else
			throw new RuntimeException("Login URL not specified in Config File.");
	}

	public String readSuccessfulLoginUrl() {
		String value = prop.getProperty("successfullLoginURL");
		if (value != null)
			return value;
		else
			throw new RuntimeException("Successful Login URL not specified in Config File.");
	}

	public String readCustomerAcUrl() {
		String value = prop.getProperty("customerAccountURL");
		if (value != null)
			return value;
		else
			throw new RuntimeException("Customer Account setting URL not specified in Config File.");
	}

	public String readHomePageTitle() {
		String value = prop.getProperty("homePageTitle");
		if (value != null)
			return value;
		else
			throw new RuntimeException("Home Page Title is not specified in Config File.");
	}

	public String readLoginPageTitle() {
		String value = prop.getProperty("SignInPageTitle");
		if (value != null)
			return value;
		else
			throw new RuntimeException("Sign In Page Title is not specified in Config File.");
	}
	
	public String readLoginNumber() {
		String value = prop.getProperty("loginNumber");
		if (value != null)
			return value;
		else
			throw new RuntimeException("Login Number not specified in Config File.");
	}

	public String readRegisterNumber() {
		String value = prop.getProperty("registerNumber");
		if (value != null)
			return value;
		else
			throw new RuntimeException("Register Number not specified in Config File.");
	}

	public String readInvalidNumber() {
		String value = prop.getProperty("inValidNumber");
		if (value != null)
			return value;
		else
			throw new RuntimeException("Invalid Number not specified in Config File.");
	}

}
