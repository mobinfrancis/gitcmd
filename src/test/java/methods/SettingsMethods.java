package methods;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentTest;
import driver.EnvironmentVariables;
import driver.GenericMethods2;
import pageObjects.SettingsObjects;
import pageObjects.SettingsObjects.RosterManagementObjects;

public class SettingsMethods {

	CoachingAppMethods coachingMethods = new CoachingAppMethods();
	GenericMethods2 gm = new GenericMethods2();

	// ------------------------Generic Methods for
	// Settings------------------------------
	public void navigateSpecificSettingTab(WebDriver driver, By locator, String objName, ExtentTest logger)
			throws InterruptedException {
		// Navigate to Settings and Specific Settings Tab
		coachingMethods.scrollIntoView(driver, SettingsObjects.SettingsCommonObjects._lnkSettings);
		coachingMethods.clickElementPass(driver, SettingsObjects.SettingsCommonObjects._lnkSettings, "FR 6.1/FR 6.2",
				"Settings", logger);
		coachingMethods.clickElementInfo(driver, locator, objName, logger);
	}

	public void getEntry(WebDriver driver, By locator, ExtentTest logger) {
		String entry = driver.findElement(locator).getText();
		coachingMethods.logInfo(logger, entry);
	}

	public void searchUserPass(WebDriver driver, String reqNo, String value, By txtSearch, By lblValue,
			ExtentTest logger) {
		coachingMethods.scrollIntoView(driver, txtSearch);
		coachingMethods.enterTextInfo(driver, txtSearch, value, "Search", logger);
		if (coachingMethods.elementExist(driver, lblValue)) {
			coachingMethods.logPass(logger, reqNo + " - " + value + " is found in the Table.", driver);
		} else
			coachingMethods.logFail(logger, reqNo + " - " + value + " not found in the Table.", driver);
	}

	public void searchDeletedUserPass(WebDriver driver, String reqNo, String value, By txtSearch, By lblValue,
			ExtentTest logger) {
		coachingMethods.scrollIntoView(driver, txtSearch);
		coachingMethods.enterTextInfo(driver, txtSearch, value, "Search", logger);
		if (!coachingMethods.elementExist(driver, lblValue)) {
			coachingMethods.logPass(logger, reqNo + " - " + value + " not found in the Table.");
		} else
			coachingMethods.logFail(logger, reqNo + " - " + value + " is still found in the Table.", driver);
	}

	public void enterNewTextPass(WebDriver driver, By locator, String reqNo, String newValue, String objName,
			ExtentTest logger) {
		driver.findElement(locator).clear();
		coachingMethods.enterTextPass(driver, locator, reqNo, newValue, objName, logger);
	}

	public void closePopupPass(WebDriver driver, By btnClose, String btnName, By lblPopup, String namePopup,
			String reqNo, ExtentTest logger) throws Exception {
		coachingMethods.clickElementInfo(driver, btnClose, btnName, logger);
		Thread.sleep(3000);
		if (!driver.findElement(lblPopup).isDisplayed()) {
			coachingMethods.logPass(logger, reqNo + " - " + namePopup + " popup is closed.");
		} else
			coachingMethods.logFail(logger, reqNo + " - " + namePopup + " popup is still displayed.", driver);
	}

	public void openPopupInfo(WebDriver driver, By btnPopup, String btnNamePopup, By lblPopup, String namePopup,
			ExtentTest logger) throws Exception {
		coachingMethods.clickElementInfo(driver, btnPopup, btnNamePopup, logger);
		coachingMethods.popupDisplayValidationInfo(driver, lblPopup, namePopup, logger);
	}

	public void openPopupPass(WebDriver driver, By btnPopup, String btnNamePopup, By lblPopup, String namePopup,
			String reqNo, ExtentTest logger) throws Exception {
		coachingMethods.clickElementInfo(driver, btnPopup, btnNamePopup, logger);
		coachingMethods.popupDisplayValidationPass(driver, lblPopup, reqNo, namePopup, logger);
	}

	// ======================Access Settings==============================

