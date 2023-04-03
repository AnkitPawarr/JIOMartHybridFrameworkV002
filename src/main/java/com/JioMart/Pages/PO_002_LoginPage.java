package com.JioMart.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.JioMart.base.base;

public class PO_002_LoginPage extends base {

	WebDriver driver;

	public PO_002_LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'loginbanner')]")
	private WebElement storeImage;

	@FindBy(xpath = "//input[@class='phone empty']")
	private WebElement mobileNo;

	@FindBy(xpath = "//button[@aria-label='button Get OTP']")
	private WebElement getOtp;

	/*
	 * @FindBy(xpath = "//div[contains(@class,'phinput')]//div[1]") private
	 * WebElement mobileNoError;
	 */

	@FindBy(xpath = "//jds-heading[@headertype='h1']//h1")
	private WebElement LoginHeader;

	@FindBy(xpath = "//jds-text[contains(@classname,'change-text')]//div[1]//jds-link[1]//a")
	private WebElement change;

	@FindBy(id = "id_input_0")
	private WebElement otp;

	@FindBy(id = "resend-link")
	private WebElement resendOtp;

	@FindBy(xpath = "//div[contains(@class,'field-error')]")
	private WebElement otpError;

	@FindBy(xpath = "//button[@aria-label='button Verify']")
	private WebElement verify;

	@FindBy(xpath = "//div[contains(@class,'panel-header')]//div[2]//a[1]//img")
	private WebElement home;

	public boolean validateLoginStoreImg() {
		return storeImage.isDisplayed();
	}

	public void enterMobileNo(String number) {
		mobileNo.click();

		mobileNo.clear();
		mobileNo.sendKeys(number);
	}

	public void clickGetOtpCTA() {
		if (getOtp.isEnabled()) {
			getOtp.click();
			log.info("Get OTP CTA is clicked.");
			Assert.assertTrue(true);
		} else {
			log.error("Get OTP CTA is Not Clickabled.");
			Assert.assertTrue(false);
		}
	}

	/*
	 * public String getLoginNoError() { return mobileNoError.getText(); }
	 */

	public String getLoginPageHeader() {
		return LoginHeader.getText();
	}

	public void clickLoginChangeCTA() {
		change.click();
	}

	public void enterLoginOTP() throws InterruptedException {
		otp.clear();
		otp.click();

		Thread.sleep(10000);
	}

	public String LoginOtpText = "Waiting for OTP...";

	public void clickResendLoginOtp() {
		wait.until(ExpectedConditions.elementToBeClickable(resendOtp));
		resendOtp.click();
	}

	public String LoginOTPError() {
		return otpError.getText();
	}

	public PO_004_DashboardPage clickLoginVerifyCTA() {
		if (verify.isEnabled()) {
			verify.click();
			log.info("Verify CTA is clicked.");
			Assert.assertTrue(true);
		} else {
			log.error("Verify CTA is Not Clickabled.");
			Assert.assertTrue(false);
		}
		return new PO_004_DashboardPage(driver);
	}

	public PO_001_HomePage clickHomeLink() {
		home.click();
		return new PO_001_HomePage(driver);
	}
}
