package com.selenium.Test;

import org.testng.annotations.Test;


import com.selenium.driver.DriverFactory;
import com.selenium.page.WikiHomePage;
import com.selenium.page.WikiResultadoPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class TestCaseWiki2 {
	WebDriver driver;

	@DataProvider(name = "datos")
	public Object[][] createData() {
		return new Object[][] { { "TDD", "Desarrollo guiado por pruebas" },{ "Selenium", "Selenium" }
			//	{ "JAVA", "Java (lenguaje de programación)" }, { "DATA DRIVEN TESTING", "Data-driven testing" },
			//	{ "Selenium", "Selenium" }
			};
	}
	
	
	//TODO, CREAR MEROTODO PARA OBREBETR DATOS EXTERNOS
	@BeforeMethod
	public void abrirBrowser(ITestContext context)  {
		//TODO : ENVIAR INFORMACION DEL BROSER Y URL DE ARCHIVO EXTERNO
		driver = DriverFactory.LevantarBrowser(driver, context);
	}

	@Test(dataProvider = "datos", description = "validar y verificar que Wikipedia HomePage contine el campo búsqueda")
	public void validarCarTextNuevo(String varBuscar, String resultado) throws Exception {
		WikiHomePage wikihomepage = PageFactory.initElements(driver, WikiHomePage.class);
		Thread.sleep(2000);
		wikihomepage.IngresarDatoCajaBusqueda(varBuscar);
		Reporter.log("Validar que el título sea " + resultado);

		WikiResultadoPage wikiRdoPage = PageFactory.initElements(driver, WikiResultadoPage.class);
	//	Assert.assertTrue((driver.getCurrentUrl().contains(resultado)), "No Contiene" + resultado);

		Reporter.log("Valida que el título sea " + varBuscar);

		Assert.assertTrue((wikiRdoPage.ObtenerTitulo().contains(resultado)),
				" el valor " + resultado + " no se encontró en el título");
	}

	@AfterMethod
	public void cerrarBrowser() {
		Reporter.log("Cerrar Browser");
		DriverFactory.FinalizarBrowser(driver);
	}
}