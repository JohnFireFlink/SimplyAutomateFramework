package genericLib;

import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pomPages.HomePage;
import pomPages.LoginPage;
import pomPages.RegisterPage;

public class BaseClass {

	public Utilities util=new Utilities();
	public static ExtentSparkReporter html;
	public static ExtentReports extent;
	public ExtentTest test;
	public Map<String, String> map;
	
	public HomePage hp=new HomePage();
	public LoginPage lp=new LoginPage();
	public RegisterPage rp=new RegisterPage();
	
	@BeforeSuite
	public void setUp()
	{
		html = new ExtentSparkReporter("./target/Report.html");
		extent = new ExtentReports();
		extent.attachReporter(html);
	}
	@BeforeClass
	public void environmentData()
	{
		map = util.readFromGoogleSheetForUniqueDataInMultiRowTable("EnvironmentalData","Environment","Prod");
	}
	
	@AfterMethod
	public void testOver(ITestResult res)
	{
		if(res.getStatus()==ITestResult.FAILURE)
		{
			if (util.driver!=null) 
			{
			TakesScreenshot tss=(TakesScreenshot) util.driver;
			String src = tss.getScreenshotAs(OutputType.BASE64);
			test.addScreenCaptureFromBase64String(src);
			}
		}
		if(res.getStatus()==ITestResult.SUCCESS)
		{
			if (util.driver!=null) 
			{
				util.closeBrowser();
			}
			
		}
		
	}
	
	@AfterSuite
	public void upDateReport()
	{
		extent.flush();
	}
	
}
