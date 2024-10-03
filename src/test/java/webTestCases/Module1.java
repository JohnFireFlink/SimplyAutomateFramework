package webTestCases;

import org.testng.annotations.Test;

import genericLib.ReUseableLib;

public class Module1 extends ReUseableLib {

	@Test
	public void tc001() throws InterruptedException
	{
		generateReport();
		util.setDelayBtwnSteps(1);
		
		navigateToWebApp("Chrome","https://google.com/");
		util.clickOn(d1.gmailLink());
		util.verifyIfDisplayed(d1.createAccBtn());
	}
	
	@Test
	public void tc002() throws InterruptedException
	{
		generateReport();
		util.setDelayBtwnSteps(1);
		
	}

}
