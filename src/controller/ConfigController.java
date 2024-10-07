package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigController {
	
	private String userHomeDirectory = System.getProperty("user.home");
	private File outputDirectory = new File(userHomeDirectory, "image-compressor");
	private File settingsDirectory = new File(outputDirectory, "settings");
	private File configFile = new File(settingsDirectory.getAbsolutePath()+File.separator+"config.properties");

	private Properties props = null;
	
    // setup can be called as often as possible
    // reloads config etc.
    public void setup() {
    	
    	// execute setup
        loadConfig(); // config should be loaded first
        
    }	
	
	private void createDefaultConfig() {
        // regardless if a config exists, overwrite it
        try {
        
        	
        	OutputStream output = new FileOutputStream(configFile.getAbsolutePath());

            Properties prop = new Properties();
            
             

          // set the default values
            //compression value of the images
            prop.setProperty("compression.value", "50");
            
            //the path where the images are stored
            prop.setProperty("output.path", outputDirectory.getAbsolutePath());

            // save config
            prop.store(output, null);

            output.close();
        } catch (IOException e) {
            // TODO: better error handling
            // should never be thrown
            e.printStackTrace();
        }
    }
	
	private void loadConfig() {
	
    	//create the directories on the system if they dont exist yet
        if(!outputDirectory.exists()) {
        	outputDirectory.mkdir();
        }
        
        if(!settingsDirectory.exists()) {
        	settingsDirectory.mkdir();
        } 
        
        // check if config is yet to be created
        if (!configFile.exists()) {
            createDefaultConfig();
        }


        try {
            props = new Properties();
            props.load(new FileInputStream(configFile.getAbsolutePath()));
        } catch (IOException e) {
            // TODO: better error handling
            // should never be thrown
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        // check if config was already loaded
        if (props == null) {
            loadConfig();
        }

        return props.getProperty(key);
    }

    public void setValue(String key, String value) {
        // check if config was already loaded
        if (props == null) {
            loadConfig();
        }

        props.setProperty(key, value);

        try {
            // save config
            FileOutputStream output = new FileOutputStream(configFile.getAbsolutePath());
            props.store(output, null);
            output.close();
        } catch (IOException e) {
            // TODO: better error handling
            // should never be thrown
            e.printStackTrace();
        }
    }
 	
	
	
	
	
	
	
}
