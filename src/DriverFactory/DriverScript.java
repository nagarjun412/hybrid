package DriverFactory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;
import com.relevantcodes.extentreports.ExtentReports;

import CommonFunctionlibrary.FunctionLibrary;
import Utilities.ExcelFileUtils;

public class DriverScript {
	WebDriver driver;
	

	@Test
	public void StartTest() throws Exception {

		
		ExcelFileUtils xl = new ExcelFileUtils();
		int rowcountmaster = xl.rowCount("MasterTestCases");
		for (int i = 1; i <= rowcountmaster; i++) {
			String Execution_Mode = xl.getData("MasterTestCases", i, 2);
			String moduleStatus = "";
			if (Execution_Mode.equalsIgnoreCase("y")) {
				String TCModule = xl.getData("MasterTestCases", i, 1);
				//ExtentReports report=new ExtentReports("D:\\arjun@123\\Stock_Accounting\\Reports\\"+TCModule+FunctionLibrary.generateDate()+./html);
				for (int j = 1; j <= xl.rowCount(TCModule); j++) {
					String Description = xl.getData(TCModule, j, 0);
					String Object_Type = xl.getData(TCModule, j, 1);
					String Locator_Type = xl.getData(TCModule, j, 2);
					String Locator_Value = xl.getData(TCModule, j, 3);
					String Test_Data = xl.getData(TCModule, j, 4);
					try {
						if (Object_Type.equalsIgnoreCase("startBrowser")) {
							driver = FunctionLibrary.startBrowser();
							System.out.println("start browser is pass");
							moduleStatus = "true";
						}
						if (Object_Type.equalsIgnoreCase("openApplication")) {
							FunctionLibrary.openApplication(driver);
							System.out.println("open application is pass");
							moduleStatus = "true";
						}
						if (Object_Type.equalsIgnoreCase("waitForElement")) {
							FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
							moduleStatus = "true";
						}
						if (Object_Type.equalsIgnoreCase("typeAction")) {
							FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
							moduleStatus = "true";
						}
						if (Object_Type.equalsIgnoreCase("clickAction")) {
							FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
							moduleStatus = "true";
						}
						if (Object_Type.equalsIgnoreCase("closeBrowser")) {
							FunctionLibrary.closeBrowser(driver);
							moduleStatus = "true";
						}
						xl.setData(TCModule, j, 5, "pass");
					//	System.out.println("Entered status as Pass in "+ TCModule);
						moduleStatus = "true";
					} catch (Exception e) {
						 java.io.File srcFile= (java.io.File) ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(srcFile, new java.io.File("D:\\arjun@123\\Stock_Accounting\\Screens\\"+Description+FunctionLibrary.generateDate()+".png"));
						xl.setData(TCModule, j, 5, "fail");
						moduleStatus = "fail";
						break;
					}
					if (moduleStatus.equalsIgnoreCase("true")) {
						xl.setData("MasterTestCases", i, 3, "pass");
						
					} else if (moduleStatus.equalsIgnoreCase("fail")) {
						xl.setData("MasterTestCases", i, 3, "fail");
					}
				}
			} 
			
			
			else {
				xl.setData("MasterTestCases", i, 3, "Not executed");
			}
			
		}

	}
}
