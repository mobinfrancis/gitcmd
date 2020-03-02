package methods;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

import driver.GenericMethods2;
import pageObjects.ExtractReportsObjects;

public class ExtractReportsMethods {

	CoachingAppMethods coachingMethods = new CoachingAppMethods();
	GenericMethods2 gm = new GenericMethods2();
	Format formatter = new SimpleDateFormat("MMMM dd, yyyy");
	Format numFormatter = new SimpleDateFormat("MM/dd/yyyy");
	String date = formatter.format(new Date());
	String numDate = numFormatter.format(new Date());

	public void extractReportsPerformance(WebDriver driver, ExtentTest logger, String browser, String fromYearPerf,
			String fromMonthPerf, String fromDayPerf, String toYearPerf, String toMonthPerf, String toDayPerf,
			String showEntryPerf, String searchPerf) throws Exception {

		// Click Extract Reports
		gm.scrollToViewElement(driver, ExtractReportsObjects._linkExtractReports);
		if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._linkExtractReports)) {
			coachingMethods.clickElementPass(driver, ExtractReportsObjects._linkExtractReports, "HR PAM ADMIN / PROJECT ADMIN :STEP 1",
					"Extract Reports link", logger);
			coachingMethods.logInfo(logger, "Performance Coaching Data");

			// Select Date From in Performance Coaching Data
			if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._dateFilterFromPerf)) {
				driver.findElement(ExtractReportsObjects._dateFilterFromPerf).click();
				driver.findElement(ExtractReportsObjects._clickDaysHeader).click();
				driver.findElement(ExtractReportsObjects._clickMonthHeader).click();
				driver.findElement(ExtractReportsObjects._selectedYear(fromYearPerf)).click();
				driver.findElement(ExtractReportsObjects._selectedMonth(fromMonthPerf)).click();
				Thread.sleep(3000);
				coachingMethods.clickElementPass(driver, ExtractReportsObjects._selectedDay(fromDayPerf), "HR PAM ADMIN / PROJECT ADMIN :STEP 2",
						fromMonthPerf + " " + fromDayPerf + " " + fromYearPerf, logger);
			} else
				coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 2- Date From filter doesn't exists", driver);

			// Select Date To in Performance Coaching Data
			if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._dateFilterToPerf)) {
				driver.findElement(ExtractReportsObjects._dateFilterToPerf).click();
				driver.findElement(ExtractReportsObjects._clickDaysHeader).click();
				driver.findElement(ExtractReportsObjects._clickMonthHeader).click();
				driver.findElement(ExtractReportsObjects._selectedYear(toYearPerf)).click();
				driver.findElement(ExtractReportsObjects._selectedMonth(toMonthPerf)).click();
				coachingMethods.clickElementPass(driver, ExtractReportsObjects._selectedDay(toDayPerf), "HR PAM ADMIN / PROJECT ADMIN :STEP 3",
						toMonthPerf + " " + toDayPerf + " " + toYearPerf, logger);
			} else
				coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 3 - Date To filter doesn't exists", driver);

			// Click Filter button
			if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._btnFilterPerf))
				coachingMethods.clickElementPass(driver, ExtractReportsObjects._btnFilterPerf, "HR PAM ADMIN / PROJECT ADMIN :STEP 4",
						"Filter button", logger);
			else
				coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 4 - Filter button doesn't exists", driver);

			// Select Show entries
			if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._drpShowEntriesPerf)) {
				coachingMethods.selectVisibleTextPass(driver, ExtractReportsObjects._drpShowEntriesPerf, "HR PAM ADMIN / PROJECT ADMIN :STEP 5",
						showEntryPerf, "Show Entries", logger);
				coachingMethods.scrollIntoView(driver, ExtractReportsObjects._lblShowEntriesPerf);
				if (coachingMethods.waitForObjectExistence(driver,
						ExtractReportsObjects._lblResultEntriesPerf(showEntryPerf))) {
					String showEntries = driver.findElement(ExtractReportsObjects._lblShowEntriesPerf).getText();
					if (!showEntries.contains(showEntryPerf))
						coachingMethods.logInfo(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 6 - Number of data is less than or equal to 10");
					else
						coachingMethods.logPass(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 6 - " + showEntries, driver);
				} else {
					String showEntries = driver.findElement(ExtractReportsObjects._lblShowEntriesOther).getText();
					coachingMethods.logInfo(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 6  - " + showEntries);
				}
			} else
				coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 6  - Show Entries dropdown doesn't exists", driver);

			// Click Excel button in Performance Coaching Data
			coachingMethods.scrollIntoView(driver, ExtractReportsObjects._btnExcelPerf);
			if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._btnExcelPerf))
				coachingMethods.fileDownloadPass(driver, ExtractReportsObjects._btnExcelPerf, "Excel button", browser,
						"Performance Coaching Data export", "HR PAM ADMIN / PROJECT ADMIN :STEP 7 ", logger);
			else
				coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 7 - Excel button doesn't exists", driver);

			// Click CSV button in Performance Coaching Data
			if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._btnCSVPerf))
				coachingMethods.fileDownloadPass(driver, ExtractReportsObjects._btnCSVPerf, "CSV button", browser,
						"Coaching Dashboard - Reports", "HR PAM ADMIN / PROJECT ADMIN :STEP 8", logger);
			else
				coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 8 - CSV button doesn't exists", driver);

			// Search
			coachingMethods.scrollIntoView(driver, ExtractReportsObjects._txtSearchPerf);
			if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._txtSearchPerf)) {
				coachingMethods.enterTextPass(driver, ExtractReportsObjects._txtSearchPerf, "HR PAM ADMIN / PROJECT ADMIN :STEP 9", searchPerf,
						"Search", logger);
			} else
				coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 9 - Search box doesn't exists", driver);

			// Click Page numbers
			coachingMethods.scrollIntoView(driver, ExtractReportsObjects._lblPaginationPerf);
			String entriesPerf = driver.findElement(ExtractReportsObjects._lblShowEntriesPerf).getText();
			coachingMethods.logInfo(logger, entriesPerf);
			List<WebElement> pages = driver.findElements(ExtractReportsObjects._lblPaginationPerf);
			if (pages.size() <= 3)
				coachingMethods.logInfo(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 10- Entries is less than or equal to 10");
			else {
				for (int i = 1; i < pages.size() - 1; i++) {
					if (coachingMethods.elementExist(driver, ExtractReportsObjects._clickPageNumPerf(i))) {
						coachingMethods.clickElementPass(driver, ExtractReportsObjects._clickPageNumPerf(i), "HR PAM ADMIN / PROJECT ADMIN :STEP 10",
								"Page " + i, logger);
						Thread.sleep(2000);
					}
				}
				driver.findElement(ExtractReportsObjects._clickPageNumPerf(2)).click();
				WebElement prev = driver.findElement(ExtractReportsObjects._btnPrevPerf);
				if (prev.isEnabled())
					coachingMethods.clickElementPass(driver, ExtractReportsObjects._btnPrevPerf, "FR 7.10",
							"Previous button", logger);
				else
					coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 10- Previous button is disabled", driver);
				Thread.sleep(2000);
				WebElement next = driver.findElement(ExtractReportsObjects._btnNextPerf);
				if (next.isEnabled())
					coachingMethods.clickElementPass(driver, ExtractReportsObjects._btnNextPerf, "HR PAM ADMIN / PROJECT ADMIN :STEP 10",
							"Next button", logger);
				else
					coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 10 - Next button is disabled", driver);
			}
		} else
			coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 10- Extract Reports link doesn't exists", driver);
	}

	public void extractReportsOther(WebDriver driver, ExtentTest logger, String browser, String fromYearOther,
			String fromMonthOther, String fromDayOther, String toYearOther, String toMonthOther, String toDayOther,
			String showEntryOther, String searchOther) throws Exception {

		coachingMethods.logInfo(logger, "Other Coaching Data");
		// Current Date
		System.out.println(date);
		System.out.println(numDate);
		coachingMethods.scrollIntoView(driver, ExtractReportsObjects._lblShowEntriesOther);
		if (coachingMethods.elementExist(driver, ExtractReportsObjects._dateCurrent1Other)) {
			String currentDate1Other = driver.findElement(ExtractReportsObjects._dateCurrent1Other).getText();
			String currentDate2Other = driver.findElement(ExtractReportsObjects._dateCurrent2Other).getText();
			String currentDate3Other = driver.findElement(ExtractReportsObjects._dateCurrent3Other).getText();
			if (currentDate1Other.contains(date) || currentDate2Other.contains(date)
					|| currentDate3Other.contains(numDate))
				coachingMethods.logPass(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 1 - Scheduled Coaching Session for current date is displayed",
						driver);
			else
				coachingMethods.logInfo(logger,
						"FR 7.2 - Scheduled Coaching Session for current date is not displayed");
		} else if (coachingMethods.elementExist(driver, ExtractReportsObjects._noData))
			coachingMethods.logInfo(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 1- No Scheduled Coaching Session for current date");

		// Select Date From in Other Coaching Data
		coachingMethods.scrollIntoView(driver, ExtractReportsObjects._dateFilterFromOther);
		if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._dateFilterFromOther)) {
			driver.findElement(ExtractReportsObjects._dateFilterFromOther).click();
			driver.findElement(ExtractReportsObjects._clickDaysHeader).click();
			driver.findElement(ExtractReportsObjects._clickMonthHeader).click();
			driver.findElement(ExtractReportsObjects._selectedYear(fromYearOther)).click();
			driver.findElement(ExtractReportsObjects._selectedMonth(fromMonthOther)).click();
			coachingMethods.clickElementPass(driver, ExtractReportsObjects._selectedDay(fromDayOther), "HR PAM ADMIN / PROJECT ADMIN :STEP 2",
					fromMonthOther + " " + fromDayOther + " " + fromYearOther, logger);
		} else
			coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 2- Date From filter doesn't exists", driver);

		// Select Date To in Other Coaching Data
		if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._dateFilterToOther)) {
			driver.findElement(ExtractReportsObjects._dateFilterToOther).click();
			driver.findElement(ExtractReportsObjects._clickDaysHeader).click();
			driver.findElement(ExtractReportsObjects._clickMonthHeader).click();
			driver.findElement(ExtractReportsObjects._selectedYear(toYearOther)).click();
			driver.findElement(ExtractReportsObjects._selectedMonth(toMonthOther)).click();
			coachingMethods.clickElementPass(driver, ExtractReportsObjects._selectedDay(toDayOther), "HR PAM ADMIN / PROJECT ADMIN :STEP 3",
					toMonthOther + " " + toDayOther + " " + toYearOther, logger);
		} else
			coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 3 - Date To filter doesn't exists", driver);

		// Click Filter button
		if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._btnFilterOther))
			coachingMethods.clickElementPass(driver, ExtractReportsObjects._btnFilterOther, "HR PAM ADMIN / PROJECT ADMIN :STEP 4", "Filter button",
					logger);
		else
			coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 4 - Filter button doesn't exists", driver);

		// Select Show entries
		coachingMethods.scrollIntoView(driver, ExtractReportsObjects._drpShowEntriesOther);
		if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._drpShowEntriesOther)) {
			coachingMethods.selectVisibleTextPass(driver, ExtractReportsObjects._drpShowEntriesOther, "HR PAM ADMIN / PROJECT ADMIN :STEP 5",
					showEntryOther, "Show Entries", logger);
			coachingMethods.scrollIntoView(driver, ExtractReportsObjects._lblShowEntriesOther);
			if (coachingMethods.waitForObjectExistence(driver,
					ExtractReportsObjects._lblResultEntriesOther(showEntryOther))) {
				String showEntries = driver.findElement(ExtractReportsObjects._lblShowEntriesOther).getText();
				if (!showEntries.contains(showEntryOther))
					coachingMethods.logInfo(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 5 - Number of data is less than or equal to 10");
				else
					coachingMethods.logPass(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 5 - " + showEntries, driver);
			} else {
				String showEntries = driver.findElement(ExtractReportsObjects._lblShowEntriesOther).getText();
				coachingMethods.logInfo(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 5 - " + showEntries);
			}
		} else
			coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 5- Show Entries dropdown doesn't exists", driver);

		// Click Excel button in Performance Coaching Data
		coachingMethods.scrollIntoView(driver, ExtractReportsObjects._btnExcelOther);
		if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._btnExcelOther)) {
			coachingMethods.fileDownloadPass(driver, ExtractReportsObjects._btnExcelOther, "Excel button", browser,
					"Other Coaching Data export", "HR PAM ADMIN / PROJECT ADMIN :STEP 6", logger);
		} else
			coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 6 - Excel button doesn't exists", driver);

		// Click CSV button in Performance Coaching Data
		if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._btnCSVOther)) {
			coachingMethods.fileDownloadPass(driver, ExtractReportsObjects._btnCSVOther, "CSV button", browser,
					"Coaching Dashboard - Reports", "HR PAM ADMIN / PROJECT ADMIN :STEP 7", logger);
		} else
			coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 7 - CSV button doesn't exists", driver);

		// Search
		coachingMethods.scrollIntoView(driver, ExtractReportsObjects._txtSearchOther);
		if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._txtSearchOther)) {
			coachingMethods.enterTextPass(driver, ExtractReportsObjects._txtSearchOther, "HR PAM ADMIN / PROJECT ADMIN :STEP 8", searchOther,
					"Search", logger);
		} else
			coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 8 - Search box doesn't exists", driver);

		// Click Page numbers
		coachingMethods.scrollIntoView(driver, ExtractReportsObjects._lblPaginationOther);
		String entriesOther = driver.findElement(ExtractReportsObjects._lblShowEntriesOther).getText();
		coachingMethods.logInfo(logger, entriesOther);
		List<WebElement> pages = driver.findElements(ExtractReportsObjects._lblPaginationOther);
		if (pages.size() <= 3)
			coachingMethods.logInfo(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 9 - Entries is less than or equal to 10");
		else {
			for (int i = 1; i < pages.size() - 1; i++) {
				if (coachingMethods.elementExist(driver, ExtractReportsObjects._clickPageNumOther(i))) {
					coachingMethods.clickElementPass(driver, ExtractReportsObjects._clickPageNumOther(i), "HR PAM ADMIN / PROJECT ADMIN :STEP 9",
							"Page " + i, logger);
					Thread.sleep(2000);
				}
			}
			driver.findElement(ExtractReportsObjects._clickPageNumOther(2)).click();
			WebElement prev = driver.findElement(ExtractReportsObjects._btnPrevOther);
			if (prev.isEnabled())
				coachingMethods.clickElementPass(driver, ExtractReportsObjects._btnPrevOther, "HR PAM ADMIN / PROJECT ADMIN :STEP 10 ",
						"Previous button", logger);
			else
				coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 10- Previous button is disabled", driver);
			Thread.sleep(2000);
			WebElement next = driver.findElement(ExtractReportsObjects._btnNextOther);
			if (next.isEnabled())
				coachingMethods.clickElementPass(driver, ExtractReportsObjects._btnNextOther, "HR PAM ADMIN / PROJECT ADMIN :STEP 11", "Next button",
						logger);
			else
				coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 11- Next button is disabled", driver);
		}

	}

	public void extractReportsTRIAD(WebDriver driver, ExtentTest logger, String browser, String fromYearTRIAD,
			String fromMonthTRIAD, String fromDayTRIAD, String toYearTRIAD, String toMonthTRIAD, String toDayTRIAD,
			String showEntryTRIAD, String searchTRIAD) throws Exception {

		coachingMethods.logInfo(logger, "TRIAD Coaching Data");
		// Current Date
		System.out.println(date);
		System.out.println(numDate);
		coachingMethods.scrollIntoView(driver, ExtractReportsObjects._lblShowEntriesTRIAD);
		if (coachingMethods.elementExist(driver, ExtractReportsObjects._dateCurrent1TRIAD)) {
			String currentDate1TRIAD = driver.findElement(ExtractReportsObjects._dateCurrent1TRIAD).getText();
			String currentDate2TRIAD = driver.findElement(ExtractReportsObjects._dateCurrent2TRIAD).getText();
			String currentDate3TRIAD = driver.findElement(ExtractReportsObjects._dateCurrent3TRIAD).getText();
			if (currentDate1TRIAD.contains(date) || currentDate2TRIAD.contains(date)
					|| currentDate3TRIAD.contains(numDate))
				coachingMethods.logPass(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 1- Scheduled Coaching Session for current date is displayed",
						driver);
			else
				coachingMethods.logInfo(logger,
						"HR PAM ADMIN / PROJECT ADMIN :STEP 2 - Scheduled Coaching Session for current date is not displayed");
		} else if (coachingMethods.elementExist(driver, ExtractReportsObjects._noData))
			coachingMethods.logInfo(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 2 - No Scheduled Coaching Session for current date");

		// Select Date From in Other Coaching Data
		coachingMethods.scrollIntoView(driver, ExtractReportsObjects._dateFilterFromTRIAD);
		if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._dateFilterFromTRIAD)) {
			driver.findElement(ExtractReportsObjects._dateFilterFromTRIAD).click();
			driver.findElement(ExtractReportsObjects._clickDaysHeader).click();
			driver.findElement(ExtractReportsObjects._clickMonthHeader).click();
			driver.findElement(ExtractReportsObjects._selectedYear(fromYearTRIAD)).click();
			driver.findElement(ExtractReportsObjects._selectedMonth(fromMonthTRIAD)).click();
			coachingMethods.clickElementPass(driver, ExtractReportsObjects._selectedDay(fromDayTRIAD), "HR PAM ADMIN / PROJECT ADMIN :STEP 3",
					fromMonthTRIAD + " " + fromDayTRIAD + " " + fromYearTRIAD, logger);
		} else
			coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 3- Date From filter doesn't exists", driver);

		// Select Date To in Other Coaching Data
		if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._dateFilterToTRIAD)) {
			driver.findElement(ExtractReportsObjects._dateFilterToTRIAD).click();
			driver.findElement(ExtractReportsObjects._clickDaysHeader).click();
			driver.findElement(ExtractReportsObjects._clickMonthHeader).click();
			driver.findElement(ExtractReportsObjects._selectedYear(toYearTRIAD)).click();
			driver.findElement(ExtractReportsObjects._selectedMonth(toMonthTRIAD)).click();
			coachingMethods.clickElementPass(driver, ExtractReportsObjects._selectedDay(toDayTRIAD), "HR PAM ADMIN / PROJECT ADMIN :STEP 4",
					toMonthTRIAD + " " + toDayTRIAD + " " + toYearTRIAD, logger);
		} else
			coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 4- Date To filter doesn't exists", driver);

		// Click Filter button
		if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._btnFilterTRIAD))
			coachingMethods.clickElementPass(driver, ExtractReportsObjects._btnFilterTRIAD, "HR PAM ADMIN / PROJECT ADMIN :STEP 5", "Filter button",
					logger);
		else
			coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 5 - Filter button doesn't exists", driver);

		// Select Show entries
		coachingMethods.scrollIntoView(driver, ExtractReportsObjects._drpShowEntriesTRIAD);
		if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._drpShowEntriesTRIAD)) {
			coachingMethods.selectVisibleTextPass(driver, ExtractReportsObjects._drpShowEntriesTRIAD, "HR PAM ADMIN / PROJECT ADMIN :STEP 6",
					showEntryTRIAD, "Show Entries", logger);
			coachingMethods.scrollIntoView(driver, ExtractReportsObjects._lblShowEntriesTRIAD);
			if (coachingMethods.waitForObjectExistence(driver,
					ExtractReportsObjects._lblResultEntriesTRIAD(showEntryTRIAD))) {
				String showEntries = driver.findElement(ExtractReportsObjects._lblShowEntriesTRIAD).getText();
				if (!showEntries.contains(showEntryTRIAD))
					coachingMethods.logInfo(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 7 - Number of data is less than or equal to 10");
				else
					coachingMethods.logPass(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 7 - " + showEntries, driver);
			} else {
				String showEntries = driver.findElement(ExtractReportsObjects._lblShowEntriesOther).getText();
				coachingMethods.logInfo(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 7 - " + showEntries);
			}
		} else
			coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 7 - Show Entries dropdown doesn't exists", driver);

		// Click Excel button in Performance Coaching Data
		coachingMethods.scrollIntoView(driver, ExtractReportsObjects._btnExcelTRIAD);
		if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._btnExcelTRIAD))
			coachingMethods.fileDownloadPass(driver, ExtractReportsObjects._btnExcelTRIAD, "Excel button", browser,
					"TRIAD Coaching Data export", "HR PAM ADMIN / PROJECT ADMIN :STEP 8", logger);
		else
			coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 8 - Excel button doesn't exists", driver);

		// Click CSV button in Performance Coaching Data
		if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._btnCSVTRIAD)) {
			coachingMethods.fileDownloadPass(driver, ExtractReportsObjects._btnCSVTRIAD, "CSV button", browser,
					"Coaching Dashboard - Reports", "HR PAM ADMIN / PROJECT ADMIN :STEP 9", logger);
		} else
			coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 9 - CSV button doesn't exists", driver);

		// Search
		coachingMethods.scrollIntoView(driver, ExtractReportsObjects._txtSearchTRIAD);
		if (coachingMethods.waitForObjectExistence(driver, ExtractReportsObjects._txtSearchTRIAD)) {
			coachingMethods.enterTextPass(driver, ExtractReportsObjects._txtSearchTRIAD, "HR PAM ADMIN / PROJECT ADMIN :STEP 10", searchTRIAD,
					"Search", logger);
		} else
			coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 10 - Search box doesn't exists", driver);

		// Click Page numbers
		coachingMethods.scrollIntoView(driver, ExtractReportsObjects._lblPaginationTRIAD);
		String entriesTRIAD = driver.findElement(ExtractReportsObjects._lblShowEntriesTRIAD).getText();
		coachingMethods.logInfo(logger, entriesTRIAD);
		List<WebElement> pages = driver.findElements(ExtractReportsObjects._lblPaginationTRIAD);
		if (pages.size() <= 3)
			coachingMethods.logInfo(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 11 - Entries is less than or equal to 10");
		else {
			for (int i = 1; i < pages.size() - 1; i++) {
				if (coachingMethods.elementExist(driver, ExtractReportsObjects._clickPageNumTRIAD(i))) {
					coachingMethods.clickElementPass(driver, ExtractReportsObjects._clickPageNumTRIAD(i), "HR PAM ADMIN / PROJECT ADMIN :STEP 11",
							"Page " + i, logger);
					Thread.sleep(2000);
				}
			}
			driver.findElement(ExtractReportsObjects._clickPageNumTRIAD(2)).click();
			WebElement prev = driver.findElement(ExtractReportsObjects._btnPrevTRIAD);
			if (prev.isEnabled())
				coachingMethods.clickElementPass(driver, ExtractReportsObjects._btnPrevTRIAD, "HR PAM ADMIN / PROJECT ADMIN :STEP 12",
						"Previous button", logger);
			else
				coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 12 - Previous button is disabled", driver);
			Thread.sleep(2000);
			WebElement next = driver.findElement(ExtractReportsObjects._btnNextTRIAD);
			if (next.isEnabled())
				coachingMethods.clickElementPass(driver, ExtractReportsObjects._btnNextTRIAD, "HR PAM ADMIN / PROJECT ADMIN :STEP 13", "Next button",
						logger);
			else
				coachingMethods.logFail(logger, "HR PAM ADMIN / PROJECT ADMIN :STEP 13- Next button is disabled", driver);
		}

	}
}
