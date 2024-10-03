package genericLib;

import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
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
	
	public static ExtentSparkReporter html;
	public static ExtentReports extent;
	public ExtentTest test;
	public Map<String, String> map;
	public Utilities util=new Utilities(test);
	public String os=System.getProperty("os.name").toLowerCase();
	
	//Create Object if you are creating new pomPages to access the elements
	public HomePage hp=new HomePage();
	public LoginPage lp=new LoginPage();
	public RegisterPage rp=new RegisterPage();
	
	public void generateReport()
	{
		test=extent.createTest(Reporter.getCurrentTestResult().getName());
		util=new Utilities(test);
	}
	
	@BeforeSuite
	public void createReport()
	{
		html = new ExtentSparkReporter("./target/Report.html");
		extent = new ExtentReports();
		extent.attachReporter(html);
	}
	
	@BeforeClass
	public void setEnvironment()
	{
		//If you want to change the url based on environment
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
	public void updateReport()
	{
		extent.flush();
	}
	
}
