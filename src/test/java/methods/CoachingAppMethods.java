package methods;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import driver.EnvironmentVariables;
import driver.GenericMethods2;
import pageObjects.LoginObjects;

public class CoachingAppMethods extends GenericMethods2 {

	// Login
	public void login(WebDriver driver, String userName, String password, ExtentTest logger) throws Exception {
		if (waitForObjectExistence(driver, LoginObjects.email)) {
			driver.findElement(LoginObjects.email).sendKeys(userName);
			driver.findElement(LoginObjects.nextBtn).click();
			if (waitForObjectExistence(driver, LoginObjects.password)) {
				driver.findElement(LoginObjects.password).sendKeys(password);
				driver.findElement(LoginObjects.signIn).click();
			}
			if (waitForObjectExistence(driver, LoginObjects.staySignedInNo))
				driver.findElement(LoginObjects.staySignedInNo).click();
		} else
			logFail(logger, "Login failed", driver);
	}

	// Privacy Statement Modal
	public void modalPrivacy(WebDriver driver, ExtentTest logger) throws Exception {
		if (waitForObjectExistence(driver, LoginObjects._modalPrivacy)) {
			driver.findElement(LoginObjects._btnCloseModal).click();
			logInfo(logger, "Privacy Statement modal is closed");
		} else
			logInfo(logger, "No modal appeared");
	}
	// Feedback and Self Recognition Modal
	public void modalFeedback(WebDriver driver, ExtentTest logger) throws InterruptedException {
		if (elementExist(driver, LoginObjects._modalFeedback)) {
		
		Thread.sleep(4000);
			driver.findElement(LoginObjects._btnCloseModalFeedback).click();
			logInfo(logger, "Culture Feedback  modal is closed");
		} else
			logInfo(logger, "No modal appeared");
	}

	// Click Info
	public void clickElementInfo(WebDriver driver, By locator, String objName, ExtentTest logger) {
		if (elementExist(driver, locator)) {
			driver.findElement((locator)).click();
			logInfo(logger, objName + " is clicked");

		} else
			logFail(logger, objName + " not found", driver);
	}

	// Click Pass
	public void clickElementPass(WebDriver driver, By locator, String reqNo, String objName, ExtentTest logger) throws InterruptedException {
		if (elementExist(driver, locator)) {
			driver.findElement((locator)).click();
			Thread.sleep(3000);
			logPass(logger, reqNo + " - " + objName + " is clicked");

		} else
			logFail(logger, reqNo + " - " + objName + " not found", driver);
	}

	// Enter Text Pass
	public void enterTextPass(WebDriver driver, By locator, String reqNo, String value, String objName,
			ExtentTest logger) {
		if (elementExist(driver, locator)) {
			driver.findElement(locator).sendKeys(value);
			logPass(logger, reqNo + " - " + value + " is entered in " + objName + " field.", driver);
		} else
			logFail(logger, reqNo + " - " + objName + " not found.", driver);
	}

	// Enter Text Info
	public void enterTextInfo(WebDriver driver, By locator, String value, String objName, ExtentTest logger) {
		if (elementExist(driver, locator)) {
			driver.findElement(locator).sendKeys(value);
			logInfo(logger, value + " is entered in " + objName + " field.");
		} else
			logFail(logger, objName + " not found.");
	}

