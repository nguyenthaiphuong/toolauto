package Services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

import java.util.UUID;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import io.appium.java_client.remote.MobileCapabilityType;

public class AutomationAndroidImp implements IAutomationAndroid {
	public void startAuto() throws InterruptedException, IOException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// -----------------------
		// capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION,
		// "1.7.2");
		// capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
		// "4.4");
		// capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		// capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"Appium");
		// capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
		// "emulator-5554");
		// capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,
		// "Browser");
		// capabilities.setCapability("newCommandTimeout", 2000);
		// ---------------------------
		capabilities.setCapability("platformName", "Android");
		//device name tuy thuoc vao emulator nen phai run emulator moi get duoc
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "127.0.0.1:62001");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
		capabilities.setCapability("appActivity", ".Settings");
		capabilities.setCapability("appPackage", "com.android.settings");
		capabilities.setCapability("newCommandTimeout", 2000);

		// AndroidElement
		// ------------------------
		AndroidDriver mobiledriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		// Thread.sleep(5000);

		new TouchAction(mobiledriver).press(115, 650).waitAction(Duration.ofMillis(1000)).moveTo(115, 150).release()
				.perform();

		boolean flag = false;
		
		List<AndroidElement> allelements = null;
		Thread.sleep(1000);
		
		allelements = mobiledriver.findElementsByName("Add account");
		allelements.removeAll(Collections.singleton(null));
		allelements.get(0).click();

		Thread.sleep(1000);
		allelements = mobiledriver.findElementsByName("Google");
		allelements.removeAll(Collections.singleton(null));
		allelements.get(0).click();

		Thread.sleep(1000);
		allelements = mobiledriver.findElementsById("com.google.android.gsf.login:id/create_button");
		allelements.removeAll(Collections.singleton(null));
		allelements.get(0).click();

		allelements = mobiledriver.findElementsById("com.google.android.gsf.login:id/first_name_edit");
		allelements.removeAll(Collections.singleton(null));
		allelements.get(0).sendKeys("12fwejf");

		allelements = mobiledriver.findElementsById("com.google.android.gsf.login:id/last_name_edit");
		allelements.removeAll(Collections.singleton(null));
		allelements.get(0).sendKeys("12fwejfweff");

		allelements = mobiledriver.findElementsById("com.google.android.gsf.login:id/next_button");
		allelements.removeAll(Collections.singleton(null));
		allelements.get(0).click();

		// wait

		while (!flag) {
			flag = waitForPresence(mobiledriver, 5000, "com.google.android.gsf.login:id/username_edit");
		}
		flag = false;
		allelements = mobiledriver.findElementsById("com.google.android.gsf.login:id/username_edit");
		allelements.removeAll(Collections.singleton(null));
		allelements.get(0).sendKeys("12fwfewfwfejf");

		allelements = mobiledriver.findElementsById("com.google.android.gsf.login:id/next_button");
		allelements.removeAll(Collections.singleton(null));
		allelements.get(0).click();

		// wait
		while (!flag) {
			flag = waitForPresence(mobiledriver, 5000, "com.google.android.gsf.login:id/password_edit");
		}
		flag = false;
		allelements = mobiledriver.findElementsById("com.google.android.gsf.login:id/password_edit");
		allelements.removeAll(Collections.singleton(null));
		allelements.get(0).sendKeys("*&-@^%!,^@!");
		
		Thread.sleep(1500);
		allelements = mobiledriver.findElementsById("com.google.android.gsf.login:id/confirm_password_edit");
		allelements.removeAll(Collections.singleton(null));
		allelements.get(0).sendKeys("*&-@^%!,^@!");
		
		allelements = mobiledriver.findElementsById("com.google.android.gsf.login:id/next_button");
		allelements.removeAll(Collections.singleton(null));
		allelements.get(0).click();

		// wait
		while (!flag) {
			flag = waitForPresence(mobiledriver, 5000, "com.google.android.gsf.login:id/no");
		}
		flag = false;
		allelements = mobiledriver.findElementsById("com.google.android.gsf.login:id/no");
		allelements.removeAll(Collections.singleton(null));
		allelements.get(0).click();

		allelements = mobiledriver.findElementsById("com.google.android.gsf.login:id/next_button");
		allelements.removeAll(Collections.singleton(null));
		allelements.get(0).click();
		
		// wait
		while (!flag) {
			flag = waitForPresence(mobiledriver, 5000, "com.google.android.gsf.login:id/yes_button");
		}
		flag = false;
		allelements = mobiledriver.findElementsById("com.google.android.gsf.login:id/yes_button");
		allelements.removeAll(Collections.singleton(null));
		allelements.get(0).click();

		// wait
		while (!flag) {
			flag = waitForPresence(mobiledriver, 5000, "com.google.android.gsf.login:id/captcha_answer_edit");
		}
		flag = false;
		allelements = mobiledriver.findElementsById("com.google.android.gsf.login:id/captcha_answer_edit");
		allelements.removeAll(Collections.singleton(null));
		screenshot("D:\\", mobiledriver);
	}

	public boolean waitForPresence(AndroidDriver driver, int timeLimitInSeconds, String targetResourceID) {

		try {
			List<AndroidElement> allelements = null;
			allelements=driver.findElementsById(targetResourceID);
			allelements.removeAll(Collections.singleton(null));
			WebDriverWait wait = new WebDriverWait(driver, timeLimitInSeconds);
			wait.until(ExpectedConditions.visibilityOf(allelements.get(0)));
			boolean isElementPresent = allelements.get(0).isDisplayed();
			return isElementPresent;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void screenshot(String path_screenshot, AndroidDriver driver) throws IOException {
		File srcFile = driver.getScreenshotAs(OutputType.FILE);
		String filename = UUID.randomUUID().toString();
		File targetFile = new File(path_screenshot + filename + ".jpg");
		FileUtils.copyFile(srcFile, targetFile);
	}

}
