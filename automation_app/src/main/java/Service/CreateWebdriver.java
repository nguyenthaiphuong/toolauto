package Service;

import Bean.SystemConfig;
import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateWebdriver {

    @Autowired
    PathDriver pathDriver;
    @Autowired
    SystemConfig systemCofig;

    public WebDriver getFirefox(String binaryFirefox) {
        pathDriver.setPathFireFox();
        //
        WebDriver webDriver = null;
        try {
            System.setProperty(PathDriver.webDriverFirefox, PathDriver.dirDriverFirefox);
            File pathToBinary = new File(binaryFirefox);
            FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
            FirefoxProfile firefoxProfile = new FirefoxProfile();
//            webDriver = new FirefoxDriver(ffBinary, firefoxProfile);
            return webDriver;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return webDriver;
        }
    }

    // neu file binary de o? thuc muc khac' khong phai /user/bin , vi du heroku
    public WebDriver getGoogle(String binaryGoogle) {
        //
        pathDriver.setPathGoogle();
        //
        WebDriver webDriver = null;
        try {
            //
            System.setProperty(PathDriver.webDriverGoogle, PathDriver.dirDriverGoogle);

            switch (systemCofig.os) {
                case "Linux":
                    ChromeOptions options = new ChromeOptions();
                    options.setBinary(binaryGoogle);
//                    options.addArguments("--window-size=1920,1080");
                    webDriver = new ChromeDriver(options);
                    break;
                case "Windows":
                    ChromeOptions optionswindow = new ChromeOptions();
//                    optionswindow.addArguments("user-data-dir=C:\\Users\\Hello\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
                    webDriver = new ChromeDriver();
                    
                    break;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return webDriver;
        }

    }

}
