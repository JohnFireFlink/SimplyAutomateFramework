package genericLib;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReUseableLib extends BaseClass{

	//Create your reUseable StepGroups as new methods
	
	public void navigateToWebApp(String browserName, String url) throws InterruptedException
	{
		if (os.contains("win") || os.contains("mac")) 
		{
			util.openBrowser(browserName);
		}
		else
		{
			util.openHeadlessBrowser(browserName);
		}
		util.maximizeBrowser();
		util.navigateTo(url);
		util.setImplicitWait(5);

	}
	
	//DataProvider
	
	public List<ArrayList<String>> xLdataProvider(String sheetName) throws EncryptedDocumentException, IOException
	{
		List<ArrayList<String>> listOfRowData=new ArrayList<ArrayList<String>>();
		
		FileInputStream fis=new FileInputStream("./src/test/resources/DataProviderSheet.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int noOfCols = sh.getRow(0).getLastCellNum();
		int noOfData = sh.getLastRowNum();
		for (int i = 1; i <= noOfData; i++) 
		{
			ArrayList<String> rowData=new ArrayList<String>();
			for (int j = 0; j < noOfCols; j++) {
				try {
					rowData.add(sh.getRow(i).getCell(j).getStringCellValue());
				} catch (NullPointerException e) {
					rowData.add("");
				}
			}
			listOfRowData.add(rowData);
		}
		return listOfRowData;
	}
}
