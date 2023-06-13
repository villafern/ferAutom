package com.selenium.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.MetodosUtiles.Utiles;
import com.selenium.driver.DriverFactory;
import com.selenium.page.WikiHomePage;
import com.selenium.page.WikiResultadoPage;

public class TestCaseWiki {
	WebDriver driver;

	@BeforeMethod
	public void abrirBrowser(ITestContext context) {
		// TODO : ENVIAR INFORMACION DEL BROSER Y URL DE ARCHIVO EXTERNO
		driver = DriverFactory.LevantarBrowser(driver, context);
	}

	@AfterMethod
	public void cerrarBrowser() {
		Reporter.log("Cerrar Browser");
		DriverFactory.FinalizarBrowser(driver);
	}

	@DataProvider(name = "dataIdiomas")
	public Object[][] createDataIdioma() {
		return new Object[][] { { "af", "Selenium", "Seleen", "Afrikaans" },
				{ "es", "Selenium", "Slenium", "Español" }, { "pt", "Selenium", "Selênio", "Português'" },
				{ "de", "Selenium", "Selenium", "Deutsch" }, { "en", "Selenium", "Selenium", "English" }, };
	}

	@Test(dataProvider = "dataIdiomas", description = "Validar que funcionen búsquedas con diferentes idiomas - TDD")
	public void BusquedaConDiferentesIdiomas(String vIdioma, String vBusqueda, String resultado, String Descrip)
			throws Exception {
		WikiHomePage wikihomepage = PageFactory.initElements(driver, WikiHomePage.class);
		Thread.sleep(1000);
		wikihomepage.VerificarCajaBusqueda();
		// java.util.List<WebElement> allElements
		// =driver.findElements(By.id("searchLanguage"));
		// Utiles.Escribir("Desplegar menú de Idiomas");
		// for (WebElement lIdioma: allElements) {
		// if (lIdioma.getText().contains(vIdioma)) {

		Select selectIdiomas = new Select(driver.findElement(By.id("searchLanguage")));

		Utiles.Escribir("Recorrer lista de Idiomas de la Caja de Búsqueda");
		for (WebElement lIdioma : selectIdiomas.getOptions()) {

			if (lIdioma.getAttribute("lang").equals(vIdioma)) {

				Utiles.Escribir("Seleccionamos el idioma: " + Descrip + ", de la Caja de Búsqueda");
				lIdioma.click();

				Thread.sleep(1000);

				wikihomepage.IngresarDatoCajaBusqueda(vBusqueda);
				Utiles.Escribir("Validar que el ingreso de la búsqueda sea: " + vBusqueda);

				WikiResultadoPage wikiRdoPage = PageFactory.initElements(driver, WikiResultadoPage.class);
				Utiles.Escribir("Validar que el título a obtener sea: " + resultado);
				Assert.assertTrue((wikiRdoPage.ObtenerTitulo().contains(resultado)),
						" el valor " + resultado + " no se encontró en el título");
				break;
			}
		}
	}

}
