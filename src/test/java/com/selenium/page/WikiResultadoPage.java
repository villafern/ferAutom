package com.selenium.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.selenium.MetodosUtiles.Utiles;

public class WikiResultadoPage {
	WebDriver driver;

	public WikiResultadoPage(WebDriver ldriver) {
		this.driver = ldriver;
	}
	
	 @FindBy(id = "firstHeading")

	private WebElement lblTitulo;

	public String ObtenerTitulo() {
		Utiles.Escribir("Verificar que el titulo obtenido sea: " + lblTitulo.getText());
		return lblTitulo.getText();
	}

	public String ObtenerTituloDriver(String dato) throws Exception {
		return driver.getTitle().toString();

	}
}
