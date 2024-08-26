package test;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pom.CartPage;
import pom.HomePage;
import pom.Login;
import pom.Searched;
import qa.base.Basee;
import qa.utils.Utilities;

public class LoginTest extends Basee {

	public WebDriver driver;
	HomePage homePage;
	Login login;
	Searched search;
	CartPage cartPage;
	
	
	@BeforeTest()
	public void openBrowser() {
		
		loadProperties();
		driver=initializeBrowser(prop.getProperty("browserName"));
				
	}
	
	@BeforeClass
	public void pomClassObject() {
		homePage = new HomePage(driver);
		login = new Login(driver);
		search = new Searched(driver);
		cartPage = new CartPage(driver);
	}
	
	
	@BeforeMethod
	public void openApplication() {
		
		driver.get("https://store.steampowered.com/");
	}
	@Test
	public void checkUrl() {
		homePage.clickOnLogin();
		String actualUrl = driver.getCurrentUrl();
		System.out.println(actualUrl);
		String expectedUrl = "https://store.steampowered.com/login/?redir=&redir_ssl=1&snr=1_4_4__global-header";
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	@Test(dataProvider="validCredentialsSupplier")
	public void loginandCheckErrormessage(String email,String pass) {
		homePage.clickOnLogin();
//		login.sendEmail("kaido@gmail.com");
//		login.sendPass("kaido123");
		login.sendEmail(email);
		login.sendPass(pass);
		login.clickOnSignUpButton();
		String actualErrorMessage = login.getWrongCredentialErrorMessage();
		System.out.println(actualErrorMessage);
		String expectedErrorMessage = "Please check your password and account name and try again." ;
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] getData() throws IOException {
		
		//Object [][] data = Utilities.getTestDataFromexcel("");
		//return Utilities.getTestDataFromexcel("stm");
		Object [][] data = Utilities.getTestDataFromexcel("stm");
		return data;
	}
//	@AfterMethod
//	public void afterMethod() {
//		System.out.println("afterMethod");
//	}
//	
//	@AfterClass
//	public void makePomClassNull() {
//		
//		homePage =null;
//		login=null;
//		search=null;
//		cartPage=null;
//	}
//	
//	@AfterTest
//	public void existBrowser() {
//		
//		System.gc();
//		driver.quit();
//	}
}
