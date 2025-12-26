package Mobile_App.appDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Welcome_Page extends TestBaseClass {
	
	@Test (priority = 1)
	public void verifyLogo() {
		
		
		
		WebElement logo =driver.findElement(By.id("com.satyukt.myfarmapp:id/imageViewLogo"));
		 Assert.assertTrue(logo.isDisplayed());
		 System.out.println("Logo Check");
		
		
	}
	
	
	@Test(priority=2)
	public void verifyLoginBtnVisible()
	{
		WebElement loginBtnVisible = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Login\"]"));
		 Assert.assertTrue(loginBtnVisible.isDisplayed());
		 System.out.println("Login Btn is visible");
		
	}
	
	@Test(priority=3)
	public void verifySignUpBtnVisible()
	{
		WebElement signUpBtnVisible = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Sign up\"]"));
		Assert.assertTrue(signUpBtnVisible.isDisplayed());
		System.out.println("Sign Up Btn is Visible");
				
	}
	
	@Test(priority=4)
	public void verifyWelcomeText()
	{
		
		WebElement welcomeText = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Welcome\"]"));
		String actualWelcomeText = welcomeText.getText();
		String expectedWelcomeText = "Welcome";
		
		Assert.assertEquals(actualWelcomeText, expectedWelcomeText);
		
		
	}
	
	
	@Test(priority =5)
	public void verifyLoginBtnEnabled() throws InterruptedException
	{
		WebElement loginBtn = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Login\"]"));
		loginBtn.click();
		Thread.sleep(30000);
        System.out.println("Click on Login Btn");
        //Assert.assertTrue(loginBtn.isEnabled());

    WebElement LoginText = driver.findElement(By.id("com.satyukt.myfarmapp:id/textView10"));
    String actualLoginText = LoginText.getText();
    String expectedLoginText = "Logint";
    
    Assert.assertEquals(actualLoginText, expectedLoginText);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
