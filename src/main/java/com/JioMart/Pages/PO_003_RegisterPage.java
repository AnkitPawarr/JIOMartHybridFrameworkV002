package com.JioMart.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.JioMart.base.base;

public class PO_003_RegisterPage extends base {

	WebDriver driver; 
	public PO_003_RegisterPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@class='pro-img']")
	private WebElement storeImage;

	@FindBy(id = "signup-heading")
	private WebElement header;

	@FindBy(id = "reg_firstname")
	private WebElement firstName;

	@FindBy(xpath = "//form//div[1]//div[@class='md-form position-relative'][1]//div[1]")
	private WebElement firstNameError;

	@FindBy(id = "reg_lastname")
	private WebElement lastName;

	@FindBy(xpath = "//form//div[1]//div[@class='md-form position-relative'][2]//div[1]")
	private WebElement lastNameError;

	@FindBy(id = "reg_email")
	private WebElement mailId;

	@FindBy(xpath = "//form//div[1]//div[@class='md-form position-relative'][3]//div[1]")
	private WebElement mailIdError;

	@FindBy(id = "register_pwd")
	private WebElement password;

	@FindBy(xpath = "//form//div[2]//div[@class='password-msg']//div")
	private WebElement passwordError;

	@FindBy(xpath = "//div[@class='password-msg']")
	private WebElement passwordPolicyText;

	@FindBy(id = "register_confirm_pwd")
	private WebElement confirmPassword;

	@FindBy(xpath = "//form//div[3]//div[@class='md-form position-relative']//div[1]")
	private WebElement confirmPassError;

	@FindBy(xpath = "//a[contains(@class,'chgmob')]")
	private WebElement changeNumber;

	@FindBy(xpath = "//input[@class='whatcheckbox']")
	private WebElement wpCheckbox;

	@FindBy(xpath = "//p[@class='otptxt mt-0']//span")
	private WebElement verifyRegisterNumber;

	@FindBy(xpath = "//input[@formcontrolname='regotp0']")
	private WebElement enterRegOTP;

	@FindBy(id = "login_resend_otp")
	private WebElement resendRegOTP;

	@FindBy(xpath = "//form//div[5]//div[1]//div[3]")
	private WebElement OTPRegErr;

	@FindBy(xpath = "//button[@class='btn-login']")
	private WebElement verifyRegBtn;

	public void validateStoreImg() {
		storeImage.isDisplayed();
	}

	public String getRegisterPageHeader() {
		return header.getText();
	}

	public String enterDetails = "Please enter your details.";

	public void enterFirstName(String fName) {
		firstName.click();
		firstName.clear();
		firstName.sendKeys(fName);
	}

	public String getFirstNameError() {
		return firstNameError.getText();
	}

	public void enterLastName(String lName) {
		lastName.click();
		lastName.clear();
		lastName.sendKeys(lName);
	}

	public String getLastNameError() {
		return lastNameError.getText();
	}

	public void enterEmailId(String emailId) {
		mailId.click();
		mailId.clear();
		mailId.sendKeys(emailId);
	}

	public String getEmailIdError() {
		return mailIdError.getText();
	}

	public void enterPassword(String pw) {
		password.click();
		password.clear();
		password.sendKeys(pw);
	}

	public String getPasswordError() {
		return passwordError.getText();
	}

	public void enterConfirmPassword(String confirmPw) {
		confirmPassword.click();
		confirmPassword.clear();
		confirmPassword.sendKeys(confirmPw);
	}

	public String getconfirmPasswordError() {
		return confirmPassError.getText();
	}

	public void clickWpCheckbox() {
		wpCheckbox.click();
	}

	public String verifyRegisterNumber() {
		return verifyRegisterNumber.getText();
	}

	public void clickChangeNumber() {
		changeNumber.click();
	}

	public void enterRegisterOTP() throws InterruptedException {
		enterRegOTP.clear();
		enterRegOTP.click();

		Thread.sleep(1000);
	}

	public void clickResendRegOTP() {
		wait.until(ExpectedConditions.elementToBeClickable(resendRegOTP));
		resendRegOTP.click();
	}

	public String RegisterOTPError() {
		return OTPRegErr.getText();
	}

	public PO_004_DashboardPage clickRegisterVerify() {
		verifyRegBtn.click();
		return new PO_004_DashboardPage(driver);
	}

}
