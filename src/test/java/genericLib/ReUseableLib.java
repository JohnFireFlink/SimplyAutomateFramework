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
	
	public void navigateToHomePage(String browserName) throws InterruptedException
	{
		Map<String, String> mapData = util.readFromGoogleSheetForUniqueDataInMultiRowTable("EnvironmentalData","Environment","Prod");
		//util.openBrowser(browserName);
		util.openHeadlessBrowser(browserName);
		util.maximizeBrowser();
		util.navigateTo(mapData.get("URL"));
		util.setImplicitWait(5);
		util.verifyIfDisplayed(hp.registerLink());
	}
	
	public void navigateToRegisterPage(String browserName) throws InterruptedException
	{
		navigateToHomePage(browserName);
		util.clickOn(hp.registerLink());
		util.verifyIfDisplayed(rp.registerTxt());
	}
	
	public void navigateToLoginPage(String browserName) throws InterruptedException
	{
		navigateToHomePage(browserName);
		util.clickOn(hp.logInLink());
		util.verifyIfDisplayed(lp.WelcomeTxt());
	}
	
	public void loginAsUser(String browserName) throws InterruptedException
	{
		navigateToLoginPage(browserName);
		util.EnterInto(lp.emailTF(), "mkar@gmail.com");
		util.EnterInto(lp.pwdTF(), "Ka@12345");
		util.clickOn(lp.logInBtn());
		util.verifyIfDisplayed(hp.logoutLink());
	}
	
	//Data Provider
	
	@DataProvider(name = "Login Data")
	public String [][] dpdata() throws IOException
	{
		FileInputStream f=new FileInputStream(new File("D:\\Documents\\LoginData.xlsx"));
		XSSFWorkbook wb=new XSSFWorkbook(f);
		Sheet sh1 = wb.getSheet("Sheet1");
		int lastRowIndex = sh1.getLastRowNum();
		short lastColumnIndex = sh1.getRow(0).getLastCellNum();
		String [][] strAr= new String[lastRowIndex][lastColumnIndex];
		
		for (int i = 0; i < lastRowIndex; i++) 
		{
			for (int j = 0; j < lastColumnIndex; j++) 
			{
				strAr[i][j] = sh1.getRow(i+1).getCell(j).getStringCellValue();	
			}
		}
		wb.close();
		return strAr;
	}
}
