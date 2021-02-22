package com.qa.tdl.website;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class WebpageTest {
	private WebDriver driver;
	
	 @BeforeAll
	    public void setup() {
	        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.manage().window().setSize(new Dimension(1366, 768));
	 }
    
	 @Test
	 public void createToDo() throws InterruptedException {
		 driver.get(ToDoPage.URL);
		 
	 }

}
