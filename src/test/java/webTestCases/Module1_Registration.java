package webTestCases;

import org.testng.Reporter;
import org.testng.annotations.Test;

import genericLib.ReUseableLib;

public class Module1_Registration extends ReUseableLib {

	@Test
	public void tc001_VerifyRegistrationWithValidData() throws InterruptedException
	{

		test=extent.createTest(Reporter.getCurrentTestResult().getName());
		util.generateReport(test);
		util.setDelayBtwnSteps(1000);

		navigateToRegisterPage("Chrome");

		util.clickOn(rp.genderRadioBtn("Male"));
		util.EnterInto(rp.firstNameTF(), "Karthick");
		util.EnterInto(rp.lastNameTF(), "TestUser");
		util.EnterInto(rp.emailTF(), "testUser"+util.generateRandomNum(5)+"@gmail.com");
		util.EnterInto(rp.pwdTF() ,"Ka@12345");
		util.EnterInto(rp.confirmPwdTF(), "Ka@12345");
		util.clickOn(rp.registerBtn());

		util.verifyIfDisplayed(rp.registrationCompletedMsg());
	}

	@Test
	public void tc002_VerifyRegistrationWithInValidEmail() throws InterruptedException
	{
		test=extent.createTest(Reporter.getCurrentTestResult().getName());
		util.generateReport(test);
		util.setDelayBtwnSteps(1000);

		navigateToRegisterPage("Chrome");

		util.clickOn(rp.genderRadioBtn("Male"));
		util.EnterInto(rp.firstNameTF(), "Karthick");
		util.EnterInto(rp.lastNameTF(), "TestUser");
		util.EnterInto(rp.emailTF(), "testUser"+util.generateRandomNum(5));
		util.EnterInto(rp.pwdTF() ,"Ka@12345");
		util.EnterInto(rp.confirmPwdTF(), "Ka@12345");
		util.clickOn(rp.registerBtn());

		util.verifyIfDisplayed(rp.wrongEmailText());
	}

	@Test
	public void tc003_VerifyRegistrationWithAlreadyRegisteredData() throws InterruptedException
	{
		test=extent.createTest(Reporter.getCurrentTestResult().getName());
		util.generateReport(test);
		util.setDelayBtwnSteps(1000);
		
		navigateToRegisterPage("Chrome");

		util.clickOn(rp.genderRadioBtn("Male"));
		util.EnterInto(rp.firstNameTF(), "Karthick");
		util.EnterInto(rp.lastNameTF(), "TestUser");
		util.EnterInto(rp.emailTF(), "mkar@gmail.com");
		util.EnterInto(rp.pwdTF() ,"Ka@12345");
		util.EnterInto(rp.confirmPwdTF(), "Ka@12345");
		util.clickOn(rp.registerBtn());

		util.verifyIfDisplayed(rp.alreadyExistsMsg());
	}

}
