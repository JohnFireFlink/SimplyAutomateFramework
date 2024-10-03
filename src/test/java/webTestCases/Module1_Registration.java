package webTestCases;

import java.util.Map;

import org.testng.Reporter;
import org.testng.annotations.Test;

import genericLib.ReUseableLib;

public class Module1_Registration extends ReUseableLib {

	@Test
	public void tc001_VerifyRegistrationWithValidData() throws InterruptedException
	{
		generateReport();
		Map<String, String> mapData = util.readFromGoogleSheetForUniqueDataInSingleRowTable("Registration", "tc001");
		util.setDelayBtwnSteps(1);
		Reporter.log(os);
		navigateToRegisterPage("Chrome",map.get("URL"));
		
		util.clickOn(rp.genderRadioBtn("Male"));
		util.EnterInto(rp.firstNameTF(), mapData.get("FirstName"));
		util.EnterInto(rp.lastNameTF(), mapData.get("LastName"));
		util.EnterInto(rp.emailTF(), mapData.get("DomainName")+util.generateRandomNum(5)+"@gmail.com");
		util.EnterInto(rp.pwdTF() ,mapData.get("Password"));
		util.EnterInto(rp.confirmPwdTF(), mapData.get("Password"));
		util.clickOn(rp.registerBtn());

		util.verifyIfDisplayed(rp.registrationCompletedMsg());
	}

	@Test
	public void tc002_VerifyRegistrationWithInValidEmail() throws InterruptedException
	{
		generateReport();
		Map<String, String> mapData = util.readFromGoogleSheetForUniqueDataInSingleRowTable("Registration", "tc002");
		util.setDelayBtwnSteps(1);

		navigateToRegisterPage("FireFox",map.get("URL"));

		util.clickOn(rp.genderRadioBtn("Male"));
		util.EnterInto(rp.firstNameTF(),mapData.get("FirstName"));
		util.EnterInto(rp.lastNameTF(), mapData.get("LastName"));
		util.EnterInto(rp.emailTF(), mapData.get("InvalidEmail"));
		util.EnterInto(rp.pwdTF() ,mapData.get("Password"));
		util.EnterInto(rp.confirmPwdTF(), mapData.get("Password"));
		util.clickOn(rp.registerBtn());

		util.verifyIfDisplayed(rp.wrongEmailText());
	}

	@Test
	public void tc003_VerifyRegistrationWithAlreadyRegisteredData() throws InterruptedException
	{
		generateReport();
		Map<String, String> mapData = util.readFromGoogleSheetForUniqueDataInSingleRowTable("Registration", "tc003");
		util.setDelayBtwnSteps(1);
		
		navigateToRegisterPage("Chrome",map.get("URL"));

		util.clickOn(rp.genderRadioBtn("Male"));
		util.EnterInto(rp.firstNameTF(), mapData.get("FirstName"));
		util.EnterInto(rp.lastNameTF(), mapData.get("LastName"));
		util.EnterInto(rp.emailTF(), mapData.get("RegisteredEmail"));
		util.EnterInto(rp.pwdTF() ,mapData.get("Password"));
		util.EnterInto(rp.confirmPwdTF(), mapData.get("Password"));
		util.clickOn(rp.registerBtn());

		util.verifyIfDisplayed(rp.alreadyExistsMsg());
		
	}

}
