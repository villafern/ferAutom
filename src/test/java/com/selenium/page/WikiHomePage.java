package com.selenium.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.selenium.MetodosUtiles.Utiles;

public class WikiHomePage {
	WebDriver driver;

	/** Contructor que en este caso utiliza el driver enviado por parametro **/
	public WikiHomePage(WebDriver ldriver) {
		driver = ldriver;
	}

// ***** IDENTIFICAMOS LOS ELEMENTOS POR SU LOCATOR EJEMPLO ID O XPATH
	/*
	 * @FindBy(id = "js-link-box-es") private WebElement idiomaEspaniol;
	 */
	@FindBy(id = "searchInput")
	private WebElement caja;
	@FindBy(id = "searchLanguage")
	private WebElement languageCombo;

	public String getCaja() {
		Utiles.Escribir("Obtiene el contenido de la Caja de Búsqueda");
		return caja.getText();
	}

	public boolean SeVisualizaCaja() {
		Utiles.Escribir("Validar que exista la Caja de Búsqueda");
		return caja.isDisplayed();
	}

	public void VerificarCajaBusqueda() {
		Utiles.Escribir("Localizar y comprobar que la Caja de Búsqueda se muestre");
		Assert.assertTrue((caja.isDisplayed()), "La Caja de Búsqueda no se visualiza");

	}

	public void IngresarDatoCajaBusqueda(String dato) {
		Utiles.Escribir("Ingresar la palabra " + dato);

		caja.sendKeys(dato);
		Utiles.Escribir("Presionar Enter");
		caja.sendKeys(Keys.ENTER);
	}

}