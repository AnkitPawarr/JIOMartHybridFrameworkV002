package com.JioMart.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.JioMart.base.base;

public class PO_001_HomePage {
	
	WebDriver driver;
	
	public PO_001_HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "sign_in_text")
	private WebElement SignInButton;
	
	public PO_002_LoginPage clickSignInBtn() {
		SignInButton.click();
		return new PO_002_LoginPage(driver);
	}
}
