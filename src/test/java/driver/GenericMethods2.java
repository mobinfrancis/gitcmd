package driver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.naming.directory.NoSuchAttributeException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GenericMethods2 {
	/**
	 * Prints the custom message in the Extent reports output - Pass
	 * 
	 * @param logger
	 *            - ExtentTest Logger
	 * @param msg
	 *            - Message
	 */
	public void logPass(ExtentTest logger, String msg) {
		logger.log(LogStatus.PASS, msg);
	}

	/**
	 * Prints the custom message in the Extent reports output with screenshot -
	 * Pass
	 * 
	 * @param logger-
	 *            Extent logger
	 * @param msg
	 *            - Message
	 * @param driver
	 *            - WebDriver
	 */
	public void logPass(ExtentTest logger, String msg, WebDriver driver) {
		logPass(logger, msg);
		String screeshotPath = captureScreenshot(driver);
		String image = logger.addScreenCapture(screeshotPath);
		logger.log(LogStatus.PASS, msg, image);
	}

	/**
	 * Clicks on a Button
	 * 
	 * @param driver
	 *            - Webdriver
	 * @param locator
	 *            - Object locator
	 * @param buttonName
	 *            - Button Name
	 */
	public void clickButton(WebDriver driver, By locator, String buttonName, ExtentTest logger) {
		try {
			WebElement button = driver.findElement(locator);
			if (!(button == null)) {
				// JavascriptExecutor js = (JavascriptExecutor) driver;
				// js.executeScript("arguments[0].click();", button);
				button.click();
				logInfo(logger, "Clicked on Button : " + buttonName);
			} else
				logFail(logger, buttonName + " not found", driver);
		} catch (Exception e) {
			logFail(logger, buttonName + " not found. Exception thrown is : " + e.getMessage(), driver);
		}
	}

	/***
	 * to mouse hover on element
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param locator
	 *            - Object locator
	 * @param objName
	 *            - Object Name
	 * @return
	 */
	public void mouseHover(WebDriver driver, By locator, String objName, ExtentTest logger) {
		System.out.println("The locator is : ");

		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			Thread.sleep(2000);
			Actions action = new Actions(driver);
			// get web element
			WebElement we = driver.findElement(locator);

			// mouse hover on locator
			action.moveToElement(we).click().build().perform();
			logInfo(logger, "Mouse hoverd on the Object : " + objName);

		} catch (TimeoutException e) {
			logFail(logger, "Object is not present " + objName + " Timeout Exception is occured : " + e.getMessage(),
					driver);

		} catch (ElementNotVisibleException e) {
			logFail(logger, "Object is not visible " + objName + " Exception is " + e.getMessage(), driver);

		} catch (ElementNotFoundException e) {
			logFail(logger, "Object is found " + objName + " Exception is " + e.getMessage(), driver);
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Prints the custom message in the Extent reports output - Info
	 * 
	 * @param logger
	 *            - ExtentTest Logger
	 * @param msg
	 *            - Message
	 */
	public void logInfo(ExtentTest logger, String msg) {
		logger.log(LogStatus.INFO, msg);
	}

	/**
	 * Prints the custom message in the Extent reports output with screenshot -
	 * Info
	 * 
	 * @param logger-
	 *            Extent logger
	 * @param msg
	 *            - Message
	 * @param driver
	 *            - WebDriver
	 */
	public void logInfo(ExtentTest logger, String msg, WebDriver driver) {
		logInfo(logger, msg);
		String screeshotPath = captureScreenshot(driver);
		String image = logger.addScreenCapture(screeshotPath);
		logger.log(LogStatus.INFO, msg, image);
	}

	/**
	 * Prints the custom message in the Extent reports output with screenshot -
	 * Fail
	 * 
	 * @param logger-
	 *            Extent logger
	 * @param msg
	 *            - Message
	 * @param driver
	 *            - WebDriver
	 */
	public void logFail(ExtentTest logger, String msg, WebDriver driver) {
		logFail(logger, msg);
		String screeshotPath = captureScreenshot(driver);
		String image = logger.addScreenCapture(screeshotPath);
		logger.log(LogStatus.FAIL, msg, image);
	}

	/**
	 * Prints the custom message in the Extent reports output - Fail
	 * 
	 * @param logger
	 *            - ExtentTest Logger
	 * @param msg
	 *            - Message
	 */
	public void logFail(ExtentTest logger, String msg) {
		logger.log(LogStatus.FAIL, msg);
	}

	/***
	 * This method used to Wait for an object to be located in the UI
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param object
	 *            - Object, which needs to be located
	 */
	public void waitForObject(WebDriver driver, By object) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(object));
		wait.until(ExpectedConditions.visibilityOfElementLocated(object));
	}

	public WebElement waitForAndReturnObject(WebDriver driver, By object) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(object));
		return ele;
	}

	/**
	 * This method used to read the excel data and store it two dimensional
	 * Array
	 * 
	 * @param fileName
	 *            - Where the file located in project
	 * @param sheetName
	 *            - Sheet, where data needs to be extracted
	 * @return
	 */
	public String[][] getExcelData(String fileName, String sheetName) {
		String[][] arrayExcelData = null;
		org.apache.poi.ss.usermodel.Workbook tempWB;

		try {

			if (fileName.contains(".xlsx")) {
				tempWB = new XSSFWorkbook(fileName);
			} else {
				InputStream inp = new FileInputStream(fileName);
				tempWB = new HSSFWorkbook(new POIFSFileSystem(inp));
			}

			org.apache.poi.ss.usermodel.Sheet sheet = tempWB.getSheet(sheetName);

			// Total rows counts the top heading row
			int totalNoOfRows = sheet.getLastRowNum();
			System.out.println("The total rows are : " + totalNoOfRows);
			Row row = sheet.getRow(0);
			int totalNoOfCols = row.getLastCellNum();
			System.out.println("The total colums are : " + totalNoOfCols);

			arrayExcelData = new String[totalNoOfRows][totalNoOfCols];

			try {
				for (int i = 1; i < totalNoOfRows + 1; i++) {

					for (int j = 0; j < totalNoOfCols; j++) {
						row = sheet.getRow(i);
						try {
							System.out.println(row.getCell(j).toString().trim());
							System.out.println(
									"The cell values are : " + i + "," + j + ": " + row.getCell(j).toString().trim());
							arrayExcelData[i - 1][j] = row.getCell(j).toString().trim();
						} catch (Exception e) {
							arrayExcelData[i - 1][j] = "";
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		return arrayExcelData;
	}

	/**
	 * This method used to find the Column Index in excel sheet, depends on the
	 * column name
	 * 
	 * @param sheet
	 *            - Excel sheet, where needs to find the column
	 * @param ColName
	 *            - Col Name
	 * @return
	 */
	public int findCol(Sheet sheet, String ColName) {
		Row row = null;
		int colCount = 0;

		row = sheet.getRow(0);
		if (!(row == null)) {
			colCount = row.getLastCellNum();
		} else
			colCount = 0;

		for (int j = 0; j < colCount; j++) {
			if (!(row.getCell(j) == null)) {
				if (row.getCell(j).toString().trim().equalsIgnoreCase(ColName)
						|| row.getCell(j).toString().trim().equalsIgnoreCase((ColName + "[][String]"))) {
					return j;
				}
			}
		}
		return -1;
	}

	/***
	 * This method used to get the value from the excel sheet
	 * 
	 * @param SheetName
	 *            - Sheet, where data needs to extracted
	 * @param colName
	 *            - Column Name
	 * @param rowNo
	 *            - Row number, at which data needs to extracted
	 * @return
	 */
	public String getValueFromDatasheet(String SheetName, String colName, int rowNo, ExtentTest logger,
			WebDriver driver) {
		try {
			Workbook tempWB;
			String value = "";
			if (EnvironmentVariables._dataPoolPath.contains(".xlsx"))
				tempWB = new XSSFWorkbook(EnvironmentVariables._dataPoolPath);

			else {
				FileInputStream inp = new FileInputStream(EnvironmentVariables._dataPoolPath);
				tempWB = new HSSFWorkbook(new POIFSFileSystem(inp));
			}

			Sheet sheet = tempWB.getSheet(SheetName);
			Row row = sheet.getRow(rowNo);

			if (row == null) {
				return null;
			}
			try {
				value = row.getCell(findCol(sheet, colName)).toString().trim();
				return value;
			} finally {
			}
		} catch (FileNotFoundException e) {
			logFail(logger, "File not found in the path : " + EnvironmentVariables._dataPoolPath, driver);
		} catch (IOException e) {
			logFail(logger, "Problem in reading the File", driver);
		}
		return null;
	}

	/**
	 * To select the list value from the dropdown using the list value
	 * 
	 * @param driver
	 *            - Webdriver
	 * @param locator
	 *            - Object locator
	 * @param lstValue
	 *            - List value to be select
	 * @param dropdownName
	 *            - Dropdown Name
	 */
	public void selectValueFromList(WebDriver driver, By locator, String lstValue, String dropdownName,
			ExtentTest logger) {
		try {
			WebElement elm = driver.findElement(locator);
			if (!(elm == null)) {
				Select s = new Select(elm);
				s.selectByValue(lstValue);
				logInfo(logger, "Selected the list value : " + lstValue);
			} else
				logFail(logger, "Element not found : " + dropdownName, driver);

		} catch (Exception e) {
			logFail(logger, "Element not found : " + dropdownName + "Exception thrown is : " + e.getMessage(), driver);
		}
	}

	/**
	 * To select the list value from the dropdown using the list value
	 * 
	 * @param driver
	 *            - Webdriver
	 * @param locator
	 *            - Object locator
	 * @param lstValue
	 *            - List value to be select
	 * @param dropdownName
	 *            - Dropdown Name
	 */
	public void selectValueFromListByText(WebDriver driver, By locator, String lstValue, String dropdownName,
			ExtentTest logger) {
		try {
			WebElement elm = driver.findElement(locator);
			if (!(elm == null)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", elm);
				// elm.click();
				Select s = new Select(elm);
				s.selectByVisibleText(lstValue);
				logInfo(logger, "Selected the list value : " + lstValue);
			} else
				logFail(logger, "Element not found : " + dropdownName, driver);

		} catch (Exception e) {
			if (!(e.getMessage().contains("stale element reference"))) {
				logFail(logger, "Element not found : " + dropdownName + "Exception thrown is : " + e.getMessage(),
						driver);
			}
			System.out.println("Exception thrown is : " + e.getMessage());
		}
	}

	/**
	 * To select the list value from the dropdown using the list value
	 * 
	 * @param driver
	 *            - Webdriver
	 * @param locator
	 *            - Object locator
	 * @param lstIndex
	 *            - Index of the list value to be select
	 * @param dropdownName
	 *            - Dropdown Name
	 */
	public void selectValueFromList(WebDriver driver, By locator, int lstIndex, String dropdownName,
			ExtentTest logger) {
		try {
			WebElement elm = driver.findElement(locator);
			if (!(elm == null)) {
				Select s = new Select(elm);
				s.selectByIndex(lstIndex);
				logInfo(logger, "Selected the list value at index : " + lstIndex);
			} else {
				logFail(logger, "Element not found : " + dropdownName, driver);
			}
		} catch (Exception e) {
			logFail(logger, "Element not found : " + dropdownName + "Exception thrown is : " + e.getMessage(), driver);
		}
	}

	/**
	 * Enters the text in the text field
	 * 
	 * @param driver
	 *            - Webdriver
	 * @param locator
	 *            - Object locator
	 * @param text
	 *            - String that needs to be entered
	 * @param objName
	 *            - Object Name
	 */
	public void enterText(WebDriver driver, By locator, String text, String objName, ExtentTest logger) {
		try {
			WebElement elm = driver.findElement(locator);
			if (!(elm == null)) {
				elm.sendKeys(text);
				logInfo(logger, "Successfully entered the text " + text + " in the field :" + objName);
			} else
				logFail(logger, "Element not found", driver);
		} catch (ElementNotFoundException e) {
			logFail(logger, "Element not found", driver);
		} catch (InvalidElementStateException e) {
			logFail(logger, "Element is not proper state to Enter.Element is disabled", driver);
		} catch (Exception e) {
			logFail(logger, "Other Exceptions : " + e.getMessage(), driver);
		}
	}

	/**
	 * Clicks on a link
	 * 
	 * @param driver
	 *            - Webdriver
	 * @param locator
	 *            - Object locator
	 * @param linkName
	 *            - Link Name
	 */
	public void clickLink(WebDriver driver, By locator, String linkName, ExtentTest logger) {
		try {
			WebElement link = driver.findElement(locator);
			if (!(link == null)) {
				link.click();
				// link.sendKeys(Keys.ENTER);
				logInfo(logger, "Clicked on link : " + linkName);
			} else
				logFail(logger, linkName + " not found", driver);
		} catch (Exception e) {
			logFail(logger, linkName + " not found. Exception thrown is : " + e.getMessage(), driver);
		}
	}

	/***
	 * Waits for the object to be present
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param locator
	 *            - Object locator
	 * @param objName
	 *            - Object Name
	 * @return
	 */
	public boolean waitForObjectExistence(WebDriver driver, By locator, String objName, ExtentTest logger) {
		System.out.println("The locator is : ");

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			Thread.sleep(2000);
			logInfo(logger, "Object is present : " + objName);
			System.out.println("The locator is : " + objName);
			return true;
		} catch (TimeoutException e) {
			logFail(logger, "Object is not present " + objName + " Timeout Exception is occured : " + e.getMessage(),
					driver);
			return false;
		} catch (ElementNotVisibleException e) {
			logFail(logger, "Object is not visible " + objName + " Exception is " + e.getMessage(), driver);
			return false;
		} catch (ElementNotFoundException e) {
			logFail(logger, "Object is found " + objName + " Exception is " + e.getMessage(), driver);
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/***
	 * Selects the Check box
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param locator
	 *            - Object locator
	 * @param checkBoxName
	 *            - Check box Name
	 */
	public void selectCheckBox(WebDriver driver, By locator, String checkBoxName, ExtentTest logger) {
		try {
			WebElement chkBox = driver.findElement(locator);
			if (!(chkBox == null)) {
				chkBox.click();
				logInfo(logger, "Selected the Checkbox : " + checkBoxName);
			} else
				logFail(logger, "Not sSelected the Checkbox : " + checkBoxName, driver);
		} catch (ElementNotFoundException e) {
			logFail(logger, "Object not found : " + e.getMessage(), driver);
		}

		catch (Exception e) {
			logFail(logger, "Exception is thrown : " + e.getMessage(), driver);
		}
	}

	/**
	 * To retrieve the element Property
	 * 
	 * @param driver
	 *            - WebDrvier
	 * @param locator
	 *            - Object locator
	 * @param propertyName
	 *            - Element Property
	 * @param objectName
	 *            - Element Name
	 * @return
	 * @throws NoSuchAttributeException
	 */
	public String getObjectProperty(WebDriver driver, By locator, String propertyName, String objectName,
			ExtentTest logger) throws NoSuchAttributeException {
		try {
			WebElement elm = driver.findElement(locator);
			if (!(elm == null)) {
				String propertyValue = elm.getAttribute(propertyName).toString().trim();
				return propertyValue;
			} else {
				logFail(logger, "Element is not found", driver);
				return null;
			}
		} catch (ElementNotFoundException e) {
			logFail(logger, "Element not found : " + objectName, driver);
			return null;
		}
	}

	/**
	 * Get the list values present in the drodown
	 * 
	 * @param driver
	 *            - Webdriver
	 * @param locator
	 *            - Object locator
	 * @param lstName
	 *            - Dropdown name
	 * @return
	 */
	public List<WebElement> getTheDropdownValues(WebDriver driver, By locator, String lstName, ExtentTest logger) {
		try {
			WebElement elm = driver.findElement(locator);
			Select s = new Select(elm);
			List<WebElement> options = s.getOptions();
			return options;
		} catch (ElementNotFoundException e) {
			logFail(logger, "Element not found : " + lstName, driver);
			return null;
		} catch (Exception e) {
			logFail(logger, "Exception : " + e.getMessage(), driver);
			return null;
		}
	}

	/**
	 * Switching Frames
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param frameIndex
	 *            - FrameIndex
	 */
	public void switchToFrame(WebDriver driver, int frameIndex, ExtentTest logger) {
		try {
			driver.switchTo().frame(frameIndex);
			logInfo(logger, "Successfully switched to Frame. Frame Index :" + frameIndex);
		} catch (Exception e) {
			logFail(logger, "Unable to switch Frames", driver);
		}
	}

	/**
	 * Switching Frames
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param frameName
	 *            - FrameName
	 */
	public void switchToFrame(WebDriver driver, String frameName, ExtentTest logger) {
		try {
			driver.switchTo().frame(frameName);
			logInfo(logger, "Successfully switched to Frame. Frame Name :" + frameName);
		} catch (Exception e) {
			logFail(logger, "Unable to switch to frame - " + frameName, driver);
		}
	}

	/**
	 * Switching Frames
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param frame
	 *            - WebElement Frame
	 */
	public void switchToFrame(WebDriver driver, WebElement frame, ExtentTest logger) {
		try {
			driver.switchTo().frame(frame);
			logInfo(logger, "Successfully switched to Frame. Frame :" + frame);
		} catch (Exception e) {
			logFail(logger, "Unable to switch Frames", driver);
		}
	}

	/***
	 * Read the Table Content and prints the data in the console
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param locator
	 *            - Object locator
	 */
	/** Locater have a path of table **/
	// Table/TBODY/TR/TD

	public void displayTableContent(WebDriver driver, By locator, ExtentTest logger) {
		try {
			WebElement tblMain = driver.findElement(locator);
			if (!(tblMain == null)) {
				WebElement tblbody = tblMain.findElement(By.tagName("TBODY"));

				// Get the Total number of Rows - TR
				List<WebElement> rows = tblbody.findElements(By.tagName("TR"));
				List<WebElement> cols = rows.get(0).findElements(By.tagName("TD"));

				for (int i = 0; i < rows.size(); i++) {
					for (int j = 0; j < cols.size(); j++) {
						String cellContent = cols.get(j).getAttribute("text").toString().trim();
						System.out.println("The content :" + i + j + ": " + cellContent);
					}
				}
			} else
				logFail(logger, "Table not found", driver);
		} catch (NoSuchElementException e) {
			logFail(logger, "Exception occured : " + e.getMessage(), driver);
		}
	}

	/**
	 * Get the Column position in the table depends on the Column Name
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param locator
	 *            - Table locator
	 * @param colName
	 *            - Column Name
	 * @param logger
	 *            - ExtentTest Logger
	 * @return
	 */
	public int getTheColumnPosition(WebDriver driver, By locator, String colName, ExtentTest logger) {
		int colPos = -1;
		try {
			WebElement tblMain = driver.findElement(locator);
			if (!(tblMain == null)) {
				WebElement tblbody = tblMain.findElement(By.tagName("TBODY"));

				// Get the Total number of Rows - TR
				List<WebElement> heading = tblbody.findElements(By.tagName("TH"));

				for (int i = 0; i < heading.size(); i++) {
					String columnName = heading.get(i).getText();
					System.out.println("The column name is : " + columnName);
					if (columnName.equalsIgnoreCase(colName)) {
						colPos = i;
						break;
					}
				}
			}

			if (colPos == -1) {
				logFail(logger, "No column found with name : " + colName, driver);
				return colPos;
			}

		} catch (Exception e) {
			logFail(logger, "No colum found", driver);
		}
		return colPos;
	}

	/**
	 * Clicks on Table column based on the column Index and Searching text
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param locator
	 *            - Object locator
	 * @param searchText
	 *            - Searching text
	 * @param colIndex
	 *            - Column Index
	 */
	public void clickOnTableColumn(WebDriver driver, By locator, String searchText, String colIndex,
			ExtentTest logger) {
		try {
			WebElement tblMain = driver.findElement(locator);
			if (!(tblMain == null)) {
				WebElement tblbody = tblMain.findElement(By.tagName("TBODY"));

				// Get the Total number of Rows - TR
				List<WebElement> rows = tblbody.findElements(By.tagName("TR"));
				List<WebElement> cols = rows.get(0).findElements(By.tagName("TD"));

				for (int i = 0; i < rows.size(); i++) {
					WebElement rowElm = rows.get(i);
					WebElement colRequired = rowElm.findElement(By.xpath(".//TD[" + colIndex + "]"));

					String cellContent = colRequired.getAttribute("text").toString().trim();
					if (cellContent.equalsIgnoreCase(searchText)) {
						colRequired.click();
						logPass(logger, "Clicked on the Row number : " + i + " with the column : " + colIndex, driver);
						break;
					}
					System.out.println("The content at row : " + i + " columm index :" + colIndex
							+ ". CellContent is : " + cellContent);
				}
			} else
				logFail(logger, "Table not found", driver);

		} catch (ElementNotFoundException e) {
			logFail(logger, "No Element found", driver);
		} catch (Exception e) {
			logFail(logger, "The exception is :" + e.getMessage(), driver);
		}
	}

	/**
	 * Return the current window handles
	 * 
	 * @param driver
	 * @return
	 */

	public String getTheCurrentWindowHandle(WebDriver driver, ExtentTest logger) {
		try {
			String currentWindowName = driver.getWindowHandle();
			return currentWindowName;
		} catch (NoSuchWindowException e) {
			logFail(logger, "No window found", driver);
			return null;
		}
	}

	/**
	 * Switch to other window handles
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param currentWindow
	 *            - Window handle, where drive has to switch
	 * @return
	 */
	public boolean switchToNewlyOpenedWindow(WebDriver driver, String currentWindow, ExtentTest logger) {
		try {
			Set<String> totalwindows = driver.getWindowHandles();
			for (String window : totalwindows) {
				if (window.equalsIgnoreCase(currentWindow)) {
					driver.switchTo().window(window);
					logInfo(logger, "Successfully navigated to Newly opened window");
					break;
				}
			}
		} catch (NoSuchWindowException e) {
			logFail(logger, "No window found with the Window handle", driver);
			return false;
		}
		return true;
	}

	/**
	 * Enter the username and password in the Windows based popup for Internet
	 * Explorer
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param userName
	 *            - Username
	 * @param password
	 *            - Password
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public void enterTextInWindowsPopup(WebDriver driver, String userName, String password, ExtentTest logger)
			throws AWTException, InterruptedException {
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(userName);
			logInfo(logger, "Entered the username : " + userName);
			StringSelection stringSelection = new StringSelection(password);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_CONTROL); // Copy
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			logInfo(logger, "Entered the password : " + password);
			alert.accept();
			logInfo(logger, "Accepted the Alert");
			Thread.sleep(3000);
		}

		catch (NoAlertPresentException e) {
			logFail(logger, "No Alert Present", driver);
		}

		catch (Exception e) {
			logFail(logger, "Exception occured " + e.getMessage(), driver);
		}
	}

	/**
	 * Verifies that element is present or not
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param locator
	 *            - Locator
	 * @param ObjectName
	 *            - Object Name
	 * @param logger
	 *            - ExtentTest Logger
	 * @return
	 */
	public boolean verifyObejctExistence(WebDriver driver, By locator, String ObjectName, ExtentTest logger) {
		try {
			WebElement elm = driver.findElement(locator);
			if (elm.isDisplayed()) {
				logPass(logger, ObjectName + " is displayed");
				return true;
			} else {
				logFail(logger, ObjectName + " is not displayed", driver);
				return false;
			}
		} catch (ElementNotFoundException e) {
			logFail(logger, "Element not found", driver);
			return false;
		} catch (Exception e) {
			logFail(logger, "Exception Occured : " + e.getMessage(), driver);
			return false;
		}
	}

	public boolean verifyObejctAbsence(WebDriver driver, By locator, String ObjectName, ExtentTest logger) {
		try {
			WebElement elm = driver.findElement(locator);
			if (elm.isDisplayed()) {
				logFail(logger, ObjectName + " is displayed", driver);
				return true;
			} else {
				logPass(logger, ObjectName + " is not displayed");
				return false;
			}
		} catch (ElementNotFoundException e) {
			logPass(logger, "Element not found", driver);
			return false;
		} catch (Exception e) {
			logPass(logger, "" + e.getMessage(), driver);
			return false;
		}
	}

	/*
	 * public boolean verifyObejctAbsence(WebDriver driver, WebElement ele,
	 * String ObjectName,ExtentTest logger) { boolean flag=false; if(ele!=null)
	 * { if (ele.isDisplayed()) { flag = true; logFail(logger, ObjectName+
	 * " is displayed", driver); } else { logPass(logger, ObjectName +
	 * " is not displayed");
	 * 
	 * } } else { logInfo(logger, "Element is not found", driver); } return
	 * flag; }
	 */
	/**
	 * Retrieves the object property based on the property Name
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param property
	 *            - Object Property Name
	 * @param locator
	 *            - Object Locator
	 * @param logger
	 *            - ExtentTest logger
	 * @return
	 * @throws AttributeNotFoundException
	 */
	public String retrieveObjectProperty(WebDriver driver, String property, By locator, ExtentTest logger)
			throws AttributeNotFoundException {
		String propValue = "";
		try {
			WebElement elm = driver.findElement(locator);
			if (!(elm == null)) {
				if (property.equalsIgnoreCase("text()")) {
					propValue = elm.getText().toString().trim();
					logInfo(logger, "The retrived value from the field is : " + propValue);
				} else {
					propValue = elm.getAttribute(property).toString().trim();
					logInfo(logger, "The retrived value from the field is : " + propValue);
				}
			}
		} catch (ElementNotFoundException e) {
			logFail(logger, "Element not found", driver);
			return null;
		}

		catch (Exception e) {
			logFail(logger, "Exception is : " + e.getMessage(), driver);
			return null;
		}
		return propValue;
	}

	/**
	 * Return the current Date
	 * 
	 * @return
	 */
	public String getCurrentDate() {
		java.util.Calendar cal = java.util.Calendar.getInstance();// calculating
																	// current
																	// date
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(java.util.Calendar.getInstance().getTime());
		System.out.println("Date1 is " + timeStamp);
		return timeStamp;
	}

	/**
	 * Return the month name based on the month index
	 * 
	 * @param m
	 * @return
	 */
	public String months(int m) {
		String month;
		if (m == 1) {
			month = "January";
			return month;
		}
		if (m == 2) {
			month = "February";
			return month;
		}
		if (m == 3) {
			month = "March";
			return month;
		}
		if (m == 4) {
			month = "April";
			return month;
		}
		if (m == 5) {
			month = "May";
			return month;
		}
		if (m == 6) {
			month = "June";
			return month;
		}
		if (m == 7) {
			month = "July";
			return month;
		}
		if (m == 8) {
			month = "August";
			return month;
		}
		if (m == 9) {
			month = "September";
			return month;
		}
		if (m == 10) {
			month = "October";
			return month;
		}
		if (m == 11) {
			month = "November";
			return month;
		}
		if (m == 12) {
			month = "December";
			return month;
		}
		return "";
	}

	/**
	 * Get the list values from the list box
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param locator
	 *            - Locator
	 * @param logger
	 *            - ExtentTest Logger
	 * @return
	 */
	public ArrayList<String> getTheListValues(WebDriver driver, By locator, ExtentTest logger) {
		ArrayList<String> a = new ArrayList<String>();
		try {
			Select n = new Select(driver.findElement(locator));
			List<WebElement> listValues = n.getOptions();

			for (int i = 0; i < listValues.size(); i++) {
				String value = listValues.get(i).getAttribute("text").toString().trim();
				a.add(value);
			}
		} catch (ElementNotFoundException e) {
			logFail(logger, "Element not found", driver);
			return null;
		} catch (Exception e) {
			logFail(logger, "Exception occured is : " + e.getMessage(), driver);
			return null;
		}
		return a;
	}

	/**
	 * Captures the screenshot of the page
	 * 
	 * @param driver
	 *            - WebDriver
	 * @return
	 */
	public String captureScreenshot(WebDriver driver) {
		String userDirector = System.getProperty("user.dir");
		// String userDirector = System.getProperty("user.dir") + "/";

		String s1 = null, s2 = "";
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		if (true) {
			try {

				String failureImageFileName = new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss")
						.format(new GregorianCalendar().getTime()) + ".png";
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				/*
				 * FileUtils.copyFile(scrFile, new
				 * File("Screenshot\\"+failureImageFileName)); s1 = userDirector
				 * +"\\Screenshot\\" + failureImageFileName ;
				 */
				FileUtils.copyFile(scrFile, new File("./Extent_Reports/" + failureImageFileName));
				s1 = failureImageFileName;

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return s1;
	}

	/**
	 * Validates the cell content in a table based on the column index
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param locator
	 *            - Object locator
	 * @param colIndex
	 *            - Column Index
	 * @param cellValue
	 *            - Cell Value that needs to be checked
	 * @param logger
	 *            - ExtentTest logger
	 */
	public void verifyCellContentByColIndex(WebDriver driver, By locator, String attributeName, int colIndex,
			String cellValue, ExtentTest logger, String noUserExist) {
		try {
			boolean matchFound = false;
			String cellContent = "";
			WebElement tblMain = driver.findElement(locator);
			if (!(tblMain == null)) {
				WebElement tblbody = tblMain.findElement(By.tagName("TBODY"));

				// Get the Total number of Rows - TR
				List<WebElement> rows = tblbody.findElements(By.tagName("TR"));

				if (rows.size() > 0) {
					// Iterate over the rows and get the column values
					for (int i = 0; i < rows.size(); i++) {
						List<WebElement> cols = rows.get(i).findElements(By.tagName("TD"));
						try {
							cellContent = cols.get(colIndex).getAttribute(attributeName).toString().trim();
							System.out.println("The cell content :" + cellContent);
						} catch (Exception e) {
							continue;
						}

						if (cellContent.equalsIgnoreCase(cellValue)) {
							logPass(logger, "Expected content is displayed at row " + i + " at column " + colIndex,
									driver);
							matchFound = true;
							break;
						} else
							continue;
					}
					if (matchFound == false) {
						if (noUserExist.equalsIgnoreCase("NoUser"))
							logPass(logger, "User " + noUserExist + " has been successfully removed ", driver);
						else
							logFail(logger, "No matching row found with the searching Keyword", driver);
					}
				} else
					logFail(logger, "No rows found in the table", driver);
			} else
				logFail(logger, "Table not found", driver);
		} catch (NoSuchElementException e) {
			logFail(logger, "Exception occured : " + e.getMessage(), driver);
		}
	}

	/**
	 * Gets the row number based on the cell content
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param locator
	 *            - Locator
	 * @param attributeName
	 *            - Attribute where cell value needs to be retrieved
	 * @param colIndex
	 *            - Column Index
	 * @param cellValue
	 *            - Cell value
	 * @param logger
	 *            - ExtentTest logger
	 * @return
	 */
	public int getRowNumberBasedOnCellContent(WebDriver driver, By locator, String attributeName, int colIndex,
			String cellValue, ExtentTest logger) {
		try {
			boolean matchFound = false;
			String cellContent = "";

			int rowIndex = 0;
			WebElement tblMain = driver.findElement(locator);
			if (!(tblMain == null)) {
				WebElement tblbody = tblMain.findElement(By.tagName("TBODY"));

				// Get the Total number of Rows - TR
				List<WebElement> rows = tblbody.findElements(By.tagName("TR"));
				System.out.println("The number of rows are : " + rows.size());

				if (rows.size() > 0) {
					// Iterate over the rows and get the column values
					for (rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
						List<WebElement> cols = rows.get(rowIndex).findElements(By.tagName("TD"));
						try {
							cellContent = cols.get(colIndex).getAttribute(attributeName).toString().trim();
							System.out.println("The cell content :" + cellContent);
						} catch (Exception e) {
							continue;
						}

						if (cellContent.equalsIgnoreCase(cellValue)) {
							logPass(logger,
									"Expected content is displayed at row " + rowIndex + "at column " + colIndex);
							matchFound = true;
							break;
						} else
							continue;
					}
					if (matchFound == false) {
						logFail(logger, "No matching row found with the searching Keyword", driver);
						return -1;
					}
				} else {
					logFail(logger, "No rows found in the table", driver);
					return -1;
				}
			} else {
				logFail(logger, "Table not found", driver);
				return -1;
			}

			return rowIndex;
		} catch (NoSuchElementException e) {
			logFail(logger, "Exception occured : " + e.getMessage(), driver);
			return -1;
		}
	}

	/**
	 * Get the total Row count
	 * 
	 * @param driver
	 * @param locator
	 * @param logger
	 * @return
	 */
	public int getRowCount(WebDriver driver, By locator, ExtentTest logger) {
		List<WebElement> rows = null;
		try {

			WebElement tblMain = driver.findElement(locator);
			if (!(tblMain == null)) {
				WebElement tblbody = tblMain.findElement(By.tagName("TBODY"));

				// Get the Total number of Rows - TR
				rows = tblbody.findElements(By.tagName("TR"));
				List<WebElement> cols = rows.get(0).findElements(By.tagName("TD"));

			} else
				logFail(logger, "No Table found", driver);

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

		return rows.size();
	}

	public void setDate(String DATE, By locator1, By locator2, By locator3, By locator4, By locator5, ExtentTest logger,
			WebDriver driver) throws InterruptedException {
		/*
		 * try { String year,month,date,dateNow,temp[],newDate=null;
		 * if(DATE.startsWith("days")) { String days =
		 * DATE.split("=")[1].toString().trim(); Calendar currentDate =
		 * Calendar.getInstance(); SimpleDateFormat formatter= new
		 * SimpleDateFormat("yyyy/MMMM/d"); dateNow =
		 * formatter.format(currentDate.getTime()); //System.out.println(
		 * "Todays date is :=>  " + dateNow); year =
		 * dateNow.split("/")[0].toString(); month =
		 * dateNow.split("/")[1].toString(); date =
		 * dateNow.split("/")[2].toString();
		 * 
		 * currentDate.add(Calendar.DATE, Integer.parseInt(days)); newDate =
		 * formatter.format(currentDate.getTime()); System.out.println(
		 * "New date is :=>  " + newDate); year =
		 * newDate.split("/")[0].toString(); month =
		 * newDate.split("/")[1].toString(); date =
		 * newDate.split("/")[2].toString();
		 * 
		 * } else{ temp=DATE.split("/"); date=temp[0]; month=temp[1];
		 * year=temp[2]; }
		 * 
		 * newDate = month+"/"+date+"/"+year; String cur_month , cur_year; int
		 * cur_month_id,cur_year_id,set_yr,set_date , set_month; int diff_yr,
		 * diff_month, total_diff; int flag =0; WebElement topRowMonth =
		 * driver.findElement(BusinessAdvisorObjects.datePickerMnth); WebElement
		 * topRowYr = driver.findElement(BusinessAdvisorObjects.datePickerYr);
		 * cur_month = topRowMonth.getText().toString().trim(); cur_year =
		 * topRowYr.getText().toString().trim();
		 * 
		 * cur_month_id = monthid(cur_month); cur_year_id =
		 * Integer.parseInt(cur_year);
		 * 
		 * set_month=monthid(month); set_date=Integer.parseInt(date);
		 * set_yr=Integer.parseInt(year);
		 * 
		 * diff_yr=set_yr - cur_year_id; diff_month=set_month - cur_month_id;
		 * total_diff = diff_month +12*(diff_yr);
		 * 
		 * if(total_diff!=0) { if(total_diff>0) { flag=1; } if(total_diff<0) {
		 * flag=2; total_diff= -total_diff; } } for(int i=0;i<total_diff;i++) {
		 * if(flag==1) { while(true) { topRowMonth =
		 * driver.findElement(BusinessAdvisorObjects.datePickerMnth); topRowYr =
		 * driver.findElement(BusinessAdvisorObjects.datePickerYr); String
		 * monthSelected = topRowMonth.getText().toString().trim(); String
		 * yrSelected = topRowYr.getText().toString().trim();
		 * 
		 * if(monthSelected.toLowerCase().contains(month.toLowerCase()) &&
		 * yrSelected.equalsIgnoreCase(year)){ break;}
		 * 
		 * WebElement nxtMonth =
		 * driver.findElement(BusinessAdvisorObjects.datePickerNext);
		 * (nxtMonth).click(); }
		 * 
		 * } if(flag==2)while(true) { topRowMonth =
		 * driver.findElement(BusinessAdvisorObjects.datePickerMnth); topRowYr =
		 * driver.findElement(BusinessAdvisorObjects.datePickerYr); String
		 * monthSelected = topRowMonth.getText().toString().trim(); String
		 * yrSelected = topRowYr.getText().toString().trim();
		 * 
		 * if(monthSelected.toLowerCase().contains(month.toLowerCase()) &&
		 * yrSelected.equalsIgnoreCase(year)){ break;} WebElement preMonth =
		 * driver.findElement(BusinessAdvisorObjects.datePickerPrev);
		 * preMonth.click(); } Thread.sleep(2000); }
		 * 
		 * Thread.sleep(2000); WebElement dateTable =
		 * driver.findElement(BusinessAdvisorObjects.datePickerCalendar);
		 * WebElement d =
		 * dateTable.findElement(By.xpath("//a[text()='"+date+"']"));
		 * if(d.isDisplayed()) { d.click(); logInfo(logger, "Selected Date : "+
		 * newDate);} else logFail(logger, "Expected Date is not displayed",
		 * driver); } catch(ArrayIndexOutOfBoundsException e){ logfail(logger,
		 * "Error"); } catch(Exception e) { e.printStackTrace(); logInfo(logger,
		 * "The Exception occured :"+ e.getMessage(), driver); }
		 */}

	public int monthid(String month) {
		int i = 0;
		String cal[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		for (i = 0; i < 12; i++) {
			if ((cal[i].toLowerCase()).contains(month.toLowerCase()))
				return (i + 1);
		}
		return 20;
	}

	/**
	 * Retrieve the Alert text and Validates with expected text and accepts the
	 * Alert
	 * 
	 * @param driver
	 * @param expText
	 * @param logger
	 */
	public void handleAlert(WebDriver driver, String expText, ExtentTest logger) {
		try {

			WebDriverWait wa = new WebDriverWait(driver, 60);
			wa.until(ExpectedConditions.alertIsPresent());

			Alert a = driver.switchTo().alert();
			String alertText = a.getText().toString().trim();

			if (alertText.contains(expText)) {
				a.accept();
				logPass(logger, "Expected Alert message is displayed : " + alertText);
				Thread.sleep(3000);
			} else {
				a.accept();
				logFail(logger, "Expected Alert message is not displayed : " + alertText, driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the cell content based on the Row number
	 * 
	 * @param driver
	 * @param locator
	 * @param rowIndex
	 * @param colIndex
	 * @param attributeName
	 * @param logger
	 * @return
	 */
	public String retrieveCellContentBasedOnRowNumber(WebDriver driver, By locator, int rowIndex, int colIndex,
			String attributeName, ExtentTest logger) {
		boolean matchFound = false;
		String cellContent = "";
		try {
			WebElement tblMain = driver.findElement(locator);
			if (!(tblMain == null)) {
				WebElement tblbody = tblMain.findElement(By.tagName("TBODY"));

				// Get the Total number of Rows - TR
				List<WebElement> rows = tblbody.findElements(By.tagName("TR"));

				if (rows.size() > 0 && rows.size() >= rowIndex) {
					// Iterate over the rows and get the column values
					List<WebElement> col = rows.get(rowIndex).findElements(By.tagName("TD"));
					try {
						cellContent = col.get(colIndex).getAttribute(attributeName).toString().trim();
						System.out.println("The cell content :" + cellContent);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else
					logFail(logger, "No rows found in the table", driver);
			} else
				logFail(logger, "Table not found", driver);
		} catch (NoSuchElementException e) {
			logFail(logger, "Exception occured : " + e.getMessage(), driver);
			return null;
		}
		return cellContent;
	}

	/**
	 * Stores the values in the data pool based on the Row and Column number
	 * 
	 * @param dpPath
	 *            - Data pool path
	 * @param sheetName
	 *            - Sheet Name
	 * @param colNumber
	 *            - Column Number
	 * @param rowNumber
	 *            - Row Number
	 * @param Value
	 *            - Value to be stored
	 * @param logger
	 *            - ExtentTest Logger
	 * @param driver
	 *            - WebDriver
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	public boolean storeValue(String dpPath, String sheetName, int colNumber, int rowNumber, String Value,
			ExtentTest logger, WebDriver driver) throws Exception, IOException {
		boolean pass = true;
		try {
			// File excel = new File("C://temp/Employee.xlsx");
			FileInputStream fis = new FileInputStream(dpPath);
			XSSFWorkbook book = new XSSFWorkbook(fis);
			XSSFSheet sheet = book.getSheet(sheetName);

			Cell cell = null;
			Row row = sheet.getRow(rowNumber);
			if (row == null)
				row = sheet.createRow(rowNumber);

			row.getCell(colNumber);
			if (cell == null) {
				cell = row.createCell(colNumber);

			}
			cell.setCellValue(Value);
			fis.close();

			// Write the output to a file
			String s = dpPath.substring(0, dpPath.lastIndexOf("\\"));
			FileOutputStream fileOut = new FileOutputStream(new File(s + "/Selenium_DataSheet.xlsx"));
			book.write(fileOut);
			fileOut.close();
			File fp = new File(dpPath);
			File fout = new File(s + "/Selenium_DataSheet.xlsx");
			fp.delete();
			fout.renameTo(fp);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
			pass = false;
		} catch (IOException e) {
			e.printStackTrace();
			pass = false;
		}

		return pass;
	}

	/**
	 * Checks whether element is hidden or not
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param element
	 *            - Element
	 * @param comment
	 *            - Comment
	 * @param logger
	 *            -
	 * @throws InterruptedException
	 */
	public void checkHiddenElement(WebDriver driver, By element, String comment, ExtentTest logger)
			throws InterruptedException {
		if (elementExist(driver, element)) {
			logFail(logger, comment, driver);
		} else {
			logPass(logger, comment);
		}
	}
	// checks whether element exists

	/**
	 * Checks whether element exists
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param locator
	 *            - Locator
	 * @return
	 */
	public boolean elementExist(WebDriver driver, By locator) {
		boolean exist = false;
		try {
			driver.findElement(locator);
			exist = true;
		} catch (Exception e) {
			exist = false;
		}
		return exist;
	}

	//

	/**
	 * Used to get a number from a string displayed in the browser.
	 * 
	 * @param label
	 *            - Element Label
	 * @return
	 */
	public int getNoFromString(String label) {
		int intValue = 0, i = 0;
		char value[] = new char[255];
		String stringValue = "";
		for (i = 0; i < label.length(); i++) {
			value[i] = label.charAt(i);
			if (Character.isDigit(value[i])) {
				stringValue += value[i];
			}

			// System.out.println("Character at position ("+i+") is
			// <<<"+label.charAt(i)+">>");
		}
		intValue = Integer.parseInt(stringValue);
		return intValue;
	}

	/**
	 * To Check whether the desired field is Empty or not
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param locator
	 *            - Object locator
	 * @return true - if field is empty
	 * @return false - if field is not empty
	 **/

	public boolean verifyFieldIsEmpty(WebDriver driver, By locator, ExtentTest logger) {
		WebElement ele = driver.findElement(locator);

		if (ele.getAttribute("Value").isEmpty()) {
			logPass(logger, "Value is Empty");
			return true;
		} else {
			logPass(logger, "Value is not Empty");
			return false;
		}
	}

	/**
	 * To Check whether the desired field is disabled or not
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param locator
	 *            - Object locator
	 * @param message
	 *            - description of the locator
	 **/
	public boolean checkDisabledField(WebDriver driver, By locator, String message, ExtentTest logger) {
		String disableProperty = driver.findElement(locator).getAttribute("disabled").toString();
		if (disableProperty.equals("Disabled")) {
			logPass(logger, message + "field is Disabled");
			return true;
		} else {
			logFail(logger, message + "field is Not Disabled", driver);
			return false;
		}
	}

	public void downloadFile(WebDriver driver, Robot robj) {
		robj.keyPress(KeyEvent.VK_ENTER);
	}

	/**
	 * Enter the username and password in the Windows based popup for Internet
	 * Explorer
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param userName
	 *            - Username
	 * @param password
	 *            - Password
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public void downloadFile(WebDriver driver, ExtentTest logger) throws AWTException, InterruptedException {
		try {
			Robot robot = new Robot();
			robot.mouseMove(1039, 705); // move mouse point to specific location
			robot.delay(1500); // delay is to make code wait for mentioned
								// milliseconds before executing next step
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left
																// click
			robot.delay(1500);
			captureScreenshot(driver);
			logPass(logger, "Clicked on File Download", driver);
			// robot.keyPress(KeyEvent.VK_DOWN); // press keyboard arrow key to
			// select Save radio button

			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			logPass(logger, "Clicked on Save file Button", driver);
			captureScreenshot(driver);
			// For Close Button
			robot.mouseMove(1149, 705);
			robot.delay(1500);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(3000);
			logPass(logger, "Closed the windows Save/Download Dialog", driver);
			// press enter key of keyboard to perform above selected action
		} catch (Exception e) {
			e.printStackTrace();
			logFail(logger, "Exception occured " + e.getMessage(), driver);
		}
	}

	public int findCellnum(Row row1, String cellContent) {

		for (Cell cell : row1) {
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
					return cell.getColumnIndex();
				}
			}
		}

		return 0;
	}

	public String[][] getTestDataFromExcelSheet(String fileName, String sheetName) {
		String[][] arrayExcelData = null;
		org.apache.poi.ss.usermodel.Workbook tempWB;

		try {
			// trim sheet name to 32 characters if it is greater
			if (sheetName.length() > 32) {
				sheetName = sheetName.substring(0, 31);
			}
			if (fileName.contains(".xlsx")) {
				tempWB = new XSSFWorkbook(fileName);
			} else {
				InputStream inp = new FileInputStream(fileName);
				tempWB = new HSSFWorkbook(new POIFSFileSystem(inp));
			}

			org.apache.poi.ss.usermodel.Sheet sheet = tempWB.getSheet(sheetName);

			// Total rows counts the top heading row
			int totalNoOfRows = sheet.getLastRowNum();
			System.out.println("The total rows are : " + totalNoOfRows);
			/*
			 * Row row = sheet.getRow(0); int totalNoOfCols =
			 * row.getLastCellNum(); System.out.println(
			 * "The total colums are : " + totalNoOfCols);
			 */

			arrayExcelData = new String[totalNoOfRows + 1][];

			try {
				for (int i = 0; i < totalNoOfRows + 1; i++) {
					Row row1 = sheet.getRow(i);
					int totalNoOfCols1 = row1.getLastCellNum();
					int lastcolumn = findCellnum(row1, "endOfRow");
					System.out.println("lastcolumn: " + lastcolumn);
					arrayExcelData[i] = new String[lastcolumn];
					for (int j = 0; j < lastcolumn; j++) {
						// row = sheet.getRow(i);
						try {
							System.out.println(row1.getCell(j).toString().trim());
							System.out.println(
									"The cell values are : " + i + "," + j + ": " + row1.getCell(j).toString().trim());
							arrayExcelData[i][j] = row1.getCell(j).toString().trim();
						} catch (Exception e) {
							arrayExcelData[i][j] = "";
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		return arrayExcelData;
	}

	public boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} // try
		catch (Exception e) {
			return false;
		} // catch
	}
	public void scrollObj(WebDriver driver, By locator) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(locator);
		Point loc = element.getLocation();
		loc.x = element.getLocation().getX() - 200;
		loc.y = element.getLocation().getY() - 200;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(700);
	}
	
	public void Scroll(WebDriver driver, By locator) throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(locator);
		 js.executeScript("window.scrollBy(1000,0)","", element);
			Thread.sleep(700);
	}
	public void scrollDown(WebDriver driver, By locator) throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(locator);
		js.executeScript("window.scrollBy(0,1000)");
			Thread.sleep(700);
	}
	public void scrollToViewElement(WebDriver driver, By locator) throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(locator);
		js.executeScript("arguments[0].scrollIntoView();",element );
			Thread.sleep(700);
	}
	public void Scroll3(WebDriver driver, By locator) throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(locator);
		 js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(700);
	}

}
