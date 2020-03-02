package pageObjects;

import org.openqa.selenium.By;

public class ViewCoachingLogsObjects {
	
	public static class PerformancCoaching{
		public static final By _linkViewCoachingLog = By.xpath("//*[contains(text(),\"View Coaching Logs\")]");
		public static final By _txtSearch = By.xpath("//input[@type='search']");
		public static final By _tblCoachLogs = By.id("tblLogs_wrapper");
		public static final By _btnNext = By.id("tblLogs_next");		
		//Coach view page
		public static final By _txtPerformanceCoaching = By.xpath("//*[contains(text(),'Performance Coaching')]");
		public static final By _txtStatus = By.xpath("//*[contains(text(),'Performance Coaching')]//following::td[3]");
		public static final By _txtCoachingID = By.xpath("//*[contains(text(),'Performance Coaching')]//preceding::td[2]");
		public static final By _btnOpen (String CoachingType) {
			return By.xpath("//*[contains(text(),'"+CoachingType+"')]//following::a[1]");
		}
		public static final By _txtCoachingIDPending (String CoahingID) {
			return By.xpath("//*[contains(text(),\"Performance Coaching\")]//preceding::td[contains(text(),'"+CoahingID+"')]");
		}		
		public static final By _txtCoachEID = By.xpath("(//a[text()='View Profile'])[1]//parent::td");
		public static final By _btnCoachViewProfile = By.xpath("//a[@class=\"btn btn-primary btn-sm\"]");
		public static final By _txtCoachProfileEID = By.xpath("//*[@id=\"profile-reciever\"]/div /div[1]/div[1]/input");			
		//coachee view profile
		public static final By _txtCoacheeEID = By.xpath("(//a[text()='View Profile'])[2]//parent::td");
		public static final By _btnCoacheeViewProfile = By.xpath("//a[@class=\"btn btn-sm btn-primary\"]");
		public static final By _txtCoacheeProfileEID = By.xpath("//*[@id=\"profile-reciever\"]/div/div[1]/div[1]/input");	
		public static final By _txtOpening = By.name("coach_notes");
		public static final By _txtFocus = By.name("focus1");
		public static final By _txtFocusSLA = By.name("target1");
		public static final By _txtSpecificBehavior = By.name("behavior_issue1");
		public static final By _txtUnderstandBehavior = By.name("cause1");
		public static final By _txtAction1 = By.name("action1");
		public static final By _txtActionItem1 = By.xpath("(//textarea[@name=\"action_item1[]\"])[1]");
		public static final By _txtActionItem2 = By.xpath("(//textarea[@name=\"action_item1[]\"])[2]");
		public static final By _txtActionItem3 = By.xpath("(//textarea[@name=\"action_item1[]\"])[3]");		
		public static final By _txtCoacheeNotes = By.name("coachee_notes");		
		public static final By _chkboxIrritable = By.name("irritable");
		public static final By _chkboxBehavior = By.name("behavior");
		public static final By _chkboxApathy = By.name("apathy");
		public static final By _chkboxAttendance = By.name("attendance");
		public static final By _chkboxNegligence = By.name("negligence");		
		public static final By _radRed = By.xpath("//input[@value=\"Red\"]");
		public static final By _radAmber = By.xpath("//input[@value=\"Amber\"]");
		public static final By _radGreen = By.xpath("//input[@value=\"Green\"]");		
		public static final By _drpAmberAttrition = By.name("why");				
		public static final By _radCompany = By.xpath("//input[@value=\"Company\"]");
		public static final By _radOpportunities = By.xpath("//input[@value=\"Opportunities\"]");
		public static final By _radPeople = By.xpath("//input[@value=\"People\"]");
		public static final By _radWork = By.xpath("//input[@value=\"Work\"]");
		public static final By _radWorkEnvironment = By.xpath("//input[@value=\"Work Environment\"]");		
		public static final By _radYes = By.xpath("//input[@value=\"Yes\"]");		
		public static final By _btnConfirm = By.xpath("//*[@type=\"submit\"]");		
		public static final By _popupSaved = By.xpath("//span[contains(text(),\"Successfully Saved!\")]");			
	//	public static final By _tblLog = By.id("tblLogs");
		

	}
	
	public static class OtherCoaching{
		public static final By _linkViewCoachingLog = By.xpath("//*[contains(text(),\"View Coaching Logs\")]");
		public static final By _txtSearch = By.xpath("//input[@type='search']");
		public static final By _tblCoachLogs = By.id("tblLogs_wrapper");
		public static final By _btnNext = By.id("tblLogs_next");
		//Coach view page
		public static final By _txtOtherCoaching = By.xpath("//td[text()='Coaching']");
		public static final By _txtStatus = By.xpath("//td[text()='Coaching']//following::td[3]");
		public static final By _txtCoachingID = By.xpath("//td[text()='Coaching']//preceding::td[2]");
		public static final By _btnCoachViewProfile = By.xpath("//a[@class=\"btn btn-primary btn-sm\"]");
		public static final By _txtCoachProfileEID = By.xpath("//*[@id='profile-reciever']/div /div[1]/div[1]/input");	
		public static final By _txtCoachEID = By.xpath("(//a[text()='View Profile'])[1]//parent::td");
		public static final By _btnOpen (String CoachingType) {
			return By.xpath("//td[text()='"+CoachingType+"']//following::a[1]");
		}
		public static final By _txtCoachingIDPending (String CoahingID) {
			return By.xpath("//td[text()=\"Coaching\"]//preceding::td[contains(text(),'"+CoahingID+"')]");
		}
		public static final By _txtCoacheeEID = By.xpath("//table[@class='table']/tbody/tr[2]/td[4]/a");
		public static final By _btnCoacheeViewProfile = By.xpath("//a[@class=\"btn btn-sm btn-primary\"]");
		public static final By _txtCoacheeProfileEID = By.xpath("//*[@id=\"profile-reciever\"]/div/div[1]/div[1]/input");	
		
