package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import CommonFunctionlibrary.FunctionLibrary;
import Utilities.ExcelFileUtils;

public class Driverscript1 {
	
	WebDriver driver;
	@Test
	public void starttest() throws Exception{
	ExcelFileUtils xl=new ExcelFileUtils();
	int rowcountmaster=xl.rowCount("MasterTestCases");
	for (int i = 1; i <= rowcountmaster; i++) {
		String ExecutionMode=xl.getData("MasterTestCases", i, 2);
		String moduleStatus = "";
		if (ExecutionMode.equalsIgnoreCase("y")) {
			String Module_Name=xl.getData("MasterTestCases", i, 1);
			for (int j = 1; j <xl.rowCount(Module_Name); j++) {
			String Description=	xl.getData(Module_Name, j, 0);
			String Object_Type=xl.getData(Module_Name, j, 1);
		String Locator_Type=xl.getData(Module_Name, j, 2);
		String Locator_Value=xl.getData(Module_Name, j, 3);
		String Test_Data=xl.getData(Module_Name, j, 4);
		try {
			if (Object_Type.equalsIgnoreCase("startBrowser")) {
				FunctionLibrary.startBrowser();
				System.out.println("startbrowser is pass");
				moduleStatus="true"; 
			}
			if (Object_Type.equalsIgnoreCase("openApplication")) {
				FunctionLibrary.openApplication(driver);
				System.out.println("openapplication is pass");
				moduleStatus="true";
			}
			if (Object_Type.equalsIgnoreCase("waitForElement")) {
				FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
				System.out.println("waitelement is pass");	
				moduleStatus="true";
			}
			if (Object_Type.equalsIgnoreCase("typeAction")) {
				FunctionLibrary.typeAction(driver,  Locator_Type, Locator_Value, Test_Data);
				System.out.println("typeaction is passed");
				moduleStatus="true";
			}
			if (Object_Type.equalsIgnoreCase("clickAction")) {
				FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
				System.out.println("click action is passed");
				moduleStatus="true";
			
				
			}
			if (Object_Type.equalsIgnoreCase("closeBrowser")) {
				FunctionLibrary.closeBrowser(driver);
				System.out.println("closebrowser is pass");
				moduleStatus="true";
			}
			xl.setData(Module_Name, j, 5, "pass");
		} catch (Exception e) {
			xl.setData(Module_Name, j, 5, "fail");
			moduleStatus="fail";
			
		}
		if (moduleStatus.equalsIgnoreCase("true")) {
			xl.setData("MasterTestCases", i, 3, "pass");
		}else {
			if (moduleStatus.equalsIgnoreCase("false")) {
				xl.setData("MasterTestCases", i, 3, "false");
			}
		}
			}

		}
		
	}
	}

}
