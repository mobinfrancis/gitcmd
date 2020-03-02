package settings_Scripts;

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
import methods.CoachingAppMethods;
import methods.SettingsMethods;

public class RosterManagement extends SettingsMethods {

	WebDriver driver = null;
	CoachingAppMethods coachingMethods = new CoachingAppMethods();
	RosterManagementMethods rosterManagementMethods = new RosterManagementMethods();
	ExtentReports reports;
	ExtentTest logger;

	@BeforeClass
	public void Open() throws InterruptedException, Exception {
		// Extent Reports
		reports = new ExtentReports(EnvironmentVariables.extendReportsPath("Setting - Roster Management"),
				NetworkMode.OFFLINE);
		logger = reports.startTest("Setting - Roster Management");
	}

	@Test(dataProvider = "testdata")
	public void coaching(String flag, String browser, String level, String userName, String password, String scenario,
			String EID, String employeeStatus, String fullName, String role, String businessEmail, String siteLocation,
			String eidTeamLead, String eidTeamManager, String eidDeliveryLead, String tower, String project,
			String segment, String rosterTemplate, String uploadEID1, String uploadEID2, String coachingDashboardCSV,
			String coachingDashboardXLSX, String newEID, String remarks) throws Exception {
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
			else if (browser.equalsIgnoreCase("Firefox")) {
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
			
			coachingMethods.modalFeedback(driver, logger);

			// Add Single User Scenario
			if (scenario.equalsIgnoreCase("Add Single User")) {
				coachingMethods.logInfo(logger, "Scenario: " + scenario);
				rosterManagementMethods.addSingleUser(driver, EID, employeeStatus, fullName, role, businessEmail,
						siteLocation, eidTeamLead, eidTeamManager, eidDeliveryLead, tower, project, segment, logger);
			}

			// Add Multiple Users Scenario
			else if (scenario.equalsIgnoreCase("Add Multiple Users")) {
				coachingMethods.logInfo(logger, "Scenario: " + scenario);
				rosterManagementMethods.addMultipleUsers(driver, browser, rosterTemplate, uploadEID1, uploadEID2,
						logger);
			}

			// Edit User
			else if (scenario.equalsIgnoreCase("Edit User")) {
				coachingMethods.logInfo(logger, "Scenario: " + scenario);
				rosterManagementMethods.editUser(driver, browser, coachingDashboardCSV, coachingDashboardXLSX, EID,
						newEID, employeeStatus, fullName, role, businessEmail, siteLocation, eidTeamLead,
						eidTeamManager, eidDeliveryLead, tower, project, segment, remarks, logger);
			} else
				coachingMethods.logFail(logger, "Please select a valid scenario.", driver);

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
		String sheetName = "Settings-RosterManagement";
		Object[][] data = coachingMethods.getExcelData(EnvironmentVariables._dataPoolPath, sheetName);
		return data;
	}
}