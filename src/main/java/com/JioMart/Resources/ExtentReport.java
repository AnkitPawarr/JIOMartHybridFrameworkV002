package com.JioMart.Resources;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	public static ExtentReports getReportObject() {
		String date = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		String reportPath = System.getProperty("user.dir") + "//Reports//" + "Test-Report " + date + ".html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		sparkReporter.config().setDocumentTitle("JIO");
		sparkReporter.config().setReportName("Jio Automation Report" + date);
		sparkReporter.config().setTheme(Theme.STANDARD);

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Host name", "Local host");
		extent.setSystemInfo("Environment", "PROD");
		extent.setSystemInfo("QA", "Ankit Pawar");

		return extent;
	}

}