	public void accessSettings(WebDriver driver, String browser, String level, String EID, String AccessLevel,
			String UpdateEID, String Status, String EditAccessLevel, ExtentTest logger) throws Exception {
		String Access;
		// Click Settings
		coachingMethods.clickElementInfo(driver, SettingsObjects.SettingsCommonObjects._lnkSettings, "Settings",
				logger);
		coachingMethods.pageRedirectionValidationInfo(driver, SettingsObjects.SettingsCommonObjects._btnPamSettings,
				"Settings", logger);
		// Click Access Setting
		coachingMethods.clickElementPass(driver, SettingsObjects.SettingsCommonObjects._btnAccessSettings, "DEVELOPER : STEP 1",
				"Access Setting", logger);
		// Click Grant Access
		coachingMethods.clickElementPass(driver, SettingsObjects.AccessSetting._btnGrant, "DEVELOPER : STEP 2", "Grant Access",
				logger);

		// closing Grant Pop up
		if (coachingMethods.waitForObjectExistence(driver, SettingsObjects.AccessSetting._popupGrantAccess)) {
			coachingMethods.logPass(logger, "Grant Access Pop up is displayed");
			coachingMethods.clickElementPass(driver, SettingsObjects.AccessSetting._btnX, "DEVELOPER : STEP 3", "X", logger);
		} else
			coachingMethods.logFail(logger, "Grant Access Pop up is not displayed", driver);
		Thread.sleep(2000);
		WebElement GrantPopUp = driver.findElement(SettingsObjects.AccessSetting._popupGrantAccess);
		if (GrantPopUp.isDisplayed()) {
			coachingMethods.logFail(logger, "Grant Access Pop up is not closed", driver);
		} else
			coachingMethods.logPass(logger, "Grant Access Pop up is closed");
		Thread.sleep(2000);
		// Click Grant Access
		coachingMethods.clickElementPass(driver, SettingsObjects.AccessSetting._btnGrant, "DEVELOPER : STEP 4", "Grant Access",
				logger);

		// grant access pop up
		if (coachingMethods.waitForObjectExistence(driver, SettingsObjects.AccessSetting._popupGrantAccess)) {
			// Select EIDs
			coachingMethods.clickElementInfo(driver, SettingsObjects.AccessSetting._drpSelectEID, "Select EID", logger);
			// coachingMethods.clickElementInfo(driver,
			// SettingsObjects.AccessSetting._grantEID(EID), "EID", logger);
			String eid = coachingMethods.getValueFromDatasheet("accessSetting", "EID", 1, logger, driver);
			String[] selectedeid = eid.split(", ");
			for (int i = 0; i < selectedeid.length; i++) {
				// remove comments
				gm.scrollToViewElement(driver, SettingsObjects.AccessSetting._selEID(selectedeid[i]));
				coachingMethods.clickElementPass(driver, SettingsObjects.AccessSetting._selEID(selectedeid[i]),
						"DEVELOPER : STEP 5", selectedeid[i], logger);
			}
			// Click text "Grant Access"
			coachingMethods.clickElementInfo(driver, SettingsObjects.AccessSetting._popupGrantAccess, "Grant Access",
					logger);
			// Select Access Level
			coachingMethods.selectVisibleTextPass(driver, SettingsObjects.AccessSetting._drpLevel, "DEVELOPER : STEP 6",
					AccessLevel, "Access Level:", logger);
			// Click Submit
			coachingMethods.clickElementPass(driver, SettingsObjects.AccessSetting._btnSubmit, "DEVELOPER : STEP 7", "Submit",
					logger);
		} else
			coachingMethods.logFail(logger, "Grant Access Pop up is not displayed", driver);
		// Successfully saved pop up
		if (coachingMethods.waitForObjectExistence(driver, SettingsObjects.AccessSetting._popupSaved)) {
			coachingMethods.logPass(logger, "Grant Access is Saved");
		} else
			coachingMethods.logFail(logger, "Grant Access is not Saved");

		Thread.sleep(2000);

		// Click Bulk Upload
		coachingMethods.clickElementPass(driver, SettingsObjects.AccessSetting._btnBulkUpload, "DEVELOPER : STEP 8", "Bulk Upload",
				logger);

		// closing Bulk upload Pop up
		if (coachingMethods.waitForObjectExistence(driver, SettingsObjects.AccessSetting._btnBulkUpload)) {
			Thread.sleep(1500);
			coachingMethods.logPass(logger, "Bulk Upload Pop up is displayed");
			coachingMethods.clickElementPass(driver, SettingsObjects.AccessSetting._btnCLose, "DEVELOPER : STEP 9", "X", logger);
		} else
			coachingMethods.logFail(logger, "Bulk Upload Pop up is not displayed", driver);
		Thread.sleep(1500);

		// Click Bulk Upload
		coachingMethods.clickElementPass(driver, SettingsObjects.AccessSetting._btnBulkUpload, "DEVELOPER : STEP 10", "Bulk Upload",
				logger);
		// Bulk upload pop up
		if (coachingMethods.waitForObjectExistence(driver, SettingsObjects.AccessSetting._popupBulkUpload)) {
			Thread.sleep(1500);
			// Click Download Template
			coachingMethods.fileDownloadPass(driver, SettingsObjects.AccessSetting._linkDownloadTemplate,
					"Download Template", browser, ".xlsx", "DEVELOPER : STEP 11", logger);
			/*
			 * 
			 * Using jOptions for uploading the roster template manually
			 * 
			 * Reason: AutoIT scripts would not work sometimes
			 */
			/*
			 * if (browser.equalsIgnoreCase("firefox")) { WebElement ChooseFile =
			 * driver.findElement(SettingsObjects.AccessSetting._linkUploadTemplate);
			 * JavascriptExecutor executor = (JavascriptExecutor) driver;
			 * executor.executeScript("arguments[0].click();", ChooseFile);
			 * Thread.sleep(1500);
			 * Runtime.getRuntime().exec(EnvironmentVariables._uploadFileFF);
			 * Thread.sleep(1500); } else { coachingMethods.clickElementPass(driver,
			 * SettingsObjects.AccessSetting._linkUploadTemplate, "FR 6.48",
			 * "Upload Template", logger); Thread.sleep(1500);
			 * Runtime.getRuntime().exec(EnvironmentVariables._uploadFileChrome);
			 * Thread.sleep(1500); }
			 */

			final JDialog dialog = new JDialog();
			dialog.setAlwaysOnTop(true);

			int reply = JOptionPane.showConfirmDialog(null, "Uploaded the file?", "FILE UPLOAD",
					JOptionPane.YES_NO_OPTION);

			if (reply == JOptionPane.YES_OPTION) {
				// Click Submit
				coachingMethods.clickElementPass(driver, SettingsObjects.AccessSetting._btnSubmitBulk, "DEVELOPER : STEP 12",
						"Submit", logger);
			}

			if (coachingMethods.waitForObjectExistence(driver, SettingsObjects.AccessSetting._popupSaved)) {
				coachingMethods.logPass(logger, "Bulk Upload is saved");
			} else
				coachingMethods.logFail(logger, "Select file to upload or Uploaded file is invalid", driver);

		} else
			coachingMethods.logFail(logger, "Bulk Upload Pop up is not displayed", driver);

		// Click Excel and CSV
		coachingMethods.fileDownloadPass(driver, SettingsObjects.AccessSetting._btnExcel, "Excel", browser,
				"RosterAccessSettings_", "DEVELOPER : STEP 13", logger);
		Thread.sleep(1000);
		coachingMethods.fileDownloadPass(driver, SettingsObjects.AccessSetting._btnCSV, "CSV", browser,
				"RosterAccessSettings_", "DEVELOPER : STEP 14", logger);
		Thread.sleep(1000);

		// enter text in Search field
		coachingMethods.enterTextPass(driver, SettingsObjects.AccessSetting._txtSearch, "DEVELOPER : STEP 15", UpdateEID, "Search",
				logger);
		Thread.sleep(1000);
		String Table = driver.findElement(SettingsObjects.AccessSetting._tblSetting).getText();
		if (Table.contains(AccessLevel)) {
			coachingMethods.logPass(logger, "DEVELOPER : STEP 16 Table is filtered by EID: " + UpdateEID);
		} else
			coachingMethods.logFail(logger, "DEVELOPER : STEP 17 Table is not filtered by EID: " + UpdateEID, driver);

		// Click Update
		gm.scrollToViewElement(driver, SettingsObjects.AccessSetting._btnUpdate(UpdateEID));
		Thread.sleep(4000);
		WebElement element = driver.findElement(SettingsObjects.AccessSetting._btnUpdate(UpdateEID));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		// Fill up update pop up
		if (coachingMethods.waitForObjectExistence(driver, SettingsObjects.AccessSetting._popupUpdate)) {

			coachingMethods.selectVisibleTextPass(driver, SettingsObjects.AccessSetting._selStatus, "DEVELOPER : STEP 18", Status,
					"Status", logger);
			Thread.sleep(1500);
			coachingMethods.selectVisibleTextPass(driver, SettingsObjects.AccessSetting._selLevel, "DEVELOPER : STEP 19",
					EditAccessLevel, "Status", logger);
			Thread.sleep(1000);
			coachingMethods.clickElementPass(driver, SettingsObjects.AccessSetting._btnSaveClose, "DEVELOPER : STEP 20",
					"Save & Close", logger);
		} else
			coachingMethods.logFail(logger, "Edit Access Pop up is not displayed", driver);
		Thread.sleep(2000);

		// Enter text in Search field
		coachingMethods.enterTextPass(driver, SettingsObjects.AccessSetting._txtSearch, "DEVELOPER : STEP 21", UpdateEID, "Search",
				logger);
		Thread.sleep(1000);
		String Table1 = driver.findElement(SettingsObjects.AccessSetting._tblSetting).getText();
		if (Table1.contains(EditAccessLevel)) {
			coachingMethods.logInfo(logger, "Access Level of " + UpdateEID + " is updated to " + EditAccessLevel,
					driver);
		} else
			coachingMethods.logFail(logger, "Access leve of " + UpdateEID + " is not updated", driver);

	}

	// =======================PAM Settings================================

