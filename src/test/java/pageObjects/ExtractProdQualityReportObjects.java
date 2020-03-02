package pageObjects;

import org.openqa.selenium.By;

public class ExtractProdQualityReportObjects {

	public static class CoachingAppMenu {
		public final static By _lnkbtnExtractProdQuality = By
				.xpath("//p[contains(text(),'Extract Prod & Quality Reports')]");
	}

	public static class FilterSection {
		public final static By _drpMonth = By.xpath("//select[@name='month']");
		public final static By _drpYear = By.xpath("//select[@name='year']");
		public final static By _btnSubmit = By.xpath("//input[@name='dtFilter']");
	}

	public static class ProductivityReportsSection {
		public final static By _txtSearch = By
				.xpath("//div[@id='tblReports_filter']//input[@class='form-control form-control-sm']");
		public final static By _btnCSV = By.xpath("//div[@id='tblReports_wrapper']//span[contains(text(),'CSV')]");
		public final static By _btnExcel = By.xpath("//div[@id='tblReports_wrapper']//span[contains(text(),'Excel')]");
		public final static By _btnNext = By
				.xpath("//li[@id='tblReports_next']//a[@class='page-link'][contains(text(),'Next')]");
		public final static By _btnPrevious = By
				.xpath("//li[@id='tblReports_previous']//a[@class='page-link'][contains(text(),'Previous')]");
		public final static By _lblEntries = By.xpath("//div[@id='tblReports_info']");
		public final static By _dlPagination = By.xpath("//div[@id='tblReports_paginate']//ul//li");
		public final static By _lblNoData = By.xpath("//td[contains(text(),'No matching records found')]");

		public final static By _btnPagination(int pagination) {
			return By.xpath(
					"//div[@id='tblReports_paginate']//a[@class='page-link'][contains(text(),'" + pagination + "')]");
		}
	}

	public static class QualityReportsSection {
		public final static By _txtSearch = By
				.xpath("//div[@id='tblQuality_filter']//input[@class='form-control form-control-sm']");
		public final static By _btnCSV = By.xpath("//div[@id='tblQuality_wrapper']//span[contains(text(),'CSV')]");
		public final static By _btnExcel = By.xpath("//div[@id='tblQuality_wrapper']//span[contains(text(),'Excel')]");
		public final static By _btnNext = By.xpath("//li[@id='tblQuality_next']");
		public final static By _btnPrevious = By.xpath("//li[@id='tblQuality_previous']");
		public final static By _lblEntries = By.xpath("//div[@id='tblQuality_info']");
		public final static By _dlPagination = By.xpath("//div[@id='tblQuality_paginate']//ul//li");
		public final static By _lblNoData = By.xpath("//td[contains(text(),'No data available in table')]");

		public final static By _btnPagination(int pagination) {
			return By.xpath(
					"//div[@id='tblQuality_paginate']//a[@class='page-link'][contains(text(),'" + pagination + "')]");
		}
	}
}