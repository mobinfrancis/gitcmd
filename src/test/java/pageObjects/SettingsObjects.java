package pageObjects;

import org.openqa.selenium.By;

public class SettingsObjects {

	public static class SettingsCommonObjects {
		public static final By _lnkSettings = By.xpath("//p[contains(text(),'Settings')]");
		public static final By _btnRosterManagement = By.xpath("//button[contains(text(),'Roster Management')]");
		public static final By _btnAccessSettings = By.xpath("//button[contains(text(),\"Access Setting\")]");
		public static final By _btnCoachingTargetSettings = By
				.xpath("//button[contains(text(),\"Coaching Target Settings\")]");
		public static final By _btnTowerSettings = By.xpath("//button[contains(text(),\"Tower Settings\")]");
		public static final By _btnAppUsage = By.xpath("//button[contains(text(),\"App Usage\")]");
		public static final By _btnPamSettings = By.xpath("//button[contains(text(),'Tower Settings')]");
		public final static By _lblSuccessInfo = By.xpath("//span[contains(text(),'Successfully Saved!')]");
		public final static By _extractCSV = By.xpath("//button[@name='exportCSV']");
		public final static By _extractExcel = By.xpath("//button[@name='exportXLS']");
	}

	public static class AccessSetting {
		public static final By _btnGrant = By.xpath("//button[contains(text(),\"Grant Access\")]");
		// grant pop up
		public static final By _popupGrantAccess = By.xpath("//h4[text()='Grant Access']");
		public static final By _drpSelectEID = By.xpath("//*[text()='Nothing selected']");

		public static final By _selEID(String EID) {
			return By.xpath("//a[@class=\"dropdown-item\"]//following::span[contains(text(),'" + EID + "')]");
		}

		public static final By _drpLevel = By.xpath("//select[@name='access']");
		public static final By _btnX = By.xpath("//h4[text()='Grant Access']//following-sibling::button");
		public static final By _btnSubmit = By.name("grant");
		// pop up success
		public static final By _popupSaved = By.xpath("//*[text()=\"Successfully Saved!\"]");
		// bulk upload
		public static final By _btnBulkUpload = By.xpath("//*[contains(text(),\"Bulk Upload\")]");
		// bulk upload pop up
		public static final By _popupBulkUpload = By.xpath("//button[contains(text(),\"Bulk Upload\")]");
		public static final By _linkDownloadTemplate = By.xpath("//a[contains(text(),\"Download Template\")]");
		public static final By _linkUploadTemplate = By.xpath("//input[@name='file']");
		public static final By _btnSubmitBulk = By.name("SaveMultipleAccess");
		public static final By _btnCLose = By.xpath("//h4[text()='Bulk Grant Access']//following-sibling::button");
		// reports
		public static final By _btnExcel = By.xpath("//button[@name='extractXLS']");
		public static final By _btnCSV = By.xpath("//button[@name='extractCSV']");
		// search
		public static final By _txtSearch = By.xpath("//*[@id=\"tblaccess_filter\"]/label/input");
		public static final By _tblSetting = By.id("tblaccess");

		// update
		public static final By _btnUpdate(String UpdateEID) {
			return By.xpath("//td[text()='"+UpdateEID+"']//following-sibling::td//button[text()='Update']");
		}

		public static final By _txtAccessLevel(String UpdateEID) {
			return By.xpath("//*[contains(text(),'" + UpdateEID + "')]//following::td[1]");
		}

		// update pop up
		public static final By _popupUpdate = By.xpath("//h4[text()='Edit Access']");
		public static final By _txtEID = By.name("eid");
		public static final By _selStatus = By.name("status");
		public static final By _selLevel = By
				.xpath("//label[text()='Access level: ']//following-sibling::select[@name='access']");
		public static final By _btnSaveClose = By.name("submitEditA");
		public static final By _btnClose = By.xpath("(//button[text()='Close'])[1]");
		public static final By _grantEID(String EID) {
			return By.xpath("//span[text()='" + EID + "']");
		}
	}

