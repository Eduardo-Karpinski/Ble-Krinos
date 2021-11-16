package org.blekrinos.application.controller;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

import org.apache.commons.validator.routines.UrlValidator;
import org.blekrinos.application.util.Util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Controller implements Initializable {

	private WebEngine engine;
	@FXML private WebView webView;
	@FXML private TextField searchField;
	@FXML private Button backPage;
	@FXML private Button forwardPage;
	@FXML private Button reloadPage;
	@FXML private TabPane tabPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		searchField.setPrefWidth(Util.WIDTH);
		webView.setPrefWidth(Util.WIDTH);
		webView.setPrefHeight(Util.HEIGHT - 80);
		tabPane.setPrefWidth(Util.WIDTH);
		
		engine = webView.getEngine();
		
		engine.locationProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				searchField.setText(newValue);
			}
		});
		
		webView.resize(0, 0);
		
		engine.load("https://duckduckgo.com");
		engine.setUserAgent("Ble Krínos/1.0.0 ("+System.getProperty("os.name") + "; " + System.getProperty("os.arch")+";"+")");
		
		addMenuIcons();
	    
	}
	
	public void selectAllText() {
		searchField.selectAll();
	}

	public void loadPage(KeyEvent keyEvent) throws UnsupportedEncodingException {
		if (keyEvent.getCode() == KeyCode.ENTER) {
			String url = searchField.getText();

			boolean isValid = new UrlValidator().isValid(url);
			
			if (isValid) {
				engine.load(url);
			} else {
				engine.load("https://duckduckgo.com//?q="+URLEncoder.encode(url, "UTF-8"));
			}
			
			searchField.setText(engine.getLocation());
		}
	}
	
	public void reloadPage() {
		engine.reload();
	}
	
	public void forwardPage() {
		engine.getHistory().go(engine.getHistory().getEntries().size() > 1 && engine.getHistory().getCurrentIndex() < engine.getHistory().getEntries().size() - 1 ? 1 : 0);
	}
	
	public void backPage() {
		engine.getHistory().go(engine.getHistory().getEntries().size() > 1 && engine.getHistory().getCurrentIndex() > 0 ? -1 : 0);
	}
	
	private void addMenuIcons() {
		ImageView backPageIcon = new ImageView(new Image(Util.RESOURCE_CLASS.getResource("resources/backPage.png").toString()));
		backPageIcon.setFitHeight(15);
		backPageIcon.setFitWidth(15);
		
		ImageView forwardPageIcon = new ImageView(new Image(Util.RESOURCE_CLASS.getResource("resources/forwardPage.png").toString()));
		forwardPageIcon.setFitHeight(15);
		forwardPageIcon.setFitWidth(15);
		
		ImageView reloadPageIcon = new ImageView(new Image(Util.RESOURCE_CLASS.getResource("resources/refresh.png").toString()));
		reloadPageIcon.setFitHeight(15);
		reloadPageIcon.setFitWidth(15);
		
	    backPage.setGraphic(backPageIcon);
	    forwardPage.setGraphic(forwardPageIcon);
	    reloadPage.setGraphic(reloadPageIcon);
	}

	public WebView getWebView() {
		return webView;
	}

}