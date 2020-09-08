package com.test.register.pages;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	
WebDriver driver;
String successMsg = "Thank you for registering with SAP Conversational AI";
	
	public RegistrationPage(WebDriver driver){
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	
	public void signUp() {
		
		driver.findElement(By.xpath("//*[text()='Sign up']")).click();	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void fillInRegistrationForm(String firstName, String lastName, String gmail, String passWord) {
		
		driver.switchTo().frame("IDS_UI_Window");
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		driver.findElement(By.id("mail")).sendKeys(gmail);
		driver.findElement(By.id("newPasswordInput")).sendKeys(passWord);
		driver.findElement(By.id("retypeNewPasswordInput")).sendKeys(passWord);
   
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.id("pdAccept")));
		js.executeScript("arguments[0].click();", driver.findElement(By.id("touAccept")));
		js.executeScript("arguments[0].click();", driver.findElement(By.id("sapStoreRegisterFormSubmit")));
	}
	
	public void validateRegistration() {
		
		WebElement header1 = driver.findElement(By.tagName("h1"));
		String actualMsg = header1.getText();
		assertEquals(successMsg, actualMsg, "Registration Failed");
	}

}