	public static class PamSetting {
		public static final By _btnAddEntry = By.xpath("//button[contains(text(),'Add Entry')]");
		// Add entry Pop up
		public static final By _popupAddEntry = By.xpath("//h4[text()='Add Entry']");
		public static final By _btnX = By.xpath("//h4[text()='Add Entry']//following-sibling::button");
		public static final By _selEID = By.xpath("//*[@name='eid']");
		public static final By _fullName = By.xpath("//input[@name='fullname']");
		public static final By _supervisor = By.xpath("//input[@name='manager']");
		public static final By _txtAttribute1 = By.name("a1");
		public static final By _txtAttribute2 = By.name("a2");
		public static final By _txtAttribute3 = By.name("a3");
		public static final By _selMonth = By.name("month");
		public static final By _selStatus = By.name("feedback");
		public static final By _btnSubmit = By.name("btnPAM");
		public static final By _txtSaved = By.xpath("//*[text()=\"Successfully Saved!\"]");
		public static final By _txtDuplicate = By
				.xpath("//*[text()=\"User being entered has a duplicate in the database.\"]");
		// bulk entry
		public static final By _btnBulkEntry = By.xpath("//button[contains(text(),'Bulk Entry')]");
		// bulk entry pop up
		public static final By _popupBulkEntry = By.xpath("//h4[text()='Bulk Entry']");
		public static final By _btnCloseBulk = By.xpath("//h4[text()='Bulk Entry']//following-sibling::button");
		public static final By _linkDownloadTemplate = By.xpath("//a[text()='Download Template']");
		public static final By _selFile = By.xpath("//input[@name='file']");
		public static final By _btnSubmitBulk = By.name("SaveMultipleUser");
		// table
		public static final By _tblPAM = By.xpath("//*[@id=\"tblPam\"]");
		// search
		public static final By _txtSearch = By.xpath("//*[@id='tblPam_filter']/label/input");
		// page number
		public static final By _lblPage = By.xpath("//div[@id='tblPam_paginate']//ul//li");

		public static By _PageNumber(int i) {
			return By.xpath("//a[contains(text(),'" + i + "')]");
		}

		// previous next
		public static final By _btnPrevious = By.xpath("//a[contains(text(),\"Previous\")]");
		public static final By _btnNext = By.xpath("//a[contains(text(),\"Next\")]");
		// reports
		public static final By _btnExcel = By.xpath("//button[@name='extractXLS']");
		public static final By _btnCSV = By.xpath("//button[@name='extractCSV']");
	}

	public static class Coaching {
		public static final By _btnAddEntry = By.xpath("//button[contains(text(),\"Add Entry\")]");
		public static final By _txtTarget = By.xpath("//*[@id=\"setTwoNumberDecimal\"]");
		public static final By _selTower = By.xpath("//select[@name=\"tower\"]");
		public static final By _btnSubmit = By.name("SaveTarget");

		public static final By _txtCoachingTarget(String Target) {
			return By.xpath("//td[text()='" + Target + "']");
		}

		public static final By _txtTower(String Tower) {
			return By.xpath("//td[text()='" + Tower + "']");
		}
	}

	public static class AppUsage {
		public static final By _selMonth = By.name("month");
		public static final By _selYear = By.name("year");
		public static final By _btnFilter = By.xpath("//*[@id=\"btnFilter\"]");
		public static final By _tblAppUsage = By.xpath("//*[@id=\"AppUsage\"]");
		public static final By _btnCSV = By.xpath("//span[contains(text(),\"CSV\")]");
		// page number
		public static final By _lblPage = By.xpath("//*[@id=\"tblAppUsage_paginate\"]//ul//li");

		public static By _PageNumber(int i) {
			return By.xpath("//a[contains(text(),'" + i + "')]");
		}

		// previous next
		public static final By _btnPrevious = By.xpath("//a[contains(text(),\"Previous\")]");
		public static final By _btnNext = By.xpath("//a[contains(text(),\"Next\")]");

		// search
		public static final By _txtSearch = By.xpath("//*[@id=\"tblAppUsage_filter\"]/label/input");
	}

	public static class RosterManagementObjects {
		public static class RosterManagementCommonObjects {
			public final static By _btnNext = By.xpath("//a[contains(text(),'Next')]");
			public final static By _btnPrevious = By.xpath("//li[@id='users_previous']");
			public final static By _txtSearch = By.xpath("//input[@class='form-control form-control-sm']");
			public final static By _btnExcel = By.xpath("//button[@name='exportXLS']");
			public final static By _btnCSV = By.xpath("//button[@name='exportCSV']");
			public final static By _dlPagination = By.xpath("//div[@id='users_paginate']//ul//li");
			public final static By _lblNoData = By.xpath("//td[@class='dataTables_empty']");
			public final static By _lblEntries = By.xpath("//div[@id='users_info']");