	public void pamSetting(WebDriver driver, String browser, String EID, String Text, String Month, String Status,String FullName,String Supervisor,
			ExtentTest logger) throws Exception {
		// Click Settings
		coachingMethods.clickElementInfo(driver, SettingsObjects.SettingsCommonObjects._lnkSettings, "Settings",
				logger);

		// Click Add Entry
		coachingMethods.waitForObjectExistence(driver, SettingsObjects.PamSetting._btnAddEntry);
		coachingMethods.clickElementPass(driver, SettingsObjects.PamSetting._btnAddEntry, "HR PAM ADMIN : STEP 1", "Add Entry",
				logger);

		// closing add entry pop up
		if (coachingMethods.waitForObjectExistence(driver, SettingsObjects.PamSetting._popupAddEntry)) {
			Thread.sleep(1000);
			coachingMethods.logInfo(logger, "Add Entry Pop Up is displayed");
			coachingMethods.clickElementPass(driver, SettingsObjects.PamSetting._btnX, "HR PAM ADMIN : STEP 2", "X / Close", logger);
		} else
			coachingMethods.logFail(logger, "Add Entry Pop Up is not displayed", driver);
		Thread.sleep(1000);
		WebElement popUp = driver.findElement(SettingsObjects.PamSetting._popupAddEntry);
		if (popUp.isDisplayed()) {
			coachingMethods.logFail(logger, "Add Entry Pop up not closed", driver);
		} else
			coachingMethods.logPass(logger, "Add Entry Pop up closed");
		Thread.sleep(1000);
		//Click Add Entry
		 coachingMethods.clickElementPass(driver,
		 SettingsObjects.PamSetting._btnAddEntry, "HR PAM ADMIN : STEP 3", "Add Entry",logger);
		// FIll up fields in Add Entry pop up
		
			coachingMethods.enterTextPass(driver, SettingsObjects.PamSetting._selEID, "HR PAM ADMIN : STEP 4", EID, "Select EID:",
					logger);
			
			
			coachingMethods.enterTextPass(driver, SettingsObjects.PamSetting._txtAttribute1, "HR PAM ADMIN : STEP 5", Text,
					"Attribute 1", logger);
			
			Thread.sleep(4000);
			String FName = driver.findElement(SettingsObjects.PamSetting._fullName).getAttribute("value");
			//Verifying fulname
			if(FName.equals(FullName))
			{
				gm.logPass(logger, "First Name is Correct", driver);
			}
			else
			{
				gm.logFail(logger, "First Name is Incorrect", driver);
			}
			Thread.sleep(4000);
			String SuperName = driver.findElement(SettingsObjects.PamSetting._supervisor).getAttribute("value");
			if(SuperName.equals(Supervisor))
			{
				gm.logPass(logger, "Supervisor name is Correct", driver);
			}
			else
			{
				gm.logFail(logger, "Supervisor Name is Incorrect", driver);
			}
			coachingMethods.enterTextPass(driver, SettingsObjects.PamSetting._txtAttribute2, "HR PAM ADMIN : STEP 6", Text,
					"Attribute 2", logger);
			coachingMethods.enterTextPass(driver, SettingsObjects.PamSetting._txtAttribute3, "HR PAM ADMIN : STEP 7", Text,
					"Attribute 3", logger);
			coachingMethods.selectVisibleTextPass(driver, SettingsObjects.PamSetting._selMonth, "HR PAM ADMIN : STEP 8", Month,
					"First Alert Month:", logger);
			coachingMethods.selectVisibleTextPass(driver, SettingsObjects.PamSetting._selStatus, "HR PAM ADMIN : STEP 9", Status,
					"Feedback Status (With/Without):", logger);
			coachingMethods.clickElementPass(driver, SettingsObjects.PamSetting._btnSubmit, "HR PAM ADMIN : STEP 10", "Submit",
					logger);


		// WebElement Saved = driver.findElement( SettingsObjects.PamSetting._txtSaved);
		if (coachingMethods.waitForObjectExistence(driver, SettingsObjects.PamSetting._txtSaved)) {
			coachingMethods.logInfo(logger, "Add Entry is successfully saved!");
		} else
			coachingMethods.logInfo(logger, EID + "Already has PAM Attributes");
		Thread.sleep(1500);

		// Click Bulk Entry
		coachingMethods.clickElementPass(driver, SettingsObjects.PamSetting._btnBulkEntry, "HR PAM ADMIN : STEP 11", "Bulk Entry",
				logger);
		// closing bulk entry pop up
		if (coachingMethods.waitForObjectExistence(driver, SettingsObjects.PamSetting._popupBulkEntry)) {
			coachingMethods.logInfo(logger, "Bulk Entry pop up is displayed");
			coachingMethods.clickElementPass(driver, SettingsObjects.PamSetting._btnCloseBulk, "HR PAM ADMIN : STEP 12", "X / Close",
					logger);
		} else
			coachingMethods.logFail(logger, "Bulk Entry pop up is not displayed.", driver);
		Thread.sleep(1500);
		WebElement Bulk = driver.findElement(SettingsObjects.PamSetting._popupAddEntry);
		if (Bulk.isDisplayed()) {
			coachingMethods.logFail(logger, "Add Entry Pop up not closed", driver);
		} else
			coachingMethods.logPass(logger, "Add Entry Pop up closed");
		Thread.sleep(1500);

		// Click Bulk Entry
		coachingMethods.clickElementPass(driver, SettingsObjects.PamSetting._btnBulkEntry, "HR PAM ADMIN : STEP 13", "Bulk Entry",
				logger);

		if (coachingMethods.waitForObjectExistence(driver, SettingsObjects.PamSetting._popupBulkEntry)) {
			// Click Download Template
			coachingMethods.fileDownloadPass(driver, SettingsObjects.PamSetting._linkDownloadTemplate,
					"Download Template", browser, ".xlsx", "HR PAM ADMIN : STEP 14", logger);
			Thread.sleep(10000);
			/*
			if (browser.equalsIgnoreCase("firefox")) {
				WebElement ChooseFile = driver.findElement(SettingsObjects.PamSetting._selFile);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", ChooseFile);
				Thread.sleep(1500);
				Runtime.getRuntime().exec(EnvironmentVariables._uploadFPAMFF);
				Thread.sleep(1500);
			} else {
				coachingMethods.clickElementPass(driver, SettingsObjects.PamSetting._selFile, "FR 6.80",
						"Upload Template", logger);
				Thread.sleep(1500);
				Runtime.getRuntime().exec(EnvironmentVariables._uploadPAMChrome);
				Thread.sleep(1500);
			}
			/*
			 * 
			 * Using jOptions for uploading the roster template manually
			 * 
			 * Reason: AutoIT scripts would not work sometimes
			 */
			

			final JDialog dialog = new JDialog();
			dialog.setAlwaysOnTop(true);

			int reply = JOptionPane.showConfirmDialog(null, "Uploaded the file?", "FILE UPLOAD",
					JOptionPane.YES_NO_OPTION);

			if (reply == JOptionPane.YES_OPTION) {
				// Click Submit
				coachingMethods.clickElementPass(driver, SettingsObjects.PamSetting._btnSubmitBulk, "HR PAM ADMIN : STEP 15", "Submit",
						logger);
			}
			
		} else
			coachingMethods.logFail(logger, "Bulk Entry pop up is not displayed", driver);
		Thread.sleep(1000);
		// Successfully saved Pop up

		if (coachingMethods.waitForObjectExistence(driver, SettingsObjects.PamSetting._txtSaved)) {
			coachingMethods.logInfo(logger, "Bulk Entry is successfully saved!");
		} else {
			coachingMethods.logFail(logger, EID + "EIDs entered already has PAM Attributes or Invalid Template",
					driver);
		}
		Thread.sleep(1500);

		// download Excel and CSV
		coachingMethods.fileDownloadPass(driver, SettingsObjects.PamSetting._btnExcel, "Excel", browser, "PAMDBSettings_",
				"HR PAM ADMIN : STEP 16", logger);
		Thread.sleep(1000);
		coachingMethods.fileDownloadPass(driver, SettingsObjects.PamSetting._btnCSV, "CSV", browser, "PAMDBSettings_", "HR PAM ADMIN : STEP 17",
				logger);
		Thread.sleep(1000);


		//Enter text in Search Field
		coachingMethods.enterTextPass(driver, SettingsObjects.PamSetting._txtSearch, "HR PAM ADMIN : STEP 18", EID, "Search", logger);
	Thread.sleep(3000);
		String Table = driver.findElement(SettingsObjects.PamSetting._tblPAM).getText();
		Thread.sleep(4000);
		if (Table.contains(EID)) {
			coachingMethods.logPass(logger, "HR PAM ADMIN : STEP 19 Table is filtered by EID: " + EID, driver);
		} else
			coachingMethods.logFail(logger, "HR PAM ADMIN : STEP 19 Table is not filtered by EID: " + EID, driver);
		Thread.sleep(1000);

	}

