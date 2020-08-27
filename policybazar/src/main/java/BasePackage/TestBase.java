package BasePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static ExtentReports report;
	public static ExtentTest test;
	public static String dest;
	public static String time;
//	public static WebEventListener eventListener;
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/configurationproperty/config.property");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", 
					"C:\\Users\\megha\\Downloads\\policybazar\\policybazar\\src\\test\\resources\\Drivers\\chromedriver.exe");	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("Firefox")){
			System.setProperty("webdriver.gecko.driver", 
					"C:\\Users\\megha\\Downloads\\policybazar\\policybazar\\src\\test\\resources\\Drivers\\geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}
	@BeforeMethod
	public void Reportsetup()
	{
		try
		{
			report=new ExtentReports("report.html",true);
		//	report.addSystemInfo("HostName", "")
			report.addSystemInfo("Environment", "Windows")
			.addSystemInfo("User","Me")
			.addSystemInfo("Project Name", "");
			report.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
				
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	@AfterMethod
	public void getReport(ITestResult result) {
		try {
			String screnshotpath = takeScreenshot(driver);
			if (result.getStatus() == ITestResult.FAILURE) {

				test.log(LogStatus.FAIL, result.getThrowable());
				test.log(LogStatus.FAIL, "Below is the screen shot:"+test.addScreenCapture(screnshotpath));
				test.log(LogStatus.FAIL, "Test Case Fail is:"+result.getName());

			}
			else if(result.getStatus()==ITestResult.SUCCESS)
			{
				test.log(LogStatus.PASS, "Test Case pass is:"+result.getName());
				test.log(LogStatus.PASS, "Below is the screen shot:"+test.addScreenCapture(screnshotpath));
			}
			else if(result.getStatus()==ITestResult.SKIP)
			{
				test.log(LogStatus.SKIP, "test Case skip is:"+result.getName());
			}
			else if(result.getStatus()==ITestResult.STARTED)
			{test.log(LogStatus.INFO, "Test Case started");

			}
			report.endTest(test);

		} catch (Exception es) {
			System.out.println("Report genration Excepion is" + es.getMessage());
		}
	}
	@AfterTest
	public void endTest()
	{
		report.flush();
		report.close();
		driver.close();
	}
	
	public static String takeScreenshot(WebDriver driver) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
			Date date = new Date();
			//System.out.println(dateFormat.format(date)); // 2016/11/16 12:08:43
			time = dateFormat.format(date);
			//System.out.println("Time is" + time);
			TakesScreenshot tc = (TakesScreenshot) driver;
			File src = tc.getScreenshotAs(OutputType.FILE);

			dest = "destpath" + time + ".png"; 
			File destination = new File(dest);
			FileUtils.copyFile(src, destination);
			//System.out.println("image destination" + dest);
			System.out.println("Screenshot taken");

			// return dest;
		} catch (Exception ex) {
			System.out.println("Screenshot error is" + ex.getMessage());
		}
		return dest;
	}
}
