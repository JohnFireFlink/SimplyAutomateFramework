package webTestCases;

import org.testng.annotations.Test;

import genericLib.ReUseableLib;

public class Module2 extends ReUseableLib {

	@Test
	public void tc001() throws InterruptedException
	{
		generateReport();
		util.setDelayBtwnSteps(1);
		if (os.contains("win") || os.contains("mac")) 
		{
			util.openChromeWithProfile("TestUser");
		}
		else
		{
			util.openHeadLessChromeWithProfile("TestUser");
		}
		
		util.navigateTo("https://gmail.com");
		util.getCurrentPageTitle();
		Thread.sleep(5000);
	}
}