	// ===================Coaching Target Settings========================

	public void coachingTarget(WebDriver driver, String Target, String Tower, ExtentTest logger) throws Exception {
		// Click Settings
		coachingMethods.clickElementInfo(driver, SettingsObjects.SettingsCommonObjects._lnkSettings, "Settings",
				logger);
		coachingMethods.pageRedirectionValidationInfo(driver, SettingsObjects.SettingsCommonObjects._btnPamSettings,
				"Settings", logger);
		// Click Coaching Target Settings
		coachingMethods.clickElementInfo(driver, SettingsObjects.SettingsCommonObjects._btnCoachingTargetSettings,
				"Coaching Target Settings", logger);

		if (coachingMethods.waitForObjectExistence(driver, SettingsObjects.Coaching._txtTarget)) {
			// Enter Text in Input coaching Target
			coachingMethods.enterTextPass(driver, SettingsObjects.Coaching._txtTarget, "FR 6.60", Target,
					"Input Coaching Target here:", logger);
		} else
			coachingMethods.logFail(logger, "Input Coaching Target field is not displayed", driver);
		Thread.sleep(1500);

		if (coachingMethods.waitForObjectExistence(driver, SettingsObjects.Coaching._selTower)) {
			// Enter Text in Select Tower Here
			coachingMethods.enterTextPass(driver, SettingsObjects.Coaching._selTower, "FR 6.61", Tower,
					"Select Tower here:", logger);
		} else
			coachingMethods.logFail(logger, "Select Tower here: field is not displayed", driver);
		Thread.sleep(1500);
		// Click Submit
		coachingMethods.clickElementPass(driver, SettingsObjects.Coaching._btnSubmit, "FR 6.62", "Submit", logger);
		Thread.sleep(1500);
		// Validation
		String target = driver.findElement(SettingsObjects.Coaching._txtCoachingTarget(Target)).getText();
		String tower = driver.findElement(SettingsObjects.Coaching._txtTower(Tower)).getText();
		if (target.equalsIgnoreCase(Target) && tower.equalsIgnoreCase(Tower)) {
			coachingMethods.logPass(logger, "Coaching Targets for " + Tower + " is set to" + Target, driver);
		} else {
			coachingMethods.logFail(logger, "Coaching Targets for " + Tower + " is not set to " + Target, driver);
		}
	}

	// =========================App Usage=================================

	public void appUsage(WebDriver driver, String browser, String userName, String Month, String Year,
			String MonthNumber, ExtentTest logger) throws Exception {
		// Click Settings
		coachingMethods.clickElementInfo(driver, SettingsObjects.SettingsCommonObjects._lnkSettings, "Settings",
				logger);
		coachingMethods.pageRedirectionValidationInfo(driver, SettingsObjects.SettingsCommonObjects._btnPamSettings,
				"Settings", logger);
		// Click App Usage
		coachingMethods.clickElementInfo(driver, SettingsObjects.SettingsCommonObjects._btnAppUsage, "App Usage",
				logger);

		if (coachingMethods.waitForObjectExistence(driver, SettingsObjects.AppUsage._selMonth)) {
			// Select Month
			coachingMethods.selectVisibleTextPass(driver, SettingsObjects.AppUsage._selMonth, "FR 6.63", Month,
					"Select Month: ", logger);
			// Select Year
			coachingMethods.selectVisibleTextPass(driver, SettingsObjects.AppUsage._selYear, "FR 6.64", Year,
					"Select Year: ", logger);
			Thread.sleep(1000);
			// Click Filter
			coachingMethods.clickElementPass(driver, SettingsObjects.AppUsage._btnFilter, "FR 6.65", "Filter", logger);
			Thread.sleep(5000);
			// Validation table is filtered
			if (coachingMethods.waitForObjectExistence(driver, SettingsObjects.AppUsage._tblAppUsage)) {
				String data = driver.findElement(By.xpath("//*[@id=\"AppUsage\"]/tr[1]/td[2]")).getText();
				if (data.contains(MonthNumber) && data.contains(Year)) {
					coachingMethods.logPass(logger, "Table is filtered by " + Month + Year);
				} else
					coachingMethods.logFail(logger, "Table is not filtered by " + Month + Year, driver);
			}
			// Click CSV
			coachingMethods.fileDownloadPass(driver, SettingsObjects.AppUsage._btnCSV, "CSV", browser, ".csv",
					"FR 6.66", logger);
			// Click Page number
			coachingMethods.scrollIntoView(driver, SettingsObjects.AppUsage._lblPage);
			List<WebElement> pages = driver.findElements(SettingsObjects.AppUsage._lblPage);
			if (pages.size() <= 3) {
				coachingMethods.logInfo(logger, "FR 6.68 - Entries is less than or equal to 10");
			} else {
				for (int i = 1; i < pages.size() - 1; i++) {
					if (coachingMethods.elementExist(driver, SettingsObjects.AppUsage._PageNumber(i))) {
						coachingMethods.clickElementPass(driver, SettingsObjects.AppUsage._PageNumber(i), "FR 6.68",
								"Page " + i, logger);
						Thread.sleep(2000);
					}
				}
				WebElement previous = driver.findElement(SettingsObjects.AppUsage._btnPrevious);
				if (previous.isEnabled()) {
					// CLick Previous
					coachingMethods.clickElementPass(driver, SettingsObjects.AppUsage._btnPrevious, "FR 6.68",
							"Previous", logger);
					Thread.sleep(2000);
					// CLick Next
					coachingMethods.clickElementPass(driver, SettingsObjects.AppUsage._btnNext, "FR 6.68", "Next",
							logger);
					Thread.sleep(2000);
				} else
					coachingMethods.logInfo(logger, "FR 8.68 - Previous and Next Button is disabled");
			}
			Thread.sleep(2000);
			// Enter text in search field
			coachingMethods.enterTextPass(driver, SettingsObjects.AppUsage._txtSearch, "FR 6.7", userName, "Search",
					logger);
			Thread.sleep(1000);
			// Validation that table is filtered
			String Table = driver.findElement(SettingsObjects.AppUsage._tblAppUsage).getText();
			if (Table.contains(userName)) {
				coachingMethods.logPass(logger, "FR 6.67 Table is filtered by EID: " + userName, driver);
			} else
				coachingMethods.logFail(logger, "FR 6.67 Table is not filtered by EID: " + userName, driver);
			Thread.sleep(1000);
		} else
			coachingMethods.logFail(logger, "User is not in the App Usage Page!", driver);
	}

