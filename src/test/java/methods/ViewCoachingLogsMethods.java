package methods;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;

import driver.GenericMethods2;
import pageObjects.ScheduleCoachingSessionsObjects;
import pageObjects.ViewCoachingLogsObjects;
import pageObjects.ViewCoachingLogsObjects.OtherCoaching;

public class ViewCoachingLogsMethods {

	CoachingAppMethods coachingMethods = new CoachingAppMethods();
	GenericMethods2 gm = new GenericMethods2();
	String CoachingID;
	

	// =================================Performance
	// Coaching===============================

	public void performanceCoaching(WebDriver driver, String CoachingType, String Text, String Focus, String SLA,
			String Amber, ExtentTest logger) throws Exception {

		// click View coaching logs
		coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._linkViewCoachingLog,
				"LEADS : STEP 1", "View Coaching Logs", logger);
		// page redirection
		coachingMethods.pageRedirectionValidationInfo(driver, ViewCoachingLogsObjects.PerformancCoaching._tblCoachLogs,
				"Coach View", logger);
		// click next button if coaching log is not displayed in first page

		// search by Status Scheduled
		coachingMethods.enterTextInfo(driver, ViewCoachingLogsObjects.PerformancCoaching._txtSearch, "Scheduled",
				"Search", logger);
		Thread.sleep(4000);

