package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ConfigController;
import controller.FrameController;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.Panel;
import java.awt.GridLayout;
import javax.swing.DropMode;

public class SettingsMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsMenu frame = new SettingsMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SettingsMenu() {
		
		ConfigController configController = new ConfigController();
		String currentStoragePath = configController.getValue("output.path");
		String currentCompressionValue = configController.getValue("compression.value");
		
		setTitle("Einstellungen");
		setForeground(GuiStyle.FOREGROUND_TEXT_WHITE);
		setFont(GuiStyle.TEXT_FONT);
		setBackground(GuiStyle.BACKGROUND_GREEN);
		setBounds(100, 100, 605, 206);
		setLocationRelativeTo(null);
		toFront();
		requestFocus();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{440, 0};
		gbl_contentPane.rowHeights = new int[]{94, 38, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
	//creating the gui objects	
		//create the panels
		JPanel panel = new JPanel();
		JPanel middleGridPanel = new JPanel();
		JPanel bottomGridPanel = new JPanel();

		//create a table that is used for the labels of the slider
		Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(0, new JLabel("0"));
        labels.put(50, new JLabel("50"));
        labels.put(100, new JLabel("100"));
        
        //create the labels
		JLabel compressionValueLabel = new CustomLabel("Komprimierungswert:");
		JLabel storagePathLabel = new CustomLabel("Aktueller Speicherort:");
		storagePathLabel.setText("Aktueller Speicherort: ");
		
		
        //create the slider
		JSlider compressionValueSlider = new JSlider();
		compressionValueSlider.setForeground(GuiStyle.SLIDER_COLOR);
		
		//create a text field
		JTextField currentStoragePathTextField;currentStoragePathTextField = new JTextField();
		currentStoragePathTextField.setText(currentStoragePath);
		currentStoragePathTextField.setEditable(false);
		currentStoragePathTextField.setHorizontalAlignment(SwingConstants.CENTER);
		currentStoragePathTextField.setColumns(15);		
		
		//create the buttons
		CustomButton saveSettingsButton = new CustomButton("Speichern");
		CustomButton closeSettingsButton = new CustomButton("Schließen");
		
		//use the table with the labels for the slider
		compressionValueSlider.setLabelTable(labels);		
		compressionValueSlider.setPaintTicks(true);
		compressionValueSlider.setPaintLabels(true);
		
		//set the layout of the panels
        GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		GridBagConstraints gbc_middleGridPanel = new GridBagConstraints();
		gbc_middleGridPanel.insets = new Insets(0, 0, 5, 0);
		gbc_middleGridPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_middleGridPanel.gridx = 0;
		gbc_middleGridPanel.gridy = 1;
		contentPane.add(middleGridPanel, gbc_middleGridPanel);
		middleGridPanel.setLayout(new BoxLayout(middleGridPanel, BoxLayout.X_AXIS));
		
		GridBagConstraints gbc_bottomGridPanel = new GridBagConstraints();
		gbc_bottomGridPanel.gridx = 0;
		gbc_bottomGridPanel.gridy = 2;
		contentPane.add(bottomGridPanel, gbc_bottomGridPanel);
		
		

	
	//add the objects to the panels				
		panel.add(compressionValueSlider, BorderLayout.SOUTH);
		panel.add(compressionValueLabel, BorderLayout.NORTH);
		
		middleGridPanel.add(storagePathLabel);
		
		middleGridPanel.add(currentStoragePathTextField);
	

		bottomGridPanel.add(saveSettingsButton);
		bottomGridPanel.add(closeSettingsButton);
		
	//add logic to the objects through listeners
		saveSettingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String newCompressionValue = Integer.toString(compressionValueSlider.getValue());
				configController.setValue("compression.value", newCompressionValue);
				JOptionPane.showMessageDialog(new JPanel(), "Die Einstellungen wurden gespeichert.");
				
			}
		});
		
		closeSettingsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FrameController.useLastFrame(SettingsMenu.this);
			}
		});
		
		
	//Go back to the main menu in case the window gets closed from the button at the top
	super.addWindowListener(new WindowAdapter() {
		
		
		
		//Change the frame once the window is closed
		 @Override
         public void windowClosing(WindowEvent e) {
             FrameController.useLastFrame(SettingsMenu.this);
         }
		
		
	});
		
	}

}