	// =========================Roster Management Methods======================
	public class RosterManagementMethods {
		// ===================Roster Management - Add Single User===============
		public void addSingleUser(WebDriver driver, String EID, String employeeStatus, String fullName, String role,
				String businessEmail, String siteLocation, String eidTeamLead, String eidTeamManager,
				String eidDeliveryLead, String tower, String project, String segment, ExtentTest logger)
				throws Exception {
			// Navigate to Settings - Roster Management
			navigateSpecificSettingTab(driver, SettingsObjects.SettingsCommonObjects._btnRosterManagement,
					"Roster Management", logger);

			// Enter EID
			coachingMethods.enterTextPass(driver, RosterManagementObjects.AddSingleUser._txtEID, "PROJECT ADMIN/DEVEOPER : STEP 1", EID, "EID",
					logger);

			// Select Employee Status
			coachingMethods.selectVisibleTextPass(driver, RosterManagementObjects.AddSingleUser._drpEmployeeStatus,
					"PROJECT ADMIN/DEVEOPER : STEP 2", employeeStatus, "Employee Status", logger);

			// Enter Full Name
			gm.scrollToViewElement(driver, RosterManagementObjects.AddSingleUser._txtFullName);
			coachingMethods.enterTextPass(driver, RosterManagementObjects.AddSingleUser._txtFullName, "PROJECT ADMIN/DEVEOPER : STEP 3",
					fullName, "Full Name", logger);

			// Enter Role
			gm.scrollToViewElement(driver, RosterManagementObjects.AddSingleUser._txtRole);
			coachingMethods.enterTextPass(driver, RosterManagementObjects.AddSingleUser._txtRole, "PROJECT ADMIN/DEVEOPER : STEP 4", role,
					"Role", logger);

			// Enter Business Email
			coachingMethods.scrollIntoView(driver, RosterManagementObjects.AddSingleUser._txtBusinessEmail);
			coachingMethods.enterTextPass(driver, RosterManagementObjects.AddSingleUser._txtBusinessEmail, "PROJECT ADMIN/DEVEOPER : STEP 5",
					businessEmail, "Business Email", logger);

			// Select Site Location
			gm.scrollToViewElement(driver, RosterManagementObjects.AddSingleUser._drpSiteLocation);
			coachingMethods.selectVisibleTextPass(driver, RosterManagementObjects.AddSingleUser._drpSiteLocation,
					"PROJECT ADMIN/DEVEOPER : STEP 6", siteLocation, "Site Location", logger);

			// Enter Team Lead
			gm.scrollToViewElement(driver, RosterManagementObjects.AddSingleUser._txtTeamLead);
			coachingMethods.enterTextPass(driver, RosterManagementObjects.AddSingleUser._txtTeamLead, "PROJECT ADMIN/DEVEOPER : STEP 7",
					eidTeamLead, "Team Lead", logger);

			// Enter Team Manager
			coachingMethods.enterTextPass(driver, RosterManagementObjects.AddSingleUser._txtTeamManager, "PROJECT ADMIN/DEVEOPER : STEP 8",
					eidTeamManager, "Team Manager", logger);

			// Enter Service Delivery Lead
			gm.scrollToViewElement(driver, RosterManagementObjects.AddSingleUser._txtDeliveryLead);
			coachingMethods.enterTextPass(driver, RosterManagementObjects.AddSingleUser._txtDeliveryLead, "PROJECT ADMIN/DEVEOPER : STEP 9",
					eidDeliveryLead, "Service Delivery Lead", logger);

			// Select Tower
			coachingMethods.selectVisibleTextPass(driver, RosterManagementObjects.AddSingleUser._drpTower, "PROJECT ADMIN/DEVEOPER : STEP 10",
					tower, "Tower", logger);

			// Select Project
			if (coachingMethods.waitForObjectExistence(driver, RosterManagementObjects.AddSingleUser._drpProject)) {
				coachingMethods.selectVisibleTextPass(driver, RosterManagementObjects.AddSingleUser._drpProject,
						"PROJECT ADMIN/DEVEOPER : STEP 11", project, "Project", logger);
			} else
				coachingMethods.logFail(logger, "PROJECT ADMIN/DEVEOPER : STEP 11 - Project dropdown not found.", driver);

			// Select Segment
			if (coachingMethods.waitForObjectExistence(driver, RosterManagementObjects.AddSingleUser._drpSegment)) {
				coachingMethods.selectVisibleTextPass(driver, RosterManagementObjects.AddSingleUser._drpSegment,
						"PROJECT ADMIN/DEVEOPER : STEP 12", segment, "Segment", logger);
			} else
				coachingMethods.logFail(logger, "PROJECT ADMIN/DEVEOPER : STEP 12 - Segment dropdown not found.", driver);

			// Click Submit
			coachingMethods.scrollIntoView(driver, RosterManagementObjects.AddSingleUser._btnSubmit);
			coachingMethods.clickElementInfo(driver, RosterManagementObjects.AddSingleUser._btnSubmit, "Submit",
					logger);

			// Successfully Saved Informational Message
			coachingMethods.popupDisplayValidationInfo(driver, SettingsObjects.SettingsCommonObjects._lblSuccessInfo,
					"Successfully Saved Informational Message", logger);

			// Search Existing User via Search Field
			searchUserPass(driver, "FR 6.15", EID, RosterManagementObjects.RosterManagementCommonObjects._txtSearch,
					RosterManagementObjects.RosterManagementCommonObjects._lblUser(EID), logger);
		}

		// ===============Roster Management - Add Multiple Users================
		public void addMultipleUsers(WebDriver driver, String browser, String rosterTemplate, String uploadEID1,
				String uploadEID2, ExtentTest logger) throws Exception {
			// Navigate to Settings - Roster Management
			navigateSpecificSettingTab(driver, SettingsObjects.SettingsCommonObjects._btnRosterManagement,
					"Roster Management", logger);

			// Download Roster Template
			coachingMethods.fileDownloadPass(driver,
					SettingsObjects.RosterManagementObjects.AddMultipleUsers._lnkDownloadTemplate, "Download Template",
					browser, rosterTemplate, "PROJECT ADMIN/DEVEOPER : STEP 16", logger);
			Thread.sleep(4000);
			/*
			 * Using jOptions for uploading the roster template manually
			 * 
			 * Reason: AutoIT scripts would not work sometimes Note: As of 1/23/2020:
			 * Template which is getting downloaded is of xlsx format, convert to .csv files
			 * before uploading
			 */

			final JDialog dialog = new JDialog();
			dialog.setAlwaysOnTop(true);

			int reply = JOptionPane.showConfirmDialog(null, "Uploaded the file?", "FILE UPLOAD",
					JOptionPane.YES_NO_OPTION);

			if (reply == JOptionPane.YES_OPTION) {
				coachingMethods.clickElementInfo(driver,
						SettingsObjects.RosterManagementObjects.AddMultipleUsers._btnSubmit, "Submit", logger);
			}

			// Successfully Saved Informational Message
			coachingMethods.popupDisplayValidationInfo(driver, SettingsObjects.SettingsCommonObjects._lblSuccessInfo,
					"Successfully Saved Informational Message", logger);

			// Search Uploaded Users via Search Field
			searchUserPass(driver, "PROJECT ADMIN/DEVEOPER : STEP 17", uploadEID1,
					RosterManagementObjects.RosterManagementCommonObjects._txtSearch,
					RosterManagementObjects.RosterManagementCommonObjects._lblUser(uploadEID1), logger);
			driver.findElement(RosterManagementObjects.RosterManagementCommonObjects._txtSearch).clear();
			searchUserPass(driver, "PROJECT ADMIN/DEVEOPER : STEP 18", uploadEID2,
					RosterManagementObjects.RosterManagementCommonObjects._txtSearch,
					RosterManagementObjects.RosterManagementCommonObjects._lblUser(uploadEID2), logger);
		}

		// ===================Extract Roster Summary============================
		public void extractRosterSummary(WebDriver driver, String browser, String rosterTemplate, ExtentTest logger)
				throws Exception {
			// Navigate to Settings - Roster Management
			navigateSpecificSettingTab(driver, SettingsObjects.SettingsCommonObjects._btnRosterManagement,
					"Roster Management", logger);
			gm.scrollToViewElement(driver, SettingsObjects.SettingsCommonObjects._extractCSV);
			coachingMethods.fileDownloadPass(driver, SettingsObjects.SettingsCommonObjects._extractCSV, "Download CSV",
					browser, rosterTemplate, "PROJECT ADMIN/DEVELOPER : STEP 1", logger);
			coachingMethods.fileDownloadPass(driver, SettingsObjects.SettingsCommonObjects._extractExcel,
					"Download EXCEL", browser, rosterTemplate, "PROJECT ADMIN/DEVELOPER : STEP 1", logger);

		}

