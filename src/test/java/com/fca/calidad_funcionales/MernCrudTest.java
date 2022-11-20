package com.fca.calidad_funcionales;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.OrderWith;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;


public class MernCrudTest {

	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  JavascriptExecutor js;
	  @Before
	  public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
	    //System.setProperty("webdriver.chrome.driver", "");
	    driver = new ChromeDriver();
	    baseUrl = "https://www.google.com/";
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	    js = (JavascriptExecutor) driver;
	  }

	  @Test
	  public void testMern() throws Exception {
	    driver.get("https://mern-crud.herokuapp.com/");
	    pause(3000);
	    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
	    pause(3000);
	    driver.findElement(By.name("name")).click();
	    driver.findElement(By.name("name")).clear();
	    driver.findElement(By.name("name")).sendKeys("Elias test");
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("elias@prueba.com");
	    driver.findElement(By.name("age")).clear();
	    driver.findElement(By.name("age")).sendKeys("22");
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Stop'])[1]/following::div[1]")).click();
	    pause(3000);
	    driver.findElement(By.xpath("//i")).click();
	    /*
	    //String mensaje = driver.findElement(By.xpath(" /html/body/div[2]/div/div[2]/form/div[4]/div/p")).getText();
	    //assertThat(mensaje,is("Successfully added!"));
	     */
	    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Elias test[\\s\\S]*$"));
	   
	 }
	   
	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	  
	  private void pause(long mils) {
		  try {
			  Thread.sleep(mils);
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
	  }
	
}
