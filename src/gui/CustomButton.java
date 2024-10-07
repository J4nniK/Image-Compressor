package gui;

import java.awt.Dimension;
import javax.swing.JButton;

public class CustomButton extends JButton{

	private static final long serialVersionUID = 1L;
	public CustomButton() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomButton(String text) {
		super(text);
		super.setFont(GuiStyle.TEXT_FONT);
		
		//use the ui style of the parent button
		this.setUI(super.getUI());
	
	}
		

	    @Override
	    public Dimension getPreferredSize() {
	        Dimension size = super.getPreferredSize();
	        size.width += size.height;
	        return size;
	    }	
	
	   
	    
	    
}
