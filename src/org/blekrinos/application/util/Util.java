package org.blekrinos.application.util;

import org.blekrinos.application.Main;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Util {
	
	public static final Rectangle2D RECTANCLE2D = Screen.getPrimary().getVisualBounds();
	public static final Class<Main> RESOURCE_CLASS = Main.class;
	public static Stage primaryStage = null;
	
}