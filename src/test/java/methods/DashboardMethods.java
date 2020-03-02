package methods;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import pageObjects.DashboardObjects;

public class DashboardMethods {

	CoachingAppMethods coachingMethods = new CoachingAppMethods();
	
	public void dashboard(WebDriver driver, ExtentTest logger, String level, String search, String month, String year, String tower) throws Exception {
		
		// Click User/Profile icon
		if(coachingMethods.waitForObjectExistence(driver, DashboardObjects._iconProfile)) {
			coachingMethods.clickElementPass(driver, DashboardObjects._iconProfile, "FR 1.7", "User/Profile icon", logger);
			if(coachingMethods.waitForObjectExistence(driver, DashboardObjects._lblMyProfile))
				coachingMethods.logPass(logger, "PROJECT ADMIN : STEP 1 - User Profile is displayed", driver);
			else
				coachingMethods.logFail(logger, "PROJECT ADMIN : STEP 1- User Profile is not displayed", driver);
		} else
			coachingMethods.logFail(logger, "PROJECT ADMIN : STEP 1 - User/Profile icon doesn't exists", driver);
		
		// Click Online Coaching App logo
		if(coachingMethods.waitForObjectExistence(driver, DashboardObjects._logoOnlineCoachingApp))
			coachingMethods.clickElementPass(driver, DashboardObjects._logoOnlineCoachingApp, "PROJECT ADMIN : STEP 2", "Online Coaching App logo exist", logger);
		else
			coachingMethods.logFail(logger, "PROJECT ADMIN : STEP 2 - Online Coaching App logo doesn't exists", driver);
		
		// Click Search box
		if(coachingMethods.waitForObjectExistence(driver, DashboardObjects._txtSearchBox)) {
			coachingMethods.clickElementPass(driver, DashboardObjects._txtSearchBox, "PROJECT ADMIN : STEP 3", "Search box", logger);
			coachingMethods.enterTextPass(driver, DashboardObjects._txtSearchBox, "PROJECT ADMIN : STEP 3", search, "Search", logger);
		
		} else
			coachingMethods.logFail(logger, "PROJECT ADMIN : STEP 3- Search box doesn't exists", driver);
		
		// Click Notification icon
		if(coachingMethods.waitForObjectExistence(driver, DashboardObjects._iconNotification)) {
			coachingMethods.clickElementPass(driver, DashboardObjects._iconNotification, "PROJECT ADMIN : STEP 4", "Notification icon", logger);
			if(coachingMethods.waitForObjectExistence(driver, DashboardObjects._drpNotification))
				coachingMethods.logPass(logger, "PROJECT ADMIN : STEP 5 - Notification is displayed", driver);
			else
				coachingMethods.logFail(logger, "PROJECT ADMIN : STEP 5 - Notification is not displayed", driver);
		} else
			coachingMethods.logFail(logger, "PROJECT ADMIN : STEP 5 - Notification icon doesn't exists", driver);
		
		// Click Bookmark icon
		if(coachingMethods.waitForObjectExistence(driver, DashboardObjects._iconBookmark)) {
			coachingMethods.clickElementPass(driver, DashboardObjects._iconBookmark, "PROJECT ADMIN : STEP 6 ", "Bookmark icon", logger);
			if(coachingMethods.waitForObjectExistence(driver, DashboardObjects._drpBookmark))
				coachingMethods.logPass(logger, "PROJECT ADMIN : STEP 6 - Bookmark dropdown is displayed", driver);
			else
				coachingMethods.logFail(logger, "PROJECT ADMIN : STEP 6 - Bookmark dropdown is not displayed", driver);
		} else
			coachingMethods.logFail(logger, "PROJECT ADMIN : STEP 6- Bookmark icon doesn't exists", driver);
		
		if(level.equalsIgnoreCase("HR PA") || level.equalsIgnoreCase("HR PAM") || level.equalsIgnoreCase("Project Admin") || level.equalsIgnoreCase("Tower Admin") ||
				level.equalsIgnoreCase("Developer")) {
			
			// Click Dashboard
			driver.findElement(DashboardObjects._iconProfile).click();
			if(coachingMethods.waitForObjectExistence(driver, DashboardObjects._linkDashboard)) {
				coachingMethods.clickElementPass(driver, DashboardObjects._linkDashboard, "FR 1.2", "Dashboard", logger);
				if(coachingMethods.waitForObjectExistence(driver, DashboardObjects._lblOverallCompliance))
					coachingMethods.logPass(logger, "PROJECT ADMIN : STEP 7- Dashboard is displayed", driver);
				else
					coachingMethods.logFail(logger, "PROJECT ADMIN : STEP 7 - Dashboard is not displayed", driver);
			} else
				coachingMethods.logFail(logger, "PROJECT ADMIN : STEP 7 - Dashboard link doesn't exists", driver);
			
			// Select Month
			if(coachingMethods.waitForObjectExistence(driver, DashboardObjects._selMonth)) {
				driver.findElement(DashboardObjects._selMonth).click();
				coachingMethods.selectVisibleTextPass(driver, DashboardObjects._selMonth, "PROJECT ADMIN : STEP 8", month, "Month", logger);
			} else
				coachingMethods.logFail(logger, "PROJECT ADMIN : STEP 8 - Month dropdown is not displayed", driver);
	
			// Select Year
			if(coachingMethods.waitForObjectExistence(driver, DashboardObjects._selYear)) {
				driver.findElement(DashboardObjects._selYear).click();
				coachingMethods.selectVisibleTextPass(driver, DashboardObjects._selYear, "PROJECT ADMIN : STEP 9", year, "Year", logger);
			} else
				coachingMethods.logFail(logger, "PROJECT ADMIN : STEP 9 - Year dropdown is not displayed", driver);
			// Select Tower
			if(coachingMethods.waitForObjectExistence(driver, DashboardObjects._selTower)) {
				driver.findElement(DashboardObjects._selTower).click();
				coachingMethods.selectVisibleTextPass(driver, DashboardObjects._selTower, "PROJECT ADMIN : STEP 10", tower, "Tower", logger);
			} else
				coachingMethods.logFail(logger, "PROJECT ADMIN : STEP 10 - Tower dropdown is not displayed", driver);
			
			// Click Filter button
			if(coachingMethods.waitForObjectExistence(driver, DashboardObjects._btnFilter))
				coachingMethods.clickElementPass(driver, DashboardObjects._btnFilter, "PROJECT ADMIN : STEP 11", "Filter button", logger);
			else
				coachingMethods.logFail(logger, "PROJECT ADMIN : STEP 11 - Filter button doesn't exists", driver);
			Thread.sleep(3000);
			coachingMethods.logInfo(logger, "Results are displayed", driver);
		} else
			System.out.println("End");
	} // End of dashboard	
}