			public final static By _lblUser(String EID) {
				return By.xpath("//td[contains(text(),'" + EID + "')]");
			}

			public final static By _btnPagination(int pagination) {
				return By.xpath("//a[@class='page-link'][contains(text(),'" + pagination + "')]");
			}

			public final static By _lblEdit(String EID) {
				return By.xpath("//td[contains(text(),'" + EID + "')]");
			}
		}

		public static class AddSingleUser {
			public final static By _txtEID = By.xpath("//input[@name='eid']");
			public final static By _drpEmployeeStatus = By.xpath("//select[@name='status']");
			public final static By _txtFullName = By.xpath("//input[@name='fullname']");
			public final static By _txtRole = By.xpath("//input[@name='role']");
			public final static By _txtBusinessEmail = By.xpath("//input[@placeholder='@example.com']");
			public final static By _drpSiteLocation = By.xpath("//select[@name='SiteLoc']");
			public final static By _txtTeamLead = By.xpath("//div[@class='col-md-6']//form//input[@name='tl']");
			public final static By _txtTeamManager = By.xpath("//div[@class='col-md-6']//form//input[@name='tm']");
			public final static By _txtDeliveryLead = By.xpath("//div[@class='col-md-6']//form//input[@name='sdl']");
			public final static By _drpTower = By.xpath("//select[@id='tower']");
			public final static By _drpProject = By.xpath("//select[@id='project_select']");
			public final static By _drpSegment = By.xpath("//select[@id='segment_select']");
			public final static By _btnSubmit = By.xpath("//button[@name='SaveUser']");
		}

		public static class AddMultipleUsers {
			public final static By _lnkDownloadTemplate = By.xpath("//a[contains(text(),'Download Template')]");
			public final static By _btnChooseFile = By.xpath("//input[@name='file']");
			public final static By _btnSubmit = By.xpath("//button[@name='SaveMultipleUser']");
		}

		public static class EditUser {
			public final static By _lblMoreDetailsPopup = By.xpath("//h4[contains(text(),'Details')]");
			public final static By _btnMoreDetailsClose = By
					.xpath("//div[@id='details']//button[@class='btn btn-danger btn-block'][contains(text(),'Close')]");

			public final static By _btnEdit(String EID) {
				return By.xpath(
						"(//td[contains(text(),'" + EID + "')]/following::button[@class='btn btn-info btn-xs'])[2]");
			}

			public final static By _btnMoreDetails(String EID) {
				return By.xpath(
						"(//td[contains(text(),'" + EID + "')]/following::button[@class='btn btn-warning btn-xs'])[2]");
			}

			public final static By _lblEditPopup = By.xpath("//*[contains(text(),'Edit User')]");
			public final static By _txtNewEID = By.xpath("(//*[@name='eid'])[2]");
			public final static By _drpEmployeeStatus = By.xpath("(//select[@name='status'])[2]");
			public final static By _txtFullName = By.xpath("(//input[@name='fullname'])[2]");
			public final static By _txtRole = By.xpath("(//input[@name='role'])[2]");
			public final static By _txtBusinessEmail = By.xpath("(//*[@name='BEmail'])[2]");
			public final static By _drpSiteLocation = By.xpath("(//select[@name='SiteLoc'])[2]");
			public final static By _txtTeamLead = By.xpath("(//*[@name='tl'])[2]");
			public final static By _txtTeamManager = By.xpath("(//*[@name='tm'])[2]");
			public final static By _txtDeliveryLead = By.xpath("(//*[@name='sdl'])[2]");
			public final static By _drpTower = By.xpath("(//select[@name='tower'])[2]");
			public final static By _drpProject = By.xpath("//select[@name='project']");
			public final static By _drpSegment = By.xpath("//select[@name='segment']");
			public final static By _txtSegment = By.xpath("//input[@name='segment']");
			public final static By _txtRemarks = By.xpath("//textarea[@name='remarks']");
			public final static By _btnSaveAndClose = By.xpath("//button[contains(text(),'Save & Close')]");
			public final static By _btnEditClose = By
					.xpath("//div[@id='edit']//button[@class='btn btn-danger btn-block'][contains(text(),'Close')]");
		}
	}

	public static class TowerSettingsObjects {
		public static class TowerSettingsCommonObjects {
			public final static By _btnUpdate(String value) {
				return By.xpath("//td[contains(text(),'" + value + "')]/following::button[@title='Update']");
			}

