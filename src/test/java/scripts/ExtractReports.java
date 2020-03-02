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
import methods.ExtractReportsMethods;

public class ExtractReports {

	WebDriver driver = null;
	CoachingAppMethods coachingMethods = new CoachingAppMethods();
	ExtractReportsMethods erMethods = new ExtractReportsMethods();
	ExtentReports reports;
	ExtentTest logger;

	@BeforeClass
	public void launchBrowser() throws InterruptedException {
		// Extent Reports
		reports = new ExtentReports(EnvironmentVariables.extendReportsPath("Extract   Reports"),
				NetworkMode.OFFLINE);
		logger = reports.startTest("Extract  Reports");
	}

	@Test(dataProvider = "TestData")
	public void extractReports(String flag, String browser, String level, String userName, String password,
			String fromYearPerf, String fromMonthPerf, String fromDayPerf, String toYearPerf, String toMonthPerf,
			String toDayPerf, String showEntryPerf, String searchPerf, String fromYearOther, String fromMonthOther,
			String fromDayOther, String toYearOther, String toMonthOther, String toDayOther, String showEntryOther,
			String searchOther, String fromYearTRIAD, String fromMonthTRIAD, String fromDayTRIAD, String toYearTRIAD,
			String toMonthTRIAD, String toDayTRIAD, String showEntryTRIAD, String searchTRIAD) throws Exception {
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
			Thread.sleep(3000);
			coachingMethods.modalFeedback(driver, logger);

			// Extract Reports - Performance Coaching Data
			erMethods.extractReportsPerformance(driver, logger, browser, fromYearPerf, fromMonthPerf, fromDayPerf,
					toYearPerf, toMonthPerf, toDayPerf, showEntryPerf, searchPerf);

			// Extract Reports - Other Coaching Data
			erMethods.extractReportsOther(driver, logger, browser, fromYearOther, fromMonthOther, fromDayOther,
					toYearOther, toMonthOther, toDayOther, showEntryOther, searchOther);

			if (level.equalsIgnoreCase("Project Admin") )
				// Extract Reports - TRIAD Coaching Data
				erMethods.extractReportsTRIAD(driver, logger, browser, fromYearTRIAD, fromMonthTRIAD, fromDayTRIAD,
						toYearTRIAD, toMonthTRIAD, toDayTRIAD, showEntryTRIAD, searchTRIAD);
			else
				System.out.println("End");

			coachingMethods.logInfo(logger,
					"=========================================== End of Scenario ==============================================");

			driver.close();
		}
	}

	@DataProvider(name = "TestData")
	public Object[][] getValueFromExcel(ITestContext context) {
		String sheetName = "ExtractReports";
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