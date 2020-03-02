package pageObjects;

import org.openqa.selenium.By;

public class LoginObjects {
	
	public final static By email = By.name("loginfmt");
	public final static By nextBtn = By.id("idSIButton9");
	public final static By password = By.id("passwordInput");
	public final static By signIn = By.id("submitButton");
	public final static By staySignedInNo = By.id("idBtn_Back");

	// Modal
	public static By _modalPrivacy = By.xpath("//div[@id='privacy']/div/div");
	public static By _btnCloseModal = By.xpath("//button[text()[contains(.,'Close')]]");
	public static By _modalFeedback = By.xpath("//div[@id='cultureFeedback']/div/div");
	public static By _btnCloseModalFeedback=By.xpath("//div[@id='cultureFeedback']/div/div//button[@class='close']");
}