			public final static By _btnDelete(String value) {
				return By.xpath("//td[contains(text(),'" + value + "')]/following::button[@title='Delete']");
			}

			public final static By _lblCreated(String value) {
				return By.xpath("//td[contains(text(),'" + value + "')]");
			}
		}

		public static class TowerObjects {
			public final static By _txtTower = By.xpath("//div[@class='col-md-3']//input[@placeholder='Enter here']");
			public final static By _btnSave = By.xpath("//button[@name='savetower']");
			public final static By _txtSearch = By
					.xpath("//div[@id='ttower_filter']//input[@class='form-control form-control-sm']");
			public final static By _lblEditTower = By.xpath("//h4[contains(text(),'Edit Tower')]");
			public final static By _txtTowerName = By.xpath("//div[@class='form-group']//input[@name='tower']");
			public final static By _btnSaveAndClose = By.xpath("//button[@name='updatetower']");
			public final static By _btnCloseEditTower = By
					.xpath("//div[@id='towerUp']//button[@class='btn btn-danger btn-block'][contains(text(),'Close')]");
			public final static By _lblRemoveTower = By.xpath("//h4[contains(text(),'Remove Tower')]");
			public final static By _btnDeleteAndClose = By.xpath("//button[contains(@name,'removetower')]");
			public final static By _btnCloseRemoveTower = By.xpath(
					"//div[@id='towerRe']//button[contains(@class,'btn btn-danger btn-block')][contains(text(),'Close')]");
		}

		public static class ProjectObjects {
			public final static By _drpTower = By.xpath("//select[contains(@name,'Ptower')]");
			public final static By _txtProject = By
					.xpath("//div[contains(@class,'col-md-4')]//input[contains(@placeholder,'Enter here')]");
			public final static By _btnSave = By.xpath("//button[contains(@name,'saveProject')]");
			public final static By _txtSearch = By
					.xpath("//div[@id='project_filter']//input[contains(@class,'form-control form-control-sm')]");
			public final static By _lblEditProject = By.xpath("//h4[contains(text(),'Edit Project')]");
			public final static By _txtProjectName = By
					.xpath("//div[contains(@class,'form-group')]//input[contains(@name,'project')]");
			public final static By _btnSaveAndClose = By.xpath("//button[contains(@name,'updateProject')]");
			public final static By _btnClose = By.xpath(
					"//div[@id='projectUp']//button[contains(@class,'btn btn-danger btn-block')][contains(text(),'Close')]");
			public final static By _lblDeleteProject = By.xpath("//h4[contains(text(),'Delete Project')]");
			public final static By _btnDeleteAndClose = By.xpath("//button[contains(@name,'removeProject')]");
			public final static By _btnCancel = By.xpath(
					"//div[@id='projectRem']//button[contains(@class,'btn btn-danger btn-block')][contains(text(),'Cancel')]");
		}

		public static class SegmentObjects {
			public final static By _drpTower = By.xpath("//select[@id='stower']");
			public final static By _drpProject = By.xpath("//select[@id='project_select']");
			public final static By _txtSegment = By
					.xpath("//div[@id='Segment']//input[contains(@placeholder,'Enter here')]");
			public final static By _btnSave = By.xpath("//button[contains(@name,'saveSegment')]");
			public final static By _txtSearch = By
					.xpath("//div[@id='segment_filter']//input[contains(@class,'form-control form-control-sm')]");
			public final static By _lblEditSegment = By.xpath("//h4[contains(text(),'Edit Segment')]");
			public final static By _txtSegmentName = By.xpath("//input[@name='segment']");
			public final static By _btnSaveAndClose = By.xpath("//button[@name='updateSegment']");
			public final static By _btnCancelEditSegment = By.xpath(
					"//div[@id='segmentUp']//button[@class='btn btn-danger btn-block'][contains(text(),'Cancel')]");

			public final static By _lblDeleteSegment = By.xpath("//h4[contains(text(),'Delete Segment')]");
			public final static By _btnDeleteAndClose = By.xpath("//button[contains(@name,'removeSegment')]");
			public final static By _btnCancelDeletSegment = By.xpath(
					" //div[@id='segmentRe']//button[contains(@class,'btn btn-danger btn-block')][contains(text(),'Cancel')]");
		}
	}
}
