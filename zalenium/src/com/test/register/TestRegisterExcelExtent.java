package com.test.register;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.test.register.util.TestUtil;

public class TestRegisterExcelExtent extends ReportListener{
	
	

	@DataProvider
	public Object[][] getInputFromExcel() {
		try {
			return TestUtil.getCellData();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
   @BeforeMethod
   public void beforeTest() {
	   driver = new ChromeDriver(); 	
       driver.get("https://cai.tools.sap");	   
   }
	
	@Test(dataProvider = "getInputFromExcel", enabled = true)
	public void testRegistration(String firstName, String lastName, String email, String passWord) {
		
		String gmail = email;
		String successMsg = "Thank you for registering with SAP Conversational AI";
		if (gmail.equals(null)) {
			gmail = "divya.rekha" + "+" + System.currentTimeMillis() + "@gmail.com";
		}
		
		driver.findElement(By.xpath("//*[text()='Sign up']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		reportLog("Clicked The SignUp");
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
		reportLog("Accepted the Agreement");
		WebElement header1 = driver.findElement(By.tagName("h1"));
		String actualMsg = header1.getText();
		assertEquals(successMsg, actualMsg, "Registration Failed");
		reportLog("Registration Successful..Email sent");
		

	}
	
	@AfterMethod
	public void afterMethod() {
		driver.close();
		
	}

}
