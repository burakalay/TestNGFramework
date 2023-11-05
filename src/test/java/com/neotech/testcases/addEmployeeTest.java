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

public class addEmployeeTest extends CommonMethods {

	@Test(dataProvider = "excelData", groups = "addEmp")
	public void addEmployee(String firstName, String lastName, String username, String password) {

		LoginPageElements login = new LoginPageElements();
	    DashboardPageElements dashboard = new DashboardPageElements();
		AddEmployeePageElements addEmp = new AddEmployeePageElements();

		sendText(login.username, ConfigsReader.getProperty("username"));
		sendText(login.password, ConfigsReader.getProperty("password"));

		click(login.loginButton);
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

	@DataProvider(name = "data")
	public Object[][] getData() {

		Object[][] credentials = { { "Michael", "Scott", "Michae012", "Fene@r123" },
				{ "Deivid", "deSouza", "Deiv112", "Intqer@Fan123" }, { "Alex", "deSouza", "Alex12", "Artist1@23" } };

		return credentials;
	}

}
