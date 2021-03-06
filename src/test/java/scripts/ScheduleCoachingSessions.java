package scripts;

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
import methods.ScheduleCoachingSessionsMethods;

public class ScheduleCoachingSessions {

	WebDriver driver = null;
	CoachingAppMethods coachingMethods = new CoachingAppMethods();
	ScheduleCoachingSessionsMethods scsMethods = new ScheduleCoachingSessionsMethods();
	ExtentReports reports;
	ExtentTest logger;

	@BeforeClass
	public void launchBrowser() throws InterruptedException {
		// Extent Reports
		reports = new ExtentReports(EnvironmentVariables.extendReportsPath("Schedule  Coaching Sessions"),
				NetworkMode.OFFLINE);
		logger = reports.startTest("Schedule Coaching Sessions");
	}

	@Test(dataProvider = "TestData")
	public void scheduleCoachingSessions(String flag, String browser, String level, String userName, String password,
			String coachee, String coachingType, String coachForTRIAD, String location, String realtime,
			String duration, String typeOfDate, String monthYear, String day, String date, String startTime,
			String ampm, String agenda) throws Exception {
		// Verify browser
		if (flag.equalsIgnoreCase("Y")) {

			coachingMethods.logInfo(logger,
					"=========================================== Start of Scenario ==============================================");

			if (browser.equalsIgnoreCase("chrome")) {
				System.out.println("Launching Chrome browser...");
				coachingMethods.logInfo(logger, "Browser: Google Chrome");
				System.setProperty(EnvironmentVariables._chromeDrivertype, EnvironmentVariables._chromeDriverpath);
				driver = new ChromeDriver();

			} else if (browser.equalsIgnoreCase("firefox")) {
				System.out.println("Launching Firefox browser...");
				coachingMethods.logInfo(logger, "Browser: Mozilla Firefox");
				System.setProperty(EnvironmentVariables._firefoxDrivertype, EnvironmentVariables._firefoxDriverpath);
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("IE")) {
				System.out.println("Launching IE browser...");
				coachingMethods.logInfo(logger, "Browser: Internet Explorer");
				System.setProperty(EnvironmentVariables._ieDrivertype, EnvironmentVariables._ieDriverpath);
				driver = new InternetExplorerDriver();
			} else if (browser.equals("") || !browser.equalsIgnoreCase("chrome") || !browser.equalsIgnoreCase("firefox")
					|| !browser.equalsIgnoreCase("ie")) {
				coachingMethods.logFail(logger, "Choose valid browser");
				System.out.println("Choose valid browser");
			}

			driver.manage().window().maximize();
			driver.get(EnvironmentVariables._URL);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			// Log-in
			coachingMethods.logInfo(logger, "User role is " + level);
			coachingMethods.login(driver, userName, password, logger);

			// Close Modal
			coachingMethods.modalPrivacy(driver, logger);
			coachingMethods.modalFeedback(driver, logger);
			Thread.sleep(3000);

			// Schedule Coaching Sessions
			scsMethods.scheduleCoachingSessions(driver, logger, coachee, coachingType, coachForTRIAD, location,
					realtime, duration, typeOfDate, monthYear, day, date, startTime, ampm, agenda);

			coachingMethods.logInfo(logger,
					"=========================================== End of Scenario ==============================================");

			driver.close();
		}
	}

	@DataProvider(name = "TestData")
	public Object[][] getValueFromExcel(ITestContext context) {
		String sheetName = "ScheduleCoachingSessions";
		Object[][] data = coachingMethods.getExcelData(EnvironmentVariables._dataPoolPath, sheetName);
		return data;
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		System.out.println("Closing Browser...");
		Thread.sleep(3000);
		reports.endTest(logger);
		reports.flush();
		driver.quit();
	}
}