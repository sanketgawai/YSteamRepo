package test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Temp {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://store.steampowered.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,20);
		
		WebElement login = driver.findElement(By.xpath("//a[text()='login']"));
		login.click();
		//test case check url
		WebElement emailId = driver.findElement(By.className("_2GBWeup5cttgbTw8FM3tfx"));
		emailId.sendKeys("kaido@gmail.com");
	
		WebElement pass = driver.findElement(By.xpath("//div[text()='Password']/following-sibling::input"));
		pass.sendKeys("kaido1234");
		
		WebElement rememberMeCheckbox = driver.findElement(By.xpath("//div[@class='_28MB9LhS2kVTalIp0NHDv4']"));
		rememberMeCheckbox.click();
		
		WebElement signUp = driver.findElement(By.xpath("//button[text()='Sign in']"));
		signUp.click();
		
		WebElement errorMessage = driver.findElement(By.xpath("//div[@class='_1W_6HXiG4JJ0By1qN_0fGZ']"));
		//wait.until(ExpectedConditions.visibilityOf(errorMessage));
		
		String expectedErrorMessage = "Please check your password and account name and try again.";
		wait.until(ExpectedConditions.textToBePresentInElement(errorMessage, expectedErrorMessage));
		
		System.out.println(errorMessage.getText());
		System.out.println("end of script");
			

		if(errorMessage.getText().equalsIgnoreCase("Please check your password and account name and try again."))
		{
			System.out.println("errorMessage is visible");
		}
		else
		{
			System.out.println("errorMessage is not visible");
		}
		
		WebElement store = driver.findElement(By.xpath("//span[@id='logo_holder']/parent::div/following-sibling::div/a"));
		store.click();
		
		WebElement search = driver.findElement(By.xpath("//input[@id='store_nav_search_term']"));
		search.sendKeys("black myth wukong");
		search.sendKeys(Keys.RETURN);

		List<WebElement> ListOfGames = driver.findElements(By.xpath("//span[@class='title']/parent::div"));
		List<WebElement> textListOfGames = driver.findElements(By.xpath("//span[@class='title']"));
	
		for(int i=0;i<=textListOfGames.size();i++) {
			if(textListOfGames.get(i).getText().equalsIgnoreCase("Black Myth: Wukong")) {
				ListOfGames.get(i).click();
				break;
			}
		}
		
		WebElement addToCart = driver.findElement(By.xpath("//a[@id='btn_add_to_cart_847489']"));
		addToCart.click();
	
		WebElement viewMyCart = driver.findElement(By.xpath("//div[text()='Added to your cart!']/parent::div/descendant::button/following-sibling::button"));
		wait.until(ExpectedConditions.visibilityOf(viewMyCart));
		viewMyCart.click();
		
		//testcase check cart url
		//testcase check product has been added successful
		WebElement gameTitle = driver.findElement(By.xpath("//div[text()='Black Myth: Wukong']"));
		String actualGameTitle = gameTitle.getText();
		String expectedGameTitle = "Black Myth: Wukong";
		if(actualGameTitle.equalsIgnoreCase(expectedGameTitle)) {
			System.out.println("game is added");
		}
		else {
			System.out.println("game is  not added");
		}
		//testcase check price
		WebElement gamePrice = driver.findElement(By.xpath("//div[@class='pk-LoKoNmmPK4GBiC9DR8']"));
		System.out.println(gamePrice);
		String expectedgamePrice ="₹3,599.00";
		if(gamePrice.getText().equalsIgnoreCase(expectedgamePrice)) {
			System.out.println("price matched");
		}else {
			System.out.println("price not matched");
		}
		
	}
}
