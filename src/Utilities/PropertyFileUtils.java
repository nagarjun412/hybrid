package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtils {
	
	public static String  getValueForKey(String key ) throws Exception{
		
		Properties pr=new Properties();
		FileInputStream fi=new FileInputStream("D:\\arjun@123\\Stock_Accounting\\PropartiesFile\\repository.proparties");
		pr.load(fi);		
		return(String) pr.get(key);
		}
}
