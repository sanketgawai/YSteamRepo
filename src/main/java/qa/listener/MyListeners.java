package qa.listener;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import qa.utils.ExtentReporter;

public class MyListeners implements ITestListener{

	ExtentReports extentReport;
	ExtentTest extentTest;
	public void onStart(ITestContext context) {
		
		extentReport = ExtentReporter.generateExtentReport();
		
	}

	public void onTestStart(ITestResult result) {
		
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName()+"started execution");
	}

	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS, result.getName()+"got successfull executed");
	}

	public void onTestFailure(ITestResult result) {
		
		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TakesScreenshot t = (TakesScreenshot)driver;
		File src = t.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty(System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png"));
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String destinationScreenshotPath = System.getProperty(System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png");
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName()+"got failed");
	}
	
	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+"got skipped");
	}
	
//	public void onFinish(ITestContext context) {
//		
//		extentReport.flush();//***** if don't flush all details not added to report
//		
//		//***** below code for auto logic for extent report it will display report after test over 
//		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\extentReport.html";
//		File extentReport = new File(pathOfExtentReport);//path of extent report 
//		try {
//			Desktop.getDesktop().browse(extentReport.toURI());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	
//	}
	
	public void onFinish(ITestContext context) {
	    extentReport.flush(); // Ensure all details are added to the report

	    // Path to the extent report
	    String pathOfExtentReport = System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html";
	    File extentReportFile = new File(pathOfExtentReport);

	    // Check if the file exists
	    if (extentReportFile.exists()) {
	        try {
	            Desktop.getDesktop().browse(extentReportFile.toURI());
	        } catch (IOException e) {
	            System.err.println("Error opening the extent report: " + e.getMessage());
	            e.printStackTrace();
	        }
	    } else {
	        System.err.println("Extent report file does not exist at: " + pathOfExtentReport);
	    }
	}
	
}
