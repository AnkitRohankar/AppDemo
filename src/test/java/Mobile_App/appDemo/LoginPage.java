package Mobile_App.appDemo;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import resoureces.DBUtils;

public class LoginPage extends TestBaseClass {
	
	
	
	 WebDriverWait wait;
	 
	 String dbMobileNumber = "8888883333";     // Database check
	 String loginMobileNumber = "8888883333";  // Login Check

	 
	    By loginBtn = By.xpath("//android.widget.TextView[@text='Login']");
	    By mobileTextbox = By.id("com.satyukt.myfarmapp:id/user_id_text");
	    By signInBtn = By.id("com.satyukt.myfarmapp:id/sign_in_btn");
	    By submitOtpBtn = By.id("com.satyukt.myfarmapp:id/submitbtn");
	    By homeText = By.xpath("(//android.widget.TextView[@text='Home'])[1]");
	    By videoPopup = By.id("com.satyukt.myfarmapp:id/dialogTitle");
	    By changeLogBtn = By.id("com.satyukt.myfarmapp:id/okbtn");
	    
	    
	    // Login Clicked
	    
	    public void clickLogin() {
	        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
	        System.out.println("Login clicked");
	    }
	    

	    public void enterMobile(String mobile) {
	        WebElement mobileField =
	                wait.until(ExpectedConditions.visibilityOfElementLocated(mobileTextbox));
	        mobileField.clear();
	        mobileField.sendKeys(mobile);
	        System.out.println("Mobile Number entered: " + mobile);
	    }
	    
	    
	    // Database Validation
	    
	    
	    @Test(priority = 1)
	    public void validateMobileNumberInDB() {

	        boolean isPresent = DBUtils.isMobilePresent(dbMobileNumber);

	        System.out.println("Checking DB for mobile: " + dbMobileNumber);

	        Assert.assertTrue(isPresent,
	                "Mobile number NOT found in database");

	        System.out.println("Mobile number exists in DB");
	    }

	    
	    
	    
	    // Login Verify --> Homepage Screen
	    
	    @Test(priority = 2)
	    public void verifyLogin() throws InterruptedException {

	        wait = new WebDriverWait(driver, Duration.ofSeconds(60));

	        clickLogin();
	        System.out.println("Click on Login Btn");
	        
	        enterMobile(loginMobileNumber);
	   

	        driver.findElement(
	                By.id("com.satyukt.myfarmapp:id/sign_in_btn")
	        ).click();

	        System.out.println("Sign In clicked with mobile: " + loginMobileNumber);

	        // OTP box check
	        WebElement otpBox1 = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        By.id("com.satyukt.myfarmapp:id/otp1")));

	        Assert.assertTrue(otpBox1.isDisplayed(),
	                "OTP screen not displayed");

	        // Manual OTP entry wait
	        wait.until(d -> !otpBox1.getText().trim().isEmpty());

	        driver.hideKeyboard();

	        Thread.sleep(40000);

	        driver.findElement(
	                By.id("com.satyukt.myfarmapp:id/submitbtn")
	        ).click();
	        
	        boolean isHomeLoaded = false;

	        try {
	            WebElement videoPopUp = wait.until(
	                    ExpectedConditions.visibilityOfElementLocated(
	                            By.id("com.satyukt.myfarmapp:id/dialogTitle")));
	            isHomeLoaded = true;
	            System.out.println("Video popup displayed");
	        } catch (Exception e) {
	            // ignore
	        }

	        try {
	            WebElement homeText = wait.until(
	                    ExpectedConditions.visibilityOfElementLocated(
	                            By.xpath("(//android.widget.TextView[@text='Home'])[1]")));
	            isHomeLoaded = true;
	            System.out.println("Home text displayed");
	        } catch (Exception e) {
	            // ignore
	        }

	        try {
	            WebElement changeLogCloseBtn = wait.until(
	                    ExpectedConditions.visibilityOfElementLocated(
	                            By.id("com.satyukt.myfarmapp:id/okbtn")));
	            isHomeLoaded = true;
	            System.out.println("Change log close button displayed");
	        } catch (Exception e) {
	            // ignore
	        }

	        //  Validation
	        Assert.assertTrue(isHomeLoaded,
	                "Login unsuccessful – Home screen not loaded");

	        System.out.println("Login successful");

	    }

}