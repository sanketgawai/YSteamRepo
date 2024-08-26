package pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;
	Actions act; 	
	JavascriptExecutor js;
	WebDriverWait wait;
	
	@FindBy(xpath="//a[text()='login']")
	private WebElement login;
	
	@FindBy(xpath="//input[@id='store_nav_search_term']")
	private WebElement search;
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver); 	
		js = (JavascriptExecutor)driver;
		wait = new WebDriverWait(driver,10);
	}
	
	public void clickOnLogin() {
		login.click();
	}
	
	public void searchWukongInSearchField() {
		search.sendKeys("black myth wukong");
		search.sendKeys(Keys.RETURN);
	}
	
}
