package automation_app.automation_app;

import java.io.IOException;

import Services.*;
public class App 
{
	private static IAutomationAndroid IAutomationAndroid;
	
    public static void main( String[] args ) throws InterruptedException, IOException
    {
    	IAutomationAndroid = new AutomationAndroidImp();
    	IAutomationAndroid.startAuto();
    }
}
