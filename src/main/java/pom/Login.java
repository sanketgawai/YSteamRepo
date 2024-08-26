package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

	WebDriver driver; 
	WebDriverWait wait;
	
	@FindBy(className="_2GBWeup5cttgbTw8FM3tfx")
	private WebElement emailId;
	
	@FindBy(xpath="//div[text()='Password']/following-sibling::input")
	private WebElement pass;
	
	@FindBy(xpath="//div[@class='_28MB9LhS2kVTalIp0NHDv4']")
	private WebElement rememberMeCheckbox;
	
	@FindBy(xpath="//button[text()='Sign in']")
	private WebElement signUpButton;
	
	@FindBy(xpath="//div[@class='_1W_6HXiG4JJ0By1qN_0fGZ']")
	private WebElement errorMessage;
	
	public Login(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,20);
	}
	
	public void sendEmail(String email) {
		//emailId.sendKeys("kaido@gmail.com");
		emailId.sendKeys(email);
	}
	public void sendPass(String pas) {
		//pass.sendKeys("kaido123");
		pass.sendKeys(pas);
	}
	public void sendEmailAndPass(String email,String pas) {
		emailId.sendKeys(email);
		pass.sendKeys(pas);
	}
	
	public void clickOnSignUpButton() {
		FunctionalCls.waitForElementToVisible(driver, rememberMeCheckbox);
		rememberMeCheckbox.click();
		signUpButton.click();
	}
	
	public String getWrongCredentialErrorMessage() {
		//String erMessage = errorMessage.getText();
		//return erMessage;
		
		//wait.until(ExpectedConditions.textToBePresentInElement(errorMessage, "Please check your password and account name and try again."));
		FunctionalCls.waitForText(driver, errorMessage, "Please check your password and account name and try again.");
		return errorMessage.getText();
	}
}
