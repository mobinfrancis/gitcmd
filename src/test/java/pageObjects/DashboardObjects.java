package pageObjects;

import org.openqa.selenium.By;

public class DashboardObjects {

	public static By _iconProfile = By.xpath("//a[@href='./profile.php']");
	public static By _lblMyProfile = By.xpath("//*[@class='card-title' and contains(text(),'My Profile')]");
	public static By _logoOnlineCoachingApp = By.xpath("//a[contains(text(),'Online Coaching App')]");
	public static By _txtSearchBox = By.xpath("//input[@name='search']");
	public static By _iconNotification = By.xpath("//a[@id='navbarDropdownMenuLink']");
	public static By _drpNotification = By.id("notifRecieve");
	public static By _iconBookmark = By.xpath("//a[@id='sitesMenu']");
	public static By _drpBookmark = By.xpath("//div[@aria-labelledby='sitesMenu']");
	public static By _linkDashboard = By.xpath("//p[contains(text(),'Dashboard')]");
	public static By _lblOverallCompliance = By.xpath("//*[@class='card-title' and contains(text(),'Overall Compliance')]");
	public static By _selMonth = By.xpath("//select[@id='month']");
	public static By _selYear = By.xpath("//select[@id='year']");
	public static By _selTower = By.xpath("//select[@id='stower']");
	public static By _btnFilter = By.xpath("//input[@id='btnFilter']");
}
