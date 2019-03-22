package com.qa.hs.keyword.test;

import org.testng.annotations.Test;

import com.qa.hs.keyword.engine.KeyWordEngine;

public class LoginTest {
	public KeyWordEngine keywdeng;
	
	@Test
	public void loginTest()
	{
		keywdeng = new KeyWordEngine();
   		keywdeng.startExecution("login");
	}
	

}
