package webTestCases;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import genericLib.ReUseableLib;

public class Module3_Search extends ReUseableLib {

	@Test
	public void tc007_VerifySearchWithValidData() throws InterruptedException
	{
		generateReport();
		Map<String, String> mapData = util.readFromGoogleSheetForUniqueDataInSingleRowTable("Search", "tc007");
		util.setDelayBtwnSteps(1);
		
		loginAsUser("Chrome",map.get("URL"));
		util.EnterInto(hp.searchTF(), mapData.get("SearchText"));
		ArrayList<String> listOfEle = util.getTextOfAllElements(hp.searchSuggestions());
		util.clickOn(hp.searchBtn());
		Thread.sleep(2000);
		for (int i = 0; i < listOfEle.size(); i++) 
		{
			util.EnterInto(hp.searchTF(), "Computer");
			util.clickOn(hp.searchSuggestion(listOfEle.get(i)));
			Thread.sleep(2000);
		}
	}
	
}