	// Wait for Object Existence
	public boolean waitForObjectExistence(WebDriver driver, By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Select Pass
	public void selectVisibleTextPass(WebDriver driver, By locator, String reqNo, String value, String objName,
			ExtentTest logger) throws InterruptedException {
		if (elementExist(driver, locator)) {
			Select Option = new Select(driver.findElement(locator));
			Option.selectByVisibleText(value);
			Thread.sleep(3000);
			logPass(logger, reqNo + " - " + value + " is selected in " + objName + " dropdown field", driver);
		} else {
			logFail(logger, reqNo + " - " + objName + " not found", driver);
		}
	}

	// Select Info
	public void selectVisibleTextInfo(WebDriver driver, By locator, String value, String objName, ExtentTest logger) {
		if (elementExist(driver, locator)) {
			Select Option = new Select(driver.findElement(locator));
			Option.selectByVisibleText(value);
			logInfo(logger, value + " is selected in " + objName + " dropdown field");
		} else {
			logFail(logger, objName + " not found", driver);
		}
	}

	// Page Redirection Validation - Info
	public void pageRedirectionValidationInfo(WebDriver driver, By locator, String page, ExtentTest logger)
			throws Exception {
		if (waitForObjectExistence(driver, locator)) {
			logInfo(logger, "User is redirected to " + page + " page");
		} else
			logFail(logger, "User is not redirected to " + page + " page", driver);
	}

	// Page Redirection Validation - Pass
	public void pageRedirectionValidationPass(WebDriver driver, By locator, String reqNo, String page,
			ExtentTest logger) throws Exception {
		if (waitForObjectExistence(driver, locator)) {
			logPass(logger, reqNo + " - " + "User is redirected to " + page + " page");
		} else
			logFail(logger, reqNo + " - " + "User is not redirected to " + page + " page", driver);
	}

	// Popup Display Validation
	public void popupDisplayValidationInfo(WebDriver driver, By locator, String popup, ExtentTest logger)
			throws Exception {
		if (waitForObjectExistence(driver, locator)) {
			logInfo(logger, popup + " is displayed");
		} else
			logFail(logger, popup + " not displayed", driver);
	}

	// Download File
	public void fileDownloadPass(WebDriver driver, By downloadButton, String objName, String browser, String fileName,
			String reqNo, ExtentTest logger) throws Exception {
		clickElementInfo(driver, downloadButton, objName, logger);
		Thread.sleep(3000);
		if (browser.equalsIgnoreCase("FF")) {
			Runtime.getRuntime().exec(EnvironmentVariables._downloadFileFirefox);
			Thread.sleep(5000);
			verifyFileDownloaded(driver, fileName, reqNo, logger);
		} else
			verifyFileDownloaded(driver, fileName, reqNo, logger);
	}

	// Validate File is Downloaded
	public void verifyFileDownloaded(WebDriver driver, String file, String reqNo, ExtentTest logger) throws InterruptedException {
		File getLatestFile = getLatestFilefromDir(System.getProperty("user.home") + "\\Downloads");
		String fileName = getLatestFile.getName();
		Thread.sleep(3000);
		System.out.println("File Name = " + fileName);
		if (fileName.contains(file))
			logPass(logger, reqNo + " - " + file + " report is downloaded.");
		else
			logFail(logger, reqNo + "Downloaded file name, doesn't match with expected", driver);
	}

	/* Get the latest file from a specific directory */
	public File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	// Date Picker
	public void datePicker(WebDriver driver, String yearValue, By yearLocator, String monthValue, By monthLocator,
			By dayLocator, By nextButton) {
		// Select Year
		String yearStart = driver.findElement(yearLocator).getText();
		while (!yearStart.equals(yearValue)) {
			driver.findElement(nextButton).click();
			yearStart = driver.findElement(yearLocator).getText();
		}
		// Select Month
		String monthStart = driver.findElement(monthLocator).getText();
		while (!monthStart.equals(monthValue)) {
			driver.findElement(nextButton).click();
			monthStart = driver.findElement(monthLocator).getText();
		}
		// Select Day
		driver.findElement(dayLocator).click();
	}

	// Scroll into view
	public void scrollIntoView(WebDriver driver, By locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element = driver.findElement(locator);
		js.executeScript("arguments[0].scrollIntoView();", Element);
	}

	// Scroll into View Boolean
	public boolean scrollIntoViewBoolean(WebDriver driver, By locator) {
		boolean exist = false;
		try {
			scrollIntoView(driver, locator);
			exist = true;
		} catch (Exception e) {
			exist = false;
		}
		return exist;
	}

	// Popup Display Validation
	public void popupDisplayValidationPass(WebDriver driver, By locator, String reqNo, String popup, ExtentTest logger)
			throws Exception {
		if (waitForObjectExistence(driver, locator)) {
			logPass(logger, reqNo + popup + " is displayed");
		} else
			logFail(logger, reqNo + popup + " not displayed", driver);
	}
}