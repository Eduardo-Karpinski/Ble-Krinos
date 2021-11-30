@SuppressWarnings("module")
module org.bleKrinos {
	exports org.blekrinos.application to javafx.graphics;
	
	requires javafx.web;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.controls;
	requires commons.validator;
	requires org.apache.commons.io;
	
	requires transitive javafx.graphics;
	
	opens org.blekrinos.application.controller to javafx.fxml;
}