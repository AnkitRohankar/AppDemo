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

	 
//	    // Login Clicked
//	    
//	    public void clickLogin() {
//	        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
//	        System.out.println("Login clicked");
//	    }
//	    
//
//	    public void enterMobile(String mobile) {
//	        WebElement mobileField =
//	                wait.until(ExpectedConditions.visibilityOfElementLocated(mobileTextbox));
//	        mobileField.clear();
//	        mobileField.sendKeys(mobile);
//	        System.out.println("Mobile entered: " + mobile);
//	    }
//
//	    
//	    
//	    
//	    // Database Validation
//	    
//	    
//	    @Test(priority = 1)
//	    public void loginNumberDatabaseCheck() {
//
//	        wait = new WebDriverWait(driver, Duration.ofSeconds(45));
//
//	        clickLogin();
//
//	        String mobile = "8888883333"; // DB present number
//	        enterMobile(mobile);
//
//	        boolean isPresent = DBUtils.isMobilePresent(mobile);
//	        Assert.assertTrue(isPresent, "FAIL: Mobile number not found in DB");
//
//	        System.out.println("PASS: Mobile number exists in DB");
//	    }
//
//	
//	    
//	    // Verify Login Page
//	   
//	    @Test(priority = 2)
//	    public void verifyLogin() throws InterruptedException {
//
//	        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
//
//	        clickLogin();
//	        enterMobile("8888883333");
//	                
//        
//        
//		WebElement signInBtn = driver.findElement(By.id("com.satyukt.myfarmapp:id/sign_in_btn"));
//		signInBtn.click();
//        System.out.println("Sign In Btn Click");
//        
//                
//     // Manully OTP Check
//        
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//
//     // First OTP Box check
//        WebElement otpBox1 = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        By.id("com.satyukt.myfarmapp:id/otp1")));
//
//        Assert.assertTrue(otpBox1.isDisplayed());
//               
//
//        // manual OTP entry Check Text Box 1 
//        wait.until(driver ->
//                !otpBox1.getText().trim().isEmpty()
//        );
//
//        // Hide Keyboarrd
//        try {
//            driver.hideKeyboard();
//        } catch (Exception e) {
//            
//        }
//
//        Thread.sleep(50000);
//
//        // Hit Submit Btn
//        WebElement submitOTP = wait.until(
//                ExpectedConditions.elementToBeClickable(
//                        By.id("com.satyukt.myfarmapp:id/submitbtn")));
//
//        submitOTP.click();
//        System.out.println("Submit OTP Btn clicked");
//
//        // Actual Test
//        
//        boolean isHomeLoaded = false;
//
//        try {
//            WebElement videoPopUp = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.id("com.satyukt.myfarmapp:id/dialogTitle")));
//            isHomeLoaded = true;
//            System.out.println("Video Check");
//        } catch (Exception e) {
//            
//        }
//
//        try {
//            WebElement homeText = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath("(//android.widget.TextView[@text='Home'])[1]")));
//            isHomeLoaded = true;
//            System.out.println("Home Text check");
//        } catch (Exception e) {
//            
//        }
//
//        try {
//        	WebElement changeLogCloseBtn = wait.until(
//            ExpectedConditions.visibilityOfElementLocated(
//                    By.id("com.satyukt.myfarmapp:id/okbtn")));
//            isHomeLoaded = true;
//            System.out.println("ChangeLog Close Btn check");
//        } catch (Exception e) {
//            // ignore
//        }
//
//        // Validation 
//        Assert.assertTrue(isHomeLoaded,
//                "Login Sucessfully");
//        
//	    }
//
////
////      
////
////	        wait.until(ExpectedConditions.elementToBeClickable(signInBtn)).click();
////	        System.out.println("Sign In clicked");
////
////	        // -------- OTP FIX --------
////	        // Wait until OTP screen loaded
////	        wait.until(ExpectedConditions.visibilityOfElementLocated(otp1));
////
////	        // Wait until ALL 6 OTP boxes have value (use text attribute)
////	        wait.until(driver -> {
////
////	            String o1 = driver.findElement(otp1).getAttribute("text");
////	            String o2 = driver.findElement(otp2).getAttribute("text");
////	            String o3 = driver.findElement(otp3).getAttribute("text");
////	            String o4 = driver.findElement(otp4).getAttribute("text");
////	            String o5 = driver.findElement(otp5).getAttribute("text");
////	            String o6 = driver.findElement(otp6).getAttribute("text");
////
////	            return  o1 != null && !o1.isEmpty() &&
////	                    o2 != null && !o2.isEmpty() &&
////	                    o3 != null && !o3.isEmpty() &&
////	                    o4 != null && !o4.isEmpty() &&
////	                    o5 != null && !o5.isEmpty() &&
////	                    o6 != null && !o6.isEmpty();
////	        });
////
////	        System.out.println("OTP fully entered");
////
////	        // Hide keyboard (safe)
////	        try {
////	            driver.hideKeyboard();
////	        } catch (Exception e) {}
////
////	        // Submit OTP only after full OTP
////	        wait.until(ExpectedConditions.elementToBeClickable(submitOtpBtn)).click();
////	        System.out.println("Submit OTP clicked");
////
////	        // -------- Home Validation --------
////	        boolean isHomeLoaded = false;
////
////	        try {
////	            wait.until(ExpectedConditions.visibilityOfElementLocated(homeText));
////	            isHomeLoaded = true;
////	        } catch (Exception e) {}
////
////	        try {
////	            wait.until(ExpectedConditions.visibilityOfElementLocated(videoPopup));
////	            isHomeLoaded = true;
////	        } catch (Exception e) {}
////
////	        try {
////	            wait.until(ExpectedConditions.visibilityOfElementLocated(changeLogBtn));
////	            isHomeLoaded = true;
////	        } catch (Exception e) {}
////
////	        Assert.assertTrue(isHomeLoaded, "FAIL: Login failed");
////	        System.out.println("PASS: Login successful");
////	    }
////	    // ----------- TEST 2 : DB VALIDATION -----------
//
//	  
	
