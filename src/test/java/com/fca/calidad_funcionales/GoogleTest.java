package com.fca.calidad_funcionales;

//Realización de exportaciones
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.time.Duration;


//Comienzo de la clase.
public class GoogleTest {

	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  JavascriptExecutor js;
	  
	  //Comienzo del before y agregamos en este apartado el webdriverManager.
	  @Before
	  public void setUp() throws Exception {
	    //System.setProperty("webdriver.chrome.driver", "");
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    baseUrl = "https://www.google.com/";
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	    js = (JavascriptExecutor) driver;
	  }
	  //Finalización del before.
	  
	  
	  //Comienzo de la prueba, en este caso será para validar el titulo de la pagina que es Yucatáni6
	  @Test
	  public void testTitulo() throws Exception {
	    driver.get("https://www.google.com/search?q=yucatan+i6&rlz=1C1VDKB_esMX1027MX1027&oq=yuc&aqs=chrome.0.69i59j69i57j46i67i131i433j46i67j46i131i175i199i433i512j69i60l3.2768j0j7&sourceid=chrome&ie=UTF-8");
	    driver.findElement(By.xpath("//div[@id='rso']/div/div/div/div/div/div/div/div/a/h3")).click();
	    driver.get("https://siies.yucatan.gob.mx/yucatani6/");
	    //assertEquals("Yucatáni6", driver.getTitle());
	    driver.findElement(By.xpath("//*[text()='Yucatáni6']"));
	    System.out.println("La verificación del titulo es correcta.");
	  }
	  //Fin de el test e imprimimos un mensaje en caso de que todo vaya bien.
	  
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
}
