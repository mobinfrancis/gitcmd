package pageObjects;

import org.openqa.selenium.By;

public class ScheduleCoachingSessionsObjects {

	public static By _linkScheduleCoachingSessions = By.xpath("//p[text()[contains(.,'Schedule Coaching Session')]]");
	public static By _msgSuccess = By.xpath("//span[text()='Successfully Saved!']");
	public static By _drpCoachee = By.xpath("//input[@name='coachee']");

	public static By _selCoachee(String coachee) {
		return By.xpath("//*[@id=\"eids\"]/option[@value='" + coachee + "']");
	}

	public static By _drpCoachingType = By.name("type");
	public static By _drpCoachForTRIAD = By.name("coachfortriad");
	public static By _txtLocation = By.name("location");
	public static By _chkRealtime = By.id("realtime");

	public static By _radDuration(String duration) {
		return By.xpath("//input[@name='duration' and @value='" + duration + "']");
	}

	public static By _txtDate = By.name("date");
	public static By _clickDaysHeader = By
			.xpath("(//div[@class='datepicker-days']//following::th[@class='datepicker-switch'])[1]");
	public static By _clickMonthHeader = By
			.xpath("(//div[@class='datepicker-months']//following::th[@class='datepicker-switch'])[1]");
	public static By _validateYear = By
			.xpath("(//div[@class='datepicker-days']//following::th[@class='datepicker-switch'])[1]");
	public static By _btnNextYear = By.xpath("(//div[@class='datepicker-days']//following::th[@class='next'])[1]");

	public static By _selectedDay(String day) {
		return By.xpath("//td[@class='day' and text()='" + day + "']");
	}

	public static By _drpStartTime = By.name("start_time");
	public static By _drpAMPM = By.name("AMPM");
	public static By _txtAgenda = By.name("agenda");
	public static By _btnSchedule = By.name("submitSched");
	public final static By _btnViewCoachingLogs = By.xpath("//p[contains(text(),'View Coaching Logs')]");

}