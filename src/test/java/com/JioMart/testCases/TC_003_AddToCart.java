package com.JioMart.testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.JioMart.Pages.PO_002_LoginPage;
import com.JioMart.Pages.PO_004_DashboardPage;
import com.JioMart.Pages.PO_006_CartPage;
import com.JioMart.Resources.XLUtilities;
import com.JioMart.base.base;

public class TC_003_AddToCart extends base {
	PO_002_LoginPage loginPage;
	PO_004_DashboardPage dashboardPage;
	PO_006_CartPage cartPage;

	XLUtilities xlUtil;
	int a = 0;

	@Test(priority = 1)
	public void verifyLoginTest() throws InterruptedException {

		loginPage = new PO_002_LoginPage(driver);
		driver.get(signInUrl);
		log.info("User landed on Sign in page.");

		loginPage.enterMobileNo(loginNumber);
		loginPage.clickGetOtpCTA();

		loginPage.enterLoginOTP();
		log.info("Mobile number and OTP is entered.");

		dashboardPage = loginPage.clickLoginVerifyCTA();
		/*
		 * Thread.sleep(1000); if (driver.getCurrentUrl().equals(successfulLoginUrl)) {
		 * Assert.assertTrue(true); log.info("User Logged in Successfully"); } else {
		 * log.error("Unable to Login"); Assert.assertTrue(false); }
		 */

	}

	@Test(dependsOnMethods = "verifyLoginTest", dataProvider = "Items", priority = 1)
	public void verifyCartCount(String testData, String item) throws InterruptedException, IOException {
		List<WebElement> allItems = dashboardPage.getDashboardItemNames();
		Thread.sleep(4000);
		log.info("Fetched all the Items listed on webpage.");

		log.info(testData);
		for (int i = 0; i < allItems.size(); i++) {
			if (allItems.get(i).getText().contains(item)) {
				Assert.assertTrue(true);
				wait.until(ExpectedConditions.visibilityOf(allItems.get(i)));
				js.executeScript("arguments[0].scrollIntoView();", allItems.get(i));
				log.info("Item Found is : " + allItems.get(i).getText());

				dashboardPage.clickAddToCart().get(i).click();
				log.info("Add to cart CTA is clicked.");
				a++; // For add to cart CTA.
				Thread.sleep(1000);
				if (dashboardPage.getCartCount() == a) {
					Assert.assertTrue(true);
					log.info("Item Successfully Added into the Cart.");
					log.info("Count of Items added in Cart : " + dashboardPage.getCartCount());
					break;

				} else {
					log.error("Item Failed to be Added into the Cart.");
					log.error("Count Expected : " + dashboardPage.getCartCount() + " But Count found : " + a);
					Assert.assertTrue(false);
				}
			}
		}
	}

	@Test(dependsOnMethods = "verifyLoginTest", priority = 2)
	public void verifyItemsAddedInCart() {
		String path = System.getProperty("user.dir") + "/testData/JioMart_Items.xlsx";
		xlUtil = new XLUtilities(path);

		int TotalRows = 0;
		try {
			TotalRows = xlUtil.getRowCount("Items");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// int r = TotalRows - 1;
		log.info("Total number of Items in the Data : " + TotalRows);

		cartPage = dashboardPage.clickCart();
		int totalItems = cartPage.getProductName().size();

		if (totalItems == TotalRows) {
			Assert.assertTrue(true);
			log.info("All Items successfully added into the Cart.");
		} else {
			log.error("No of Items Expected : " + TotalRows + " No of Items added in Cart : " + totalItems);
			Assert.assertEquals(TotalRows, totalItems);
			Assert.assertTrue(false);
		}
	}

	@DataProvider(name = "Items")
	public String[][] getItemsData() throws IOException {
		String itemsPath = ".//testData//JioMart_Items.xlsx";

		xlUtil = new XLUtilities(itemsPath);

		int totalRow = xlUtil.getRowCount("Items");
		int totalColumn = xlUtil.getColCount("Items", 1);

		String itemsData[][] = new String[totalRow][totalColumn];

		for (int r = 1; r <= totalRow; r++) {
			for (int c = 0; c < totalColumn; c++) {
				itemsData[r - 1][c] = xlUtil.readData("Items", r, c);
			}
		}
		return itemsData;

	}
}