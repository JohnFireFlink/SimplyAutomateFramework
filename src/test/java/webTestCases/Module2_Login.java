package webTestCases;

import java.util.Map;

import org.testng.annotations.Test;

import genericLib.ReUseableLib;

public class Module2_Login extends ReUseableLib {

	@Test
	public void tc004_VerifyLoginWithValidCredentials() throws InterruptedException
	{
		generateReport();
		Map<String, String> mapData = util.readFromGoogleSheetForUniqueDataInSingleRowTable("Login", "tc004");
		util.setDelayBtwnSteps(1);
		
		navigateToLoginPage("Chrome",map.get("URL"));
		util.EnterInto(lp.emailTF(), mapData.get("Email"));
		util.EnterInto(lp.pwdTF(), mapData.get("Password"));
		util.clickOn(lp.logInBtn());
		util.verifyIfDisplayed(hp.logoutLink());
	}
	
	@Test
	public void tc005_VerifyLoginWithInValidCredentials() throws InterruptedException
	{
		generateReport();
		Map<String, String> mapData = util.readFromGoogleSheetForUniqueDataInSingleRowTable("Login", "tc005");
		util.setDelayBtwnSteps(1);
		
		navigateToLoginPage("FireFox",map.get("URL"));
		util.EnterInto(lp.emailTF(), mapData.get("Email"));
		util.EnterInto(lp.pwdTF(), mapData.get("InvalidPwd"));
		util.clickOn(lp.logInBtn());
		util.verifyIfDisplayed(lp.IncorrectCredMsg());
	}
	
	@Test
	public void tc006_VerifyLoginWithNoCredentials() throws InterruptedException
	{
		generateReport();
		util.setDelayBtwnSteps(1);
		
		navigateToLoginPage("Edge",map.get("URL"));
		util.clickOn(lp.logInBtn());
		util.verifyIfDisplayed(lp.noAccountFoundMsg());
	}
	
}
