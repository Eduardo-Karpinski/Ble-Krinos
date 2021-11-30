package org.blekrinos.application.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.blekrinos.application.Main;

import javafx.stage.Stage;


public class Util {
	
	public static final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
	public static final Class<Main> RESOURCE_CLASS = Main.class;
	public static Stage primaryStage = null;
	
}