		//Coaching Notes
		public static final By _txtNotesFromCoachee = By.name("coachee_notes");
		public static final By _txtNotesFromCoach = By.name("coach_notes");
		public static final By _txtStrengths = By.name("Strengths");
		public static final By _txtDevelopments = By.name("AfD");
		public static final By _txtActionPlan = By.name("AP");
		public static final By _txtCommitment = By.name("Commitment");
		
		public static final By _txtCoacheeNotes = By.name("coachee_notes");		
		public static final By _chkboxIrritable = By.name("irritable");
		public static final By _chkboxBehavior = By.name("behavior");
		public static final By _chkboxApathy = By.name("apathy");
		public static final By _chkboxAttendance = By.name("attendance");
		public static final By _chkboxNegligence = By.name("negligence");		
		public static final By _radRed = By.xpath("//input[@value=\"Red\"]");
		public static final By _radAmber = By.xpath("//input[@value=\"Amber\"]");
		public static final By _radGreen = By.xpath("//input[@value=\"Green\"]");		
		public static final By _drpAmberAttrition = By.name("why");				
		public static final By _radCompany = By.xpath("//input[@value=\"Company\"]");
		public static final By _radOpportunities = By.xpath("//input[@value=\"Opportunities\"]");
		public static final By _radPeople = By.xpath("//input[@value=\"People\"]");
		public static final By _radWork = By.xpath("//input[@value=\"Work\"]");
		public static final By _radWorkEnvironment = By.xpath("//input[@value=\"Work Environment\"]");		
		public static final By _radYes = By.xpath("//input[@value=\"Yes\"]");		
		public static final By _btnConfirm = By.xpath("//*[@type=\"submit\"]");		
		public static final By _popupSaved = By.xpath("//span[contains(text(),\"Successfully Saved!\")]");
		
		
	}
	
	public static class TRIAD{
		public static final By _linkViewCoachingLog = By.xpath("//*[contains(text(),\"View Coaching Logs\")]");
		public static final By _txtSearch = By.xpath("//input[@type='search']");
		public static final By _tblCoachLogs = By.id("tblLogs_wrapper");
		public static final By _txtTRIAD = By.xpath("//td[text()='TRIAD']");
		public static final By _txtStatus = By.xpath("//td[text()='TRIAD']//following::td[3]");
		public static final By _txtCoachingID = By.xpath("//td[text()='TRIAD']//preceding::td[2]");
		public static final By _btnOpen (String CoachingType) {
			return By.xpath("//td[text()='"+CoachingType+"']//following::a[1]");
		}
		public static final By _txtCoachingIDPending (String CoahingID) {
			return By.xpath("//td[text()=\"TRIAD\"]//preceding::td[contains(text(),'"+CoahingID+"')]");
		}	
		
		public static final By _radYesNo (String num,String YesNo){
			return By.xpath("//input[@name='"+num+"' and @value='"+YesNo+"']");
		}
		
		public static final By _btnX = By.xpath("/html/body/div[3]/button/i");	
	}

	public static class CoachingLogs{	
		public static final By _linkViewCoachingLog = By.xpath("//*[contains(text(),\"View Coaching Logs\")]");
		public static final By _txtSearch = By.xpath("//input[@type='search']");
		public static final By _tblCoachLogs = By.id("tblLogs_wrapper");
		public static final By _txtID = By.xpath("//*[@id='tblreciever']/tr[1]/td[1]");
		public static final By _linkResched (String ID) {
			return By.xpath("//*[contains(text(),'"+ID+"')]//following::a[3]");
		}
		public static final By _popUpResched = By.xpath("//h4[text()='Reschedule Coaching Session']");	
		
		//status
		public static final By _txtStatus (String CoachingID) {
			return By.xpath("//*[contains(text(),'"+CoachingID+"')]//following::td[5]");
		}
		//date
		public static final By _drpDate = By.id("date");
		//date table
		public static final By _tblDate = By.xpath("//div[@class='datepicker-days']/table");
		public static final By _btnMonthYear = By.xpath("//div[@class='datepicker-days']//following::th[@class='datepicker-switch'][1]");
		public static final By _tblYear = By.xpath("((//table[@class=\"table-condensed\"])[3]//following::td[@colspan=\"7\"])[1]");
		public static final By _btnNext = By.xpath("(//div[@class=\"datepicker-days\"]//following::th[@class=\"next\"])[1]");	
		public static final By _Day (String Day) {
			return By.xpath("//div[@class=\"datepicker-days\"]//following::td[text()='"+Day+"' and @class=\"day\"]");
	}		
		//time
		public static final By _drpStartTime = By.name("start_time");
		public static final By _drpAmPm = By.name("AMPM");
		//rad
		public static final By _rad1hour = By.xpath("//input[@value=\"1\"]");
		public static final By _rad30Min = By.xpath("//input[@value=\"30\"]");
		public static final By _btnResched = By.xpath("//input[@value=\"Resched\"]");
		public static final By _btnX = By.xpath("//*[@id=\"reschedModalasdaqwe\"]/div/div/div[1]/button");
		public static final By _txtDate (String ID) {
			return By.xpath("//*[contains(text(),'"+ID+"')]//following::td[3]");
		}	
		public static final By _linkCancel (String ID) {
			return By.xpath("//*[contains(text(),'"+ID+"')]//following::a[2]");
		}	
		public static final By _drpFilter = By.xpath("//*[@id=\"filter\"]");	
		
	}	
		

}
