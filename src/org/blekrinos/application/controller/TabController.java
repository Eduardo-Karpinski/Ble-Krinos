package org.blekrinos.application.controller;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

import org.apache.commons.validator.routines.UrlValidator;
import org.blekrinos.application.util.Util;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;

public class TabController implements Initializable {

	private Tab myTab;
	@FXML
	private TextField searchField;
	@FXML
	private Button backPage;
	@FXML
	private Button forwardPage;
	@FXML
	private Button reloadPage;
	@FXML
	private WebView webView;
	@FXML
	private ProgressBar progressBar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		webView.getEngine().locationProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				searchField.setText(newValue);
			}
		});

		webView.getEngine().getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
			if (newState == State.SUCCEEDED) {
				String title = getTitle();
				if (title != null) {
					myTab.setText(title);
				}
			}
		});
		
		progressBar.progressProperty().bind(webView.getEngine().getLoadWorker().progressProperty());
		webView.getEngine().load("https://duckduckgo.com");

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
				webView.getEngine().load(url);
			} else {
				webView.getEngine().load("https://duckduckgo.com//?q=" + URLEncoder.encode(url, "UTF-8"));
			}
		}
	}

	public void reloadPage() {
		webView.getEngine().reload();
	}

	public void forwardPage() {
		webView.getEngine().getHistory().go(webView.getEngine().getHistory().getEntries().size() > 1 && webView.getEngine().getHistory().getCurrentIndex() < webView.getEngine().getHistory().getEntries().size() - 1 ? 1 : 0);
	}

	public void backPage() {
		webView.getEngine().getHistory().go(webView.getEngine().getHistory().getEntries().size() > 1 && webView.getEngine().getHistory().getCurrentIndex() > 0 ? -1 : 0);
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

	private String getTitle() {
		try {
			NodeList heads = webView.getEngine().getDocument().getElementsByTagName("head");
			if (heads.getLength() > 0) {
				NodeList titles = ((Element) heads.item(0)).getElementsByTagName("title");
				if (titles.getLength() > 0) {
					return titles.item(0).getTextContent();
				}
			}
			return null;
		} catch (Exception ignore) {
			return null;
		}
	}

	public WebView getWebView() {
		return webView;
	}

	public void setMyTab(Tab myTab) {
		this.myTab = myTab;
	}
	
	public ProgressBar getProgressBar() {
		return progressBar;
	}
	
	public TextField getSearchField() {
		return searchField;
	}
	
}
