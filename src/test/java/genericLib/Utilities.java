package genericLib;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONArray;
import org.json.JSONObject;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utilities {

	WebDriver driver;
	int delay;
	ExtentTest test;
	String passMessage;
	String failMessage;
	
	public Utilities(ExtentTest test)
	{
		this.test=test;
	}

	public void setDelayBtwnSteps(int seconds)
	{
		delay=seconds*1000;
	}
	
	private JSONObject getGoogleSheetData(String sheetName)
	{
		String range = "A1:Z500";	//Increase range if required
		String sheetID=""; 			//Enter your own Google SheetId
		String apiKey=""; 			//Generate your GoogleSheet API key and enter the same
		
		String sheetRange=sheetName+"!"+range;
		String sheetURL="https://sheets.googleapis.com/v4/spreadsheets/"+sheetID+"/values/"+sheetRange;
		RestAssured.baseURI=sheetURL;
		RequestSpecification request = RestAssured.given();
		Response response = request.header("X-goog-api-key", apiKey).get();
		String str = response.getBody().asString();
		JSONObject jsonObj = new JSONObject(str);
		return jsonObj;
	}


//	Sample Code for adding new NLP:
	
//	public void demoNLP(ArrayList<Object> ele) throws InterruptedException
//	{
//		try 
//		{   
//			Element = driver.findElement((By)ele.get(1));
//			ElementName = (String)ele.get(0);
//	
//			Type your logic here...
//	
//			passMessage="Message to be displayed";
//
//			Reporter.log(passMessage, true);
//			if(test!=null)
//			{
//			test.log(Status.PASS, passMessage);
//			}
//		} 
//		catch (Exception e) 
//		{
//	        failMessage="Message to be displayed";
//			Reporter.log(failMessage, true);
//			if(test!=null)
//			{
//			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
//			}
//			throw e;
//		}
//
//		Thread.sleep(delay);
//	}
	
	
//NLP's
	
	public void openChromeWithProfile(String profilePath) throws InterruptedException
	{
		try 
		{   
			ChromeOptions opt=new ChromeOptions();
			String userDirPath = System.getProperty("user.dir")+"\\src\\test\\resources\\";
			opt.addArguments("--user-data-dir="+userDirPath+profilePath);
			driver=new ChromeDriver(opt);
			passMessage="Opened browser with specified profile";

			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
	        failMessage="Failed to open browser";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void openHeadLessChromeWithProfile(String profilePath) throws InterruptedException
	{
		try 
		{   
			ChromeOptions opt=new ChromeOptions();
			String userDirPath = System.getProperty("user.dir")+"\\src\\test\\resources\\";
			opt.addArguments("--user-data-dir="+userDirPath+profilePath);
			opt.addArguments("--headless");
			driver=new ChromeDriver(opt);
			passMessage="Opened browser with specified profile";

			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
	        failMessage="Failed to open browser";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void openBrowser(String browserName) throws InterruptedException
	{
		String bName = browserName.toLowerCase();

		try 
		{
			switch (bName) {
			case "chrome":
				driver=new ChromeDriver();
				passMessage="Chrome browser launched";
				Reporter.log(passMessage, true);
				if(test!=null)
				{
				test.log(Status.PASS, passMessage);
				}
				break;
			case "firefox":
				driver=new FirefoxDriver();
				passMessage="Firefox browser launched";
				Reporter.log(passMessage, true);
				if(test!=null)
				{
				test.log(Status.PASS, passMessage);
				}
				break;
			case "edge":
				driver=new EdgeDriver();
				passMessage="Edge browser launched";
				Reporter.log(passMessage, true);
				if(test!=null)
				{
				test.log(Status.PASS, passMessage);
				}
				break;
			default:
				driver=new ChromeDriver();
				passMessage="Launching default browser Chrome ("+bName+" is not valid browser)";
				Reporter.log(passMessage, true);
				if(test!=null)
				{
				test.log(Status.PASS, passMessage);
				}
				break;

			}
		}
		catch (Exception e) 
		{
			failMessage="Failed to launch browser";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}

	public void maximizeBrowser() throws InterruptedException
	{
		try 
		{
			driver.manage().window().maximize();
			passMessage="Browser maximized";
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to maximize browser";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}

	public void navigateTo(String url) throws InterruptedException
	{
		try 
		{
			driver.get(url);
			passMessage="Navigated to "+url;
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to navigate to "+url;
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}

	public void setImplicitWait(int seconds) throws InterruptedException
	{
		try 
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
			passMessage="Implicit wait set to "+seconds+" seconds";
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to set Implicit wait";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public String getCurrentPageTitle() throws InterruptedException
	{
		String title="";
		try 
		{   
			title=driver.getTitle();
	
			passMessage="Page title : "+title;
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
	        failMessage="Failed to fetch title of current page";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
		return title;
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
			passMessage="List of text fetched from "+(String)ele.get(0)+" are "+strList.toString();
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to fetch text from "+(String)ele.get(0);
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
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
			passMessage="Clicked on "+(String)ele.get(0);
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to clicked on "+(String)ele.get(0);
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void doubleClickOn(ArrayList<Object> ele) throws InterruptedException
	{
		try 
		{
			Actions act=new Actions(driver);
			act.doubleClick(driver.findElement((By)ele.get(1))).perform();
			passMessage="Double clicked on "+(String)ele.get(0);
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to double click on "+(String)ele.get(0);
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void rightClickOn(ArrayList<Object> ele) throws InterruptedException
	{
		try 
		{
			Actions act=new Actions(driver);
			act.contextClick(driver.findElement((By)ele.get(1))).perform();
			passMessage="Right clicked on "+(String)ele.get(0);
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to right click on "+(String)ele.get(0);
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void mouseHoverOn(ArrayList<Object> ele) throws InterruptedException
	{
		try 
		{
			Actions act=new Actions(driver);
			act.moveToElement(driver.findElement((By)ele.get(1))).perform();
			passMessage="Mouse hovered on "+(String)ele.get(0);
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to Mouse hover on "+(String)ele.get(0);
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void clickOnCoordinate(int x, int y) throws InterruptedException
	{
		try 
		{
			Actions act=new Actions(driver);
			act.moveByOffset(x,y).click().perform();
			passMessage="Clicked on "+x+","+y;
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to click on "+x+","+y;
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void clickOnCurrentCursorPoint() throws InterruptedException
	{
		try 
		{
			Actions act=new Actions(driver);
			act.click().perform();
			passMessage="Clicked on current cursor point";
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to click on current cursor point";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}

	public void EnterInto(ArrayList<Object> ele, String input) throws InterruptedException
	{
		try 
		{
			driver.findElement((By)ele.get(1)).sendKeys(input);
			passMessage="Entered "+input+" into "+(String)ele.get(0);
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to enter "+input+" into "+(String)ele.get(0);
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}

	public void clearTextUsingShortCutKeys(ArrayList<Object> ele) throws InterruptedException
	{
		try 
		{
			driver.findElement((By)ele.get(1)).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			passMessage="Text cleared from "+(String)ele.get(0);
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to clear text from "+(String)ele.get(0);
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}

	public void clearTextFrom(ArrayList<Object> ele) throws InterruptedException
	{
		try 
		{
			driver.findElement((By)ele.get(1)).clear();
			passMessage="Text cleared from "+(String)ele.get(0);
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to clear text from "+(String)ele.get(0);
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
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
			passMessage="Waited till "+(String)ele.get(0)+" is visible";
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to wait till "+(String)ele.get(0);
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
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
				passMessage=(String)ele.get(0)+" is displayed";
				Reporter.log(passMessage, true);
				if(test!=null)
				{
				test.log(Status.PASS, passMessage);
				}
			}
			else
			{
				passMessage=(String)ele.get(0)+" is not displayed";
				Reporter.log(passMessage, true);
				if(test!=null)
				{
				test.log(Status.PASS, passMessage);
				}
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to verify "+(String)ele.get(0);
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
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
			passMessage="Browser closed";
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to close browser";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL,failMessage+"  - Exception : "+e);
			}
			throw e;
		}

	}

	public void clearTextAndEnterInto(ArrayList<Object> ele, String input) throws InterruptedException
	{
		try 
		{
			driver.findElement((By)ele.get(1)).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			driver.findElement((By)ele.get(1)).sendKeys(input);
			passMessage="Text cleared and entered "+input+" into "+(String)ele.get(0);
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to clear and enter "+input+" into "+(String)ele.get(0);
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
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
			passMessage="Clicked on "+(String)ele.get(0);
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to clicked on "+(String)ele.get(0);
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
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
				passMessage="Chrome browser launched";
				Reporter.log(passMessage, true);
				if(test!=null)
				{
				test.log(Status.PASS, passMessage);
				}
				break;
			case "firefox":
				FirefoxOptions ffopt = new FirefoxOptions();
				ffopt.addArguments("-headless");
				driver=new FirefoxDriver(ffopt);
				passMessage="FireFox browser launched";
				Reporter.log(passMessage, true);
				if(test!=null)
				{
				test.log(Status.PASS, passMessage);
				}
				break;
			case "edge":
				EdgeOptions edgeOpt=new EdgeOptions();
				edgeOpt.addArguments("--headless");
				driver=new EdgeDriver(edgeOpt);
				passMessage="Edge browser launched";
				Reporter.log(passMessage, true);
				if(test!=null)
				{
				test.log(Status.PASS, passMessage);
				}
				break;
			default:
				ChromeOptions opt = new ChromeOptions();
				opt.addArguments("--headless");
				driver=new ChromeDriver(opt);
				passMessage="Launching default browser Chrome ("+bName+" is not valid browser)";
				Reporter.log(passMessage, true);
				if(test!=null)
				{
				test.log(Status.PASS, passMessage);
				}
				break;	
			}
		}
		catch (Exception e) 
		{
			failMessage="Failed to launch browser";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
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
			passMessage="Random Number generated : "+randNum;
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		}
		catch (Exception e) 
		{
			failMessage="Failed to generate random numbers";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
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
			passMessage="Concatinated String : "+str;
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to concat";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}
		
		return str;
	}
	
	public Map<String, String> readFromGoogleSheetForUniqueDataInMultiRowTable(String sheetName, String colHeaderName, String uniqueData)
	{
		
		Map<String, String> mapData=new HashMap<String, String>();
		
		try 
		{
			JSONObject jsonObj = getGoogleSheetData(sheetName);
			
			List<ArrayList<String>> data = (ArrayList<ArrayList<String>>)jsonObj.toMap().get("values");
			ArrayList<String> headerRow = data.get(0);
			int i=0;
			int j=0;
			for (String header : headerRow) 
			{	
				if(header.equals(colHeaderName))
				{
					break;
				}
				i++;
			}
			if(i==headerRow.size())
			{
				throw new NullPointerException(); 
			}
			for (ArrayList<String> aList : data) 
			{
				String val =  aList.get(i);
				
				if(val.equals(uniqueData))
				{
					break;
				}
				j++;
			}
			if(j==data.size())
			{
				throw new IndexOutOfBoundsException(); 
			}
			ArrayList<String> rowData = data.get(j);
			if(rowData.size()!=headerRow.size())
			{
				int loop = headerRow.size()-rowData.size();
				
				for (int k = 0; k < loop; k++) 
				{
					rowData.add("");
				}	
			}
			for (int k = 0; k < headerRow.size() ; k++) 
			{
				mapData.put(headerRow.get(k), rowData.get(k));
			}
			passMessage="MapData : "+mapData.toString();
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (NullPointerException e) 
		{
			failMessage="No header found";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage);
			}
			throw e;
		}
		catch (IndexOutOfBoundsException e) 
		{
			failMessage="Unique Data not found";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage);
			}
			throw e;
		}
		catch (Exception e) 
		{
			failMessage="Failed to read data";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}
		return mapData;
	}
	
	public Map<String,String> readFromGoogleSheetForUniqueDataInSingleRowTable(String sheetName, String uniqueData)
	{
		Boolean loop=true;
		int uniqueDataIndex=0;
		Map<String,String> mapData=new HashMap<String, String>();
		
		try 
		{	
			JSONObject jsonObj = getGoogleSheetData(sheetName);

			JSONArray data = (JSONArray) jsonObj.get("values");
			for (int i = 0; i < data.length(); i++) 
			{
				JSONArray array = (JSONArray) data.get(i);
				if(array.length()==0)
				{
					data.remove(i);
				}
			}
			int num=0;
			for (int i = 0; i < data.length(); i++) 
			{
				if(loop==true)
				{
					JSONArray array = (JSONArray) data.get(i);
					for (int j = 0; j < array.length(); j++) 
					{
						if (array.get(j).equals(uniqueData)) 
						{
							uniqueDataIndex=i;
							loop=false;
							num=0;
							break;
						}
					}
					num++;
				}
				else
				{
					break;
				}
			}
			if(num==data.length())
			{
				return mapData;
			}
			else {

				JSONArray val=(JSONArray) data.get(uniqueDataIndex);
				JSONArray key=(JSONArray) data.get(uniqueDataIndex-1);
				
				if(key.length()!=val.length())
				{
					int missData = key.length()-val.length();
					int lastIndex = key.length()-1;
					
					for (int i = missData; i > 0; i--) 
					{
						val.put(lastIndex, "");
						lastIndex--;
					}
				}
				for (int i = 0; i < key.length(); i++) 
				{
					mapData.put((String)key.get(i), (String)val.get(i));
				}
			}
			passMessage="MapData : "+mapData.toString();
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to read data";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}
		
		return mapData;
	}
	
	public void selectDropdownOptionByIndex(ArrayList<Object> ele, int index) throws InterruptedException
	{
		try 
		{
			Select s=new Select(driver.findElement((By)ele.get(1)));
			s.selectByIndex(index);
			passMessage="selected index : "+index+" from "+(String)ele.get(0);
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to select index : "+index;
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void selectDropdownOptionByValueAttribute(ArrayList<Object> ele, String val) throws InterruptedException
	{
		try 
		{
			Select s=new Select(driver.findElement((By)ele.get(1)));
			s.selectByValue(val);
			passMessage="selected "+val+" from "+(String)ele.get(0);
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to select : "+val;
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void selectDropdownOptionByText(ArrayList<Object> ele, String text) throws InterruptedException
	{
		try 
		{
			Select s=new Select(driver.findElement((By)ele.get(1)));
			s.selectByVisibleText(text);
			passMessage="selected "+text+" from "+(String)ele.get(0);
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to select : "+text;
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void switchTabIfUrlIs(String url) throws InterruptedException
	{
		String parentId="";
		try 
		{
			parentId=driver.getWindowHandle();
			Set<String> sessionId = driver.getWindowHandles();
			int loop=0;
			for (String id : sessionId) {
				driver.switchTo().window(id);
				if (driver.getCurrentUrl().equals(url)) {
					break;
				}
				loop++;
			}
			if (loop==sessionId.size()) 
			{
				driver.switchTo().window(parentId);
				throw new NullPointerException(); 
			}
			passMessage="Switched to tab with Url : "+url;
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (NullPointerException e) 
		{
			failMessage="Failed to switch : No Url found";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage);
			}
			throw e;
		}
		catch (Exception e) 
		{
			failMessage="Failed to switch";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void switchTabIfTitleIs(String title) throws InterruptedException
	{
		String parentId="";
		try 
		{
			parentId=driver.getWindowHandle();
			Set<String> sessionId = driver.getWindowHandles();
			int loop=0;
			for (String id : sessionId) {
				driver.switchTo().window(id);
				if (driver.getTitle().equals(title)) {
					break;
				}
				loop++;
			}
			if (loop==sessionId.size()) 
			{
				driver.switchTo().window(parentId);
				throw new NullPointerException(); 
			}
			passMessage="Switched to tab with title : "+title;
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (NullPointerException e) 
		{
			failMessage="Failed to switch : No title found";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage);
			}
			throw e;
		}
		catch (Exception e) 
		{
			failMessage="Failed to switch";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}

	public void switchTabIfUrlContains(String url) throws InterruptedException
	{
		String parentId="";
		try 
		{
			parentId=driver.getWindowHandle();
			Set<String> sessionId = driver.getWindowHandles();
			int loop=0;
			for (String id : sessionId) {
				driver.switchTo().window(id);
				if (driver.getCurrentUrl().contains(url)) {
					break;
				}
				loop++;
			}
			if (loop==sessionId.size()) 
			{
				driver.switchTo().window(parentId);
				throw new NullPointerException(); 
			}
			passMessage="Switched to tab containing Url : "+url;
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (NullPointerException e) 
		{
			failMessage="Failed to switch : No Url found";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage);
			}
			throw e;
		}
		catch (Exception e) 
		{
			failMessage="Failed to switch";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void switchTabIfTitleContains(String title) throws InterruptedException
	{
		String parentId="";
		try 
		{
			parentId=driver.getWindowHandle();
			Set<String> sessionId = driver.getWindowHandles();
			int loop=0;
			for (String id : sessionId) {
				driver.switchTo().window(id);
				if (driver.getTitle().contains(title)) {
					break;
				}
				loop++;
			}
			if (loop==sessionId.size()) 
			{
				driver.switchTo().window(parentId);
				throw new NullPointerException(); 
			}
			passMessage="Switched to tab containing title : "+title;
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (NullPointerException e) 
		{
			failMessage="Failed to switch : No title found";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage);
			}
			throw e;
		}
		catch (Exception e) 
		{
			failMessage="Failed to switch";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void scrollTillVisibilityOF(ArrayList<Object> ele) throws InterruptedException
	{
		try 
		{   
			WebElement element = driver.findElement((By)ele.get(1));
			JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            passMessage="Scrolled till "+(String)ele.get(0);
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to scroll till "+(String)ele.get(0);
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void dragAndDrop(ArrayList<Object> dragEle, ArrayList<Object> dropEle) throws InterruptedException
	{
		try 
		{   
			WebElement drag = driver.findElement((By)dragEle.get(1));
			WebElement drop = driver.findElement((By)dropEle.get(1));
			Actions act=new Actions(driver);
			act.dragAndDrop(drag, drop).perform();
			passMessage="Dragged "+(String)dragEle.get(0)+" and dropped into "+(String)dropEle.get(0);
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to drag and drop";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void switchToIframeWithIndex(int index) throws InterruptedException
	{
		try 
		{   
			driver.switchTo().frame(index);
			passMessage="Switched to iFrame with index : "+index;
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to switch to iFrame with index : "+index;
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void switchToIframeWithNameOrId(String idOrName) throws InterruptedException
	{
		try 
		{   
			driver.switchTo().frame(idOrName);
			passMessage="Switched to iFrame with id or name = "+idOrName;
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to switch to iFrame with id or name = "+idOrName;
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void switchToIframeWithElemnet(ArrayList<Object> ele) throws InterruptedException
	{
		try 
		{   
			driver.switchTo().frame(driver.findElement((By)ele.get(1)));
			passMessage="Switched to "+(String)ele.get(0);
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to switch to "+(String)ele.get(0);
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void switchToParentFrame() throws InterruptedException
	{
		try 
		{   
			driver.switchTo().parentFrame();
			passMessage="Switched to parent frame";
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to switch to parent frame";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void switchToDefaultWindow() throws InterruptedException
	{
		try 
		{   
			driver.switchTo().defaultContent();
			passMessage="Switched to default window";
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to switch to default window";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void alertPopup_ClickOKButton() throws InterruptedException
	{
		try 
		{   
			driver.switchTo().alert().accept();
			passMessage="Clicked ok button on Alert popup";
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to click on ok button on Alert popup";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void alertPopup_ClickCancelButton() throws InterruptedException
	{
		try 
		{   
			driver.switchTo().alert().dismiss();
			passMessage="Clicked cancel button on Alert popup";
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to click on cancel button on Alert popup";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void alertPopup_EnterMessage(String input) throws InterruptedException
	{
		try 
		{   
			driver.switchTo().alert().sendKeys(input);
			passMessage="Entered "+input+" on Alert popup";
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to enter "+input+" on Alert popup";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public void UploadFile(ArrayList<Object> ele, String filePath) throws InterruptedException
	{
		try 
		{   
			driver.findElement((By)ele.get(1)).sendKeys(filePath);
			passMessage="Uploaded file : "+filePath;
			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (Exception e) 
		{
			failMessage="Failed to upload file : "+filePath;
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
	public Map<String, String> getDataFromXLforUniqueData_SingleRowTable(String sheetName, int uniqueColIndex, String uniqueData) throws Exception 
	{
		List<String> keys=new ArrayList<String>();
		List<String> values=new ArrayList<String>();
		Map<String, String> map=new HashMap<String, String>();
		try 
		{   
			FileInputStream fis=new FileInputStream("./src/test/resources/AutomateSimply.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			int noOfRows = sh.getLastRowNum()+1;
			int firstRowIndex=sh.getFirstRowNum();
			int uniqueDataRowIndex=0;
			String data="";
			int loop=0;
			for (int i = firstRowIndex; i < noOfRows; i++) 
			{
				try {
					data = sh.getRow(i).getCell(uniqueColIndex).getStringCellValue();
				} catch (Exception e) {	}
				
				if (data.equals(uniqueData)) 
				{
					uniqueDataRowIndex=i;
					break;
				}
				loop++;
			}
			if (loop==noOfRows) {
				throw new NullPointerException();
			}
			int firstCellIndex = sh.getRow(uniqueDataRowIndex-1).getFirstCellNum();
			int lastCellIndex=sh.getRow(uniqueDataRowIndex-1).getLastCellNum();
			
			for (int i = firstCellIndex; i < lastCellIndex; i++) 
			{
				keys.add(sh.getRow(uniqueDataRowIndex-1).getCell(i).getStringCellValue());
			}
			for (int i = firstCellIndex; i < lastCellIndex; i++) 
			{
				values.add(sh.getRow(uniqueDataRowIndex).getCell(i).getStringCellValue()); 
			}
			
			for (int i = 0; i < keys.size(); i++) {
				map.put(keys.get(i), values.get(i));
			}
			
			passMessage="MapData : "+map;

			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (NullPointerException e) 
		{
	        failMessage="No uniqueData:"+uniqueData+" found";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}
		catch (Exception e) 
		{
	        failMessage="Failed to get data";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
		return map;
	}
	
	public Map<String, String> getDataFromXLforUniqueData_MultiRowTable(String sheetName, int uniqueColIndex, String uniqueData) throws Exception 
	{
		List<String> keys=new ArrayList<String>();
		List<String> values=new ArrayList<String>();
		Map<String, String> map=new HashMap<String, String>();
		try 
		{   
			FileInputStream fis=new FileInputStream("./src/test/resources/AutomateSimply.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			int noOfRows = sh.getLastRowNum()+1;
			int firstRowIndex=sh.getFirstRowNum();
			int uniqueDataRowIndex=0;
			String data="";
			int loop=0;
			for (int i = firstRowIndex; i < noOfRows; i++) 
			{
				try {
					data = sh.getRow(i).getCell(uniqueColIndex).getStringCellValue();
				} catch (Exception e) {	}
				
				if (data.equals(uniqueData)) 
				{
					uniqueDataRowIndex=i;
					break;
				}
				loop++;
			}
			if (loop==noOfRows) {
				throw new NullPointerException();
			}
			int firstCellIndex = sh.getRow(firstRowIndex).getFirstCellNum();
			int lastCellIndex=sh.getRow(firstRowIndex).getLastCellNum();
			
			for (int i = firstCellIndex; i < lastCellIndex; i++) 
			{
				keys.add(sh.getRow(firstRowIndex).getCell(i).getStringCellValue());
			}
			for (int i = firstCellIndex; i < lastCellIndex; i++) 
			{
				values.add(sh.getRow(uniqueDataRowIndex).getCell(i).getStringCellValue()); 
			}
			
			for (int i = 0; i < keys.size(); i++) {
				map.put(keys.get(i), values.get(i));
			}
			
			passMessage="MapData : "+map;

			Reporter.log(passMessage, true);
			if(test!=null)
			{
			test.log(Status.PASS, passMessage);
			}
		} 
		catch (NullPointerException e) 
		{
	        failMessage="No uniqueData:"+uniqueData+" found";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}
		catch (Exception e) 
		{
	        failMessage="Failed to get data";
			Reporter.log(failMessage, true);
			if(test!=null)
			{
			test.log(Status.FAIL, failMessage+"  - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
		return map;
	}
}
