package com.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.Reporter;

import com.selenium.MetodosUtiles.Utiles;

public class DriverFactory {
	private enum browsers {
		EXPLORER, FIREFOX, CHROME
	};

	/*** Metodo que abre un navegador y navega a la URL que le indiquemos ***/

	public static WebDriver LevantarBrowser(WebDriver driver, ITestContext context) 
	{
		String browserName = context.getCurrentXmlTest().getParameter("NombreNavegador");
		String URL = context.getCurrentXmlTest().getParameter("url");
		
		switch (browsers.valueOf(browserName)) {
		case CHROME: // Using WebDriver
		{
			System.setProperty("webdriver.chrome.driver", "C:/eclipse/chromedriver.exe");
			Utiles.Escribir("Abrir Browser Chrome");
			driver = new ChromeDriver();
			break;
		}
		case FIREFOX:// Using WebDriver
		{
			System.setProperty("webdriver.gecko.driver", "driverBrowsers\\firefox\\geckodriver.exe");
			Utiles.Escribir("Abrir Browser Firefox");
			driver = new FirefoxDriver();
			break;
		}
		default:
			Reporter.log("No selecciono ningun browser correcto, se le asignara Chrome");
			System.setProperty("webdriver.chrome.driver", "C:/eclipse/chromedriver.exe");
			Utiles.Escribir("Abrir Browser ...");
			driver = new ChromeDriver();
			break;
		}
		driver.manage().window().maximize();
		Reporter.log("Maximizar Browser");
		driver.get(URL);
		return driver;
	}

	public static void FinalizarBrowser(WebDriver driver) {
		Utiles.Escribir("Cerrando el browser");
		driver.quit();
		driver = null;
	}
}