//
//	    WebDriverWait wait;
//
//	    // ---------- Locators ----------
//	    By loginBtn = By.xpath("//android.widget.TextView[@text='Login']");
//	    By mobileTextbox = By.id("com.satyukt.myfarmapp:id/user_id_text");
//
//	    // ---------- Common Methods ----------
//
//	    public void clickLoginButton() {
//	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
//	        System.out.println("Login button clicked");
//	    }
//
//	    public String enterMobileNumber(String mobileNumber) {
//	        WebElement mobileField =
//	                wait.until(ExpectedConditions.visibilityOfElementLocated(mobileTextbox));
//
//	        mobileField.clear();
//	        mobileField.sendKeys(mobileNumber);
//	        System.out.println("Mobile number entered: " + mobileNumber);
//
//	        return mobileField.getAttribute("text");
//	    }
//
//	    
//
//	    @Test(priority = 1)
//	    public void loginNumberDatabaseCheck() {
//
//	        clickLoginButton();
//
//	        String expectedMobile = "8766549703";
//	        String actualMobile = enterMobileNumber(expectedMobile);
//
//	        // DB validation
//	        boolean isPresent = DBUtils.isMobilePresent(actualMobile);
//
//	        Assert.assertTrue(
//	                isPresent,
//	                "FAIL: Mobile number not found in DB"
//	        );
//
//	        System.out.println("PASS: Mobile number exists in DB");
//	    }
//	

	
	