		WebElement Table = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._tblCoachLogs);
		WebElement Next = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._btnNext);
		
		while (!Table.getText().contains(CoachingType)) {
			Thread.sleep(1000);
			String x = Next.getAttribute("class");
			if (x.contains("disabled")) {
				break;
			} else
				coachingMethods.clickElementInfo(driver, ViewCoachingLogsObjects.PerformancCoaching._btnNext, "Next",
						logger);
		}
		Thread.sleep(4000);

			
			
			
			String Status = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._txtStatus).getText();
			CoachingID = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._txtCoachingID).getText();

			coachingMethods.logInfo(logger, "The Coaching ID: " + CoachingID + " and the Status is " + Status);
			
			Thread.sleep(4000);
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.PerformancCoaching._btnOpen(CoachingType));
			coachingMethods.waitForObjectExistence(driver, ViewCoachingLogsObjects.PerformancCoaching._btnOpen(CoachingType));
			
			
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._btnOpen(CoachingType),
					"LEADS : STEP 2", "Open button for " + CoachingType, logger);

		if (coachingMethods.waitForObjectExistence(driver, ViewCoachingLogsObjects.PerformancCoaching._txtCoacheeEID)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Enter Text in How is your coachee doing field
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.PerformancCoaching._txtOpening);
			coachingMethods.enterTextPass(driver, ViewCoachingLogsObjects.PerformancCoaching._txtOpening, "LEADS : STEP 3",
					Text, "How is your coachee doing? ", logger);
			// Enter Text in Determine focus field
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.PerformancCoaching._txtFocus);
			coachingMethods.enterTextPass(driver, ViewCoachingLogsObjects.PerformancCoaching._txtFocus, "LEADS : STEP 4", Focus,
					"DETERMINE THE FOCUS", logger);
			// Enter Text in Focus/SLA% field
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.PerformancCoaching._txtFocusSLA);
			coachingMethods.enterTextPass(driver, ViewCoachingLogsObjects.PerformancCoaching._txtFocusSLA, "LEADS : STEP 5",
					SLA, "Focus/SLA%", logger);

			// scroll to element
			WebElement Behavior = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._txtSpecificBehavior);
			js.executeScript("arguments[0].scrollIntoView(true);", Behavior);
			// Enter Text in What specific behavior/issue would you like to talk about
			// today?
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.PerformancCoaching._txtSpecificBehavior);
			coachingMethods.enterTextPass(driver, ViewCoachingLogsObjects.PerformancCoaching._txtSpecificBehavior,
					"LEADS : STEP 6", Text, "What specific behavior/issue would you like to talk about today?", logger);
			// Enter Text in How does your coachee understand what is expected
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.PerformancCoaching._txtUnderstandBehavior);
			coachingMethods.enterTextPass(driver, ViewCoachingLogsObjects.PerformancCoaching._txtUnderstandBehavior,
					"LEADS : STEP 7", Text, "How does your coachee understand what is expected?", logger);
			// Enter Text in What actions can he/she take to perform better than how he/she
			// is performing now?
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.PerformancCoaching._txtAction1);
			coachingMethods.enterTextPass(driver, ViewCoachingLogsObjects.PerformancCoaching._txtAction1, "LEADS : STEP 8",
					Text, "What actions can he/she take to perform better than how he/she is performing now?", logger);
			// Enter Text in What specific actions can he/she commit to doing field 1
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.PerformancCoaching._txtActionItem1);
			coachingMethods.enterTextPass(driver, ViewCoachingLogsObjects.PerformancCoaching._txtActionItem1, "LEADS : STEP 9",
					Text, "What specific actions can he/she commit to doing", logger);

			// scroll to element
			WebElement Action = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._txtActionItem2);
			js.executeScript("arguments[0].scrollIntoView(true);", Action);

			// Enter Text in What specific actions can he/she commit to doing field 2
			coachingMethods.enterTextPass(driver, ViewCoachingLogsObjects.PerformancCoaching._txtActionItem2, "LEADS : STEP 10",
					Text, "What specific actions can he/she commit to doing", logger);
			// Enter Text in What specific actions can he/she commit to doing field 3
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.PerformancCoaching._txtActionItem3);
			coachingMethods.enterTextPass(driver, ViewCoachingLogsObjects.PerformancCoaching._txtActionItem3, "LEADS : STEP 11",
					Text, "What specific actions can he/she commit to doing?", logger);
			// scroll to element
			WebElement Notes = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._txtCoacheeNotes);
			js.executeScript("arguments[0].scrollIntoView(true);", Notes);

			
			// tick Irritable
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.PerformancCoaching._chkboxIrritable);
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._chkboxIrritable,
					"LEADS : STEP 12", "Irritable", logger);
			// tick Sudden change in behavior check box
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.PerformancCoaching._chkboxBehavior);
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._chkboxBehavior,
					"LEADS : STEP 13", "Sudden Change in Behavior", logger);
			// tick Apathy check box
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.PerformancCoaching._chkboxApathy);
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._chkboxApathy,
					"LEADS : STEP 14", "Apathy", logger);
			// tick Attendance check box
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._chkboxAttendance,
					"LEADS : STEP 15", "Attendance", logger);
			// tick Habitual Negligence check box
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._chkboxNegligence,
					"LEADS : STEP 16", "Habitual Negligence", logger);
			// click Red radio button
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.PerformancCoaching._radRed);
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._radRed, "LEADS : STEP 17",
					"Red - Will attrite within a month", logger);
			// click Green radio button
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._radGreen, "LEADS : STEP 18",
					"Green - No risk/highly engaged", logger);
			// click AMber radio button
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._radAmber, "LEADS : STEP 19",
					"Amber - Will attrite within a quarter", logger);

			// scroll down to element
			WebElement drp = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._drpAmberAttrition);
			js.executeScript("arguments[0].scrollIntoView(true);", drp);

			// select from Amber drop down Attrition
			coachingMethods.selectVisibleTextPass(driver, ViewCoachingLogsObjects.PerformancCoaching._drpAmberAttrition,
					"LEADS : STEP 20", Amber, "Please indicate your reason why this employee is tagged as AMBER in attrition.",
					logger);
			// click company radio button
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._radCompany, "LEADS : STEP 21",
					"Company", logger);
			// click Opportunities radio button
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._radOpportunities,
					"FR LEADS : STEP 22", "Opportunities", logger);
			// click People radio button
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._radPeople, "LEADS : STEP 23",
					"People", logger);
			// click Work radio button
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._radWork, "LEADS : STEP 24",
					"Work", logger);
			// click Work environment radio button
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._radWorkEnvironment,
					"LEADS : STEP 25", "Work Environment", logger);
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.PerformancCoaching._radYes);
			// validate if Yes/no radio button is enabled
			if (driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._radYes).isEnabled()) {
				// click Yes radio button
				coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._radYes, "LEADS : STEP 26",
						"Yes", logger);
			} else
				coachingMethods.logInfo(logger,
						"Yes/No radio button is disabled because the Coachee has not PAM Attributes or The Coach tagged is not the Team Lead of the Coachee!");
			// click confirm button
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._btnConfirm, "LEADS : STEP 27",
					"Confirm", logger);

		} else
			coachingMethods.logFail(logger, "User is not in the Coaching Logs Page!", driver);

		coachingMethods.pageRedirectionValidationInfo(driver, ViewCoachingLogsObjects.PerformancCoaching._tblCoachLogs,
				"View Coaching Logs", logger);
		// validate if successfully saved pop up
		if (coachingMethods.waitForObjectExistence(driver, ViewCoachingLogsObjects.PerformancCoaching._popupSaved,
				"Successfully Saved", logger)) {
			coachingMethods.logPass(logger, "Coaching Log Successfully Saved");
		} else
			coachingMethods.logFail(logger, "Coaching Log is not saved!", driver);
		Thread.sleep(5000);
		// select from filter dropdown
