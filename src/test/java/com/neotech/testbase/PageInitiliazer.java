package com.neotech.testbase;

import com.neotech.pages.AddEmployeePageElements;
import com.neotech.pages.DashboardPageElements;
import com.neotech.pages.LoginPageElements;

public class PageInitiliazer extends BaseClass{
	
public static LoginPageElements login;
public	static DashboardPageElements dashboard;
public	static AddEmployeePageElements addEmp;
	
	
	public static void initiliaze() {
		
		login= new LoginPageElements();
		dashboard = new DashboardPageElements();
		addEmp = new AddEmployeePageElements();
	}
	
	
	
	
	

}
