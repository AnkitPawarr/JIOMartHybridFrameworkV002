package com.JioMart.Resources;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.JioMart.base.base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Listeners extends base implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReport.getReportObject();

	public void onTestStart(ITestResult result) {
		// Invoked each time before a test will be invoked

		test = extent.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		// Invoked each time a test succeeds.
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		// Invoked each time a test fails.
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		test.fail(result.getThrowable());

		String screenshotPath = null;
		try {
			screenshotPath = getScreenshot(driver, result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(screenshotPath, result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		// Invoked each time a test is skipped.
		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
		test.skip(result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		/*
		 * Invoked each time a method fails but has been annotated with
		 * successPercentage and this failure still keeps it within the success
		 * percentage requested.
		 */

	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// Invoked each time a test fails due to a timeout.

	}

	public void onStart(ITestContext context) {
		/*
		 * Invoked before running all the test methods belonging to the classes inside
		 * the &lt;test&gt; tag and calling all their Configuration methods.
		 */
	}

	public void onFinish(ITestContext context) {
		/*
		 * Invoked after all the test methods belonging to the classes inside the
		 * &lt;test&gt; tag have run and all their Configuration methods have been
		 * called.
		 */
		extent.flush();
		extent.getStats();
	}
}
