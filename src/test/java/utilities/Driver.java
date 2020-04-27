package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Driver {

    private static WebDriver driver; // чтоб только в этом классе видно было
    private Driver(){}

    @Parameters("browser")
    public static WebDriver getDriver(){

        // the best method is the one that has no parameters

        
        if(driver == null) { // means  if there is no driver

            String browser = ConfigReader.getProperty("browser");
            browser = browser.toLowerCase();

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    //  ChromeOptions chromeOptions = new ChromeOptions().setHeadless(true);
                    driver= new ChromeDriver();
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver= new FirefoxDriver();
                    break;

                case "chrome_headless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions().setHeadless(true);
                    // set capabilities - DesiredCapabilities --> extensions, proxy, platname
                    driver= new ChromeDriver(chromeOptions);
                    break;

                case "firefox_headless":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions().setHeadless(true);
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

            }
        }
          return driver;
    }


        public static void quit(){
           if(driver != null) {
               driver.quit();
               driver = null;
           }
        }
    // If the driver does not exist and you try to close it,
    // then you will get an error "Session does not exist"


}
