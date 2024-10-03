package pomPages;

import java.util.ArrayList;

import org.openqa.selenium.By;

public class DemoPage1 
{
	public ArrayList<Object> elementNameAndType()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		//Add Element name
		ele.add("");
		//Add Element locator (change the locatorType if required)
		ele.add(By.className(""));	
		return ele;
	}
	
	public ArrayList<Object> gmailLink()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		//Add Element name
		ele.add("Gmail Link");
		//Add Element locator (change the locatorType if required)
		ele.add(By.xpath("//a[@aria-label='Gmail ']"));	
		return ele;
	}
	
	public ArrayList<Object> createAccBtn()
	{
		ArrayList<Object> ele=new ArrayList<Object>();
		//Add Element name
		ele.add("Create Account button");
		//Add Element locator (change the locatorType if required)
		ele.add(By.xpath("//div[@class='header__aside']//div[@class='dropdown__label' and normalize-space(text())='Create an account']"));	
		return ele;
	}
}
	
