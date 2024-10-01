package pomPages;

import java.util.ArrayList;

import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class HomePage 
{
	
	public ArrayList<Object> registerLink()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("Register Link");
		ele.add(By.className("ico-register"));	
		return ele;
	}
	
	public ArrayList<Object> logoutLink()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("Logout Link");
		ele.add(By.className("ico-logout"));
		return ele;
	}
	
	public ArrayList<Object> logInLink()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("LogIn Link");
		ele.add(By.className("ico-login"));
		return ele;
	}
	
	public ArrayList<Object> searchTF()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("Search TF");
		ele.add(By.id("small-searchterms"));
		return ele;
	}
	
	public ArrayList<Object> searchSuggestions()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("Search Suggestions");
		ele.add(By.xpath("//ul[contains(@class,'ui-menu')]/li/a"));
		return ele;
	}
	
	public ArrayList<Object> searchSuggestion(String text)
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add(text);
		ele.add(By.xpath("//ul[contains(@class,'ui-menu')]/li/a[text()='"+text+"']"));
		return ele;
	}

	public ArrayList<Object> searchBtn()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		ele.add("Search Button");
		ele.add(By.xpath("//input[@value='Search store']/following-sibling::input[@type='submit']"));
		return ele;
	}
}
	
