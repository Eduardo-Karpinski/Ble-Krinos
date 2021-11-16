package org.blekrinos.application.util;

import org.blekrinos.application.Main;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class Util {
	
	public static final Rectangle2D rectangle2D = Screen.getPrimary().getVisualBounds();
	public static final double WIDTH = rectangle2D.getWidth();
	public static final double HEIGHT = rectangle2D.getHeight();
	public static final Class<Main> RESOURCE_CLASS = Main.class;
	
}