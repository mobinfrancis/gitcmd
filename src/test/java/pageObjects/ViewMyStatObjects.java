package pageObjects;

import org.openqa.selenium.By;

public class ViewMyStatObjects {

	public static By _linkViewMyStats = By.xpath("//p[contains(text(),'View My Stats')]");
	public static By _lblMyStats = By.xpath("//*[@class='card-title' and contains(text(),'My Stats')]");
}