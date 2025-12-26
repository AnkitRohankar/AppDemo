package Mobile_App.appDemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class TestBaseClass {

	
public AndroidDriver driver ;
	
	@BeforeClass
	public void setUp() throws InterruptedException, MalformedURLException
	{


		// Maually --> Start Appium Server --> Then run Code 

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("deviceName", "AnkitPhone");
		capabilities.setCapability("app", "C:\\Users\\91876\\eclipse-workspace\\appDemo\\src\\test\\java\\resoureces\\Sat2Farm.apk");
		
		System.out.println("App is open ");


		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


		
	}

	
	@AfterClass
	public void tearDown()
	{
		
		driver.quit();
        System.out.println("browser is closed");
        

        

		}
	
}
