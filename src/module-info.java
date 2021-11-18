@SuppressWarnings("module")
module org.bleKrinos {
	exports org.blekrinos.application to javafx.graphics;
	
	requires javafx.fxml;
	requires javafx.web;
	requires javafx.controls;
	requires commons.validator;
	
	requires transitive javafx.graphics;
	
	opens org.blekrinos.application.controller to javafx.fxml;
}