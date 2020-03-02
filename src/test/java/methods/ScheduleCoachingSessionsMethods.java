package methods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

import driver.GenericMethods2;
import pageObjects.ScheduleCoachingSessionsObjects;

public class ScheduleCoachingSessionsMethods {

	CoachingAppMethods coachingMethods = new CoachingAppMethods();
	GenericMethods2 gm = new GenericMethods2 ();

	public void scheduleCoachingSessions(WebDriver driver, ExtentTest logger, String coachee, String coachingType,
			String coachForTRIAD, String location, String realtime, String duration, String typeOfDate,
			String monthYear, String day, String date, String startTime, String ampm, String agenda) throws Exception {

		// Click Schedule Coaching Sessions
		if (coachingMethods.waitForObjectExistence(driver,
				ScheduleCoachingSessionsObjects._linkScheduleCoachingSessions)) {
			coachingMethods.clickElementPass(driver, ScheduleCoachingSessionsObjects._linkScheduleCoachingSessions,
					"LEADS : STEP 1", "Schedule Coaching Sessions link", logger);

			// Select Coachee
			if (coachingMethods.waitForObjectExistence(driver, ScheduleCoachingSessionsObjects._drpCoachee)) {
				Thread.sleep(3000);
				driver.findElement(ScheduleCoachingSessionsObjects._drpCoachee).click();
				coachingMethods.enterTextPass(driver, ScheduleCoachingSessionsObjects._drpCoachee, "LEADS : STEP 2", coachee,
						"Select Coachee", logger);
			} else
				coachingMethods.logFail(logger, "LEADS : STEP 2 - Select Coachee field doesn't exists", driver);

			// Select Coaching Type
			if (coachingMethods.waitForObjectExistence(driver, ScheduleCoachingSessionsObjects._drpCoachingType)) {
				driver.findElement(ScheduleCoachingSessionsObjects._drpCoachingType).click();
				coachingMethods.selectVisibleTextPass(driver, ScheduleCoachingSessionsObjects._drpCoachingType,
						"LEADS : STEP 3", coachingType, "Coaching Type", logger);
				driver.findElement(ScheduleCoachingSessionsObjects._drpCoachingType).click();
			} else
				coachingMethods.logFail(logger, "LEADS : STEP 3- Coaching Type field doesn't exists", driver);

			// Validate if coaching type is TRIAD -if TRIAD, enter coach
			if (coachingType.equalsIgnoreCase("TRIAD")) {
				if (coachingMethods.waitForObjectExistence(driver, ScheduleCoachingSessionsObjects._drpCoachForTRIAD))
					coachingMethods.selectVisibleTextInfo(driver, ScheduleCoachingSessionsObjects._drpCoachForTRIAD,
							coachForTRIAD, "Coach For TRIAD", logger);
				else
					coachingMethods.logFail(logger, "Coach for TRIAD field doesn't exists", driver);
			}

			// Enter location
			if (coachingMethods.waitForObjectExistence(driver, ScheduleCoachingSessionsObjects._txtLocation))
				coachingMethods.enterTextPass(driver, ScheduleCoachingSessionsObjects._txtLocation, "LEADS : STEP 4", location,
						"Location", logger);
			else
				coachingMethods.logFail(logger, "LEADS : STEP 4 - Location field doesn't exists", driver);

			// Tick Real-time coaching
			WebElement chkRealtime = driver.findElement(ScheduleCoachingSessionsObjects._chkRealtime);
			if (coachingMethods.waitForObjectExistence(driver, ScheduleCoachingSessionsObjects._chkRealtime)) {
				if (!chkRealtime.isSelected() && realtime.equalsIgnoreCase("y"))
					coachingMethods.clickElementPass(driver, ScheduleCoachingSessionsObjects._chkRealtime, "LEADS : STEP 5",
							"Real-Time Coaching", logger);
			} else
				coachingMethods.logFail(logger, "LEADS : STEP 5 - Real-Time Coaching doesn't exists", driver);

			// Choose duration
			if (duration.equalsIgnoreCase("1h"))
				coachingMethods.clickElementPass(driver, ScheduleCoachingSessionsObjects._radDuration("1"), "LEADS : STEP 6",
						"1-Hour Coaching Duration", logger);
			else if (duration.equalsIgnoreCase("30m"))
				coachingMethods.clickElementPass(driver, ScheduleCoachingSessionsObjects._radDuration("30"), "LEADS : STEP 6",
						"30-Minutes Coaching Duration", logger);
			else
				coachingMethods.logFail(logger, "LEADS : STEP 6 - No selected duration", driver);

			// Enter date
			// Select from calendar
			if (!chkRealtime.isSelected()) {
				if (typeOfDate.equalsIgnoreCase("select")) {
					if (coachingMethods.waitForObjectExistence(driver, ScheduleCoachingSessionsObjects._txtDate)) {
						driver.findElement(ScheduleCoachingSessionsObjects._txtDate).click();
						String valYear = driver.findElement(ScheduleCoachingSessionsObjects._validateYear).getText();
						while (!valYear.contains(monthYear)) {
							driver.findElement(ScheduleCoachingSessionsObjects._btnNextYear).click();
							valYear = driver.findElement(ScheduleCoachingSessionsObjects._validateYear).getText();
						}
						coachingMethods.clickElementPass(driver, ScheduleCoachingSessionsObjects._selectedDay(day),
								"LEADS : STEP 7", day + " " + monthYear, logger);
					} else
						coachingMethods.logFail(logger, "LEADS : STEP 7 - Date From filter doesn't exists", driver);

					// Enter date in textbox
				} else if (typeOfDate.equalsIgnoreCase("type")) {
					if (coachingMethods.waitForObjectExistence(driver, ScheduleCoachingSessionsObjects._txtDate)) {
						if (!chkRealtime.isSelected()) {
							driver.findElement(ScheduleCoachingSessionsObjects._txtDate).clear();
							coachingMethods.enterTextPass(driver, ScheduleCoachingSessionsObjects._txtDate, "LEADS : STEP 7",
									date, "Date", logger);
						} else
							coachingMethods.logFail(logger,
									"LEADS : STEP 7 - Real-Time checkbox is selected. Date field should be disabled", driver);
					} else
						coachingMethods.logFail(logger, "LEADS : STEP 7 - Date field doesn't exists", driver);

					// Enter date in textbox
				} else {
					if (coachingMethods.waitForObjectExistence(driver, ScheduleCoachingSessionsObjects._txtDate)) {
						if (!chkRealtime.isSelected()) {
							driver.findElement(ScheduleCoachingSessionsObjects._txtDate).clear();
							coachingMethods.enterTextPass(driver, ScheduleCoachingSessionsObjects._txtDate, "LEADS : STEP 7",
									date, "Date", logger);
						}
					} else
						coachingMethods.logFail(logger, "LEADS : STEP 7 - Date field doesn't exists", driver);
				}

				// Select Start time
				if (coachingMethods.waitForObjectExistence(driver, ScheduleCoachingSessionsObjects._drpStartTime)) {
					driver.findElement(ScheduleCoachingSessionsObjects._drpStartTime).click();
					coachingMethods.selectVisibleTextPass(driver, ScheduleCoachingSessionsObjects._drpStartTime,
							"LEADS : STEP 8", startTime, "Start Time", logger);
				} else
					coachingMethods.logFail(logger, "LEADS : STEP 8 - Start time field doesn't exists", driver);

				// Select AM/PM
				if (coachingMethods.waitForObjectExistence(driver, ScheduleCoachingSessionsObjects._drpAMPM)) {
					driver.findElement(ScheduleCoachingSessionsObjects._drpAMPM).click();
					coachingMethods.selectVisibleTextPass(driver, ScheduleCoachingSessionsObjects._drpAMPM, "LEADS : STEP 8",
							ampm, "AM/PM", logger);
				} else
					coachingMethods.logFail(logger, "LEADS : STEP 8 - AM/PM field doesn't exists", driver);
			}

			// Enter Agenda
			if (coachingMethods.waitForObjectExistence(driver, ScheduleCoachingSessionsObjects._txtAgenda))
				coachingMethods.enterTextPass(driver, ScheduleCoachingSessionsObjects._txtAgenda, "LEADS : STEP 9", agenda,
						"Agenda", logger);
			else
				coachingMethods.logFail(logger, "LEADS : STEP 9- Agenda field doesn't exists", driver);

			// Click Schedule button
			gm.scrollObj(driver, ScheduleCoachingSessionsObjects._btnSchedule);
			if (coachingMethods.waitForObjectExistence(driver, ScheduleCoachingSessionsObjects._btnSchedule))
				coachingMethods.clickElementPass(driver, ScheduleCoachingSessionsObjects._btnSchedule, "LEADS : STEP 9",
						"Schedule button", logger);
			else
				coachingMethods.logFail(logger, "LEADS : STEP 9 - Schedule button doesn't exists", driver);

			// Validate success message
			Thread.sleep(3000);
			if (coachingMethods.waitForObjectExistence(driver, ScheduleCoachingSessionsObjects._msgSuccess))
				coachingMethods.logPass(logger, "LEADS : STEP 10- Successfully saved!", driver);
			else
				coachingMethods.logFail(logger, "LEADS : STEP 10 - Not saved", driver);

			// Click View Coaching Logs
			coachingMethods.clickElementInfo(driver, ScheduleCoachingSessionsObjects._btnViewCoachingLogs,
					"View Coaching Logs", logger);
			coachingMethods.logPass(logger, "LEADS : STEP 10 - Successfully scheduled", driver);

		} else
			coachingMethods.logFail(logger, "LEADS : STEP 10- Schedule Coaching Sessions link doesn't exists", driver);
	} // End of Schedule Coaching Sessions
}
