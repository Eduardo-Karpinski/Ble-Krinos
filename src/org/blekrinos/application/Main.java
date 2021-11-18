package org.blekrinos.application;

import org.blekrinos.application.controller.TabPanelController;
import org.blekrinos.application.util.Util;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Util.primaryStage = primaryStage;
		
		FXMLLoader loader = new FXMLLoader(Util.RESOURCE_CLASS.getResource("resources/TabPanelController.fxml"));
		Parent root = loader.load();	
		TabPanelController controller = loader.getController();
		Scene scene = new Scene(root);	
		
		addListeners(controller, scene);
		
		primaryStage.getIcons().add(new Image(Util.RESOURCE_CLASS.getResource("resources/lirio.png").toString()));
		primaryStage.setTitle("Ble Krínos");
		primaryStage.setWidth(Util.RECTANCLE2D.getWidth());
		primaryStage.setHeight(Util.RECTANCLE2D.getHeight());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void addListeners(TabPanelController controller, Scene scene) {
		scene.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override 
		    public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	controller.getTabs().forEach(tab -> {
		    		tab.getWebView().setPrefWidth(newSceneWidth.doubleValue());
		    	});
		    }
		});
		
		scene.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override 
		    public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
		    	controller.getTabs().forEach(tab -> {
		    		tab.getWebView().setPrefHeight(newSceneHeight.doubleValue());
		    	});
		    }
		});
	}	

	public static void main(String[] args) {
//		path/to/java --module-path C:\Users\karpi\Desktop\javafx-sdk-17.0.1\lib --add-modules=javafx.controls,javafx.fxml,javafx.graphics,javafx.base,javafx.web -jar path/to/jar
		System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
		launch(args);
	}

}