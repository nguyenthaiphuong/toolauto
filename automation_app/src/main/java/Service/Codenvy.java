/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Views.JFrame;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Codenvy {

    public boolean Start(WebDriver webDriver, String username) {
        boolean status = false;
        boolean flag_wait = false;
        boolean flag_wait_capcha = false;
        String str_username = username;
//        String str_password = "Ahfweh123@#$";
        String str_password = "Zxcv123123";
        String str_LastName = "cailong";
        String str_FirstName = "cailong1";
        try {
            webDriver.get("https://outlook.live.com/owa/?nlp=1&signup=1");
            // wait
            while (!flag_wait) {
                flag_wait = waitForPresence(webDriver, 5000, "//input[@id='MemberName']");
            }
            flag_wait = false;
            WebElement input_signin = webDriver.findElement(By.xpath("//input[@id='MemberName']"));
            input_signin.sendKeys(str_username);
            webDriver.findElement(By.xpath("//input[@id='iSignupAction']")).click();
            // wait
            while (!flag_wait) {
                flag_wait = waitForPresence(webDriver, 5000, "//input[@id='PasswordInput']");
            }
            flag_wait = false;
            WebElement PasswordInput = webDriver.findElement(By.xpath("//input[@id='PasswordInput']"));
            PasswordInput.sendKeys(str_password);
            webDriver.findElement(By.xpath("//input[@id='iSignupAction']")).click();
            // wait
            while (!flag_wait) {
                flag_wait = waitForPresence(webDriver, 5000, "//input[@id='LastName']");
            }
            flag_wait = false;
            WebElement LastName = webDriver.findElement(By.xpath("//input[@id='LastName']"));
            LastName.sendKeys(str_LastName);
            WebElement FirstName = webDriver.findElement(By.xpath("//input[@id='FirstName']"));
            FirstName.sendKeys(str_FirstName);
            webDriver.findElement(By.xpath("//input[@id='iSignupAction']")).click();

            // wait
            while (!flag_wait) {
                flag_wait = waitForPresence(webDriver, 5000, "//select[@id='BirthDay']");
            }
            flag_wait = false;
            WebElement Body = webDriver.findElement(By.tagName("body"));
            String languae = Body.getAttribute("lang");
            Select BirthDay = new Select(webDriver.findElement(By.xpath("//select[@id='BirthDay']")));
            Select BirthMonth = new Select(webDriver.findElement(By.id("BirthMonth")));
            Select BirthYear = new Select(webDriver.findElement(By.id("BirthYear")));
            if (languae.equals("vi-VN")) {
                BirthDay.selectByVisibleText("3");
                BirthMonth.selectByVisibleText("Tháng Ba");
                BirthYear.selectByVisibleText("1991");
            } else {
                BirthDay.selectByVisibleText("3");
                BirthMonth.selectByVisibleText("May");
                BirthYear.selectByVisibleText("1991");
            }
            webDriver.findElement(By.xpath("//input[@id='iSignupAction']")).click();

            // wait
            while (!flag_wait) {
                flag_wait = waitForPresence(webDriver, 5000, "//img[@aria-label='Visual Challenge']");
            }
            flag_wait = false;

            WebElement img_capcha = webDriver.findElement(By.xpath("//img[@aria-label='Visual Challenge']"));
            //luu image
//            Actions action = new Actions(webDriver);
//            action.contextClick(img_capcha).build().perform();
//            Robot robot = new Robot();
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyPress(KeyEvent.VK_DOWN);
//            robot.keyPress(KeyEvent.VK_DOWN);
//            robot.keyPress(KeyEvent.VK_ENTER);
//            Thread.sleep(1500);
//            saveImg("Google",robot);
            // doi de nhap capcha
            WebElement input_capcha = null;
            while (!flag_wait_capcha) {
                if (isBrowserClosed(webDriver)) {
                    JFrame.fails++;
                    webDriver.quit();
                    return false;
                }
                try {
                    // ktra nhap capcha dung hay khong
                    input_capcha = webDriver.findElement(By.xpath("//button[@class='iconButton nextButton lowerButton']"));
                    flag_wait_capcha = true;
                } catch (Exception ex) {
                }
            }
            JFrame.success++;
            webDriver.quit();
            return true;
        } catch (Exception e) {
            System.out.println("exception:" + e.getMessage());
            JFrame.fails++;
            webDriver.quit();
            return false;
        }
    }

//    ham test
//    while (!flag_wait_capcha) {
//                input_capcha = webDriver.findElement(By.xpath("//input[@class='spHipNoClear form-control input-max-width']"));
//                str_capcha = input_capcha.getAttribute("value");
//                if (!"".equals(str_capcha)) {
//                    webDriver.findElement(By.xpath("//input[@id='iSignupAction']")).click();
//                    try {
//                        error_capcha = webDriver.findElement(By.xpath("//div[@class='alert alert-error floatLeft']"));
//                    } catch (Exception ex) {
//                        flag_wait_capcha = true;
//                        break;
//                    }
//                }
//                Thread.sleep(600);
//            }        
    // doi element load
    public boolean waitForPresence(WebDriver driver, int timeLimitInSeconds, String targetXpath) throws InterruptedException {
        if (isBrowserClosed(driver)) {
            JFrame.fails++;
            driver.quit();
        }
        try {
            WebElement element = null;
            element = driver.findElement(By.xpath(targetXpath));
            WebDriverWait wait = new WebDriverWait(driver, timeLimitInSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));
            boolean isElementPresent = element.isDisplayed();
            return isElementPresent;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Thread.sleep(500);
            return false;
        }
    }

    // luu image vao o D/tenaccount
    public void saveImg(String account, Robot robot) {
        // To press D key.
        robot.keyPress(KeyEvent.VK_D);
        // To press : key.
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_SEMICOLON);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        // To press \ key.
        robot.keyPress(KeyEvent.VK_BACK_SLASH);
        sendKeys(robot, account);
        robot.keyPress(KeyEvent.VK_BACK_SLASH);
        // To press "test" key one by one.
        robot.keyPress(KeyEvent.VK_T);
        robot.keyPress(KeyEvent.VK_E);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyPress(KeyEvent.VK_T);
        // To press Save button.
        robot.keyPress(KeyEvent.VK_ENTER);
    }

    //go ten account
    void sendKeys(Robot robot, String keys) {
        for (char c : keys.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException(
                        "Key code not found for character '" + c + "'");
            }
            robot.keyPress(keyCode);
            robot.delay(100);
            robot.keyRelease(keyCode);
            robot.delay(100);
        }
    }

    public boolean isBrowserClosed(WebDriver driver) {
        boolean isClosed = false;
        try {
            driver.getTitle();
        } catch (UnreachableBrowserException ubex) {
            isClosed = true;
        }
        return isClosed;
    }

}