//coachingMethods.selectVisibleTextInfo(driver, ViewCoachingLogsObjects.PerformancCoaching._drpFilter, "Pending", "Filter", logger);	
		coachingMethods.enterTextInfo(driver, ViewCoachingLogsObjects.PerformancCoaching._txtSearch, "Pending",
				"Search", logger);

		WebElement Table1 = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._tblCoachLogs);
		WebElement Next1 = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._btnNext);
		while (!Table1.getText().contains(CoachingID)) {
			Thread.sleep(1000);
			String x = Next1.getAttribute("class");
			if (x.contains("disabled")) {
				break;
			} else
				coachingMethods.clickElementInfo(driver, ViewCoachingLogsObjects.PerformancCoaching._btnNext, "Next",
						logger);
		}

		Thread.sleep(5000);
		// scroll into element
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement ID = driver
				.findElement(ViewCoachingLogsObjects.PerformancCoaching._txtCoachingIDPending(CoachingID));
		js.executeScript("arguments[0].scrollIntoView(true);", ID);

		// validate if status is updated
		if (coachingMethods.waitForObjectExistence(driver,
				ViewCoachingLogsObjects.PerformancCoaching._txtCoachingIDPending(CoachingID))) {
			Thread.sleep(5000);
			String CoachingID2 = driver
					.findElement(ViewCoachingLogsObjects.PerformancCoaching._txtCoachingIDPending(CoachingID))
					.getText();
			String Status1 = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._txtStatus).getText();
			if (Status1.equalsIgnoreCase("Pending") && CoachingID2.equals(CoachingID)) {
				coachingMethods.logPass(logger, "LEADS : STEP 28 Status of Performance Coaching with CoachingID: " + CoachingID2
						+ " is updated to Pending", driver);
			} else
				coachingMethods.logFail(logger,
						"LEADS : STEP 28 Status of Performance Coaching with CoachingID: " + CoachingID2 + " is not updated",
						driver);

		}

	}

	// ==============================Coaching / Other
	// Coaching===============================

	public void coaching_OtherCoaching(WebDriver driver, String CoachingType, String Text, String Amber,
			ExtentTest logger) throws Exception {
		// click view coaching logs
		coachingMethods.clickElementInfo(driver, ViewCoachingLogsObjects.OtherCoaching._linkViewCoachingLog,
				"View Coaching Logs", logger);
		coachingMethods.pageRedirectionValidationInfo(driver, ViewCoachingLogsObjects.OtherCoaching._tblCoachLogs,
				"Coach View", logger);
		// search Scheduled
		coachingMethods.enterTextInfo(driver, ViewCoachingLogsObjects.OtherCoaching._txtSearch, "Scheduled", "Search",
				logger);

		Thread.sleep(5000);

		// find coaching log in the table
		WebElement Table = driver.findElement(ViewCoachingLogsObjects.OtherCoaching._tblCoachLogs);
		WebElement Next = driver.findElement(ViewCoachingLogsObjects.OtherCoaching._btnNext);
		while (!Table.getText().contains(CoachingType)) {
			Thread.sleep(1000);
			String x = Next.getAttribute("class");
			if (x.contains("disabled")) {
				break;
			} else
				coachingMethods.clickElementInfo(driver, ViewCoachingLogsObjects.OtherCoaching._btnNext, "Next",
						logger);
		}
		// get status and coaching id
		
			Thread.sleep(5000);
			String Status = driver.findElement(ViewCoachingLogsObjects.OtherCoaching._txtStatus).getText();
			CoachingID = driver.findElement(ViewCoachingLogsObjects.OtherCoaching._txtCoachingID).getText();
			coachingMethods.logInfo(logger, "The Coaching ID: " + CoachingID + " and the Status is " + Status);
			gm.scrollObj(driver, ViewCoachingLogsObjects.OtherCoaching._btnOpen(CoachingType));
			Thread.sleep(4000);
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.OtherCoaching._btnOpen(CoachingType),
					"LEADS : STEP 1", "Open button for Coaching", logger);
		
		Thread.sleep(4000);

		if (coachingMethods.waitForObjectExistence(driver, ViewCoachingLogsObjects.OtherCoaching._txtCoacheeEID)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// enter text in Notes form the Coach field
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.OtherCoaching._txtNotesFromCoach);
			coachingMethods.enterTextPass(driver, ViewCoachingLogsObjects.OtherCoaching._txtNotesFromCoach, "LEADS : STEP 2",
					Text, "Notes from the Coach:", logger);

			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.OtherCoaching._txtStrengths);

			// enter text in Strengths
			coachingMethods.enterTextPass(driver, ViewCoachingLogsObjects.OtherCoaching._txtStrengths, "LEADS : STEP 3", Text,
					"Strengths:", logger);

			

			// Enter text in Areas for Development
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.OtherCoaching._txtDevelopments);
			coachingMethods.enterTextPass(driver, ViewCoachingLogsObjects.OtherCoaching._txtDevelopments, "LEADS : STEP 4",
					Text, "Areas for Development:", logger);
			// Enter text in Action plan
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.OtherCoaching._txtActionPlan);
			coachingMethods.enterTextPass(driver, ViewCoachingLogsObjects.OtherCoaching._txtActionPlan, "LEADS : STEP 5", Text,
					"Action Plan", logger);
			// Enter text in Commitment
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.OtherCoaching._txtCommitment);
			coachingMethods.enterTextPass(driver, ViewCoachingLogsObjects.OtherCoaching._txtCommitment, "LEADS : STEP 6", Text,
					"Commitment:", logger);

			

			// click Irritable check box
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.OtherCoaching._chkboxIrritable);
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.OtherCoaching._chkboxIrritable, "LEADS : STEP 7",
					"Irritable", logger);
			// click Sudden Change in Behavior check box
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.OtherCoaching._chkboxBehavior, "LEADS : STEP 8",
					"Sudden Change in Behavior", logger);
			// click Apathy check box
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.OtherCoaching._chkboxApathy, "LEADS : STEP 9",
					"Apathy", logger);
			// click Attendance check box
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.OtherCoaching._chkboxAttendance, "LEADS : STEP 10",
					"Attendance", logger);
			// click Habitual Negligence check box
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.OtherCoaching._chkboxNegligence, "LEADS : STEP 11",
					"Habitual Negligence", logger);
			// click Red radio button
			gm.scrollToViewElement(driver,ViewCoachingLogsObjects.OtherCoaching._radRed);
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.OtherCoaching._radRed, "LEADS : STEP 12",
					"Red - Will attrite within a month", logger);
			// click Green radio button
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.OtherCoaching._radGreen, "LEADS : STEP 13",
					"Green - No risk/highly engaged", logger);
			// click Amber radio button
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.OtherCoaching._radAmber, "LEADS : STEP 14",
					"Amber - Will attrite within a quarter", logger);

			WebElement drp = driver.findElement(ViewCoachingLogsObjects.OtherCoaching._drpAmberAttrition);
			js.executeScript("arguments[0].scrollIntoView(true);", drp);
			// Select from Amber drop down field
			coachingMethods.selectVisibleTextPass(driver, ViewCoachingLogsObjects.OtherCoaching._drpAmberAttrition,
					"LEADS : STEP 15", Amber, "Please indicate your reason why this employee is tagged as AMBER in attrition.",
					logger);
			// Click Company radio button
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.OtherCoaching._radCompany, "LEADS : STEP 16",
					"Company", logger);
			// Click Opportunities radio button
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.OtherCoaching._radOpportunities, "LEADS : STEP 17",
					"Opportunities", logger);
			// Click People radio button
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.OtherCoaching._radPeople, "LEADS : STEP 18",
					"People", logger);
			// Click Work radio button
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.OtherCoaching._radWork, "LEADS : STEP 19", "Work",
					logger);
			// Click Work Environment radio button
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.OtherCoaching._radWorkEnvironment,
					"FR 4.29", "Work Environment", logger);
			// Click Confirm button
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.OtherCoaching._btnConfirm, "LEADS : STEP 20",
					"Confirm", logger);
		} else
			coachingMethods.logFail(logger, "User is not in the Coaching Logs Page!", driver);

		coachingMethods.pageRedirectionValidationInfo(driver, ViewCoachingLogsObjects.OtherCoaching._tblCoachLogs,
				"View Coaching Logs", logger);
		// validate successfully saved
		if (coachingMethods.waitForObjectExistence(driver, ViewCoachingLogsObjects.OtherCoaching._popupSaved,
				"Successfully Saved", logger)) {
			coachingMethods.logInfo(logger, "Coaching Log Successfully Saved");
		} else
			coachingMethods.logFail(logger, "Coaching Log is not saved!", driver);
		Thread.sleep(5000);
		// select pending from filter drop down
		// coachingMethods.selectVisibleTextInfo(driver,
		// ViewCoachingLogsObjects.PerformancCoaching._drpFilter, "Pending", "Filter",
		// logger);
		coachingMethods.enterTextInfo(driver, ViewCoachingLogsObjects.OtherCoaching._txtSearch, "Pending", "Search",
				logger);

		WebElement Table1 = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._tblCoachLogs);
		WebElement Next1 = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._btnNext);
		while (!Table1.getText().contains(CoachingID)) {
			Thread.sleep(1000);
			String x = Next1.getAttribute("class");
			if (x.contains("disabled")) {
				break;
			} else
				coachingMethods.clickElementInfo(driver, ViewCoachingLogsObjects.PerformancCoaching._btnNext, "Next",
						logger);
		}
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement ID = driver.findElement(ViewCoachingLogsObjects.OtherCoaching._txtCoachingIDPending(CoachingID));
		js.executeScript("arguments[0].scrollIntoView(true);", ID);
		// validate status is updated to pending
		if (coachingMethods.waitForObjectExistence(driver,
				ViewCoachingLogsObjects.OtherCoaching._txtCoachingIDPending(CoachingID))) {
			Thread.sleep(5000);
			String CoachingID2 = driver
					.findElement(ViewCoachingLogsObjects.OtherCoaching._txtCoachingIDPending(CoachingID)).getText();
			String Status1 = driver.findElement(ViewCoachingLogsObjects.OtherCoaching._txtStatus).getText();
			if (Status1.equalsIgnoreCase("Pending") && CoachingID2.equals(CoachingID)) {
				coachingMethods.logPass(logger,
						"FR 4.32 Status of Coaching with CoachingID: " + CoachingID2 + " is updated to Pending",
						driver);
			} else
				coachingMethods.logFail(logger,
						"FR 4.32 Status of Coaching with CoachingID: " + CoachingID2 + " is not updated", driver);
		}

	}

	// ====================================TRIAD===============================================

	public void triad(WebDriver driver, String CoachingType, String p1, String p1_1, String p1_2, String p1_3,
			String p1_4, String p2, String p2_1, String p2_2, String p3, String p3_1, String p3_2, String p4,
			String p4_1, String p4_2, String p4_3, ExtentTest logger) throws Exception {
		// click view coaching logs
		coachingMethods.clickElementInfo(driver, ViewCoachingLogsObjects.TRIAD._linkViewCoachingLog,
				"View Coaching Logs", logger);
		coachingMethods.pageRedirectionValidationInfo(driver, ViewCoachingLogsObjects.TRIAD._tblCoachLogs, "Coach View",
				logger);

		// search Scheduled
		gm.scrollToViewElement(driver, ViewCoachingLogsObjects.TRIAD._txtSearch);
		coachingMethods.enterTextInfo(driver, ViewCoachingLogsObjects.TRIAD._txtSearch, "Scheduled", "Search", logger);

		// find coaching log in table
		WebElement Table = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._tblCoachLogs);
		WebElement Next = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._btnNext);
		while (!Table.getText().contains(CoachingType)) {
			Thread.sleep(1000);
			String x = Next.getAttribute("class");
			if (x.contains("disabled")) {
				break;
			} else
				coachingMethods.clickElementInfo(driver, ViewCoachingLogsObjects.PerformancCoaching._btnNext, "Next",
						logger);
		}
		// get Status and Coaching Id
	
			String Status = driver.findElement(ViewCoachingLogsObjects.TRIAD._txtStatus).getText();
			CoachingID = driver.findElement(ViewCoachingLogsObjects.TRIAD._txtCoachingID).getText();
			coachingMethods.logInfo(logger, "The Coaching ID: " + CoachingID + " and the Status is " + Status);
			gm.scrollToViewElement(driver, ViewCoachingLogsObjects.TRIAD._btnOpen(CoachingType));
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.TRIAD._btnOpen(CoachingType), "LEADS : STEP 1",
					"Open button for TRIAD", logger);
		
		Thread.sleep(1000);
		// get coach id
		String Coach = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._txtCoachEID).getText();
		Thread.sleep(1000);
		
		String[] fdbck1 = { "p1", "p1_1", "p1_2", "p1_3", "p1_4", "p2", "p2_1", "p2_2", "p3", "p3_1" };
		String[] answer = { p1, p1_1, p1_2, p1_3, p1_4, p2, p2_1, p2_2, p3, p3_1 };
		String[] req = { "FR 4.34", "FR 4.35", "FR 4.36", "FR 4.37", "FR 4.38", "FR 4.39", "FR 4.40", "FR 4.41",
				"FR 4.42", "FR 4.43" };

		String[] fdbck2 = { "p3_2", "p4", "p4_1", "p4_2", "p4_3", };
		String[] answer2 = { p3_2, p4, p4_1, p4_2, p4_3 };
		String[] req2 = { "FR 4.44", "FR 4.45", "FR 4.46", "FR 4.47", "FR 4.48" };
		Thread.sleep(3000);
	
		
	
		for (int i = 0; i < fdbck1.length; i++) {
			// click Yes/No radio button
			
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.TRIAD._radYesNo(fdbck1[i], answer[i]),
					req[i], answer[i], logger);
		
			gm.scrollDown(driver, ViewCoachingLogsObjects.TRIAD._radYesNo(fdbck1[i], answer[i]));
			
		}
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Confrim = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._btnConfirm);
		js.executeScript("arguments[0].scrollIntoView(true);", Confrim);

		for (int i = 0; i < fdbck2.length; i++) {
			// click Yes/No radio button
			gm.Scroll(driver, ViewCoachingLogsObjects.TRIAD._radYesNo(fdbck2[i], answer2[i]));
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.TRIAD._radYesNo(fdbck2[i], answer2[i]),
					req2[i], answer2[i], logger);
			
		}
		// click Confirm
		coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.PerformancCoaching._btnConfirm, "LEADS : STEP 2",
				"Confirm", logger);

		coachingMethods.pageRedirectionValidationInfo(driver, ViewCoachingLogsObjects.PerformancCoaching._tblCoachLogs,
				"View Coaching Logs", logger);
		// validate
		if (coachingMethods.waitForObjectExistence(driver, ViewCoachingLogsObjects.PerformancCoaching._popupSaved,
				"Successfully Saved", logger)) {
			coachingMethods.logInfo(logger, "Coaching Log Successfully Saved");
		} else
			coachingMethods.logFail(logger, "Coaching Log is not saved!", driver);
		Thread.sleep(5000);
		
		coachingMethods.enterTextInfo(driver, ViewCoachingLogsObjects.PerformancCoaching._txtSearch, "Pending",
				"Search", logger);

		WebElement Table1 = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._tblCoachLogs);
		WebElement Next1 = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._btnNext);
		while (!Table1.getText().contains(CoachingID)) {
			Thread.sleep(1000);
			String x = Next1.getAttribute("class");
			if (x.contains("disabled")) {
				break;
			} else
				coachingMethods.clickElementInfo(driver, ViewCoachingLogsObjects.PerformancCoaching._btnNext, "Next",
						logger);
		}
		Thread.sleep(5000);

		WebElement ID = driver.findElement(ViewCoachingLogsObjects.TRIAD._txtCoachingIDPending(CoachingID));
		js.executeScript("arguments[0].scrollIntoView(true);", ID);
		Thread.sleep(1500);
		// validate status updated to pending
		if (coachingMethods.waitForObjectExistence(driver,
				ViewCoachingLogsObjects.TRIAD._txtCoachingIDPending(CoachingID))) {
			Thread.sleep(5000);
			String CoachingID2 = driver.findElement(ViewCoachingLogsObjects.TRIAD._txtCoachingIDPending(CoachingID))
					.getText();
			String Status1 = driver.findElement(ViewCoachingLogsObjects.TRIAD._txtStatus).getText();
			if (Status1.equalsIgnoreCase("Pending") && CoachingID2.equals(CoachingID)) {
				coachingMethods.logPass(logger,
						"LEADS : STEP 3 Status of TRIAD Coaching with CoachingID: " + CoachingID2 + " is updated to Pending",
						driver);
			} else
				coachingMethods.logFail(logger,
						"LEADS : STEP 4 Status of TRIAD Coaching with CoachingID: " + CoachingID2 + " is not updated", driver);
		}
	}

	// ==================================Coaching
	// Logs=========================================
	public void coachingLogs(WebDriver driver, String MonthYear, String Day, String Time, String AMPM, String Duration,
			String Filter, ExtentTest logger) throws Exception {
		// click view coaching log
		coachingMethods.clickElementInfo(driver, ViewCoachingLogsObjects.CoachingLogs._linkViewCoachingLog,
				"View Coaching Logs", logger);
		coachingMethods.pageRedirectionValidationInfo(driver, ViewCoachingLogsObjects.CoachingLogs._tblCoachLogs,
				"Coach View", logger);

		// search Scheduled
		Thread.sleep(5000);
		coachingMethods.enterTextInfo(driver, ViewCoachingLogsObjects.CoachingLogs._txtSearch, "Scheduled", "Search",
				logger);

		// cancel
		// gm.scrollObj(driver, ScheduleCoachingSessionsObjects._btnSchedule);
		if (coachingMethods.waitForObjectExistence(driver, ViewCoachingLogsObjects.CoachingLogs._tblCoachLogs)) {
			// get coaching id to be cancelled
			CoachingID = driver.findElement(ViewCoachingLogsObjects.CoachingLogs._txtID).getText();
			// click cancel
			gm.scrollObj(driver, ViewCoachingLogsObjects.CoachingLogs._linkCancel(CoachingID));
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.CoachingLogs._linkCancel(CoachingID),
					"LEADS : STEP 1", "Cancel button for Coaching ID: " + CoachingID, logger);
		} else
			coachingMethods.logFail(logger, "Coaching Logs table is not displayed!", driver);

		// search for Cancelled coaching id
		Thread.sleep(4000);
		coachingMethods.enterTextInfo(driver, ViewCoachingLogsObjects.CoachingLogs._txtSearch, CoachingID, "Search",
				logger);

		String Status = driver.findElement(ViewCoachingLogsObjects.CoachingLogs._txtStatus(CoachingID)).getText();
		Thread.sleep(4000);

		if (Status.contains("Cancelled")) {
			coachingMethods.logPass(logger, "Coaching log with Coaching ID " + CoachingID + " is cancelled", driver);
		} else
			coachingMethods.logFail(logger, "Coaching log with Coaching ID " + CoachingID + " is not cancelled",
					driver);

		// search Scheduled
		driver.findElement(ViewCoachingLogsObjects.CoachingLogs._txtSearch).clear();
		coachingMethods.enterTextInfo(driver, ViewCoachingLogsObjects.CoachingLogs._txtSearch, "Scheduled", "Search",
				logger);

		// Reschedule
		if (coachingMethods.waitForObjectExistence(driver, ViewCoachingLogsObjects.CoachingLogs._tblCoachLogs)) {
			CoachingID = driver.findElement(ViewCoachingLogsObjects.CoachingLogs._txtID).getText();
			// click Resched button
			gm.scrollObj(driver, ViewCoachingLogsObjects.CoachingLogs._linkResched(CoachingID));
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.CoachingLogs._linkResched(CoachingID),
					"LEADS : STEP 2", "Resched button for Coaching ID: " + CoachingID, logger);
		} else
			coachingMethods.logFail(logger, "Coaching Logs table is not displayed!", driver);

		// validate pop up
		if (coachingMethods.waitForObjectExistence(driver, ViewCoachingLogsObjects.CoachingLogs._popUpResched)) {
			coachingMethods.logPass(logger, "Rescheduling Coaching Session Popup  displayed");
		} else
			coachingMethods.logFail(logger, "Rescheduling Coaching Session Popup not displayed", driver);
		Thread.sleep(1000);
		  
		 

		if (coachingMethods.waitForObjectExistence(driver, ViewCoachingLogsObjects.CoachingLogs._popUpResched)) {
			// click Select Date
			coachingMethods.clickElementInfo(driver, ViewCoachingLogsObjects.CoachingLogs._drpDate, "Select Date",
					logger);

			// Date picker
			if (coachingMethods.waitForObjectExistence(driver, ViewCoachingLogsObjects.CoachingLogs._tblDate)) {
				String MonthYear1 = driver.findElement(ViewCoachingLogsObjects.CoachingLogs._btnMonthYear).getText();

				while (!MonthYear1.contains(MonthYear)) {
					coachingMethods.clickElementInfo(driver, ViewCoachingLogsObjects.CoachingLogs._btnNext, "Next",
							logger);
					MonthYear1 = driver.findElement(ViewCoachingLogsObjects.CoachingLogs._btnMonthYear).getText();
				}
				// select Day
				coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.CoachingLogs._Day(Day), "LEADS : STEP 3",
						Day + " " + MonthYear, logger);
				// click resched
				coachingMethods.clickElementInfo(driver, ViewCoachingLogsObjects.CoachingLogs._popUpResched, "Resched",
						logger);
			} else
				coachingMethods.logFail(logger, "Calendar pop up is not displayed", driver);
			// select time
			coachingMethods.selectVisibleTextPass(driver, ViewCoachingLogsObjects.CoachingLogs._drpStartTime, "LEADS : STEP 4",
					Time, "Select Time:", logger);
			// select AM/PM
			coachingMethods.selectVisibleTextPass(driver, ViewCoachingLogsObjects.CoachingLogs._drpAmPm, "LEADS : STEP 5",
					AMPM, "AM/PM:", logger);
			// Click duration radio button
			if (Duration.equalsIgnoreCase("1 Hour")) {
				coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.CoachingLogs._rad1hour, "LEADS : STEP 6",
						"1 - Hour Coaching", logger);
			} else if (Duration.equalsIgnoreCase("30 Minutes")) {
				coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.CoachingLogs._rad30Min, "LEADS : STEP 7",
						"30 - Minutes Coaching", logger);
			} else
				coachingMethods.logFail(logger, "Select 1 Hour or 30 Minutes Coahing as duration", driver);
			// click resched
			coachingMethods.clickElementPass(driver, ViewCoachingLogsObjects.CoachingLogs._btnResched, "LEADS : STEP 8",
					"Reschedule button", logger);
		} else
			coachingMethods.logFail(logger, "Rescheduling Coaching Session pop up is not displayed!", driver);
		Thread.sleep(1500);

		// search Scheduled
		driver.findElement(ViewCoachingLogsObjects.CoachingLogs._txtSearch).clear();
		coachingMethods.enterTextInfo(driver, ViewCoachingLogsObjects.CoachingLogs._txtSearch, CoachingID, "Search",
				logger);
		WebElement Table1 = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._tblCoachLogs);
		WebElement Next1 = driver.findElement(ViewCoachingLogsObjects.PerformancCoaching._btnNext);
		while (!Table1.getText().contains(CoachingID)) {
			Thread.sleep(1000);
			String x = Next1.getAttribute("class");
			if (x.contains("disabled")) {
				break;
			} else
				coachingMethods.clickElementInfo(driver, ViewCoachingLogsObjects.PerformancCoaching._btnNext, "Next",
						logger);
		}

		// validate coaching log is rescheduled
		String Date = driver.findElement(ViewCoachingLogsObjects.CoachingLogs._txtDate(CoachingID)).getText();
		if (Date.contains(Day)) {
			coachingMethods.logPass(logger, "Coaching " + CoachingID + " is successfully rescheduled", driver);
		} else
			coachingMethods.logFail(logger, "Coaching is not rescheduled", driver);

//	//click filter	
//		coachingMethods.selectVisibleTextPass(driver, ViewCoachingLogsObjects.CoachingLogs._drpFilter, "FR 4.54", Filter, "Filter: ", logger);
//		Thread.sleep(1500);
//		//validate if table is filtered
//		String Table = driver.findElement(ViewCoachingLogsObjects.CoachingLogs._tblCoachLogs).getText();
//		if(Table.contains(Filter)) {				
//			coachingMethods.logPass(logger, "Coaching Logs is Filtered by : "+ Filter, driver);
//		}else
//			coachingMethods.logFail(logger, "Coaching Logs is not Filtered by: "+ Filter, driver);				
	}

}
