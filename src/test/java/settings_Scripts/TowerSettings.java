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

public class TowerSettings extends SettingsMethods {

	WebDriver driver = null;
	CoachingAppMethods coachingMethods = new CoachingAppMethods();
	TowerSettingsMethods towerSettingsMethods = new TowerSettingsMethods();
	ExtentReports reports;
	ExtentTest logger;

	@BeforeClass
	public void Open() throws InterruptedException, Exception {
		// Extent Reports
		reports = new ExtentReports(EnvironmentVariables.extendReportsPath("Setting - Tower Settings"),
				NetworkMode.OFFLINE);
		logger = reports.startTest("Setting - Tower Settings");
	}

	@Test(dataProvider = "testdata")
	public void coaching(String flag, String browser, String level, String userName, String password, String scenario,
			String tower, String newTower, String project, String newProject, String segment, String newSegment)
			throws Exception {
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
			Thread.sleep(3000);

			// Add Tower
			if (scenario.equalsIgnoreCase("Tower")) {
				coachingMethods.logInfo(logger, "Scenario: " + scenario);
				towerSettingsMethods.towerMethods(driver, tower, newTower, logger);
			}

			// Add Project
			else if (scenario.equalsIgnoreCase("Project")) {
				coachingMethods.logInfo(logger, "Scenario: " + scenario);
				towerSettingsMethods.projectMethods(driver, tower, project, newProject, logger);
			}

			// Add Segment
			else if (scenario.equalsIgnoreCase("Segment")) {
				coachingMethods.logInfo(logger, "Scenario: " + scenario);
				towerSettingsMethods.segmentMethods(driver, tower, project, segment, newSegment, logger);
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
		String sheetName = "Settings-TowerSettings";
		Object[][] data = coachingMethods.getExcelData(EnvironmentVariables._dataPoolPath, sheetName);
		return data;
	}
}