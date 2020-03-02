package scripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;

import driver.EnvironmentVariables;
import driver.GenericMethods2;
import methods.CoachingAppMethods;
import methods.ExtractProdQualityReportMethods;

public class ExtractProdQualityReport {

	WebDriver driver = null;
	CoachingAppMethods coachingMethods = new CoachingAppMethods();
	ExtractProdQualityReportMethods extractProdQualityReportMethods = new ExtractProdQualityReportMethods();
	GenericMethods2 gm = new GenericMethods2();
	ExtentReports reports;
	ExtentTest logger;

	@BeforeClass
	public void Open() throws InterruptedException, Exception {
		// Extent Reports
		reports = new ExtentReports(EnvironmentVariables.extendReportsPath("Extract Prod and Quality Reports"),
				NetworkMode.OFFLINE);
		logger = reports.startTest("Extract Prod and Quality Reports");
	}

	@Test(dataProvider = "testdata")
	public void coaching(String flag, String browser, String level, String userName, String password, String month,
			String year, String productivtyEID, String productivtyCSV, String productivtyExcel, String qualityEID,
			String qualityCSV, String qualityExcel) throws Exception {
		if (flag.equalsIgnoreCase("Y")) {

			coachingMethods.logInfo(logger,
					"=========================================== Start of Scenario ==============================================");

			// Chrome Browser
			if (browser.equalsIgnoreCase("Chrome")) {
				System.setProperty(EnvironmentVariables._chromeDrivertype, EnvironmentVariables._chromeDriverpath);
				driver = new ChromeDriver();
				coachingMethods.logInfo(logger, "Google Chrome is the selected browser.");
			}

			// FireFox Browser
			else if (browser.equalsIgnoreCase("FF")) {
				System.setProperty(EnvironmentVariables._firefoxDrivertype, EnvironmentVariables._firefoxDriverpath);
				driver = new FirefoxDriver();
				coachingMethods.logInfo(logger, "FIrefox is the selected browser.");
			}

			// IE Browser
			else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty(EnvironmentVariables._ieDrivertype, EnvironmentVariables._ieDriverpath);
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability("ignoreZoomSetting", true);
				driver = new InternetExplorerDriver(caps);
				coachingMethods.logInfo(logger, "Internet Explorer is the selected browser.");
			} else
				coachingMethods.logFail(logger, "Please enter a valid browser.", driver);

			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get(EnvironmentVariables._URL);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			// User role level
			coachingMethods.logInfo(logger, "User role is " + level + ".");

			// Login
			coachingMethods.login(driver, userName, password, logger);

			// Privacy Statement
			coachingMethods.modalPrivacy(driver, logger);
			Thread.sleep(3000);
			coachingMethods.modalFeedback(driver, logger);

			// Extract Prod and Quality Report
			extractProdQualityReportMethods.extractProdQualityReport(driver, browser, month, year, productivtyEID,
					productivtyCSV, productivtyExcel, qualityEID, qualityCSV, qualityExcel, logger);

			coachingMethods.logInfo(logger,
					"=========================================== End of Scenario ==============================================");

			driver.close();
		}
	}

	@AfterClass
	public void closeBrowser() {
		reports.endTest(logger);
		reports.flush();
		driver.quit();
	}

	@DataProvider(name = "testdata")
	public Object[][] getValueFromExcel(ITestContext context) {
		String sheetName = "ExtractProdQualityReport";
		Object[][] data = coachingMethods.getExcelData(EnvironmentVariables._dataPoolPath, sheetName);
		return data;
	}
}