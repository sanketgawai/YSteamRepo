package pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Searched {

	WebDriver driver;
	
	@FindBy(xpath="//span[@class='title']/parent::div")
	private List<WebElement> ListOfGames;
	
	@FindBy(xpath="//span[@class='title']")
	private List<WebElement> textListOfGames;
	
	@FindBy(xpath="//a[@id='btn_add_to_cart_847489']")
	private WebElement addToCart;
	
	
	@FindBy(xpath="//div[text()='Added to your cart!']/parent::div/descendant::button/following-sibling::button")
	private WebElement viewMyCart;
	
	public Searched(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void clickOnWukongGame() {
		for(int i=0;i<=textListOfGames.size();i++) {
			if(textListOfGames.get(i).getText().equalsIgnoreCase("Black Myth: Wukong")) {
				ListOfGames.get(i).click();
				break;
			}
		}
	}
	
	public void clickOnaddToCart() {
		addToCart.click();
	}
	
	public void clickOnviewMyCart() {
		viewMyCart.click();
	}
}
