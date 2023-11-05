package com.neotech.pages;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.neotech.utils.CommonMethods;
import com.neotech.utils.ConfigsReader;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class LoginPageElements extends CommonMethods{

	@FindBy(id = "txtUsername")
	public WebElement username;
	
	@FindBy(id = "txtPassword")
	public WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement loginButton;
	
	@FindBy(xpath="txtPassword-error")
	public WebElement emptyPasswordMessage;

	public LoginPageElements() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='toast-message']")
	public WebElement wrongCredentialsMessage;
	
	public void login() {
		
		// send username
				sendText(username, ConfigsReader.getProperty("username"));
				wait(1);

				// send password
				sendText(password, ConfigsReader.getProperty("password"));
				wait(1);

				// click on login
				click(loginButton);
				// Or use jsClick or Action.click
				wait(1);

		
		
	}
	
}
