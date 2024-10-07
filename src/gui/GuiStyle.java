package gui;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GuiStyle {

    // this class saves essential elements of the look and feel to make them easily accessible from the GUI classes
    // the elements are final so that they can't be changed while the program is running

    // used fonts
    public static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 12);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 12);

    // used colors
    // gray
    // main color for essential elements
    public static final Color SLIDER_COLOR = new Color(130, 130, 130);
    public static final Color BACKGROUND_GREEN = new Color(92, 122, 88);
    public static final Color FOREGROUND_TEXT_WHITE = new Color(255, 255, 255);    
    public static final Color DROPDOWN_COLOR = new Color(238, 238, 238);
	
}
