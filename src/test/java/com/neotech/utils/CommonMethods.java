package com.neotech.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.neotech.testbase.BaseClass;

public class CommonMethods extends BaseClass{

	/**
	 * This method clear the textbox firs, then send keys
	 * 
	 * @param element
	 * @param text
	 * 
	 */
	public static void sendText(WebElement element, String text) {
		
		element.clear();
		element.sendKeys(text);
		
	}
	/**
	 * This method checks if a radio/check box is enabled, then clicks on the
	 * element matching the input value
	 * 
	 * @param elementList
	 * @param selectValue
	 */
	public static void clickRadioOrCheckbox(List<WebElement> elementList, String selectValue) {
		
		for(WebElement el: elementList) {
			
			String elementValue = el.getAttribute("value").trim();
			
			if(el.isEnabled() && elementValue.equals(selectValue)) {
				
				el.click();
				break;
				
			}
		}
		
	}
	
	/**
	 * This method allows us to call Thread.sleep() for a certain amount of seconds
	 * 
	 * @param seconds
	 */
	
	public static void wait(int seconds) {
		
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * This method checks whether a visible text is found in a drop-down and selects
	 * it.
	 * 
	 * @param element
	 * @param visibleText
	 */
	
	public static void selectDropDown(WebElement element, String visibleText) {
		
		try {
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * This method checks if a given index is valid, and selects it if so
	 * 
	 * @param element
	 * @param index
	 */
	
	public static void selectDropdown(WebElement element, int index) {
		try {
			Select sel = new Select(element);
			sel.selectByIndex(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method switches to alert and accepts it If no alert is found,
	 * NoAlertPresentException details is printed
	 */
	
	public static void acceptAlert() {
		
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * This method will dismiss an alert if it is present
	 */
	public static void dismissAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will return the alert text. If no alert text is present, it will
	 * return null
	 * 
	 * @return
	 */
	
	public static String getAlertText() {
		String alertText = null;
		
		try {
			Alert alert = driver.switchTo().alert();
			alertText = alert.getText();
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}

		return alertText;
	}
		
	/**
	 * This method sends text to the alert if it is present
	 * 
	 * @param text
	 */
	public static void sendAlertText(String text) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(text);
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method switches to a frame using name or id
	 * 
	 * @param nameOrId
	 */
	public static void switchToFrame(String nameOrId) {
		try {
			driver.switchTo().frame(nameOrId);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * This method switches to a frame using index value
	 * 
	 * @param index
	 */
	public static void switchToFrame(int index) {
		try {
			driver.switchTo().frame(index);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method switches to a frame using WebElement
	 * 
	 * @param element
	 */
	public static void switchToFrame(WebElement element) {
		try {
			driver.switchTo().frame(element);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method switches into the child window
	 */
	public static void switchToChildWindow() {
		String mainWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!mainWindow.equals(handle)) {
				driver.switchTo().window(handle);
				break;
			}
		}
	}

	
	/**
	 * Creates a wait object
	 * 
	 * @return
	 */
	
	public static WebDriverWait getWaitObject() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		return wait;
	}
	
	public static WebDriverWait getWaitObject(int secondsToWait) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
		return wait;
	}
	
	/**
	 * Waits for the element to be clickable
	 * 
	 * @param element
	 * @return
	 */
	public static WebElement waitForClickability(WebElement element) {
		return getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Waits for the element to be visible
	 * 
	 * @param element
	 * @return
	 */
	public static WebElement waitForVisibility(WebElement element) {
		return getWaitObject().until(ExpectedConditions.visibilityOf(element));
	}
	
	
	/**
	 * This method waits for an element to be clickable and then clicks it.
	 * @param element
	 */
	public static void click(WebElement element)
	{
		waitForClickability(element);
		element.click();
	}
	
	/**
	 * This method will cast the driver to a JavascriptExecutor object and return it.
	 * 
	 * @return
	 */
	public static JavascriptExecutor getJSObject()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js;
		//in one line 
		//return (JavascriptExecutor) driver;
	}
	
	/**
	 * This method will click on an element using JavascriptExecutor
	 * 
	 * @param element
	 */
	public static void jsClick(WebElement element)
	{
		getJSObject().executeScript("arguments[0].click()", element);
	}
	
	
	/**
	 * This method will scroll the page until a specific element is in view.
	 * 
	 * @param element
	 */
	public static void scrollToElement(WebElement element)
	{
		getJSObject().executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	
	/**
	 * This method scrolls the page up based on the pixel parameter.
	 * 
	 * @param pixel
	 */
	public static void  scrollUp(int pixel)
	{
		getJSObject().executeScript("window.scrollBy(0, -"+pixel+")");
	}
	
	/**
	 * This method scrolls the page down based on the pixel parameter.
	 * 
	 * @param pixel
	 */
	public static void scrollDown(int pixel)
	{
		getJSObject().executeScript("window.scrollBy(0,"+pixel+")");
	}
	
	/**
	 * This method selects a date from the calendar given the List<WebElement> and the
	 * date to select
	 * 
	 * @param elements
	 * @param date
	 */
	public static void selectCalendarDate(List<WebElement> elements, String date)
	{
		for (WebElement day : elements)
		{
			if (day.getText().equals(date))
			{
				if (day.isEnabled())
				{
					day.click();
					break;
				}
				else
				{
					System.out.println("This day is not enabled!");
					break;
				}
					
			}
		}
	}
	
	/**
	 * This method takes a screenshot using the provided fileName
	 * 
	 * @param fileName
	 */
	
	
	/**
	 * This method takes a screenshot using the provided fileName
	 * 
	 * @param fileName
	 */
	public static String takeScreenshot(String fileName) {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		String destination = Constants.SCREENSHOT_FILEPATH + fileName + getTimeStamp() + ".png";

		try {
			FileUtils.copyFile(sourceFile, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return destination;
	}

	/**
	 * This method returns the timestamp in a String format
	 * 
	 * @return
	 */
	public static String getTimeStamp() {
		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

		return sdf.format(date);
	}

}
	
	
	
	

