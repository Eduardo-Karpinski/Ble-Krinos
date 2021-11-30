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
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabPanelController implements Initializable {

	@FXML
	private TabPane tabPane;
	private Tab newTab = new Tab("+");
	private List<TabController> tabs = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabPane.getTabs().add(newTab);
		
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
			
			controller.getProgressBar().setMinWidth(Util.primaryStage.getWidth());
			controller.getProgressBar().setPrefWidth(Util.primaryStage.getWidth());
			controller.getProgressBar().setMaxWidth(Util.primaryStage.getWidth());
    		
			controller.getSearchField().setMinWidth(Util.primaryStage.getWidth() - 61);
			controller.getSearchField().setPrefWidth(Util.primaryStage.getWidth() - 61);
			controller.getSearchField().setMaxWidth(Util.primaryStage.getWidth() - 61);
    		
			controller.getWebView().setMinWidth(Util.primaryStage.getWidth());
			controller.getWebView().setPrefWidth(Util.primaryStage.getWidth());
			controller.getWebView().setMaxWidth(Util.primaryStage.getWidth());
			controller.getWebView().setMinHeight(Util.primaryStage.getHeight() - 50);
			controller.getWebView().setPrefHeight(Util.primaryStage.getHeight() - 50);
    		controller.getWebView().setMaxHeight(Util.primaryStage.getHeight() - 50);
			
			Tab tab = new Tab();
			tab.setContent(load);
			
			Button a = new Button("x");
			tab.setGraphic(a);
			
			((Button) tab.getGraphic()).setOnAction(e -> {
				tabPane.getTabs().remove(tab);
				tabs.remove(controller);
			});
			
			controller.setMyTab(tab);
			tabs.add(controller);
			tabPane.getTabs().add(tabPane.getTabs().size() - 1, tab);
            tabPane.getSelectionModel().select(tabPane.getTabs().size() - 2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<TabController> getTabs() {
		return tabs;
	}

}