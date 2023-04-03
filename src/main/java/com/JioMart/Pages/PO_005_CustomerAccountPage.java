package com.JioMart.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.JioMart.base.base;

public class PO_005_CustomerAccountPage {
	WebDriver driver;

	public PO_005_CustomerAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//jds-icon[@ic='IcLogout']")
	private WebElement logoutPopupCTA;

	// //button[@aria-label='button Sign Out']
	@FindBy(xpath = "//div[@class='action']//jds-button[1]//button[1]")
	private WebElement logoutCTA;

	@FindBy(xpath = "//button[@aria-label='button Cancel']")
	private WebElement cancelLogout;

	public void openLogoutPopup() {
		logoutPopupCTA.click();
	}

	public PO_002_LoginPage clickLogoutBtn() {
		logoutCTA.click();
		return new PO_002_LoginPage(driver);
	}

	public void clickCancelLogoutCTA() {
		cancelLogout.click();
	}
}
