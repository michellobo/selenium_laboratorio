package selenium;

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private final static Logger LOGGER = Logger.getLogger("devops.subnivel.Control");    
    private WebDriver driver;

    @Before
    public void setUp(){
        System.out.println("Iniciando configuración...");
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void prueba(){
        // test(String inputSueldo, String inputSaldo, String retiroCorrecto, String saldoRestantoCorrecto, String impuestoCorrecto)
        this.test("1900000", "12000000", "1.200.000", "10.800.000", "96.000");
    }

    @After
    public void tearDown() {
        driver.close(); // Cierra el navegador actual abierto por el Script 
        driver.quit();  // Cierra todas las sesiones abiertas por el Script
    }

    public void test(String inputSueldo, String inputSaldo, String retiroCorrecto, String saldoRestantoCorrecto, String impuestoCorrecto) {
        driver.get("http://localhost:3000/");
        driver.manage().window().maximize();
        driver.findElement(By.id("inputSueldo")).clear();
        driver.findElement(By.id("inputSueldo")).sendKeys(inputSueldo);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("inputSaldo")).clear();
        driver.findElement(By.id("inputSaldo")).sendKeys(inputSaldo);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("botonEnviar")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Resultados
        String retiro = driver.findElement(By.id("retiro")).getText();
        String saldo = driver.findElement(By.id("saldo")).getText();
        String impuesto = driver.findElement(By.id("impuesto")).getText();
        //Validaciones
        LOGGER.log(Level.INFO, "---- RETIRO ----");
        LOGGER.log(Level.INFO, "retiro---- "+retiro+" ----");
        LOGGER.log(Level.INFO, "retiroCorrecto---- "+retiroCorrecto+" ----");
        LOGGER.log(Level.INFO, "---- SALDO ----");
        LOGGER.log(Level.INFO, "saldo---- "+saldo+" ----");
        LOGGER.log(Level.INFO, "saldoRestantoCorrecto---- "+saldoRestantoCorrecto+" ----");
        LOGGER.log(Level.INFO, "---- IMPUESTO ----");
        LOGGER.log(Level.INFO, "impuesto---- "+impuesto+" ----");
        LOGGER.log(Level.INFO, "impuestoCorrecto---- "+impuestoCorrecto+" ----");
        assertEquals(retiroCorrecto, retiro);
        assertEquals(saldoRestantoCorrecto, saldo);
        assertEquals(impuestoCorrecto, impuesto);
        driver.findElement(By.id("botonLimpiar")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}