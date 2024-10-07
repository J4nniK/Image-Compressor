package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.CompressCommands;
import controller.ConfigController;
import controller.FrameController;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	public static ArrayList<File> fileList;
	private JTextArea textArea;
	private JPanel contentPane;
	private static SettingsMenu settingsMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					FrameController.setNextFrame(frame, frame);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public MainMenu() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

		
		//makes the program use look of the system ui
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		ConfigController configController = new ConfigController();
		configController.setup();

		settingsMenu = new SettingsMenu();

		// set the name of the application window
		setTitle("Bild Komprimierung");
		// center the window in the middle of the screen
		setLocationRelativeTo(null);
		toFront();
		requestFocus();
		setFont(GuiStyle.TEXT_FONT);
		setForeground(GuiStyle.FOREGROUND_TEXT_WHITE);
		setBackground(GuiStyle.BACKGROUND_GREEN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		fileList = new ArrayList<File>();

		// creating the gui objects

		// create the panels for the menu
		contentPane = new JPanel();
		JPanel northPanel = new JPanel();
		JPanel bottomPanel = new JPanel();

		// set the layout of the panels
		// a gridbag layout is used for the top panel so that the dropdown menu remains
		// on the left side
		GridBagLayout gbl_northPanel = new GridBagLayout();
		gbl_northPanel.columnWidths = new int[] { 70, 125, 0 };
		gbl_northPanel.rowHeights = new int[] { 24, 0 };
		gbl_northPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_northPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		northPanel.setLayout(gbl_northPanel);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// Menu at the top of the screen
		JMenu dateiMenu = new JMenu("Menü");
		JMenuItem dateiMenuOpenItem = new JMenuItem("Datei öffnen");
		JMenuItem openSettingsItem = new JMenuItem("Einstellungen");

		dateiMenu.add(dateiMenuOpenItem);
		dateiMenu.add(openSettingsItem);

		// create the text area and the scrollpane
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);

		JScrollPane middleScrollPane = new JScrollPane(textArea);

		// create the buttons
		CustomButton compressButton = new CustomButton("Komprimieren");		

		// add the objects to the panels
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.add(compressButton);
		contentPane.add(northPanel, BorderLayout.NORTH);
		contentPane.add(middleScrollPane, BorderLayout.CENTER);

		JMenuBar topMenuBar = new JMenuBar();
		GridBagConstraints gbc_topMenuBar = new GridBagConstraints();
		gbc_topMenuBar.insets = new Insets(0, 0, 0, 5);
		gbc_topMenuBar.gridx = 0;
		gbc_topMenuBar.gridy = 0;
		northPanel.add(topMenuBar, gbc_topMenuBar);

		topMenuBar.add(dateiMenu);

		// add logic to the objects through listeners
		dateiMenuOpenItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// creates a String with the user directory of the system as a value
				String userDir = System.getProperty("user.home");

				// creates a filter for the filechooser which will only show image files
				FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files",
						ImageIO.getReaderFileSuffixes());

				JFileChooser chooser = new JFileChooser(userDir + "/Downloads");
				chooser.addChoosableFileFilter(imageFilter);
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setMultiSelectionEnabled(true);
				chooser.setDialogTitle("Datei auswählen");
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.showOpenDialog(null);

				for (int i = 0; i < chooser.getSelectedFiles().length; i++) {
					if (!fileList.contains(chooser.getSelectedFiles()[i])) {
						fileList.add(chooser.getSelectedFiles()[i]);
						textArea.append(
								chooser.getSelectedFiles()[i].getName() + " wurde zum komprimieren ausgewählt.\n");
					}

				}

			}
		});

		openSettingsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				FrameController.setNextFrame(settingsMenu, MainMenu.this);

			}
		});

		compressButton.addActionListener(new ActionListener() {

			CompressCommands commands = new CompressCommands();

			@Override
			public void actionPerformed(ActionEvent e) {

				// check if the user already selected a picture
				if (!textArea.getText().isBlank()) {

					// compress the selected images
					commands.compressImages(fileList);

				} else {

					// Show a Message that informs the user that no images are selected
					JOptionPane.showMessageDialog(new JPanel(),
							("Es muss zuerst ein Bild ausgewählt werden."));

				}

			}

		});

	}

}
