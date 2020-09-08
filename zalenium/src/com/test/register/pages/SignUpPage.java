package com.test.register.pages;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
	
WebDriver driver;
String successMsg = "Thank you for registering with SAP Conversational AI";
	
	public SignUpPage(WebDriver driver){
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	
	public RegistrationPage signUp() {
		
		driver.findElement(By.xpath("//*[text()='Sign up']")).click();	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return new RegistrationPage(driver);
	}
	
	

}
