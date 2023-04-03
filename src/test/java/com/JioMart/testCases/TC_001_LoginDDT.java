package com.JioMart.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.JioMart.Pages.PO_001_HomePage;
import com.JioMart.Pages.PO_002_LoginPage;
import com.JioMart.Pages.PO_003_RegisterPage;
import com.JioMart.Pages.PO_004_DashboardPage;
import com.JioMart.Pages.PO_005_CustomerAccountPage;
import com.JioMart.Resources.XLUtilities;
import com.JioMart.base.base;

public class TC_001_LoginDDT extends base {
	XLUtilities xlUtil;

	PO_001_HomePage homePage;
	PO_002_LoginPage loginPage;
	PO_003_RegisterPage registerPage;
	PO_004_DashboardPage dashboardPage;
	PO_005_CustomerAccountPage custAccountPage;

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		homePage = new PO_001_HomePage(driver);

		driver.get(baseUrl);
		if (driver.getTitle().equals(homePageTitle)) {
			Assert.assertTrue(true);
			log.info("Home Page Title is as expected : " + homePageTitle);
			log.info("User successfully navigated to Home Page.");
		} else {
			log.error("Home Page Title is not matching.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 2)
	public void verifyLoginPageTitleTest() {
		loginPage = homePage.clickSignInBtn();
		if (driver.getTitle().equals(loginPageTitle)) {
			Assert.assertTrue(true);
			log.info("Login Page Title is as expected : " + loginPageTitle);
			log.info("User successfully navigated to Login Page.");
			loginPage.clickHomeLink();
		} else {
			log.error("Login Page Title is not matching.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 3)
	public void VerifyProImage() {

		if (driver.getTitle().equals(loginPageTitle)) {
			loginPage.clickHomeLink();
		}

		homePage.clickSignInBtn();

		boolean flag = loginPage.validateLoginStoreImg();
		if (flag == true) {
			Assert.assertTrue(true);
			loginPage.clickHomeLink();
			log.info("Store Image is Available.");
		} else {
			log.error("Store Image is not availabe.");
			Assert.assertTrue(false);
		}
	}

	@Test(dataProvider = "login", priority = 4, dependsOnMethods = { "verifyLoginPageTitleTest" })
	public void VerifyLoginTestDDT(String testData, String MobileNumber) throws InterruptedException {

		if (driver.getTitle().equals(loginPageTitle)) {
			loginPage.clickHomeLink();
		}

		homePage.clickSignInBtn();
		log.info("User landed on Sign In screen.");

		log.info(testData);
		loginPage.enterMobileNo(MobileNumber);
		log.info("Mobile Number has been entered. Entered number is : " + MobileNumber);
		loginPage.clickGetOtpCTA();
		Thread.sleep(1000);

		String header = loginPage.getLoginPageHeader();

		if (header.equals("Verify Phone Number")) {
			/*
			 * if (!driver.getPageSource().contains(loginPage.LoginOtpText)) { String
			 * numberError = loginPage.getLoginNoError();
			 * log.error("Found Mobile Number Error : " + numberError);
			 * 
			 * loginPage.clickHomeLink(); Assert.assertTrue(false); }
			 */

			loginPage.enterLoginOTP();
			log.info("OTP has been Entered.");
			dashboardPage = loginPage.clickLoginVerifyCTA();
			Thread.sleep(2000);

			if (driver.getCurrentUrl().equals(successfulLoginUrl)) {
				Assert.assertTrue(true);
				log.info("Successfully Logged In.");

				String userName = dashboardPage.getUserName();
				log.info("Logged In User name is : " + userName);

				custAccountPage = dashboardPage.clickUser();
				log.info("User Profile has been clicked.");
				Thread.sleep(1000);
				js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
				custAccountPage.openLogoutPopup();
				log.info("Sign Out CTA has been clicked to open a Sign Out popup.");
				custAccountPage.clickCancelLogoutCTA();
				log.info("Cancel Sign Out CTA is been clicked.");

				custAccountPage.openLogoutPopup();
				log.info("Sign Out CTA has been clicked to open a Sign Out popup.");
				custAccountPage.clickLogoutBtn(); // SIGN OUT BUTTON NOT WORKING AT 1ST CLICK
				log.info("User Logged Out successfully.");

			} else {
				String OTPError = loginPage.LoginOTPError();
				log.error("Login has been Failed due to an OTP Error : " + OTPError);

				Assert.assertTrue(false);
			}

		} else {
			log.error("User Not Found for Entered Mobile Number.");

			Assert.assertTrue(false);
		}

	}

	@DataProvider(name = "login")
	public String[][] getLoginData() throws IOException {
		String loginPath = ".//testData//LoginDDT.xlsx";

		xlUtil = new XLUtilities(loginPath);
		int totalRow = xlUtil.getRowCount("Login");
		int totalColumn = xlUtil.getColCount("Login", 1);

		String loginData[][] = new String[totalRow][totalColumn];

		for (int r = 1; r <= totalRow; r++) {
			for (int c = 0; c < totalColumn; c++) {
				loginData[r - 1][c] = xlUtil.readData("Login", r, c);
			}
		}
		return loginData;
	}
}
