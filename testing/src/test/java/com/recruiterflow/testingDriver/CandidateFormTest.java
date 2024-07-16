package com.recruiterflow.testingDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CandidateFormTest {
    WebDriver driver;
    WebDriverWait wait;

    public CandidateFormTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
    }

    public void openLoginPage() {
        driver.get("https://recruiterflow.com/login");
    }

    public void login(String emailID, String password) throws InterruptedException {
        WebElement emailField = driver.findElement(By.className("rf-text-input"));
        WebElement passwordField = driver.findElement(By.cssSelector("input.rf-text-input[type='password']"));

        emailField.sendKeys(emailID);
        passwordField.sendKeys(password);

        // Wait for the login button to be clickable using CSS selector
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".custom-button.rf-sign-in-button")));

        // Click using JavaScript
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", loginButton);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20000));
        Thread.sleep(20000);
        
    	WebElement AddCandidate = driver.findElement(By.xpath("//button[@class='button-default candidate-create-button']"));
    	AddCandidate.click();
    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20000));
    	List<WebElement> CandidateInfoList = driver.findElements(By.className("form-control-input"));
        CandidateInfoList.get(0).sendKeys("Lavanya M V");
        CandidateInfoList.get(1).sendKeys("M V");
        CandidateInfoList.get(2).sendKeys("lavanya@gmail.com");
        CandidateInfoList.get(3).sendKeys("8073460816");
        
  
        
        //CandidateInfoList.get(4).sendKeys("BTM Layout 2nd Stage");
        CandidateInfoList.get(5).sendKeys("Bengaluru, ");
        List<WebElement> locationFields = driver.findElements(By.xpath("//input[@class='form-control-input pac-target-input']"));
        if (!locationFields.isEmpty()) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            for (WebElement locationField : locationFields) {
                js.executeScript("arguments[0].scrollIntoView(true);", locationField);
                locationField.sendKeys("Karnataka");
                Thread.sleep(2000);
            }
        } else {
            System.out.println("Location input field not found.");
        }
        CandidateInfoList.get(6).sendKeys("BTM Layout 2nd Stage");
        CandidateInfoList.get(7).sendKeys("EWS colony");
        CandidateInfoList.get(8).sendKeys("Bengaluru");
        CandidateInfoList.get(9).sendKeys("Karnataka");
        CandidateInfoList.get(10).sendKeys("India");
        CandidateInfoList.get(11).sendKeys("560076");
        CandidateInfoList.get(12).sendKeys("TelequestAi private limited");
        CandidateInfoList.get(13).sendKeys("Software Engineer");
        CandidateInfoList.get(14).sendKeys("2021-01-01");
        CandidateInfoList.get(15).sendKeys("2024-04-30");
                    
        CandidateInfoList.get(16).sendKeys("Rajeev Institute Of Technology");
        CandidateInfoList.get(17).sendKeys("Bachelors of Engineering");
        CandidateInfoList.get(18).sendKeys("Electrical and Electronics Engineering");
        CandidateInfoList.get(19).sendKeys("2017-06-15");
        CandidateInfoList.get(20).sendKeys("2020-10-12");
        CandidateInfoList.get(21).sendKeys("http://linkedin.com/in/lavanya-m-v-java-developer");
       
    }
      
    public void uploadResume(String filePath) throws InterruptedException {
        WebElement uploadButton = driver.findElement(By.xpath("//button[@class='button-default']"));
        uploadButton.sendKeys("\"C:\\Users\\nishanth pm\\Desktop\\Lavanya M V.pdf\"");
        Thread.sleep(2000);
    }
    public void submitForm() {
        driver.findElement(By.xpath("//button[@class='button-default primary-button-wrapper']")).click();
    }
    
    
    public void searchAndVerifyCandidate(String candidateName) {
        driver.get("https://recruiterflow.com/prospect");
        
        // Wait for the input field to be present and visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='rf__input-container css-x0ex5r']")));
        
        // Check if the element is present
        if (searchInput == null) {
            System.out.println("Search input field not found.");
            return;
        }
        
        // Interact with the search input field
        searchInput.sendKeys(candidateName);
        searchInput.sendKeys(Keys.RETURN); 
        
        // Wait for the candidate result to be visible
        WebElement candidate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='ag-cell-wrapper']")));
        
        // Extract and verify the candidate name
        String displayedName = candidate.findElement(By.xpath("//input[@class='ag-cell-wrapper']")).getText();
        assert displayedName.equals(candidateName) : "Expected: " + candidateName + " but found: " + displayedName;
    }


    public void close() {
        driver.quit();
    }

    public static void main(String[] args) throws InterruptedException {
        CandidateFormTest test = new CandidateFormTest();
        
        // Open login page and perform login
        test.openLoginPage();
        test.login("qa_hiring@recruiterflow.com", "qahiring007!");
       test.uploadResume("\"C:\\Users\\nishanth pm\\Desktop\\Lavanya M V.pdf\""); 
        test.submitForm();
        test.searchAndVerifyCandidate("Lavanya M V");
        
        test.close();
    }

}