//	@Test
//	public void verifyLogin() throws InterruptedException
//	
//	{
//		WebElement LoginBtn = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Login\"]"));
//		LoginBtn.click();
//        System.out.println("Login Btn is Click");
//
//        Thread.sleep(10000);
//		
//		WebElement mobileNumberTextbox = driver.findElement(By.id("com.satyukt.myfarmapp:id/user_id_text"));
//		mobileNumberTextbox.sendKeys("8888883333");
//		System.out.println("Mobile Numer is Enter");
//	
//        
////        WebElement mobileNumberTextbox = driver.findElement(
////                By.id("com.satyukt.myfarmapp:id/user_id_text"));
////
////        String expectedMobile = "8888883333";
////
////        // Enter mobile number
////        mobileNumberTextbox.sendKeys(expectedMobile);
////        System.out.println("Mobile Number is Entered");
//        
//        
//        
//		
//		WebElement signInBtn = driver.findElement(By.id("com.satyukt.myfarmapp:id/sign_in_btn"));
//		signInBtn.click();
//        System.out.println("Sign In Btn Click");
//        
//                
//     // Manully OTP Check
//        
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
//
//     // First OTP Box check
//        WebElement otpBox1 = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        By.id("com.satyukt.myfarmapp:id/otp1")));
//
//        Assert.assertTrue(otpBox1.isDisplayed());
//               
//
//        // manual OTP entry Check Text Box 1 
//        wait.until(driver ->
//                !otpBox1.getText().trim().isEmpty()
//        );
//
//        // Hide Keyboarrd
//        try {
//            driver.hideKeyboard();
//        } catch (Exception e) {
//            
//        }
//
//        Thread.sleep(20000);
//
//        // Hit Submit Btn
//        WebElement submitOTP = wait.until(
//                ExpectedConditions.elementToBeClickable(
//                        By.id("com.satyukt.myfarmapp:id/submitbtn")));
//
//        submitOTP.click();
//        System.out.println("Submit OTP Btn clicked");
//
//        // Actual Test
//        
//        boolean isHomeLoaded = false;
//
//        try {
//            WebElement videoPopUp = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.id("com.satyukt.myfarmapp:id/dialogTitle")));
//            isHomeLoaded = true;
//            System.out.println("Video Check");
//        } catch (Exception e) {
//            
//        }
//
//        try {
//            WebElement homeText = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath("(//android.widget.TextView[@text='Home'])[1]")));
//            isHomeLoaded = true;
//            System.out.println("Home Text check");
//        } catch (Exception e) {
//            
//        }
//
//        try {
//        	WebElement changeLogCloseBtn = wait.until(
//            ExpectedConditions.visibilityOfElementLocated(
//                    By.id("com.satyukt.myfarmapp:id/okbtn")));
//            isHomeLoaded = true;
//            System.out.println("ChangeLog Close Btn check");
//        } catch (Exception e) {
//            // ignore
//        }
//
//        // Validation 
//        Assert.assertTrue(isHomeLoaded,
//                "Login Sucessfully");
//
//
//      
//
//        
// 		
//		
//	}
//	
//	@Test(priority = 1)
//	public void loginNumerDatabasecheck() throws InterruptedException {
//
//	    WebElement loginBtn =
//	            driver.findElement(By.xpath("//android.widget.TextView[@text='Login']"));
//	    loginBtn.click();
//	    System.out.println("Login Btn is Clicked");
//
//	    Thread.sleep(5000);
//
//	    WebElement mobileNumberTextbox =
//	            driver.findElement(By.id("com.satyukt.myfarmapp:id/user_id_text"));
//
//	    String expectedMobile = "8766549703";
//	    mobileNumberTextbox.sendKeys(expectedMobile);
//
//	    String actualMobile = mobileNumberTextbox.getAttribute("text");
//
//	    boolean isPresent = DBUtils.isMobilePresent(actualMobile);
//
//	    if (isPresent) {
//	        System.out.println("Mobile number exists in DB");
//	        Assert.assertTrue(true, "PASS: Mobile number found in DB");
//	    } else {
//	        System.out.println("Mobile number does NOT exist in DB");
//	        Assert.fail("FAIL: Mobile number not found in DB");
//	    }
//	}

}
