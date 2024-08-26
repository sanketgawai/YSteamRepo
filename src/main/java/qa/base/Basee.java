package qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Basee {

	WebDriver driver;
	public Properties prop;
	
	public void loadProperties() {
		
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\qa\\config\\config.properties");
//		//FileInputStream file = new FileInputStream(propFile);
//		//prop.load(file);
		try {
			FileInputStream file = new FileInputStream(propFile);
			prop.load(file);//***** this is most imp//****read property like key and pair
		}catch(Throwable e)//(Throwable e)->Throwable is grand parent,(Exception e)->parent
		{
			e.printStackTrace();
		}
		
						
	}
	
	public WebDriver initializeBrowser(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			}else if(browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}else if(browserName.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
			return driver;
	}
}
