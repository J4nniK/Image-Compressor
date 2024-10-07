package gui;

import javax.swing.JLabel;

public class CustomLabel extends JLabel{

	private static final long serialVersionUID = 1L;

	public CustomLabel() {
		// TODO Auto-generated constructor stub
		
		super.setFont(GuiStyle.LABEL_FONT);
		
	}
	
	public CustomLabel(String labelText) {
		// TODO Auto-generated constructor stub
		
		super.setFont(GuiStyle.LABEL_FONT);
		super.setText(labelText);
		
	}

}