		// ===================Roster Management - Edit User=====================
		public void editUser(WebDriver driver, String browser, String coachingDashboardCSV,
				String coachingDashboardXLSX, String EID, String newEID, String employeeStatus, String fullName,
				String role, String businessEmail, String siteLocation, String eidTeamLead, String eidTeamManager,
				String eidDeliveryLead, String tower, String project, String segment, String remarks, ExtentTest logger)
				throws Exception {
			// Navigate to Settings - Roster Management
			navigateSpecificSettingTab(driver, SettingsObjects.SettingsCommonObjects._btnRosterManagement,
					"Roster Management", logger);

			// Download Reports
			coachingMethods.scrollIntoView(driver,
					SettingsObjects.RosterManagementObjects.RosterManagementCommonObjects._btnExcel);
			coachingMethods.fileDownloadPass(driver,
					SettingsObjects.RosterManagementObjects.RosterManagementCommonObjects._btnExcel, "Excel", browser,
					coachingDashboardXLSX, "FR 6.19", logger);
			coachingMethods.fileDownloadPass(driver,
					SettingsObjects.RosterManagementObjects.RosterManagementCommonObjects._btnCSV, "CSV", browser,
					coachingDashboardCSV, "FR 6.20", logger);

			// Pagination Validation
			List<WebElement> paginationRosterManagement = driver
					.findElements(SettingsObjects.RosterManagementObjects.RosterManagementCommonObjects._dlPagination);
			if (paginationRosterManagement.size() > 3) {
				// Click Next Button
				coachingMethods.scrollIntoView(driver,
						SettingsObjects.RosterManagementObjects.RosterManagementCommonObjects._btnNext);
				coachingMethods.clickElementPass(driver,
						SettingsObjects.RosterManagementObjects.RosterManagementCommonObjects._btnNext, "FR 6.39",
						"Next", logger);
				getEntry(driver, SettingsObjects.RosterManagementObjects.RosterManagementCommonObjects._lblEntries,
						logger);

				// Click Previous Button
				coachingMethods.clickElementPass(driver,
						SettingsObjects.RosterManagementObjects.RosterManagementCommonObjects._btnPrevious, "FR 6.39",
						"Previous", logger);
				getEntry(driver, SettingsObjects.RosterManagementObjects.RosterManagementCommonObjects._lblEntries,
						logger);

				// Click Pagination Numbers
				for (int i = 1; i < paginationRosterManagement.size() - 1; i++) {
					coachingMethods.clickElementPass(driver,
							SettingsObjects.RosterManagementObjects.RosterManagementCommonObjects._btnPagination(i),
							"FR 6.39", "Page " + i, logger);
					getEntry(driver, SettingsObjects.RosterManagementObjects.RosterManagementCommonObjects._lblEntries,
							logger);
				}
			} else
				coachingMethods.logInfo(logger, "Entries are not more than 10.");

			// Search Existing User via Search Field
			searchUserPass(driver, "FR 6.21", EID, RosterManagementObjects.RosterManagementCommonObjects._txtSearch,
					RosterManagementObjects.RosterManagementCommonObjects._lblUser(EID), logger);

			// Click More Details Button
			coachingMethods.scrollIntoView(driver,
					SettingsObjects.RosterManagementObjects.EditUser._btnMoreDetails(EID));
			openPopupPass(driver, SettingsObjects.RosterManagementObjects.EditUser._btnMoreDetails(EID), "More Details",
					SettingsObjects.RosterManagementObjects.EditUser._lblMoreDetailsPopup, "Details", "FR 6.22",
					logger);

			// Click Close button of More Details Popup
			closePopupPass(driver, SettingsObjects.RosterManagementObjects.EditUser._btnMoreDetailsClose, "Close",
					SettingsObjects.RosterManagementObjects.EditUser._lblMoreDetailsPopup, "More Details", "FR 6.38",
					logger);

			// Click Edit Button
			openPopupPass(driver, SettingsObjects.RosterManagementObjects.EditUser._btnEdit(EID), "Edit",
					SettingsObjects.RosterManagementObjects.EditUser._lblEditPopup, "Edit User", "FR 6.23", logger);

			// Click Close Button of Edit Popup
			closePopupPass(driver, SettingsObjects.RosterManagementObjects.EditUser._btnEditClose, "Close",
					SettingsObjects.RosterManagementObjects.EditUser._lblEditPopup, "Edit", "FR 6.38", logger);

			// Click Edit Button
			openPopupInfo(driver, SettingsObjects.RosterManagementObjects.EditUser._btnEdit(EID), "Edit",
					SettingsObjects.RosterManagementObjects.EditUser._lblEditPopup, "Edit User ", logger);

			// Enter new EID
			enterNewTextPass(driver, SettingsObjects.RosterManagementObjects.EditUser._txtNewEID, "6.24", newEID, "EID",
					logger);

			// Select new Employee Status
			coachingMethods.selectVisibleTextPass(driver,
					SettingsObjects.RosterManagementObjects.EditUser._drpEmployeeStatus, "FR 6.25", employeeStatus,
					"Employee Status", logger);

			// Enter new Full Name
			enterNewTextPass(driver, SettingsObjects.RosterManagementObjects.EditUser._txtFullName, "FR 6.26", fullName,
					"Full Name", logger);

			// Enter new Role
			enterNewTextPass(driver, SettingsObjects.RosterManagementObjects.EditUser._txtRole, "FR 6.27", role, "Role",
					logger);

			// Enter new Business Email
			enterNewTextPass(driver, SettingsObjects.RosterManagementObjects.EditUser._txtBusinessEmail, "FR6.28",
					businessEmail, "Business Email", logger);

			// Enter new Site Location
			// enterNewTextPass(driver,
			// SettingsObjects.RosterManagementObjects.EditUser._txtSiteLocation, "FR 6.29",
			// s siteLocation, "Site Location", logger);

			// Enter new Team Lead
			enterNewTextPass(driver, SettingsObjects.RosterManagementObjects.EditUser._txtTeamLead, "FR 6.30",
					eidTeamLead, "Team Lead", logger);

			// Enter new Team Manager
			enterNewTextPass(driver, SettingsObjects.RosterManagementObjects.EditUser._txtTeamManager, "FR 6.31",
					eidTeamManager, "Team Manager", logger);

			// Enter new Service Delivery Lead
			enterNewTextPass(driver, SettingsObjects.RosterManagementObjects.EditUser._txtDeliveryLead, "FR 6.32",
					eidDeliveryLead, "Service Delivery Lead", logger);

			// Select new Tower
			coachingMethods.selectVisibleTextPass(driver, SettingsObjects.RosterManagementObjects.EditUser._drpTower,
					"FR 6.33", tower, "Tower", logger);

			// Select new Project
			if (coachingMethods.waitForObjectExistence(driver,
					SettingsObjects.RosterManagementObjects.EditUser._drpProject)) {
				coachingMethods.selectVisibleTextPass(driver,
						SettingsObjects.RosterManagementObjects.EditUser._drpProject, "FR 6.34", project, "Project",
						logger);
			} else
				coachingMethods.logFail(logger, "FR 6.34 - Project dropdown not found.", driver);

			// Select new Segment
			if (coachingMethods.waitForObjectExistence(driver,
					SettingsObjects.RosterManagementObjects.EditUser._drpSegment)) {
				coachingMethods.selectVisibleTextPass(driver,
						SettingsObjects.RosterManagementObjects.EditUser._drpSegment, "FR 6.35", segment, "Segment",
						logger);
			} else
				coachingMethods.logFail(logger, "FR 6.35 - Segment dropdown not found.", driver);

			// Enter Remarks
			enterNewTextPass(driver, SettingsObjects.RosterManagementObjects.EditUser._txtRemarks, "FR 6.36", remarks,
					"Remarks", logger);

			// Click Save & Close
			coachingMethods.clickElementInfo(driver, SettingsObjects.RosterManagementObjects.EditUser._btnSaveAndClose,
					"Submit", logger);

			// Successfully Saved Informational Message
			coachingMethods.popupDisplayValidationInfo(driver, SettingsObjects.SettingsCommonObjects._lblSuccessInfo,
					"Successfully Saved Informational Message", logger);

			// Search Modified User via Search Field
			searchUserPass(driver, "FR 6.37", newEID, RosterManagementObjects.RosterManagementCommonObjects._txtSearch,
					RosterManagementObjects.RosterManagementCommonObjects._lblUser(newEID), logger);
		}
	}

