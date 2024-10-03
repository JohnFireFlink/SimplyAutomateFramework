package genericLib;

public class ReUseableLib extends BaseClass{

	//Create your reUseable StepGroups as new methods
	
	public void navigateToWebApp(String browserName, String url) throws InterruptedException
	{
		if (os.contains("win") || os.contains("mac")) 
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

	}
	
}
