package methods;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import pageObjects.ViewMyStatObjects;

public class ViewMyStatMethods {

	CoachingAppMethods coachingMethods = new CoachingAppMethods();

	public void viewMyStat(WebDriver driver, ExtentTest logger) throws InterruptedException {

		coachingMethods.logInfo(logger, "[INFO] Data in View My Stats will only be populated if user is a Team Lead");
		coachingMethods.logInfo(logger,
				"[INFO] To make user a Team Lead, go to Roster Management in Settings and edit Team Lead");

		if (coachingMethods.waitForObjectExistence(driver, ViewMyStatObjects._linkViewMyStats)) {
			coachingMethods.clickElementPass(driver, ViewMyStatObjects._linkViewMyStats, "LEADS : STEP 1",
					"View My Stats link", logger);
			if (coachingMethods.waitForObjectExistence(driver, ViewMyStatObjects._lblMyStats))
				coachingMethods.logPass(logger, "LEADS : STEP 1- My Stats is displayed", driver);
			else
				coachingMethods.logFail(logger, "LEADS : STEP 1 - Nothing is displayed", driver);
		} else
			coachingMethods.logFail(logger, "LEADS : STEP 1- View My Stats link doesn't exists", driver);
	}
}
