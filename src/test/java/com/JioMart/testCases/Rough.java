package com.JioMart.testCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.JioMart.Pages.PO_001_HomePage;
import com.JioMart.Pages.PO_002_LoginPage;
import com.JioMart.Pages.PO_004_DashboardPage;
import com.JioMart.Pages.PO_005_CustomerAccountPage;
import com.JioMart.base.base;

public class Rough extends base {

	PO_001_HomePage homepage;
	PO_002_LoginPage loginPage;
	PO_004_DashboardPage dashboardPage;
	

	@Test
	public void demoTest() throws InterruptedException {
		homepage = new PO_001_HomePage(driver);
		dashboardPage = new PO_004_DashboardPage(driver);
		
		 driver.get(baseUrl);

		
		// js.executeScript("window.scrollBy(0,5000)");
		List<WebElement> allItems = dashboardPage.getDashboardItemNames();

		for (int i = 0; i < allItems.size(); i++) {

			if (allItems.get(i).getText().contains("Parle Hide & Seek 4 Fun Flavours Choco Chip Creme Sandwich Biscuits 400 g")) {
				
				Assert.assertTrue(true);
				js.executeScript("arguments[0].scrollIntoView();", allItems.get(i));

				System.out.println(allItems.get(i).getText());

				dashboardPage.clickAddToCart().get(i).click();
				log.info("Add to cart clicked.");
				Thread.sleep(3000);

				driver.findElement(By.id("btn_minicart")).click();
				//act.moveToElement(driver.findElement(By.id("btn_minicart"))).click().build().perform();
				Thread.sleep(3000);
				break;
			}
		}
	}
}
