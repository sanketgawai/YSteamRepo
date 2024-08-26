package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	WebDriver driver;
	
	@FindBy(xpath="//div[text()='Black Myth: Wukong']")
	private WebElement gameTitle;
	
	@FindBy(xpath="//div[@class='pk-LoKoNmmPK4GBiC9DR8']")
	private WebElement gamePrice;
	
	public CartPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getGameTitle() {
		String gmTitle = gameTitle.getText();
		return gmTitle;
	}
	
	public String getGamePrice() {
		return gamePrice.getText();
	}
	
}
