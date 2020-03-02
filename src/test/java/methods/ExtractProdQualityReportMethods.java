package methods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

import pageObjects.ExtractProdQualityReportObjects.CoachingAppMenu;
import pageObjects.ExtractProdQualityReportObjects.FilterSection;
import pageObjects.ExtractProdQualityReportObjects.ProductivityReportsSection;
import pageObjects.ExtractProdQualityReportObjects.QualityReportsSection;

public class ExtractProdQualityReportMethods {

	CoachingAppMethods coachingMethods = new CoachingAppMethods();

	// ---------------------------------------Generic Methods for Extract Prod and Quality Report----------------------------------------------
	public void getEntry(WebDriver driver, By locator, ExtentTest logger) {
		String entry = driver.findElement(locator).getText();
		coachingMethods.logInfo(logger, entry);
	}

	// ---------------------------------------Extract Prod and Quality Report------------------------------------------------------------------
	public void extractProdQualityReport(WebDriver driver, String browser, String month, String year,
			String productivtyEID, String productivtyCSV, String productivtyExcel, String qualityEID, String qualityCSV,
			String qualityExcel, ExtentTest logger) throws Exception {

		// ---------------------------------------Filter Section------------------------------------
		// Click Extract Prod & Quality Report
		Thread.sleep(3000);
		coachingMethods.scrollIntoView(driver, CoachingAppMenu._lnkbtnExtractProdQuality);
		coachingMethods.clickElementPass(driver, CoachingAppMenu._lnkbtnExtractProdQuality, "FR 8.1",
				"Extract Prod & Quality Report Menu", logger);

		// Filter Functionality
		Thread.sleep(3000);
		coachingMethods.selectVisibleTextPass(driver, FilterSection._drpMonth, "PROJECT ADMIN : STEP 1", month, "Month", logger);
		coachingMethods.selectVisibleTextPass(driver, FilterSection._drpYear, "PROJECT ADMIN : STEP 2", year, "Year", logger);
		coachingMethods.clickElementInfo(driver, FilterSection._btnSubmit, "Submit", logger);
		if (!(coachingMethods.scrollIntoViewBoolean(driver, ProductivityReportsSection._lblNoData)
				&& (coachingMethods.scrollIntoViewBoolean(driver, QualityReportsSection._lblNoData)))) {
			coachingMethods.logPass(logger, "PROJECT ADMIN : STEP 3- Results for " + month + " " + year + " are dispalyed.");
		} else
			coachingMethods.logInfo(logger, "PROJECT ADMIN : STEP 3 - No matching records found.");

		// ----------------------------------Productivty Reports Section-------------------------------
		 // Download Reports
	   	coachingMethods.scrollIntoView(driver, ProductivityReportsSection._btnExcel);
		 coachingMethods.fileDownloadPass(driver,
		 ProductivityReportsSection._btnExcel, "Excel", browser,
		 productivtyExcel, "PROJECT ADMIN : STEP 4", logger);
		 coachingMethods.fileDownloadPass(driver, ProductivityReportsSection._btnCSV,
		 "CSV", browser, productivtyCSV,
		 "PROJECT ADMIN : STEP 5", logger);

		// Pagination Validation
		List<WebElement> paginationProductivityReports = driver.findElements(ProductivityReportsSection._dlPagination);
		if (paginationProductivityReports.size() > 3) {
			// Click Next Button
			coachingMethods.scrollIntoView(driver, ProductivityReportsSection._btnNext);
			coachingMethods.clickElementPass(driver, ProductivityReportsSection._btnNext, "PROJECT ADMIN : STEP 6", "Next", logger);
			getEntry(driver, ProductivityReportsSection._lblEntries, logger);

			// Click Previous Button
			coachingMethods.clickElementPass(driver, ProductivityReportsSection._btnPrevious, "PROJECT ADMIN : STEP 7", "Previous",
					logger);
			getEntry(driver, ProductivityReportsSection._lblEntries, logger);

			// Click Pagination Numbers
			for (int i = 1; i < paginationProductivityReports.size() - 1; i++) {
				coachingMethods.clickElementPass(driver, ProductivityReportsSection._btnPagination(i), "PROJECT ADMIN : STEP 8",
						"Page " + i, logger);
				getEntry(driver, ProductivityReportsSection._lblEntries, logger);
			}
		} else
			coachingMethods.logInfo(logger, "Entries are not more than 10.");

		// Search EID
		coachingMethods.scrollIntoView(driver, ProductivityReportsSection._txtSearch);
		coachingMethods.enterTextInfo(driver, ProductivityReportsSection._txtSearch, productivtyEID, "Search", logger);
		if (!coachingMethods.scrollIntoViewBoolean(driver, ProductivityReportsSection._lblNoData)) {
			coachingMethods.logPass(logger, "PROJECT ADMIN : STEP 9 - Productivity Reports for " + productivtyEID + " are displayed.");
		} else
			coachingMethods.logInfo(logger, "PROJECT ADMIN : STEP 9 - No matching records found.");

		// ---------------------------------Quality Reports Section--------------------------------
		// Download Reports
		coachingMethods.scrollIntoView(driver, QualityReportsSection._btnExcel);
		coachingMethods.fileDownloadPass(driver, QualityReportsSection._btnExcel, "Excel", browser, productivtyExcel,
				"PROJECT ADMIN : STEP 10", logger);
		coachingMethods.fileDownloadPass(driver, QualityReportsSection._btnCSV, "CSV", browser, productivtyCSV,
				"PROJECT ADMIN : STEP 11", logger);

		// Pagination Validation
		List<WebElement> paginationQualityReports = driver.findElements(QualityReportsSection._dlPagination);
		if (paginationQualityReports.size() > 3) {
			// Click Next Button
			coachingMethods.scrollIntoView(driver, QualityReportsSection._btnNext);
			coachingMethods.clickElementPass(driver, QualityReportsSection._btnNext, "PROJECT ADMIN : STEP 12", "Next", logger);
			getEntry(driver, QualityReportsSection._lblEntries, logger);

			// Click Previous Button
			coachingMethods.clickElementPass(driver, QualityReportsSection._btnPrevious, "PROJECT ADMIN : STEP 13", "Previous", logger);
			getEntry(driver, QualityReportsSection._lblEntries, logger);

			// Click Pagination Numbers
			for (int i = 1; i < paginationQualityReports.size() - 1; i++) {
				coachingMethods.clickElementPass(driver, QualityReportsSection._btnPagination(i), "PROJECT ADMIN : STEP 14",
						"Page " + i, logger);
				getEntry(driver, QualityReportsSection._lblEntries, logger);
			}
		} else
			coachingMethods.logInfo(logger, "Entries are not more than 10.");

		// Search EID
		coachingMethods.scrollIntoView(driver, QualityReportsSection._txtSearch);
		coachingMethods.enterTextInfo(driver, QualityReportsSection._txtSearch, qualityEID, "Search", logger);
		if (!coachingMethods.scrollIntoViewBoolean(driver, QualityReportsSection._lblNoData)) {
			coachingMethods.logPass(logger, "PROJECT ADMIN : STEP 15 - Quality Reports for " + qualityEID + " are displayed.");
		} else
			coachingMethods.logInfo(logger, "PROJECT ADMIN : STEP 15 - No matching records found.");
	}
}