	// =========================Tower Settings Methods=========================
	public class TowerSettingsMethods {
		// =========================Tower Section Methods======================
		public void towerMethods(WebDriver driver, String tower, String newTower, ExtentTest logger) throws Exception {
			// Navigate to Tower Settings Tab
			navigateSpecificSettingTab(driver, SettingsObjects.SettingsCommonObjects._btnTowerSettings,
					"Tower Settings", logger);

			// Enter Tower Name
			coachingMethods.enterTextPass(driver, SettingsObjects.TowerSettingsObjects.TowerObjects._txtTower,
					"FR 6.87", tower, "Tower", logger);

			// Click Save
			coachingMethods.clickElementInfo(driver, SettingsObjects.TowerSettingsObjects.TowerObjects._btnSave, "Save",
					logger);
			coachingMethods.popupDisplayValidationPass(driver, SettingsObjects.SettingsCommonObjects._lblSuccessInfo,
					"FR 6.88", "Successfully Saved Informational Message", logger);

			// Search Created Tower
			searchUserPass(driver, "FR 6.89", tower, SettingsObjects.TowerSettingsObjects.TowerObjects._txtSearch,
					SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._lblCreated(tower), logger);

			// Click Update Button
			openPopupPass(driver, SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._btnUpdate(tower),
					"Update", SettingsObjects.TowerSettingsObjects.TowerObjects._lblEditTower, "Edit Tower",
					"FR 6.90 - ", logger);

			// Click Close Button of Edit Tower
			closePopupPass(driver, SettingsObjects.TowerSettingsObjects.TowerObjects._btnCloseEditTower, "Close",
					SettingsObjects.TowerSettingsObjects.TowerObjects._lblEditTower, "Edit Tower", "FR 6.107", logger);

			// Click Update Button Again
			openPopupInfo(driver, SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._btnUpdate(tower),
					"Update", SettingsObjects.TowerSettingsObjects.TowerObjects._lblEditTower, "Edit Tower", logger);

			// Enter New Tower Name
			driver.findElement(SettingsObjects.TowerSettingsObjects.TowerObjects._txtTowerName).clear();
			coachingMethods.enterTextInfo(driver, SettingsObjects.TowerSettingsObjects.TowerObjects._txtTowerName,
					newTower, "Tower Name", logger);

			// Click Save & Close Button
			closePopupPass(driver, SettingsObjects.TowerSettingsObjects.TowerObjects._btnSaveAndClose, "Save & Close",
					SettingsObjects.TowerSettingsObjects.TowerObjects._lblEditTower, "Edit Tower", "FR 6.108", logger);

			// Successfully Saved Informational Message
			coachingMethods.popupDisplayValidationInfo(driver, SettingsObjects.SettingsCommonObjects._lblSuccessInfo,
					"Successfully Saved Informational Message", logger);

			// Search Existing Tower via Search Field
			searchUserPass(driver, "FR 6.108", newTower, SettingsObjects.TowerSettingsObjects.TowerObjects._txtSearch,
					SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._lblCreated(newTower), logger);

			// Click Delete Button
			openPopupPass(driver, SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._btnDelete(newTower),
					"Delete", SettingsObjects.TowerSettingsObjects.TowerObjects._lblRemoveTower, "Remove Tower",
					"FR 6.91 - ", logger);

			// Click Close Button
			closePopupPass(driver, SettingsObjects.TowerSettingsObjects.TowerObjects._btnCloseRemoveTower, "Close",
					SettingsObjects.TowerSettingsObjects.TowerObjects._lblRemoveTower, "Remove Tower", "FR 6.107",
					logger);

			// Click Delete Button Again
			openPopupInfo(driver, SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._btnDelete(newTower),
					"Delete", SettingsObjects.TowerSettingsObjects.TowerObjects._lblRemoveTower, "Remove Tower",
					logger);

			// Click Delete & Close
			closePopupPass(driver, SettingsObjects.TowerSettingsObjects.TowerObjects._btnDeleteAndClose,
					"Delete & Close", SettingsObjects.TowerSettingsObjects.TowerObjects._lblRemoveTower, "Remove Tower",
					"FR 6.109", logger);

			// Successfully Saved Informational Message
			coachingMethods.popupDisplayValidationInfo(driver, SettingsObjects.SettingsCommonObjects._lblSuccessInfo,
					"Successfully Saved Informational Message", logger);

			// Search Deleted Tower via Search Field
			searchDeletedUserPass(driver, "FR 6.109", newTower,
					SettingsObjects.TowerSettingsObjects.TowerObjects._txtSearch,
					SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._lblCreated(newTower), logger);
		}

