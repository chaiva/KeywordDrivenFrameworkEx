package com.qa.hs.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hs.keyword.base.TestBase;

public class KeyWordEngine {
	public  WebDriver driver;
	public Properties prop;
	public static Workbook book;
	public static Sheet sheet;
	public TestBase base;
	public final String Scenario_Sheet_Path = "C:\\chaithra\\SeleniumWS\\KeyWordDrivenHubSpot\\src\\main\\java\\com\\qa\\hs\\keyword\\scenarios\\KeyWordHubSpot.xlsx";
	String locatorName = "";
	String locatorValue = "";
	public void startExecution(String sheetName)
	{
		FileInputStream file = null;
		try {
			file = new FileInputStream(Scenario_Sheet_Path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		for(int i =0;i <sheet.getLastRowNum();i++)
		{
			int k =0;
			try
			{
			String locatorCol = sheet.getRow(i+1).getCell(k+1).toString().trim();
			if (!locatorCol.equalsIgnoreCase("NA"))
			{
				locatorName =locatorCol.split("=")[0].trim();
				locatorValue=locatorCol.split("=")[1].trim();
			}
			String actions = sheet.getRow(i+1).getCell(k+2).toString().trim();
			String value = sheet.getRow(i+1).getCell(k+3).toString().trim();
			switch(actions)
			{
			case "open browser":
				base = new TestBase();
				prop = base.init_properties();
				if(value.isEmpty()|| value.equals("NA"))
				{
					driver =base.init_driver(prop.getProperty("browser"));
				}else
				{
					driver =base.init_driver(value);
				}
				 break;
				 
			case "enter url":
				if(value.isEmpty()|| value.equals("NA"))
				{
					driver.get(prop.getProperty("browser"));
				}else
				{
					driver.get(value);
				} 
					 
			default:break;
			}
			switch(locatorName)
			{
			case "name":
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				WebElement element =driver.findElement(By.name(locatorValue));
				if(actions.equalsIgnoreCase("sendKeys"))
				{
					element.clear();
					element.sendKeys(value);
					
				}else if(actions.equalsIgnoreCase("click"))
				{
					element.click();
				}
				locatorName = null;
				break;
			}
			}catch(Exception e)
			{
				
			}

		}
	}
}
