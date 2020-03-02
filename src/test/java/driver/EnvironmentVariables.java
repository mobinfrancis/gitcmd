package driver;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class EnvironmentVariables {

	// Data Work Book Name 
	public final static String _workbookName = "CoachingApp_Selenium_Data_v1.xlsx";
	public final static String _dataPoolPath = System.getProperty("user.dir")+"\\src\\test\\java\\resources\\"+_workbookName;

	// Driver Path
	public final static String _chromeDriverpath = "./chromedriver.exe";
	public final static String _firefoxDriverpath = "./geckodriver.exe";
	public final static String _ieDriverpath = "./IEDriverServer.exe";
	
	// Driver Type
	public final static String _chromeDrivertype = "webdriver.chrome.driver";
	public final static String _firefoxDrivertype = "webdriver.gecko.driver";
	public final static String _ieDrivertype = "webdriver.ie.driver";
	
	// Constant Variables
	public final static String _URL = "https://preprod-coaching.accenture.com";	
	
	// Extent Report
	public final static String _reportPath = ".\\Extent_Reports\\Report.html";

	// Extent Report - Parallel Execution
	public static String extendReportsPath(String className) {
		String timeStamp = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
		// String dir=System.getProperty("user.dir");
		String extendReportsPathh = "./Extent_Reports/" + className + " " + timeStamp + ".html";
		return extendReportsPathh;
	} 
	
	// Download File in Firefox
	public final static String _downloadFile = "downloadReportsFirefox.exe";
	public final static String _downloadFileFirefox = System.getProperty("user.dir") + "\\src\\test\\java\\resources\\"+ _downloadFile;
	 
	
		 
//Upload Access File
	
	//Access	
		public final static String _FileAccessTemplateChrome = "RosterManagement_FileUpload.exe";
		public final static String _FileAccessTemplateFF = "FileUpload_AccessFF.exe";	
		public final static String _uploadFileChrome = System.getProperty("user.dir")+"\\src\\test\\java\\resources\\"+ _FileAccessTemplateChrome;
		public final static String _uploadFileFF = System.getProperty("user.dir")+"\\src\\test\\java\\resources\\"+_FileAccessTemplateFF;
	
	//PAM
		public final static String _FilePAMChrome = "FileUpload_PAM.exe";
		public final static String _FilePAMFF = "FileUpload_PAMFF.exe";	
		public final static String _uploadPAMChrome = System.getProperty("user.dir")+"\\src\\test\\java\\resources\\"+ _FilePAMChrome;
		public final static String _uploadFPAMFF = System.getProperty("user.dir")+"\\src\\test\\java\\resources\\"+_FilePAMFF;
	
	
	
	
	

}