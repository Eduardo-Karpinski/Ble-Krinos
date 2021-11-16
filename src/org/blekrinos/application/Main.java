package org.blekrinos.application;

import org.blekrinos.application.controller.Controller;
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
		FXMLLoader loader = new FXMLLoader(Util.RESOURCE_CLASS.getResource("resources/browser.fxml"));
		Parent root = loader.load();	
		Controller controller = loader.getController();
		Scene scene = new Scene(root);		
		primaryStage.getIcons().add(new Image(Util.RESOURCE_CLASS.getResource("resources/lirio.png").toString()));
		primaryStage.setTitle("Ble Krínos");
		primaryStage.setWidth(Util.WIDTH);
		primaryStage.setHeight(Util.HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		scene.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override 
		    public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	controller.getWebView().setPrefWidth(newSceneWidth.doubleValue());
		    }
		});
		
		scene.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override 
		    public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
		    	controller.getWebView().setPrefHeight(newSceneHeight.doubleValue());
		    }
		});
		
	}	

	public static void main(String[] args) {
//		path/to/java --module-path C:\Users\karpi\Desktop\javafx-sdk-17.0.1\lib --add-modules=javafx.controls,javafx.fxml,javafx.graphics,javafx.base,javafx.web -jar path/to/jar
		System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
		launch(args);
	}

}