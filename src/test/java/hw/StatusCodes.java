package hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.BrowserFactory;

public class StatusCodes {

    private WebDriver driver;

    @BeforeMethod
    public void setup(){
       driver = BrowserFactory.getDriver("chrome");
       driver.get("https://practice-cybertekschool.herokuapp.com");
    }

    // Test case #12
    // Step 1. Go to “https://practicecybertekschool.herokuapp.com”
    // Step 2. And click on “Status Codes”.
    // Step 3. Then click on “500”.
    // Step 4. Verify that following message is displayed: “This page returned a 500 status code”

    @Test(priority = 0,description = "Verify that the following message is displayed : “This page returned a 500 status code”")
    public void testCase12(){
       // STEP 1
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com");



 /*     STEP 2
        xPath:
           //a[text()='Status Codes']
           //ul/li[46]
           //a[@href="/status_codes"]

         linkText:
            //a[contains(text(),'Status Codes')]

         partialLinkText:
            //a[contains(text(),'Status')]
            //a[contains(text(),'Codes')]


         cssSelector:
         [href="/status_codes"]

*/
        WebElement statusCodeLink = driver.findElement(By.linkText("Status Codes"));
        statusCodeLink.click();


        // STEP 3
        WebElement statusCode = driver.findElement(By.linkText("500"));
        statusCode.click();


        //STEP 4
        String expected = "This page returned a 500 status code.";
        WebElement displayedMessageElement = driver.findElement(By.xpath("//p"));
        String actual = displayedMessageElement.getText();

        System.out.println(actual);

        if (actual.contains(expected)) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed");
        }
     driver.close();

    }



        // Test case #11
        // Step 1. Go to “https://practicecybertekschool.herokuapp.com”
        // Step 3. And click on “Status Codes”.
        // Step 4. Then click on “404”.
        // Step 5. Verify that following message is displayed: “This page returned a 404 status code”





     @Test(priority = 1,description = "Verify that the following message is displayed : “This page returned a 404 status code”")
    public void testCase11() throws Exception{

        // Step 1:
         WebDriver driver = BrowserFactory.getDriver("chrome");
         driver.get("https://practice-cybertekschool.herokuapp.com");

         // Step 2:
         WebElement statusCodeLink = driver.findElement(By.linkText("Status Codes"));
         statusCodeLink.click();

         // Step 3:
         WebElement statusCode = driver.findElement(By.linkText("404"));
         statusCode.click();
         Thread.sleep(2000);

         // Step 4:
         String expected = "This page returned a 404 status code.";
         WebElement displayedMessageElement = driver.findElement(By.xpath("//p"));
         String actual = displayedMessageElement.getText();

         System.out.println(actual);

         if (actual.contains(expected)) {
             System.out.println("Test Passed");
         } else {
             System.out.println("Test Failed");
         }

     driver.close();

    }

    /*
    DataProvider --> Provides data to test cases

    DataProvider returns data in form of single dimensional Object[]
    or 2d object array (Object[][])

     Object[] --> when you have only 1 parameter
     Object[][] --> when you have 2+ parameters
     cannot carry primitive data (int, byte) but it can
     carry wrapper class objects(Integer, Byte)

     */

    @DataProvider(name = "testData")
    public static Object [] testData(){
        return new Object[] {"404", "500","301", "200"};
    }


    @Test(dataProvider = "testData")
    public void statusCodes(String code){
        // Step 2:
        WebElement statusCodeLink = driver.findElement(By.linkText("Status Codes"));
        statusCodeLink.click();

        // Step 3:
        WebElement statusCode = driver.findElement(By.linkText(code));
        statusCode.click();

        String expected = "This page returned a " + code + " status code.";
        WebElement displayedMessageElement = driver.findElement(By.xpath("//p"));
        String actual = displayedMessageElement.getText();

        System.out.println(actual);

        // following is a HARD assertion and contains a message that
        // is displayed only if the assertion fails
        // When a HARD assertion fails, the rest of the script is skipped
        Assert.assertTrue(actual.contains(actual), "The status code does not exist");


    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }



}
