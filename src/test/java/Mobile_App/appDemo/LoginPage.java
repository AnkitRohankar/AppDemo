package Mobile_App.appDemo;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPage extends TestBaseClass {
	
	
	@Test
	public void verifyLogin() throws InterruptedException
	
	{
		WebElement LoginBtn = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Login\"]"));
		LoginBtn.click();
        System.out.println("Login Btn is Click");

        Thread.sleep(10000);
		
		WebElement mobileNumberTextbox = driver.findElement(By.id("com.satyukt.myfarmapp:id/user_id_text"));
		mobileNumberTextbox.sendKeys("8888883333");
		System.out.println("Mobile Numer is Enter");
		
		
		WebElement signInBtn = driver.findElement(By.id("com.satyukt.myfarmapp:id/sign_in_btn"));
		signInBtn.click();
        System.out.println("Sign In Btn Click");
        
                
     // Manully OTP Check
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));

     // First OTP Box check
        WebElement otpBox1 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("com.satyukt.myfarmapp:id/otp1")));

        Assert.assertTrue(otpBox1.isDisplayed());
               

        // manual OTP entry Check Text Box 1 
        wait.until(driver ->
                !otpBox1.getText().trim().isEmpty()
        );

        // Hide Keyboarrd
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
            
        }

        Thread.sleep(20000);

        // Hit Submit Btn
        WebElement submitOTP = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("com.satyukt.myfarmapp:id/submitbtn")));

        submitOTP.click();
        System.out.println("Submit OTP Btn clicked");

        // Actual Test
        
        boolean isHomeLoaded = false;

        try {
            WebElement videoPopUp = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("com.satyukt.myfarmapp:id/dialogTitle")));
            isHomeLoaded = true;
            System.out.println("Video Check");
        } catch (Exception e) {
            
        }

        try {
            WebElement homeText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("(//android.widget.TextView[@text='Home'])[1]")));
            isHomeLoaded = true;
            System.out.println("Home Text check");
        } catch (Exception e) {
            
        }

        try {
        	WebElement changeLogCloseBtn = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.id("com.satyukt.myfarmapp:id/okbtn")));
            isHomeLoaded = true;
            System.out.println("ChangeLog Close Btn check");
        } catch (Exception e) {
            // ignore
        }

        // Validation 
        Assert.assertTrue(isHomeLoaded,
                "Login Sucessfully");



        
 		
		
	}

}
