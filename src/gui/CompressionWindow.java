package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSlider;
import java.awt.Color;

public class CompressionWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel centerPanel = new JPanel();
	private JTextField compressionValue;
	private  float compressionFactor;
	private static ArrayList<File> fileList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CompressionWindow dialog = new CompressionWindow(fileList);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CompressionWindow(ArrayList<File> fileList) {
		
		
		//default value for the compression
		compressionFactor = 0.5f;
				
		//use the selected files from the JFilechooser
		this.fileList = fileList;
		
		//create the main window
		setBounds(100, 100, 450, 211);
		getContentPane().setLayout(new BorderLayout());
		centerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		
	//add the gui content		
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		
		
		gbl_centerPanel.columnWidths = new int[]{391, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0, 0, 0, 29, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		{
			JLabel compressionLabel = new JLabel("Kompressionsfaktor: ");
			GridBagConstraints gbc_compressionLabel = new GridBagConstraints();
			gbc_compressionLabel.insets = new Insets(0, 0, 5, 0);
			gbc_compressionLabel.gridx = 0;
			gbc_compressionLabel.gridy = 0;
			centerPanel.add(compressionLabel, gbc_compressionLabel);
		}
		{
			JSlider compressionSlider = new JSlider();
			compressionSlider.setForeground(GuiStyle.BACKGROUND_GREEN);
			GridBagConstraints gbc_compressionSlider = new GridBagConstraints();
			gbc_compressionSlider.insets = new Insets(0, 0, 5, 0);
			gbc_compressionSlider.gridx = 0;
			gbc_compressionSlider.gridy = 1;
			centerPanel.add(compressionSlider, gbc_compressionSlider);
		}
		{
			compressionValue = new JTextField();
			compressionValue.setEditable(false);
			GridBagConstraints gbc_compressionValue = new GridBagConstraints();
			gbc_compressionValue.insets = new Insets(0, 0, 5, 0);
			gbc_compressionValue.gridx = 0;
			gbc_compressionValue.gridy = 2;
			centerPanel.add(compressionValue, gbc_compressionValue);
			compressionValue.setColumns(20);
		}
		{
			JPanel buttonPanel = new JPanel();
			GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
			gbc_buttonPanel.gridx = 0;
			gbc_buttonPanel.gridy = 4;
			centerPanel.add(buttonPanel, gbc_buttonPanel);
			buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton okButton = new CustomButton("Start");
				buttonPanel.add(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						for (File file : fileList) {
							System.out.println(file.getName());

							BufferedImage image;
							try {
								
								image = ImageIO.read(file);
								File compressedImageFile = new File(file.getName() + "-compressed.jpg");
								OutputStream os = new FileOutputStream(compressedImageFile);

								Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
								ImageWriter writer = (ImageWriter) writers.next();

								ImageOutputStream ios = ImageIO.createImageOutputStream(os);
								writer.setOutput(ios);

								ImageWriteParam param = writer.getDefaultWriteParam();

								param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
								param.setCompressionQuality(compressionFactor); // Change the quality value you prefer
								writer.write(null, new IIOImage(image, null, null), param);

								os.close();
								ios.close();
								writer.dispose();

							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}

					}
				});
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new CustomButton("Abbrechen");
				buttonPanel.add(cancelButton);
				cancelButton.setActionCommand("Cancel");
			}
		}
	}

}

