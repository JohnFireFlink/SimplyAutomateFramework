package pomPages;

import java.util.ArrayList;

import org.openqa.selenium.By;

public class LoginPage 
{
	public ArrayList<Object> WelcomeTxt()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("Welcome Text");
		ele.add(By.xpath("//div[@class='page-title']/h1[text()='Welcome, Please Sign In!']"));
		return ele;
	}
	
	public ArrayList<Object> logInBtn()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("Login button");
		ele.add(By.xpath("//input[@value='Log in']"));
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
	
	public ArrayList<Object> IncorrectCredMsg()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("Incorrect credentials Msg");
		ele.add(By.xpath("//div[@class='validation-summary-errors']//li[text()='The credentials provided are incorrect']"));
		return ele;
	}
	
	public ArrayList<Object> noAccountFoundMsg()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("No account found Msg");
		ele.add(By.xpath("//div[@class='validation-summary-errors']//li[text()='No customer account found']"));
		return ele;
	}
}
	
