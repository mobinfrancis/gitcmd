package pageObjects;

import org.openqa.selenium.By;

public class ExtractReportsObjects {

	public static By _linkExtractReports = By.xpath("//p[contains(text(),'Extract Reports')]");
	public static By _lblPerformanceData = By.xpath("//h4[@class='card-title' and text()='Performance Coaching Data']");
	public static By _noData = By.xpath("//td[contains(text(),'No data available in table')]");
	public static By _clickDaysHeader = By
			.xpath("(//div[@class='datepicker-days']//following::th[@class='datepicker-switch'])[1]");
	public static By _clickMonthHeader = By
			.xpath("(//div[@class='datepicker-months']//following::th[@class='datepicker-switch'])[1]");

	public static By _selectedYear(String year) {
		return By.xpath("//div[@class='datepicker-years']//following::*[contains(text(),'" + year + "')][2]");
	}

	public static By _selectedMonth(String month) {
		return By.xpath("//span[text()='" + month + "']");
	}

	public static By _selectedDay(String day) {
		return By.xpath("//td[@class='day' and text()='"+day+"']");
	}

	// Performance Coaching Data
	public static By _dateCurrent1Perf = By.xpath("//table[@id='tblReports']//following::td[3]");
	public static By _dateCurrent2Perf = By.xpath("//table[@id='tblReports']//following::td[10]");
	public static By _dateCurrent3Perf = By.xpath("//table[@id='tblReports']//following::td[12]");
	public static By _dateFilterFromPerf = By.xpath("//input[@name='from']");
	public static By _dateFilterToPerf = By.xpath("//input[@name='to']");
	public static By _btnFilterPerf = By.name("dtFilter");
	public static By _drpShowEntriesPerf = By.name("tblReports_length");

	public static By _lblResultEntriesPerf(String showingEntries) {
		return By.xpath("//div[@id='tblReports_info' and contains(text(),'" + showingEntries + "')]");
	}

	public static By _lblShowEntriesPerf = By.xpath("//div[@id='tblReports_info']");
	public static By _btnExcelPerf = By
			.xpath("//button[@class='btn btn-secondary buttons-excel buttons-html5' and @aria-controls='tblReports']");
	public static By _btnCSVPerf = By
			.xpath("//button[@class='btn btn-secondary buttons-csv buttons-html5' and @aria-controls='tblReports']");
	public static By _txtSearchPerf = By.xpath("//input[@type='search' and @aria-controls='tblReports']");
	public static By _lblPaginationPerf = By.xpath("//*[@id=\"tblReports_paginate\"]/ul/li");

	public static By _clickPageNumPerf(int pageNum) {
		return By.xpath("//a[@aria-controls='tblReports' and @data-dt-idx='" + pageNum + "']");
	}

	public static By _btnPrevPerf = By.xpath("//li[@id='tblReports_previous']");
	public static By _btnNextPerf = By.xpath("//li[@id='tblReports_next']");

	// Other Coaching Data
	public static By _dateCurrent1Other = By.xpath("(//table[@id='tblReportOther']//following::td[3])[1]");
	public static By _dateCurrent2Other = By.xpath("(//table[@id='tblReportOther']//following::td[10])[1]");
	public static By _dateCurrent3Other = By.xpath("(//table[@id='tblReportOther']//following::td[12])[1]");
	public static By _dateFilterFromOther = By.xpath("//input[@name='from1']");
	public static By _dateFilterToOther = By.xpath("//input[@name='to1']");
	public static By _btnFilterOther = By.name("dtFilter1");
	public static By _drpShowEntriesOther = By.name("tblReportOther_length");

	public static By _lblResultEntriesOther(String showingEntries) {
		return By.xpath("//div[@id='tblReportOther_info' and contains(text(),'" + showingEntries + "')]");
	}

	public static By _lblShowEntriesOther = By.xpath("//div[@id='tblReportOther_info']");
	public static By _btnExcelOther = By.xpath(
			"//button[@class='btn btn-secondary buttons-excel buttons-html5' and @aria-controls='tblReportOther']");
	public static By _btnCSVOther = By.xpath(
			"//button[@class='btn btn-secondary buttons-csv buttons-html5' and @aria-controls='tblReportOther']");
	public static By _txtSearchOther = By.xpath("//input[@type='search' and @aria-controls='tblReportOther']");
	public static By _lblPaginationOther = By.xpath("//*[@id='tblReportOther_paginate']/ul/li");

	public static By _clickPageNumOther(int pageNum) {
		return By.xpath("//a[@aria-controls='tblReportOther' and @data-dt-idx='" + pageNum + "']");
	}

	public static By _btnPrevOther = By.xpath("//li[@id='tblReportOther_previous']");
	public static By _btnNextOther = By.xpath("//li[@id='tblReportOther_next']");

	// TRIAD Coaching Data
	public static By _dateCurrent1TRIAD = By.xpath("//table[@id='tblTRIAD']//following::td[3]");
	public static By _dateCurrent2TRIAD = By.xpath("//table[@id='tblTRIAD']//following::td[10]");
	public static By _dateCurrent3TRIAD = By.xpath("//table[@id='tblTRIAD']//following::td[12]");
	public static By _dateFilterFromTRIAD = By.xpath("//input[@name='from3']");
	public static By _dateFilterToTRIAD = By.xpath("//input[@name='to3']");
	public static By _btnFilterTRIAD = By.name("dtFilter3");
	public static By _drpShowEntriesTRIAD = By.name("tblTRIAD_length");

	public static By _lblResultEntriesTRIAD(String showingEntries) {
		return By.xpath("//div[@id='tblTRIAD_info' and contains(text(),'" + showingEntries + "')]");
	}

	public static By _lblShowEntriesTRIAD = By.xpath("//div[@id='tblTRIAD_info']");
	public static By _btnExcelTRIAD = By
			.xpath("//button[@class='btn btn-secondary buttons-excel buttons-html5' and @aria-controls='tblTRIAD']");
	public static By _btnCSVTRIAD = By
			.xpath("//button[@class='btn btn-secondary buttons-csv buttons-html5' and @aria-controls='tblTRIAD']");
	public static By _txtSearchTRIAD = By.xpath("//input[@type='search' and @aria-controls='tblTRIAD']");
	public static By _lblPaginationTRIAD = By.xpath("//*[@id='tblTRIAD_paginate']/ul/li");

	public static By _clickPageNumTRIAD(int pageNum) {
		return By.xpath("//a[@aria-controls='tblTRIAD' and @data-dt-idx='" + pageNum + "']");
	}

	public static By _btnPrevTRIAD = By.xpath("//li[@id='tblTRIAD_previous']");
	public static By _btnNextTRIAD = By.xpath("//li[@id='tblTRIAD_next']");

}