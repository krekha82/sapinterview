package com.test.register;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.register.pages.RegistrationPage;
import com.test.register.pages.SignUpPage;

import com.test.register.util.TestUtil;

public class testRegistrationPage extends ReportListener{
	
	/*
	 * Data Provider to Read Data From Excel
	 * 
	 * */

	@DataProvider
	public Object[][] getInputFromExcel() {
		try {
			return TestUtil.getCellData();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
  
	/* Test for testing SignUp Page.
	 * */
	@Test(dataProvider = "getInputFromExcel", enabled = true)
	public void testRegistration(String firstName, String lastName, String email, String passWord) {
		
		String gmail = email;
		
		if (gmail.equals(null)) {
			gmail = "divya.rekha" + "+" + System.currentTimeMillis() + "@gmail.com";
		}
	    SignUpPage signUP = PageFactory.initElements(driver, SignUpPage.class);
		RegistrationPage registration = PageFactory.initElements(driver, RegistrationPage.class);
		System.out.println("Registration Page");
		registration = signUP.signUp();
		reportLog("Clicked The SignUp");
		registration.fillInRegistrationForm(firstName, lastName, gmail, passWord);
		reportLog("Accepted the Agreement");
		registration.validateRegistration();
		reportLog("Registration Successful..Email sent");		

	}
	
	@AfterMethod
	public void afterMethod() {
		driver.close();
		
	}

}
