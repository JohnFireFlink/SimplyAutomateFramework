package webTestCases;

import org.testng.Reporter;
import org.testng.annotations.Test;

import genericLib.ReUseableLib;

public class Module2_Login extends ReUseableLib {

	@Test
	public void tc004_VerifyLoginWithValidCredentials() throws InterruptedException
	{
		test=extent.createTest(Reporter.getCurrentTestResult().getName());
		util.generateReport(test);
		util.setDelayBtwnSteps(1000);
		
		navigateToLoginPage("Chrome");
		util.EnterInto(lp.emailTF(), "mkar@gmail.com");
		util.EnterInto(lp.pwdTF(), "Ka@12345");
		util.clickOn(lp.logInBtn());
		util.verifyIfDisplayed(hp.logoutLink());
	}
	
	@Test
	public void tc005_VerifyLoginWithInValidCredentials() throws InterruptedException
	{
		test=extent.createTest(Reporter.getCurrentTestResult().getName());
		util.generateReport(test);
		util.setDelayBtwnSteps(1000);
		
		navigateToLoginPage("FireFox");
		util.EnterInto(lp.emailTF(), "mkar@gmail.com");
		util.EnterInto(lp.pwdTF(), "Ka@123456");
		util.clickOn(lp.logInBtn());
		util.verifyIfDisplayed(lp.IncorrectCredMsg());
	}
	
	@Test
	public void tc006_VerifyLoginWithNoCredentials() throws InterruptedException
	{
		test=extent.createTest(Reporter.getCurrentTestResult().getName());
		util.generateReport(test);
		util.setDelayBtwnSteps(1000);
		
		navigateToLoginPage("Edge");
		util.clickOn(lp.logInBtn());
		util.verifyIfDisplayed(lp.noAccountFoundMsg());
	}
	
}
