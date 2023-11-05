package com.neotech.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.neotech.pages.AddEmployeePageElements;
import com.neotech.pages.DashboardPageElements;
import com.neotech.pages.LoginPageElements;
import com.neotech.utils.CommonMethods;
import com.neotech.utils.ConfigsReader;
import com.neotech.utils.ExcelUtility;

public class EmployeeAddingTest extends CommonMethods{
	
	@Test(groups="regressionEmpAdding", dataProvider="excelData", priority=1)
	public void EmpAdding(String firstName, String lastName, String username, String password) throws InterruptedException {
		
		
		LoginPageElements login = new LoginPageElements();
	    DashboardPageElements dashboard = new DashboardPageElements();
		AddEmployeePageElements addEmp = new AddEmployeePageElements();
		
		login.login();

		//sendText(login.username, ConfigsReader.getProperty("username"));
		//sendText(login.password, ConfigsReader.getProperty("password"));

		//click(login.loginButton);
		wait(1);

		click(dashboard.pim);
		click(addEmp.addEmployee);

		waitForVisibility(addEmp.firstNameBox);

		sendText(addEmp.firstNameBox, firstName);
		sendText(addEmp.lastNameBox, lastName);
		String empId = addEmp.employeeID.getAttribute("value");
		CommonMethods.selectDropDown(addEmp.location, "New York Sales Office");

		click(addEmp.loginDetails);

		sendText(addEmp.username, username);
		sendText(addEmp.password, password);
		sendText(addEmp.confirmPassword, password);

		
		waitForVisibility(addEmp.personalForm);
		
		click(addEmp.saveButton);

		

		String actualID = addEmp.employeeID.getAttribute("value");
		Assert.assertEquals(actualID, empId, "ID's do not match!");
	}

	@DataProvider(name = "excelData")
	public Object[][] getExcelData() {
		String filePath = System.getProperty("user.dir") + "/src/test/resources/testdata/AddEmployee.xlsx";
		String sheetName = "Add";
		return ExcelUtility.excelIntoArray(filePath, sheetName);
	}
		
		
	}


