package viewCoachingLogs_Scripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;

import driver.EnvironmentVariables;
import methods.CoachingAppMethods;
import methods.ViewCoachingLogsMethods;

public class Resched_Cancel_CoachingLogs {
	WebDriver driver=null;
	CoachingAppMethods coachingMethods = new CoachingAppMethods();
	ViewCoachingLogsMethods viewcoaching = new ViewCoachingLogsMethods();
	ExtentReports reports;
	ExtentTest logger;
	
	
	@BeforeClass
	public void launchBrowser() throws InterruptedException 
	{
		//Extent Reports
		reports = new ExtentReports(EnvironmentVariables.extendReportsPath("Cancel Resched Coaching Logs"),
				NetworkMode.OFFLINE);
		logger = reports.startTest("Cancel Resched Coaching Logs");
	}
	

	@Test(dataProvider = "TestData")
	public void ViewCoachingLog(String flag, String browser, String level, String userName, String password, String MonthYear,String Day,String Time,String AMPM, String Duration,String Filter) throws Exception {
		// Verify browser
		if(flag.equalsIgnoreCase("Y")) 
		 {
			if(browser.equalsIgnoreCase("chrome"))
			{
				System.out.println("Launching Chrome browser...");
				coachingMethods.logInfo(logger, "Browser: Google Chrome");
				System.setProperty(EnvironmentVariables._chromeDrivertype, EnvironmentVariables._chromeDriverpath);
				driver = new ChromeDriver();
				
			}
			else if (browser.equalsIgnoreCase("firefox"))
			{
				System.out.println("Launching Firefox browser...");
				coachingMethods.logInfo(logger, "Browser: Mozilla Firefox");
				System.setProperty(EnvironmentVariables._firefoxDrivertype, EnvironmentVariables._firefoxDriverpath);
				driver = new FirefoxDriver();
			}
			else if (browser.equalsIgnoreCase("IE"))
			{
				System.out.println("Launching IE browser...");
				coachingMethods.logInfo(logger, "Browser: Internet Explorer");
				System.setProperty(EnvironmentVariables._ieDrivertype, EnvironmentVariables._ieDriverpath);
				driver = new InternetExplorerDriver();
			}
			else if (browser.equals("") || !browser.equalsIgnoreCase("chrome") || !browser.equalsIgnoreCase("firefox") || !browser.equalsIgnoreCase("ie"))
			{
				coachingMethods.logFail(logger, "Choose valid browser");
				System.out.println("Choose valid browser");
			}
			
			driver.manage().window().maximize();
			driver.get(EnvironmentVariables._URL);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			// Log-in
			coachingMethods.logInfo(logger, "User role is " +level);
			coachingMethods.login(driver, userName, password, logger);
			coachingMethods.modalPrivacy(driver, logger);
			coachingMethods.modalFeedback(driver, logger);
			Thread.sleep(1500);
			viewcoaching.coachingLogs(driver, MonthYear, Day,Time,AMPM,Duration,Filter,logger);
			
		} 
	}
	
	@DataProvider(name="TestData")
	public Object[][] getValueFromExcel(ITestContext context)
	{
		String sheetName = "Reshced_Cancel";
		Object[][] data = coachingMethods.getExcelData(EnvironmentVariables._dataPoolPath, sheetName);		
		return data;
	}
	
	
	@AfterClass
	public void tearDown() throws InterruptedException
    {
		System.out.println("Closing Browser...");
		Thread.sleep(3000);
		reports.endTest(logger);
        reports.flush();
        driver.quit();
    }


}
