package com.JioMart.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PO_006_CartPage {
	WebDriver driver;

	public PO_006_CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//jds-text[@classname='product-name cursor-pointer']//div[1]")
	private List<WebElement> product;

	
	
	public List<WebElement> getProductName() {
		return product;
	}
}
