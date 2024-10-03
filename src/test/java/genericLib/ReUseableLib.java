package genericLib;

public class ReUseableLib extends BaseClass{

	//StepGroups
	
	public void navigateToHomePage(String browserName, String url) throws InterruptedException
	{
		if (os.contains("win")) 
		{
			util.openBrowser(browserName);
		}
		else
		{
			util.openHeadlessBrowser(browserName);
		}
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
