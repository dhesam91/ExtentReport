package com.extentreport.demo;


	import java.io.IOException;

import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Library.Utility;
import Library.Utility1;

import org.testng.annotations.BeforeTest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;




	public class ExtentReportWithRegistration {
		
        // Create global variable which will be used in all method
	 ExtentReports extent;
	 ExtentTest logger;
	 WebDriver driver;
	
        // This code will run before executing any testcase
	@BeforeMethod
	public void setup()
	{
	    ExtentHtmlReporter reporter=new ExtentHtmlReporter("./Reports/RegistrationReport.html");
		
	    extent = new ExtentReports();
	    
	    extent.attachReporter(reporter);
	    
	    logger=extent.createTest("LoginTest");
	}

	  

	@BeforeTest
	public void openApplication() throws IOException, InterruptedException
	{
	  System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	  driver = new ChromeDriver();

	  String url = "http://newtours.demoaut.com/";
	 
	    driver.manage().window().maximize();
	    driver.get(url);
	    Thread.sleep(2000);
	    
	    Utility1.getScreenshot(driver, "Home Page Opened");
	}

	@Test(priority=0)
	  public void verifyRegisterLink() throws InterruptedException, IOException {
	  String sExpected ="Register: Mercury Tours";
	driver.findElement(By.linkText("REGISTER")).click();
	String actualresult = driver.getTitle();
	org.testng.Assert.assertEquals(actualresult, sExpected);

	Thread.sleep(2000);
    
    Utility1.getScreenshot(driver, "Register Page Opened");
	  }
	@Test(priority=1)
	public void userRegistraion() throws InterruptedException, IOException
	{
	driver.findElement(By.name("firstName")).sendKeys("Testfirstname");
	 	  driver.findElement(By.name("lastName")).sendKeys("Testlastname");
	 	  driver.findElement(By.name("phone")).sendKeys("8888888889");
	 	  driver.findElement(By.name("userName")).sendKeys("testusername");
	 	  driver.findElement(By.name("address1")).sendKeys("testaddress");
	 	  driver.findElement(By.name("address2")).sendKeys("testaddress2");
	 	  driver.findElement(By.name("city")).sendKeys("Herndon");
	 	  driver.findElement(By.name("state")).sendKeys("Va");
	 	  driver.findElement(By.name("postalCode")).sendKeys("23456");
	 	  Select drpcountry = new Select(driver.findElement(By.name("country")));
	 	    drpcountry.selectByVisibleText("UNITED STATES");
	 	   driver.findElement(By.name("email")).sendKeys("Test@test.com");
	 	   driver.findElement(By.name("password")).sendKeys("Test1234$");
	 	   driver.findElement(By.name("confirmPassword")).sendKeys("Test1234$");
	 	   driver.findElement(By.name("register")).click();
	 	   Thread.sleep(2000);
	 	   
	 	   
	 	//one way to write logic
	 	//  String expectedresult = "Note: Your user name is Test@test.com.";
	 	   
	 	  // String actualresult = driver.findElement(By.xpath("html>body>div>table>tbody>tr>td>table>tbody>tr>td>table>tbody>tr>td>table>tbody>tr>td>p>a>font>b")).getText();
	 	   
	 	// org.testng.Assert.assertEquals(actualresult, expectedresult);
	 
	 	  
	 // another way 
	 	String expectedresult = "Your user name is Test1@test.com.";
	 	  String  sActualValue=driver.findElement(By.tagName("Body")).getText();
	 	  System.out.println(sActualValue);
	 	  org.testng.Assert.assertTrue(sActualValue.contains(expectedresult));
	 	  
	 	 Utility1.getScreenshot(driver, "Registration completed");
	 	  
	 	  
	 	//  System.out.println( "check user registrion:"+sActualValue.contains(sExpected));
	 	  
	 	  // driver.findElement(By.linkText("SIGN-OFF")).click();
	 	   Thread.sleep(2000);
	}

	@Test(timeOut=5000)
	public void bookflight() throws IOException
	{
	driver.findElement(By.linkText("Flights")).click();
	
	
	Utility1.getScreenshot(driver, "Flight Page Opened");
	
	}
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String temp=Utility.getScreenshot(driver);
			
			logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
		
		extent.flush();
		
	}
	
	@AfterTest
	public void closepplication() {
		
		driver.close();
	}
	}



