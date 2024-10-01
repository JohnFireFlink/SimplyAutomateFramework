package genericLib;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Utilities {

	WebDriver driver;
	int delay;
	ExtentTest test;


	public void generateReport(ExtentTest test)
	{
		this.test=test;
	}

	public void setDelayBtwnSteps(int seconds)
	{
		delay=seconds;
	}

	//NLP's
	
	public void openBrowser(String browserName) throws InterruptedException
	{
		String bName = browserName.toLowerCase();

		try 
		{
			switch (bName) {
			case "chrome":
				driver=new ChromeDriver();
				Reporter.log("Chrome browser launched", true);
				test.log(Status.PASS, "Chrome browser launched");
				break;
			case "firefox":
				driver=new FirefoxDriver();
				Reporter.log("Firefox browser launched", true);
				test.log(Status.PASS, "Firefox browser launched");
				break;
			case "edge":
				driver=new EdgeDriver();
				Reporter.log("Edge browser launched", true);
				test.log(Status.PASS, "Edge browser launched");
				break;
			default:
				driver=new ChromeDriver();
				Reporter.log("Launching default browser Chrome ("+bName+" is not valid browser)", true);
				test.log(Status.PASS, "Launching default browser Chrome ("+bName+" is not valid browser)");
				break;

			}
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to launch browser "+e, true);
			test.log(Status.FAIL, "Failed to launch browser - Exception : "+e);
			throw e;
		}

		Thread.sleep(delay);
	}

	public void maximizeBrowser() throws InterruptedException
	{
		try 
		{
			driver.manage().window().maximize();
			Reporter.log("Browser maximized", true);
			test.log(Status.PASS, "Browser maximized");
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to maximize browser", true);
			test.log(Status.FAIL, "Failed to maximize browser - Exception : "+e);
			throw e;
		}

		Thread.sleep(delay);
	}

	public void navigateTo(String url) throws InterruptedException
	{
		try 
		{
			driver.get(url);
			Reporter.log("Navigated to "+url, true);
			test.log(Status.PASS, "Navigated to "+url);
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to navigate to "+url, true);
			test.log(Status.FAIL, "Failed to navigate to "+url+" - Exception : "+e);
			throw e;
		}

		Thread.sleep(delay);
	}

	public void setImplicitWait(int seconds) throws InterruptedException
	{
		try 
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
			Reporter.log("Implicit wait set to "+seconds+" seconds", true);
			test.log(Status.PASS, "Implicit wait set to "+seconds+" seconds");
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to set Implicit wait", true);
			test.log(Status.FAIL, "Failed to set Implicit wait - Exception : "+e);
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public ArrayList<String> getTextOfAllElements(ArrayList<Object> ele) throws InterruptedException
	{
		ArrayList<String> strList=new ArrayList<String>();
		try 
		{
			List<WebElement> elements = driver.findElements((By)ele.get(1));
			for (WebElement element : elements) {
				strList.add(element.getText());
			}
			Reporter.log("List of text fetched from "+(String)ele.get(0)+" are "+strList.toString(), true);
			test.log(Status.PASS, "List of text fetched from "+(String)ele.get(0)+" are "+strList.toString());
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to fetch text from "+(String)ele.get(0), true);
			test.log(Status.FAIL, "Failed to fetch text from "+(String)ele.get(0)+" - Exception : "+e);
			throw e;
		}

		Thread.sleep(delay);
		return strList;
	}

	public void clickOn(ArrayList<Object> ele) throws InterruptedException
	{
		try 
		{
			driver.findElement((By)ele.get(1)).click();
			Reporter.log("Clicked on "+(String)ele.get(0), true);
			test.log(Status.PASS, "Clicked on "+(String)ele.get(0));
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to clicked on "+(String)ele.get(0), true);
			test.log(Status.FAIL, "Failed to clicked on "+(String)ele.get(0)+" - Exception : "+e);
			throw e;
		}

		Thread.sleep(delay);
	}

	public void EnterInto(ArrayList<Object> ele, String input) throws InterruptedException
	{
		try 
		{
			driver.findElement((By)ele.get(1)).sendKeys(input);
			Reporter.log("Entered "+input+" into "+(String)ele.get(0), true);
			test.log(Status.PASS, "Entered "+input+" into "+(String)ele.get(0));
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to enter "+input+" into "+(String)ele.get(0), true);
			test.log(Status.FAIL, "Failed to enter "+input+" into "+(String)ele.get(0)+" - Exception : "+e);
			throw e;
		}

		Thread.sleep(delay);
	}

	public void clearTextUsingShortCutKeys(ArrayList<Object> ele) throws InterruptedException
	{
		try 
		{
			driver.findElement((By)ele.get(1)).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			Reporter.log("Text cleared from "+(String)ele.get(0), true);
			test.log(Status.PASS, "Text cleared from "+(String)ele.get(0));
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to clear text from "+(String)ele.get(0), true);
			test.log(Status.FAIL, "Failed to clear text from "+(String)ele.get(0)+" - Exception : "+e);
			throw e;
		}

		Thread.sleep(delay);
	}

	public void clearTextFrom(ArrayList<Object> ele) throws InterruptedException
	{
		try 
		{
			driver.findElement((By)ele.get(1)).clear();
			Reporter.log("Text cleared from "+(String)ele.get(0), true);
			test.log(Status.PASS, "Text cleared from "+(String)ele.get(0));
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to clear text from "+(String)ele.get(0), true);
			test.log(Status.FAIL, "Failed to clear text from "+(String)ele.get(0)+" - Exception : "+e);
			throw e;
		}

		Thread.sleep(delay);
	}

	public void waitTillVisibilityOf(ArrayList<Object> ele, int seconds) throws InterruptedException
	{
		try 
		{
			Duration Implicitwait = driver.manage().timeouts().getImplicitWaitTimeout();
			driver.manage().timeouts().implicitlyWait(Duration.ZERO);
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.visibilityOfElementLocated((By)ele.get(1)));
			driver.manage().timeouts().implicitlyWait(Implicitwait);
			Reporter.log("Waited till "+(String)ele.get(0)+" is visible", true);
			test.log(Status.PASS, "Waited till "+(String)ele.get(0)+" is visible");
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to wait till "+(String)ele.get(0), true);
			test.log(Status.FAIL, "Failed to wait till "+(String)ele.get(0)+" - Exception : "+e);
			throw e;
		}

		Thread.sleep(delay);
	}

	public boolean verifyIfDisplayed(ArrayList<Object> ele) throws InterruptedException
	{
		boolean val=false;
		try 
		{
			val=driver.findElement((By)ele.get(1)).isDisplayed();
			if (val) 
			{
				Reporter.log((String)ele.get(0)+" is displayed", true);
				test.log(Status.PASS, (String)ele.get(0)+" is displayed");
			}
			else
			{
				Reporter.log((String)ele.get(0)+" is not displayed", true);
				test.log(Status.PASS, (String)ele.get(0)+" is not displayed");
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to verify "+(String)ele.get(0), true);
			test.log(Status.FAIL, "Failed to verify "+(String)ele.get(0)+" - Exception : "+e);
			throw e;
		}

		Thread.sleep(delay);
		return val;
	}

	public void closeBrowser()
	{
		try 
		{
			driver.quit();
			Reporter.log("Browser closed", true);
			test.log(Status.PASS,"Browser closed");
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to close browser", true);
			test.log(Status.FAIL,"Failed to close browser - Exception : "+e);
			throw e;
		}

	}

	public void clearTextAndEnterInto(ArrayList<Object> ele, String input) throws InterruptedException
	{
		try 
		{
			driver.findElement((By)ele.get(1)).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			driver.findElement((By)ele.get(1)).sendKeys(input);
			Reporter.log("Text cleared and entered "+input+" into "+(String)ele.get(0), true);
			test.log(Status.PASS, "Text cleared and entered "+input+" into "+(String)ele.get(0));
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to clear and enter "+input+" into "+(String)ele.get(0), true);
			test.log(Status.FAIL, "Failed to clear and enter "+input+" into "+(String)ele.get(0)+" - Exception : "+e);
			throw e;
		}
		Thread.sleep(delay);
	}

	public void clickUsingJavaScript(ArrayList<Object> ele) throws InterruptedException
	{
		try 
		{
			WebElement element = driver.findElement((By)ele.get(1));
			JavascriptExecutor jse=(JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			Reporter.log("Clicked on "+(String)ele.get(0), true);
			test.log(Status.PASS, "Clicked on "+(String)ele.get(0));
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to clicked on "+(String)ele.get(0), true);
			test.log(Status.FAIL, "Failed to clicked on "+(String)ele.get(0)+" - Exception : "+e);
			throw e;
		}

		Thread.sleep(delay);
	}

	public void openHeadlessBrowser(String browserName) throws InterruptedException
	{
		String bName = browserName.toLowerCase();
		try 
		{
			switch (bName) {
			case "chrome":
				ChromeOptions ChromeOpt = new ChromeOptions();
				ChromeOpt.addArguments("--headless");
				driver=new ChromeDriver(ChromeOpt);
				Reporter.log("Chrome browser launched", true);
				test.log(Status.PASS, "Chrome browser launched");
				break;
			case "firefox":
				FirefoxOptions ffopt = new FirefoxOptions();
				ffopt.addArguments("-headless");
				driver=new FirefoxDriver(ffopt);
				Reporter.log("Firefox browser launched", true);
				test.log(Status.PASS, "Firefox browser launched");
				break;
			case "edge":
				EdgeOptions edgeOpt=new EdgeOptions();
				edgeOpt.addArguments("--headless");
				driver=new EdgeDriver(edgeOpt);
				Reporter.log("Edge browser launched", true);
				test.log(Status.PASS, "Edge browser launched");
				break;
			default:
				ChromeOptions opt = new ChromeOptions();
				opt.addArguments("--headless");
				driver=new ChromeDriver(opt);
				Reporter.log("Launching default browser Chrome ("+bName+" is not valid browser)", true);
				test.log(Status.PASS, "Launching default browser Chrome ("+bName+" is not valid browser)");
				break;	
			}
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to launch browser "+e, true);
			test.log(Status.FAIL, "Failed to launch browser - Exception : "+e);
			throw e;
		}
		Thread.sleep(delay);
	}

	public int generateRandomNum(int digits)
	{
		int randNum=0;

		try {
			int min=1;
			int max=10;
			for (int i = 1; i < digits; i++) 
			{
				min=min*10;
				max=max*10;
			}
			int newMin=min;
			int newMax=max-1;
			Random rand=new Random();
			randNum = rand.ints(newMin, newMax).findFirst().getAsInt();
			Reporter.log("Random Number generated : "+randNum, true);
			test.log(Status.PASS, "Random Number generated : "+randNum);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to generate random numbers", true);
			test.log(Status.FAIL, "Failed to generate random numbers - Exception : "+e);
			throw e;
		}

		return randNum;
	}

	public String concatString(Object s1, Object s2)
	{
		String str="";
		try 
		{
			str = (String)s1+(String)s2;
			Reporter.log("Concatinated String : "+str, true);
			test.log(Status.PASS, "Concatinated String : "+str);
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to concat", true);
			test.log(Status.FAIL, "Failed to concat");
			throw e;
		}
		
		return str;
	}
}
