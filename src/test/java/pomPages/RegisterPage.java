package pomPages;

import java.util.ArrayList;

import org.openqa.selenium.By;

public class RegisterPage 
{
	
	public ArrayList<Object> registerTxt()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("Register Text");
		ele.add(By.xpath("//div[@class='page-title']/h1[text()='Register']"));
		return ele;
	}
	
	public ArrayList<Object> genderRadioBtn(String gender)
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add(gender+" Radio button");
		ele.add(By.xpath("//label[text()='Gender:']/following-sibling::div/label[text()='"+gender+"']"));
		return ele;
	}
	
	public ArrayList<Object> firstNameTF()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("FirstName TextField");
		ele.add(By.id("FirstName"));
		return ele;
	}
	
	public ArrayList<Object> lastNameTF()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("LastName TextField");
		ele.add(By.id("LastName"));
		return ele;
	}
	
	public ArrayList<Object> emailTF()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("Email TextField");
		ele.add(By.id("Email"));
		return ele;
	}
	
	public ArrayList<Object> pwdTF()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("Password TextField");
		ele.add(By.id("Password"));
		return ele;
	}
	
	public ArrayList<Object> confirmPwdTF()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("Confirm Password TextField");
		ele.add(By.id("ConfirmPassword"));
		return ele;
	}
	
	public ArrayList<Object> registerBtn()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("Register button");
		ele.add(By.id("register-button"));
		return ele;
	}
	
	public ArrayList<Object> registrationCompletedMsg()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("Registration Completed Msg");
		ele.add(By.xpath("//div[normalize-space(.)='Your registration completed' and @class='result']"));
		return ele;
	}
	
	public ArrayList<Object> wrongEmailText()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("Wrong Email Text");
		ele.add(By.xpath("//span[@for='Email' and text()='Wrong email']"));
		return ele;
	}
	
	public ArrayList<Object> alreadyExistsMsg()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("Already exists error Msg");
		ele.add(By.xpath("//div[@class='validation-summary-errors']//li[text()='The specified email already exists']"));
		return ele;
	}
}
	
