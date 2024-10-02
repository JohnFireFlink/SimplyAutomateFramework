package genericLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ReUseableLib extends BaseClass{

	//StepGroups
	
	public void navigateToHomePage(String browserName, String url) throws InterruptedException
	{
		//util.openBrowser(browserName);
		util.openHeadlessBrowser(browserName);
		util.maximizeBrowser();
		util.navigateTo(url);
		util.setImplicitWait(5);
		util.verifyIfDisplayed(hp.registerLink());
	}
	
	public void navigateToRegisterPage(String browserName, String url) throws InterruptedException
	{
		navigateToHomePage(browserName,url);
		util.clickOn(hp.registerLink());
		util.verifyIfDisplayed(rp.registerTxt());
	}
	
	public void navigateToLoginPage(String browserName, String url) throws InterruptedException
	{
		navigateToHomePage(browserName,url);
		util.clickOn(hp.logInLink());
		util.verifyIfDisplayed(lp.WelcomeTxt());
	}
	
	public void loginAsUser(String browserName, String url) throws InterruptedException
	{
		navigateToLoginPage(browserName,url);
		util.EnterInto(lp.emailTF(), "mkar@gmail.com");
		util.EnterInto(lp.pwdTF(), "Ka@12345");
		util.clickOn(lp.logInBtn());
		util.verifyIfDisplayed(hp.logoutLink());
	}
}
