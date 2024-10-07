package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CompressCommands {
	
	public CompressCommands() {
		
	}
	
	
	public void compressImages(ArrayList<File> fileList) {
		
		ConfigController configController = new ConfigController();
		
		//get the current compression value from the settings file and edit the value to a float format
		String compressionValueString = configController.getValue("compression.value");
		String outputPath = configController.getValue("output.path");
		
		//assign the value to a variable
		float compressionValue = Float.parseFloat(compressionValueString)/100;		
		System.out.println("Wert: "+compressionValue);
		
		//iterate the file list
		for (File file : fileList) {
			
			BufferedImage image;
			try {
				
				//read the current image in the list
				image = ImageIO.read(file);
				
				//add a suffix to the file
				File compressedImageFile = new File(outputPath+File.separator+file.getName() + "-compressed.png");
				
				FileOutputStream os = new FileOutputStream(compressedImageFile);
			
				//create an iterator ,of the type ImageWriter, that can encode and write images
				Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("png");
				ImageWriter writer = (ImageWriter) writers.next();
				
				//create an output stream for the ImageWriter
				ImageOutputStream ios = ImageIO.createImageOutputStream(os);
				writer.setOutput(ios);

				ImageWriteParam param = writer.getDefaultWriteParam();
				
				//compress the image using the quality settings specified in this ImageWriteParam
				param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
				param.setCompressionQuality(compressionValue); 
				
				//write the compressed image to the disk
				writer.write(null, new IIOImage(image, null, null), param);

				os.close();
				ios.close();
				writer.dispose();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		
		JOptionPane.showMessageDialog(new JPanel(), "Komprimierung erfolgreich abgeschlossen.");
		
	}
	
	
	
}
