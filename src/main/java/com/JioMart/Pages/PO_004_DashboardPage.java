package com.JioMart.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.JioMart.base.base;

public class PO_004_DashboardPage {

	WebDriver driver;

	public PO_004_DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Defining All WebELements
	@FindBy(xpath = "//button[@id='btn_sign_in']")
	private WebElement user;

	@FindBy(id = "nav_link_2")
	private WebElement Groceries;

	@FindBy(xpath = "//div[contains(@class,'jm-fc-primary-grey-80')]")
	private List<WebElement> items;

	@FindBy(xpath = "//img[@class='jm-ml-xs']")
	private List<WebElement> addToCart;

	@FindBy(xpath = "//div[contains(@class,'counter-number')]")
	private WebElement cartCount;

	@FindBy(id = "btn_minicart")
	private WebElement cart;

	// Defining All Methods
	public String getUserName() {
		return user.getText();
	}

	public PO_005_CustomerAccountPage clickUser() {
		user.click();
		return new PO_005_CustomerAccountPage(driver);
	}

	public List<WebElement> getDashboardItemNames() {
		return items;
	}

	public List<WebElement> clickAddToCart() {
		return addToCart;
	}

	public void clickGroceries() {
		Groceries.click();
	}

	public int getCartCount() {
		return Integer.parseInt(cartCount.getText());
	}

	public PO_006_CartPage clickCart() {
		cart.click();
		return new PO_006_CartPage(driver);
	}
}