		// =========================Project Section Methods====================
		public void projectMethods(WebDriver driver, String tower, String project, String newProject, ExtentTest logger)
				throws Exception {
			// Navigate to Tower Settings Tab
			navigateSpecificSettingTab(driver, SettingsObjects.SettingsCommonObjects._btnTowerSettings,
					"Tower Settings", logger);

			// Select Tower
			coachingMethods.selectVisibleTextPass(driver, SettingsObjects.TowerSettingsObjects.ProjectObjects._drpTower,
					"FR 6.92", tower, "Tower", logger);

			// Enter New Project
			coachingMethods.enterTextPass(driver, SettingsObjects.TowerSettingsObjects.ProjectObjects._txtProject,
					"FR 6.93", project, "Project", logger);

			// Click Save Button
			coachingMethods.clickElementInfo(driver, SettingsObjects.TowerSettingsObjects.ProjectObjects._btnSave,
					"Save", logger);
			coachingMethods.popupDisplayValidationPass(driver, SettingsObjects.SettingsCommonObjects._lblSuccessInfo,
					"FR 6.95", "Successfully Saved Informational Message", logger);

			// Search Created Project
			searchUserPass(driver, "FR 6.96", project, SettingsObjects.TowerSettingsObjects.ProjectObjects._txtSearch,
					SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._lblCreated(project), logger);

			// Click Update Button
			openPopupPass(driver, SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._btnUpdate(project),
					"Update", SettingsObjects.TowerSettingsObjects.ProjectObjects._lblEditProject, "Edit Project",
					"FR 6.97 - ", logger);

			// Click Close Button of Edit Project
			closePopupPass(driver, SettingsObjects.TowerSettingsObjects.ProjectObjects._btnClose, "Close",
					SettingsObjects.TowerSettingsObjects.ProjectObjects._lblEditProject, "Edit Tower", "FR 6.107",
					logger);

			// Click Update Button Again
			openPopupInfo(driver, SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._btnUpdate(project),
					"Update", SettingsObjects.TowerSettingsObjects.ProjectObjects._lblEditProject, "Edit Project",
					logger);

			// Enter New Project Name
			driver.findElement(SettingsObjects.TowerSettingsObjects.ProjectObjects._txtProjectName).clear();
			coachingMethods.enterTextInfo(driver, SettingsObjects.TowerSettingsObjects.ProjectObjects._txtProjectName,
					newProject, "Tower Name", logger);

			// Click Save & Close Button
			closePopupPass(driver, SettingsObjects.TowerSettingsObjects.ProjectObjects._btnSaveAndClose, "Save & Close",
					SettingsObjects.TowerSettingsObjects.ProjectObjects._lblEditProject, "Edit Project", "FR 6.108",
					logger);

			// Successfully Saved Informational Message
			coachingMethods.popupDisplayValidationInfo(driver, SettingsObjects.SettingsCommonObjects._lblSuccessInfo,
					"Successfully Saved Informational Message", logger);

			// Search Existing Project via Search Field
			searchUserPass(driver, "FR 6.108", newProject,
					SettingsObjects.TowerSettingsObjects.ProjectObjects._txtSearch,
					SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._lblCreated(newProject), logger);

			// Click Delete Button
			openPopupPass(driver,
					SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._btnDelete(newProject), "Delete",
					SettingsObjects.TowerSettingsObjects.ProjectObjects._lblDeleteProject, "Remove Tower", "FR 6.98 - ",
					logger);

			// Click Cancel Button
			closePopupPass(driver, SettingsObjects.TowerSettingsObjects.ProjectObjects._btnCancel, "Close",
					SettingsObjects.TowerSettingsObjects.ProjectObjects._lblDeleteProject, "Delete Project", "FR 6.107",
					logger);

			// Click Delete Button Again
			openPopupInfo(driver,
					SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._btnDelete(newProject), "Delete",
					SettingsObjects.TowerSettingsObjects.ProjectObjects._lblDeleteProject, "Delete Project", logger);

			// Click Delete & Close
			closePopupPass(driver, SettingsObjects.TowerSettingsObjects.ProjectObjects._btnDeleteAndClose,
					"Delete & Close", SettingsObjects.TowerSettingsObjects.ProjectObjects._lblDeleteProject,
					"Delete Project", "FR 6.109", logger);

			// Successfully Saved Informational Message
			coachingMethods.popupDisplayValidationInfo(driver, SettingsObjects.SettingsCommonObjects._lblSuccessInfo,
					"Successfully Saved Informational Message", logger);

			// Search Deleted Project via Search Field
			searchDeletedUserPass(driver, "FR 6.109", newProject,
					SettingsObjects.TowerSettingsObjects.ProjectObjects._txtSearch,
					SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._lblCreated(newProject), logger);
		}

		// =========================Segment Section Methods====================
		public void segmentMethods(WebDriver driver, String tower, String project, String segment, String newSegment,
				ExtentTest logger) throws Exception {
			// Navigate to Tower Settings Tab
			navigateSpecificSettingTab(driver, SettingsObjects.SettingsCommonObjects._btnTowerSettings,
					"Tower Settings", logger);

			// Select Tower
			coachingMethods.selectVisibleTextPass(driver, SettingsObjects.TowerSettingsObjects.SegmentObjects._drpTower,
					"FR 6.100/FR 6.102", tower, "Tower", logger);

			// Select Project
			coachingMethods.selectVisibleTextPass(driver,
					SettingsObjects.TowerSettingsObjects.SegmentObjects._drpProject, "FR 6.99", project, "Project",
					logger);

			// Enter New Segment
			coachingMethods.enterTextPass(driver, SettingsObjects.TowerSettingsObjects.SegmentObjects._txtSegment,
					"FR 6.101", segment, "Segment", logger);

			// Click Save Button
			coachingMethods.clickElementInfo(driver, SettingsObjects.TowerSettingsObjects.SegmentObjects._btnSave,
					"Save", logger);
			coachingMethods.popupDisplayValidationPass(driver, SettingsObjects.SettingsCommonObjects._lblSuccessInfo,
					"FR 6.103", "Successfully Saved Informational Message", logger);

			// Search Created Segment
			searchUserPass(driver, "FR 6.104", segment, SettingsObjects.TowerSettingsObjects.SegmentObjects._txtSearch,
					SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._lblCreated(segment), logger);

			// Click Update Button
			openPopupPass(driver, SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._btnUpdate(segment),
					"Update", SettingsObjects.TowerSettingsObjects.SegmentObjects._lblEditSegment, "Edit Segment",
					"FR 6.105 - ", logger);

			// Click Cancel Button of Edit Segment
			closePopupPass(driver, SettingsObjects.TowerSettingsObjects.SegmentObjects._btnCancelEditSegment, "Cancel",
					SettingsObjects.TowerSettingsObjects.SegmentObjects._lblEditSegment, "Edit Segment", "FR 6.107",
					logger);

			// Click Update Button Again
			openPopupInfo(driver, SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._btnUpdate(segment),
					"Update", SettingsObjects.TowerSettingsObjects.SegmentObjects._lblEditSegment, "Edit Segment",
					logger);

			// Enter New Segment Name
			driver.findElement(SettingsObjects.TowerSettingsObjects.SegmentObjects._txtSegmentName).clear();
			coachingMethods.enterTextInfo(driver, SettingsObjects.TowerSettingsObjects.SegmentObjects._txtSegmentName,
					newSegment, "Segment Name", logger);

			// Click Save & Close Button
			closePopupPass(driver, SettingsObjects.TowerSettingsObjects.SegmentObjects._btnSaveAndClose, "Save & Close",
					SettingsObjects.TowerSettingsObjects.SegmentObjects._lblEditSegment, "Edit Segment", "FR 6.108",
					logger);

			// Successfully Saved Informational Message
			coachingMethods.popupDisplayValidationInfo(driver, SettingsObjects.SettingsCommonObjects._lblSuccessInfo,
					"Successfully Saved Informational Message", logger);

			// Search Existing Segment via Search Field
			searchUserPass(driver, "FR 6.108", newSegment,
					SettingsObjects.TowerSettingsObjects.SegmentObjects._txtSearch,
					SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._lblCreated(newSegment), logger);

			// Click Delete Button
			openPopupPass(driver,
					SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._btnDelete(newSegment), "Delete",
					SettingsObjects.TowerSettingsObjects.SegmentObjects._lblDeleteSegment, "Delete Segment",
					"FR 6.106 - ", logger);

			// Click Cancel Button
			closePopupPass(driver, SettingsObjects.TowerSettingsObjects.SegmentObjects._btnCancelDeletSegment, "Cancel",
					SettingsObjects.TowerSettingsObjects.SegmentObjects._lblDeleteSegment, "Delete Segment", "FR 6.107",
					logger);

			// Click Delete Button Again
			openPopupInfo(driver,
					SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._btnDelete(newSegment), "Delete",
					SettingsObjects.TowerSettingsObjects.SegmentObjects._lblDeleteSegment, "Delete Segment", logger);

			// Click Delete & Close
			closePopupPass(driver, SettingsObjects.TowerSettingsObjects.SegmentObjects._btnDeleteAndClose,
					"Delete & Close", SettingsObjects.TowerSettingsObjects.SegmentObjects._lblDeleteSegment,
					"Delete Segment", "FR 6.109", logger);

			// Successfully Saved Informational Message
			coachingMethods.popupDisplayValidationInfo(driver, SettingsObjects.SettingsCommonObjects._lblSuccessInfo,
					"Successfully Saved Informational Message", logger);

			// Search Deleted Segment via Search Field
			searchDeletedUserPass(driver, "FR 6.109", newSegment,
					SettingsObjects.TowerSettingsObjects.SegmentObjects._txtSearch,
					SettingsObjects.TowerSettingsObjects.TowerSettingsCommonObjects._lblCreated(newSegment), logger);
		}
	}
}
