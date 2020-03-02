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

public class ViewCoachingLogs {
	WebDriver driver=null;
	CoachingAppMethods coachingMethods = new CoachingAppMethods();
	ViewCoachingLogsMethods viewcoaching = new ViewCoachingLogsMethods();
	ExtentReports reports;
	ExtentTest logger;
	
	
	@BeforeClass
	public void launchBrowser() throws InterruptedException 
	{
		//Extent Reports
		reports = new ExtentReports(EnvironmentVariables.extendReportsPath("View Coaching Logs"),
				NetworkMode.OFFLINE);
		logger = reports.startTest("View Coaching Logs");
	}
	

	@Test(dataProvider = "TestData")
	public void ViewCoachingLog(String flag, String browser, String level, String userName, String password,
			String CoachingType, String Text, String Focus, String SLA, String Amber, String p1, String p1_1,
			String p1_2, String p1_3, String p1_4, String p2, String p2_1, String p2_2, String p3, String p3_1,
			String p3_2, String p4, String p4_1, String p4_2, String p4_3) throws Exception {
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
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			// Log-in
			coachingMethods.logInfo(logger, "User role is " +level);
			coachingMethods.login(driver, userName, password, logger);
			coachingMethods.modalPrivacy(driver, logger);
			coachingMethods.modalFeedback(driver, logger);
			Thread.sleep(1500);
			if(CoachingType.equalsIgnoreCase("Performance Coaching")) {
				viewcoaching.performanceCoaching(driver,CoachingType, Text,Focus, SLA,Amber,logger);
			}else if(CoachingType.equals("Coaching")) {
				viewcoaching.coaching_OtherCoaching(driver, CoachingType, Text, Amber, logger);
			}else if(CoachingType.equals("TRIAD")) {
				viewcoaching.triad(driver, CoachingType, p1, p1_1, p1_2, p1_3, p1_4, p2, p2_1, p2_2, p3, p3_1, p3_2, p4,  p4_1, p4_2, p4_3, logger);
			}
		} 
	}
	
	@DataProvider(name="TestData")
	public Object[][] getValueFromExcel(ITestContext context)
	{
		String sheetName = "ViewCoachingLogs";
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
