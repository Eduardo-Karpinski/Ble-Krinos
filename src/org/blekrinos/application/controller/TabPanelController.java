package org.blekrinos.application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.blekrinos.application.util.Util;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class TabPanelController implements Initializable {

	@FXML
	private VBox vBox;
	@FXML
	private TabPane tabPane;
	@FXML
	private Tab newTab;
	
	private List<TabController> tabs = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabPane.setPrefHeight(Util.RECTANCLE2D.getHeight());
		tabPane.setPrefWidth(Util.RECTANCLE2D.getWidth());
		
		newTab();
		
		tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
			if (newTab == this.newTab) {
				newTab();
			}
	    });
		
	}

	public void newTab() {
		try {
			
			FXMLLoader loader = new FXMLLoader(Util.RESOURCE_CLASS.getResource("resources/TabController.fxml"));
			Parent load = loader.load();
			TabController controller = loader.getController();
			
			/**
			 * da primeira vez pega o tamanho da tela
			 * da segunda a diante pega da Scene
			 * isso deve-se por que a aba deve ser do
			 * mesmo tamanho da Scene, mas eu não tenho
			 * ela da primeira vez por isso esse if existe.
			 */

			if (tabs.isEmpty()) {
				controller.getWebView().setPrefHeight(Util.RECTANCLE2D.getHeight() - 80);
				controller.getWebView().setPrefWidth(Util.RECTANCLE2D.getWidth());
			} else {
				controller.getWebView().setPrefHeight(Util.primaryStage.getHeight() - 80);
				controller.getWebView().setPrefWidth(Util.primaryStage.getWidth());
			}
			
			controller.getWebView().setMaxHeight(Util.RECTANCLE2D.getHeight() - 80);
			controller.getWebView().setMaxWidth(Util.RECTANCLE2D.getWidth());
			
			Tab tab = new Tab();
			tab.setContent(load);
			controller.setMyTab(tab);
			tabs.add(controller);
			tabPane.getTabs().add(tab);
			tabPane.getSelectionModel().selectLast();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VBox getvBox() {
		return vBox;
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public Tab getNewTab() {
		return newTab;
	}

	public List<TabController> getTabs() {
		return tabs;
	}

}