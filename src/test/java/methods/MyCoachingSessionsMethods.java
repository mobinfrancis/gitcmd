package methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

import pageObjects.MyCoachingSessionsObjects;
import pageObjects.ViewCoachingLogsObjects;

public class MyCoachingSessionsMethods {

	CoachingAppMethods coachingMethods = new CoachingAppMethods();

	public void myCoachingSessions(WebDriver driver, ExtentTest logger, String coacheeNote, String acknowledge,
			String p1, String p1_1, String p1_2, String p1_3, String p1_4, String p2, String p2_1, String p2_2,
			String p3, String p3_1, String p3_2, String p4, String p4_1, String p4_2, String p4_3,
			String triadAcceptDispute) throws Exception {
		
		
		// Click My Coaching Sessions
		if (coachingMethods.waitForObjectExistence(driver, MyCoachingSessionsObjects._linkMyCoachingSessions)) {
			coachingMethods.clickElementPass(driver, MyCoachingSessionsObjects._linkMyCoachingSessions, "COACHEE/LEADS : STEP 1",
					"My Coaching Sessions link", logger);
			Thread.sleep(3000);
			// Click Review/Confirm button
			coachingMethods.enterText(driver, MyCoachingSessionsObjects._myCoachSearch, "Pending", "Search", logger);
			if (coachingMethods.waitForObjectExistence(driver, MyCoachingSessionsObjects._btnReviewConfirm)) {
				String getStatus1 = driver.findElement(MyCoachingSessionsObjects._getStatusName1).getText();
				String getStatus2 = driver.findElement(MyCoachingSessionsObjects._getStatusName2).getText();
				System.out.println(getStatus1);
				System.out.println(getStatus2);

				if (!getStatus1.contains("Completed") || !getStatus1.contains("For Revision"))
					coachingMethods.clickElementPass(driver, MyCoachingSessionsObjects._btnReviewConfirm, "COACHEE/LEADS : STEP 2",
							"Review/Confirm", logger);
				else {
					if (getStatus2.contains("Completed") || getStatus2.contains("For Revision"))
						coachingMethods.clickElementPass(driver, MyCoachingSessionsObjects._btnReviewConfirm3, "COACHEE/LEADS : STEP 2",
								"Review/Confirm", logger);
					else
						coachingMethods.clickElementPass(driver, MyCoachingSessionsObjects._btnReviewConfirm2, "COACHEE/LEADS : STEP 2",
								"Review/Confirm", logger);
				}

				// Verify Coaching Details is displayed
				if (coachingMethods.waitForObjectExistence(driver, MyCoachingSessionsObjects._lblCoachingForm)) {
					coachingMethods.logPass(logger, "COACHEE/LEADS : STEP 3- Coaching Details is displayed", driver);
					WebElement fdbckDisabled = driver.findElement(By.xpath("//input[@name='p1' and @value='Yes']"));
					if (fdbckDisabled.isEnabled()) {
						if (coachingMethods.waitForObjectExistence(driver, MyCoachingSessionsObjects._isTRIAD))
							System.out.println("TRIAD form");
						else {
							driver.findElement(MyCoachingSessionsObjects._textareaCoacheeNote).clear();
							coachingMethods.enterTextPass(driver, MyCoachingSessionsObjects._textareaCoacheeNote,
									"COACHEE/LEADS : STEP 4", coacheeNote, "Notes from Coachee", logger);
							// Acknowledge
							String objNameAck = "";
							if (acknowledge.equals("1"))
								objNameAck = "I accept";
							else if (acknowledge.equals("2"))
								objNameAck = "I don't accept";
							coachingMethods.clickElementPass(driver,
									MyCoachingSessionsObjects._radAcknowledgement(acknowledge), "COACHEE/LEADS : STEP 4",
									objNameAck, logger);
						}

						Thread.sleep(2000);
						// Answer feedback
						String[] fdbck1 = { "p1", "p1_1", "p1_2" };
						String[] fdbck2 = { "p1_3", "p1_4", "p2", "p2_1", "p2_2", "p3", "p3_1", "p3_2", "p4", "p4_1",
								"p4_2", "p4_3" };
						String[] ans1 = { p1, p1_1, p1_2 };
						String[] ans2 = { p1_3, p1_4, p2, p2_1, p2_2, p3, p3_1, p3_2, p4, p4_1, p4_2, p4_3 };

						// Answer feedback for TRIAD
						String[] fdbck3 = { "p1", "p1_1", "p1_2", "p1_3", "p1_4", "p2", "p2_1", "p2_2", "p3", "p3_1" };
						String[] fdbck4 = { "p3_2", "p4", "p4_1", "p4_2", "p4_3" };
						String[] ans3 = { p1, p1_1, p1_2, p1_3, p1_4, p2, p2_1, p2_2, p3, p3_1 };
						String[] ans4 = { p3_2, p4, p4_1, p4_2, p4_3 };

						// For TRIAD Form
						if (coachingMethods.waitForObjectExistence(driver, MyCoachingSessionsObjects._isTRIAD)) {
							for (int i = 0; i < ans3.length; i++) {
								coachingMethods.clickElementPass(driver,
										MyCoachingSessionsObjects._radFeedback(fdbck3[i], ans3[i]), "COACHEE/LEADS : STEP 1",
										ans3[i], logger);
							}

							coachingMethods.scrollIntoView(driver, MyCoachingSessionsObjects._fdbckScrollHereTRIAD);

							for (int i = 0; i < ans4.length; i++) {
								coachingMethods.clickElementPass(driver,
										MyCoachingSessionsObjects._radFeedback(fdbck4[i], ans4[i]), "COACHEE/LEADS : STEP 2",
										ans4[i], logger);
							}

							// Click Acknowledge & Accept or Dispute
							if (triadAcceptDispute.equalsIgnoreCase("Accept")) {
								if (coachingMethods.waitForObjectExistence(driver,
										MyCoachingSessionsObjects._btnConfirmTRIAD))
									coachingMethods.clickElementPass(driver, MyCoachingSessionsObjects._btnConfirmTRIAD,
											"COACHEE/LEADS : STEP 3", "Acknowledge & Accept button", logger);
								else
									coachingMethods.logFail(logger,
											"COACHEE/LEADS : STEP 4 - Acknowledge & Accept button doesn't exists", driver);
							} else if (coachingMethods.waitForObjectExistence(driver,
									MyCoachingSessionsObjects._btnDisputeTRIAD))
								coachingMethods.clickElementPass(driver, MyCoachingSessionsObjects._btnDisputeTRIAD,
										"COACHEE/LEADS : STEP 4", "Dispute button", logger);
							else
								coachingMethods.logFail(logger, "FR 2.14 - Dispute button doesn't exists", driver);

							// For other forms
						} else {
							for (int i = 0; i < ans1.length; i++) {
								
								coachingMethods.clickElementPass(driver,
										MyCoachingSessionsObjects._radFeedback(fdbck1[i], ans1[i]), "COACHEE/LEADS : STEP 4", ans1[i],
										logger);
								coachingMethods.scrollToViewElement(driver, MyCoachingSessionsObjects._radFeedback(fdbck1[i], ans1[i]));
							}

							coachingMethods.scrollIntoView(driver, MyCoachingSessionsObjects._fdbckScrollHere);

							for (int i = 0; i < ans2.length; i++) {
								coachingMethods.clickElementPass(driver,
										MyCoachingSessionsObjects._radFeedback(fdbck2[i], ans2[i]), "COACHEE/LEADS : STEP 5", ans2[i],
										logger);
							}

							// Click Confirm button
							if (coachingMethods.waitForObjectExistence(driver, MyCoachingSessionsObjects._btnConfirm))
								coachingMethods.clickElementPass(driver, MyCoachingSessionsObjects._btnConfirm,
										"COACHEE/LEADS : STEP 6", "Confirm button", logger);
							else
								coachingMethods.logFail(logger, "COACHEE/LEADS : STEP 6- Confirm button doesn't exists", driver);
						}

					} else
						coachingMethods.logInfo(logger, "Form is disabled", driver);

				} else
					coachingMethods.logFail(logger, "COACHEE/LEADS : STEP 6 - Coaching Details is not displayed", driver);
			} else
				coachingMethods.logFail(logger, "COACHEE/LEADS : STEP 6 - Review/Confirm button doesn't exists", driver);
		} else
			coachingMethods.logFail(logger, "COACHEE/LEADS : STEP 6 - My Coaching Sessions link doesn't exists", driver);
			

	} // End of MyCoachingSessions
	
}
