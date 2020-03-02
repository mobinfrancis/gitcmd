package pageObjects;

import org.openqa.selenium.By;

public class MyCoachingSessionsObjects {

	public static By _linkMyCoachingSessions = By.xpath("//p[contains(text(),'My Coaching Sessions')]");
	public static By _btnReviewConfirm = By.xpath("(//a[contains(text(),'Review/Confirm')])[1]");
	public static By _btnReviewConfirm2 = By.xpath("(//a[text()='Review/Confirm'])[2]");
	public static By _btnReviewConfirm3 = By.xpath("(//a[text()='Review/Confirm'])[3]");
	public static By _getStatusName1 = By.xpath("//tr[1]/td[6]");
	public static By _getStatusName2 = By.xpath("//tr[2]/td[6]");
	public static By _getCoachingType1 = By.xpath("//tr[1]/td[3]");
	public static By _getCoachingType2 = By.xpath("//tr[2]/td[3]");
	public static By _isTRIAD = By.xpath("//input[@value='TRIAD']");

	public static By _lblCoachingForm = By.xpath("//*[@class='card-title' and contains(text(),'Coaching Form')]");
	public static By _textareaCoacheeNote = By.xpath("//textarea[@name='coachee_notes']");

	public static By _radAcknowledgement(String acknowledge) {
		return By.xpath("//input[@name='acknowledge' and @value='" + acknowledge + "']");
	}

	public static By _radFeedback(String fno, String ans) {
		return By.xpath("//input[@name='" + fno + "' and @value='" + ans + "']");
	}

	public static By _btnConfirm = By.xpath("//input[@value='Confirm']");
	public static By _btnConfirmTRIAD = By.name("submitConfirmTriad");
	public static By _btnDisputeTRIAD = By.name("submitDenyTriad");
	public static By _fdbckScrollHere = By.name("p1_3");
	public static By _fdbckScrollHereTRIAD = By.name("p3_2");
	public static By _myCoachSearch =By.xpath("//input[@type='search']");

}