package webTestCases;

import org.testng.annotations.Test;

import genericLib.ReUseableLib;

public class Module2 extends ReUseableLib {

	@Test
	public void tc001() throws InterruptedException
	{
		generateReport();
		util.setDelayBtwnSteps(1);
		
	}
	
	@Test
	public void tc002() throws InterruptedException
	{
		generateReport();
		util.setDelayBtwnSteps(1);
		
	}

}
