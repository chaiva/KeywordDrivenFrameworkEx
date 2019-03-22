package com.qa.hs.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {
	public  WebDriver driver;
	public Properties prop;
	public Properties init_properties()
	{
		
	try {
		prop = new Properties();
		FileInputStream ip = new FileInputStream("C:\\chaithra\\SeleniumWS\\KeyWordDrivenHubSpot\\src\\main\\java\\com\\qa\\hs\\keyword\\config\\config.properties");
		prop.load(ip);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return prop;
	}
	
	public WebDriver init_driver( String browserName)
	{
 //browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\chaithra\\SeleniumWS\\chromedriver.exe");	
			if(prop.getProperty("headless").equals("Yes"))
			{
				ChromeOptions opt = new ChromeOptions();
				opt.addArguments("headless");
				driver = new ChromeDriver(opt);
			}else
			{
			driver = new ChromeDriver(); 
			}
	}
		return driver;

}
	}
	

