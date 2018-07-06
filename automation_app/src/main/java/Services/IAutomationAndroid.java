package Services;

import java.io.IOException;

import io.appium.java_client.android.AndroidDriver;

public interface IAutomationAndroid {
	public void startAuto()throws InterruptedException, IOException;
	public boolean waitForPresence(AndroidDriver driver, int timeLimitInSeconds, String targetResourceID);
	public void screenshot(String path_screenshot, AndroidDriver driver) throws IOException;
}
