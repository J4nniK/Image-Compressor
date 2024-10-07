package controller;

import javax.swing.JFrame;

public class FrameController {

	private static JFrame currentFrame;
	private static JFrame lastFrame;
		
	public static void setNextFrame(JFrame next, JFrame current) {
		lastFrame = current;
		currentFrame = next;
		lastFrame.setVisible(false);
		next.setVisible(true);
		currentFrame = next;
	}
	
	public static void useLastFrame(JFrame current) {
		
		current.dispose();		
		lastFrame.setVisible(true);	
		lastFrame = current;


		
	}
	
	public static void closeAllFrames() {
		
		if(lastFrame != null) {
			lastFrame.dispose();
		}
		
		if(currentFrame != null) {
			currentFrame.dispose();
		}
	}

	
	public static void toggleVisibility(JFrame frame) {
		frame.setVisible(!frame.isVisible());
	}
	
	
	
	
	
}
