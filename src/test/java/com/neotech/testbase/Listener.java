package com.neotech.testbase;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.neotech.utils.CommonMethods;

public class Listener implements ITestListener {

	public void onStart(ITestContext context) {
		System.out.println("Functionanlity test started");
	}

	public void onFinish(ITestContext context) {
		System.out.println("Functionanlity test finished");
	}

	public void onTestStart(ITestResult result) {
		System.out.println("Test will start:" + result.getName());

		// creating a test on the report - to use it to long into extentreports
		BaseClass.test = BaseClass.report.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Passed:" + result.getName());

		// Print the passed on the report
		BaseClass.test.pass("Test passed: " + result.getName());

		// Take a screenshot and add it to the report
		try {
			BaseClass.test.addScreenCaptureFromPath(CommonMethods.takeScreenshot("passed/" + result.getName()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed:" + result.getName());

		// Print the fail on the report
		BaseClass.test.fail("Test failed: " + result.getName());

		// Take a screenshot and add it to the report
		try {
			BaseClass.test.addScreenCaptureFromPath(CommonMethods.takeScreenshot("failed/" + result.getName()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
