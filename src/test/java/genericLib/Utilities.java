package genericLib;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
//			Type your logic here...
//			Reporter.log("Type your Pass message", true);
//			if(test!=null)
//			{
//			test.log(Status.PASS, "Type your Pass message");
//			}
//		} 
//		catch (Exception e) 
//		{
//			Reporter.log("Type your Fail message", true);
//			if(test!=null)
//			{
//			test.log(Status.FAIL, "Type your Fail message - Exception : "+e);
//			}
//			throw e;
//		}
//
//		Thread.sleep(delay);
//	}
	
	
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
				if(test!=null)
				{
				test.log(Status.PASS, "Chrome browser launched");
				}
				break;
			case "firefox":
				driver=new FirefoxDriver();
				Reporter.log("Firefox browser launched", true);
				if(test!=null)
				{
				test.log(Status.PASS, "Firefox browser launched");
				}
				break;
			case "edge":
				driver=new EdgeDriver();
				Reporter.log("Edge browser launched", true);
				if(test!=null)
				{
				test.log(Status.PASS, "Edge browser launched");
				}
				break;
			default:
				driver=new ChromeDriver();
				Reporter.log("Launching default browser Chrome ("+bName+" is not valid browser)", true);
				if(test!=null)
				{
				test.log(Status.PASS, "Launching default browser Chrome ("+bName+" is not valid browser)");
				}
				break;

			}
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to launch browser "+e, true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to launch browser - Exception : "+e);
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
			Reporter.log("Browser maximized", true);
			if(test!=null)
			{
			test.log(Status.PASS, "Browser maximized");
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to maximize browser", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to maximize browser - Exception : "+e);
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
			Reporter.log("Navigated to "+url, true);
			if(test!=null)
			{
			test.log(Status.PASS, "Navigated to "+url);
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to navigate to "+url, true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to navigate to "+url+" - Exception : "+e);
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
			Reporter.log("Implicit wait set to "+seconds+" seconds", true);
			if(test!=null)
			{
			test.log(Status.PASS, "Implicit wait set to "+seconds+" seconds");
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to set Implicit wait", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to set Implicit wait - Exception : "+e);
			}
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
			if(test!=null)
			{
			test.log(Status.PASS, "List of text fetched from "+(String)ele.get(0)+" are "+strList.toString());
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to fetch text from "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to fetch text from "+(String)ele.get(0)+" - Exception : "+e);
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
			Reporter.log("Clicked on "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.PASS, "Clicked on "+(String)ele.get(0));
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to clicked on "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to clicked on "+(String)ele.get(0)+" - Exception : "+e);
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
			Reporter.log("Double clicked on "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.PASS, "Double clicked on "+(String)ele.get(0));
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to double click on "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to double click on "+(String)ele.get(0)+" - Exception : "+e);
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
			Reporter.log("Right clicked on "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.PASS, "Right clicked on "+(String)ele.get(0));
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to right click on "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to right click on "+(String)ele.get(0)+" - Exception : "+e);
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
			Reporter.log("Mouse hovered on "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.PASS, "Mouse hovered on "+(String)ele.get(0));
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to Mouse hover on "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to Mouse hover on "+(String)ele.get(0)+" - Exception : "+e);
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
			Reporter.log("Clicked on "+x+","+y, true);
			if(test!=null)
			{
			test.log(Status.PASS, "Clicked on "+x+","+y);
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to click on "+x+","+y, true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to click on "+x+","+y+" - Exception : "+e);
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
			Reporter.log("Clicked on current cursor point", true);
			if(test!=null)
			{
			test.log(Status.PASS, "Clicked on current cursor point");
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Clicked on current cursor point", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Clicked on current cursor point - Exception : "+e);
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
			Reporter.log("Entered "+input+" into "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.PASS, "Entered "+input+" into "+(String)ele.get(0));
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to enter "+input+" into "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to enter "+input+" into "+(String)ele.get(0)+" - Exception : "+e);
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
			
			Reporter.log("Text cleared from "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.PASS, "Text cleared from "+(String)ele.get(0));
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to clear text from "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to clear text from "+(String)ele.get(0)+" - Exception : "+e);
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
			
			Reporter.log("Text cleared from "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.PASS, "Text cleared from "+(String)ele.get(0));
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to clear text from "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to clear text from "+(String)ele.get(0)+" - Exception : "+e);
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
			if(test!=null)
			{
			Reporter.log("Waited till "+(String)ele.get(0)+" is visible", true);
			}
			test.log(Status.PASS, "Waited till "+(String)ele.get(0)+" is visible");
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to wait till "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to wait till "+(String)ele.get(0)+" - Exception : "+e);
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
				Reporter.log((String)ele.get(0)+" is displayed", true);
				if(test!=null)
				{
				test.log(Status.PASS, (String)ele.get(0)+" is displayed");
				}
			}
			else
			{
				Reporter.log((String)ele.get(0)+" is not displayed", true);
				if(test!=null)
				{
				test.log(Status.PASS, (String)ele.get(0)+" is not displayed");
				}
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to verify "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to verify "+(String)ele.get(0)+" - Exception : "+e);
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
			Reporter.log("Browser closed", true);
			if(test!=null)
			{
			test.log(Status.PASS,"Browser closed");
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to close browser", true);
			if(test!=null)
			{
			test.log(Status.FAIL,"Failed to close browser - Exception : "+e);
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
			Reporter.log("Text cleared and entered "+input+" into "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.PASS, "Text cleared and entered "+input+" into "+(String)ele.get(0));
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to clear and enter "+input+" into "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to clear and enter "+input+" into "+(String)ele.get(0)+" - Exception : "+e);
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
			Reporter.log("Clicked on "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.PASS, "Clicked on "+(String)ele.get(0));
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to clicked on "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to clicked on "+(String)ele.get(0)+" - Exception : "+e);
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
				Reporter.log("Chrome browser launched", true);
				if(test!=null)
				{
				test.log(Status.PASS, "Chrome browser launched");
				}
				break;
			case "firefox":
				FirefoxOptions ffopt = new FirefoxOptions();
				ffopt.addArguments("-headless");
				driver=new FirefoxDriver(ffopt);
				Reporter.log("Firefox browser launched", true);
				if(test!=null)
				{
				test.log(Status.PASS, "Firefox browser launched");
				}
				break;
			case "edge":
				EdgeOptions edgeOpt=new EdgeOptions();
				edgeOpt.addArguments("--headless");
				driver=new EdgeDriver(edgeOpt);
				Reporter.log("Edge browser launched", true);
				if(test!=null)
				{
				test.log(Status.PASS, "Edge browser launched");
				}
				break;
			default:
				ChromeOptions opt = new ChromeOptions();
				opt.addArguments("--headless");
				driver=new ChromeDriver(opt);
				Reporter.log("Launching default browser Chrome ("+bName+" is not valid browser)", true);
				if(test!=null)
				{
				test.log(Status.PASS, "Launching default browser Chrome ("+bName+" is not valid browser)");
				}
				break;	
			}
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to launch browser "+e, true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to launch browser - Exception : "+e);
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
			Reporter.log("Random Number generated : "+randNum, true);
			if(test!=null)
			{
			test.log(Status.PASS, "Random Number generated : "+randNum);
			}
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to generate random numbers", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to generate random numbers - Exception : "+e);
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
			Reporter.log("Concatinated String : "+str, true);
			if(test!=null)
			{
			test.log(Status.PASS, "Concatinated String : "+str);
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to concat", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to concat");
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
			
			Reporter.log("MapData : "+mapData.toString(), true);
			if(test!=null)
			{
			test.log(Status.PASS, "MapData : "+mapData.toString());
			}
		} 
		catch (NullPointerException e) 
		{
			Reporter.log("No header found", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "No header found");
			}
			throw e;
		}
		catch (IndexOutOfBoundsException e) 
		{
			Reporter.log("Unique Data not found", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Unique Data not found");
			}
			throw e;
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to read data - Exception : "+e, true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to read data - Exception : "+e);
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
			Reporter.log("MapData : "+mapData.toString(), true);
			if(test!=null)
			{
			test.log(Status.PASS, "MapData : "+mapData.toString());
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to read data - Exception : "+e, true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to read data - Exception : "+e);
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
			Reporter.log("selected index : "+index+" from "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.PASS, "selected index : "+index+" from "+(String)ele.get(0));
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to select index : "+index, true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to select index : "+index+" - Exception : "+e);
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
			Reporter.log("selected "+val+" from "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.PASS, "selected "+val+" from "+(String)ele.get(0));
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to select : "+val, true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to select : "+val+" - Exception : "+e);
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
			Reporter.log("selected "+text+" from "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.PASS, "selected "+text+" from "+(String)ele.get(0));
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to select : "+text, true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to select : "+text+" - Exception : "+e);
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
			Reporter.log("Switched to tab with Url : "+url, true);
			if(test!=null)
			{
			test.log(Status.PASS, "Switched to tab with Url : "+url);
			}
		} 
		catch (NullPointerException e) 
		{
			Reporter.log("Failed to switch : No Url found", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to switch : No Url found");
			}
			throw e;
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to switch", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to switch - Exception : "+e);
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
			Reporter.log("Switched to tab with title : "+title, true);
			if(test!=null)
			{
			test.log(Status.PASS, "Switched to tab with title : "+title);
			}
		} 
		catch (NullPointerException e) 
		{
			Reporter.log("Failed to switch : No title found", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to switch : No title found");
			}
			throw e;
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to switch", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to switch - Exception : "+e);
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
			Reporter.log("Switched to tab containing Url : "+url, true);
			if(test!=null)
			{
			test.log(Status.PASS, "Switched to tab containing Url : "+url);
			}
		} 
		catch (NullPointerException e) 
		{
			Reporter.log("Failed to switch : No Url found", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to switch : No Url found");
			}
			throw e;
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to switch", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to switch - Exception : "+e);
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
			Reporter.log("Switched to tab containing title : "+title, true);
			if(test!=null)
			{
			test.log(Status.PASS, "Switched to tab containing title : "+title);
			}
		} 
		catch (NullPointerException e) 
		{
			Reporter.log("Failed to switch : No title found", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to switch : No title found");
			}
			throw e;
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to switch", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to switch - Exception : "+e);
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
			Reporter.log("Scrolled till "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.PASS, "Scrolled till "+(String)ele.get(0));
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to scroll till "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to scroll till "+(String)ele.get(0)+" - Exception : "+e);
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
			Reporter.log("Dragged "+(String)dragEle.get(0)+" and dropped into "+(String)dropEle.get(0), true);
			if(test!=null)
			{
			test.log(Status.PASS, "Dragged "+(String)dragEle.get(0)+" and dropped into "+(String)dropEle.get(0));
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to drag and drop", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to drag and drop - Exception : "+e);
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
			Reporter.log("Switched to iFrame with index : "+index, true);
			if(test!=null)
			{
			test.log(Status.PASS, "Switched to iFrame with index : "+index);
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to switch to iFrame with index : "+index, true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to switch to iFrame with index : "+index+" - Exception : "+e);
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
			Reporter.log("Switched to iFrame with id or name = "+idOrName, true);
			if(test!=null)
			{
			test.log(Status.PASS, "Switched to iFrame with id or name = "+idOrName);
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to switch to iFrame with id or name = "+idOrName, true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to switch to iFrame with id or name = "+idOrName+" - Exception : "+e);
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
			Reporter.log("Switched to "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.PASS, "Switched to "+(String)ele.get(0));
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to switch to "+(String)ele.get(0), true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to switch to "+(String)ele.get(0)+" - Exception : "+e);
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
			Reporter.log("Switched to parent frame", true);
			if(test!=null)
			{
			test.log(Status.PASS, "Switched to parent frame");
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to switch to parent frame", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to switch to parent frame - Exception : "+e);
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
			Reporter.log("Switched to default window", true);
			if(test!=null)
			{
			test.log(Status.PASS, "Switched to default window");
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to switch to default window", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to switch to default window - Exception : "+e);
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
			Reporter.log("Clicked ok button on Alert popup", true);
			if(test!=null)
			{
			test.log(Status.PASS, "Clicked ok button on Alert popup");
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to click on ok button on Alert popup", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to click on ok button on Alert popup - Exception : "+e);
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
			Reporter.log("Clicked cancel button on Alert popup", true);
			if(test!=null)
			{
			test.log(Status.PASS, "Clicked cancel button on Alert popup");
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to click on cancel button on Alert popup", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to click on cancel button on Alert popup - Exception : "+e);
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
			Reporter.log("Entered "+input+" on Alert popup", true);
			if(test!=null)
			{
			test.log(Status.PASS, "Entered "+input+" on Alert popup");
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to enter "+input+" on Alert popup", true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to enter "+input+" on Alert popup - Exception : "+e);
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
			Reporter.log("Uploaded file : "+filePath, true);
			if(test!=null)
			{
			test.log(Status.PASS, "Uploaded file : "+filePath);
			}
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to upload file : "+filePath, true);
			if(test!=null)
			{
			test.log(Status.FAIL, "Failed to upload file : "+filePath+" - Exception : "+e);
			}
			throw e;
		}

		Thread.sleep(delay);
	}
	
}
