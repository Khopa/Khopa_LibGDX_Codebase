package com.khopa.core.services.configuration.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.khopa.core.CLT;
import com.khopa.core.services.configuration.controllers.ConfigurationService;

/**
 * 
 * Configuration manager
 * 
 * @author Clément Perreau
 *
 */
public class ConfigurationServiceImpl extends ConfigurationService {

	/**
	 * Path to the conf file
	 */
	private final static String filePath = "Tap2Swim/Config/config.xml";
	
	/**
	 * Root for xml conf file
	 */
	private Element root = null;
	
	@Override
	public void save() {
        Gdx.app.log(CLT.CFG, "Saving configuration");
		FileHandle file = Gdx.files.external(filePath);
		
		this.root = new Element("config", null);
		Element floats   = new Element("floats",null);
		Element integers = new Element("integers",null);
		Element strings  = new Element("strings",null);
		Element booleans = new Element("booleans",null);
		
		for(String key:floatValues.keySet()){
			Element newElement = new Element("float", null);
			newElement.setAttribute("key", key);
			newElement.setText(Float.toString(floatValues.get(key)));
			floats.addChild(newElement);
		}
		
		for(String key:integerValues.keySet()){
			Element newElement = new Element("integer", null);
			newElement.setAttribute("key", key);
			newElement.setText(Integer.toString(integerValues.get(key)));
			integers.addChild(newElement);
		}
		
		for(String key:stringValues.keySet()){
			Element newElement = new Element("string", null);
			newElement.setAttribute("key", key);
			newElement.setText(stringValues.get(key));
			strings.addChild(newElement);
		}
		
		for(String key:booleanValues.keySet()){
			Element newElement = new Element("boolean", null);
			newElement.setAttribute("key", key);
			newElement.setText(Boolean.toString(booleanValues.get(key)));
			booleans.addChild(newElement);
		}
		
		this.root.addChild(floats);
		this.root.addChild(integers);
		this.root.addChild(strings);
		this.root.addChild(booleans);
		
		file.writeString(root.toString(), false);
	
}

	@Override
	public void load() {
		XmlReader reader = new XmlReader();
		FileHandle file = Gdx.files.external(filePath);
		if(!file.exists()){ // Cree le fichier s'il n'existe pas
			createNewConfigurationFile(file);
			firstConfig();
		}
		else{
			try {
				this.root = reader.parse(file);
				Element floats   = this.root.getChildByName("floats");
				Element integers = this.root.getChildByName("integers");
				Element strings  = this.root.getChildByName("strings");
				Element booleans = this.root.getChildByName("booleans");
				
				for(Element floatElement:floats.getChildrenByName("float")){
					this.floatValues.put(floatElement.getAttribute("key"), Float.parseFloat(floatElement.getText()));
				}
				
				for(Element intElement:integers.getChildrenByName("integer")){
					this.integerValues.put(intElement.getAttribute("key"), Integer.parseInt(intElement.getText()));
				}

				for(Element strElement:strings.getChildrenByName("string")){
					this.stringValues.put(strElement.getAttribute("key"), strElement.getText());
				}
				
				for(Element boolElement:booleans.getChildrenByName("boolean")){
					this.booleanValues.put(boolElement.getAttribute("key"), Boolean.parseBoolean(boolElement.getText()));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				createNewConfigurationFile(file);
				firstConfig();
			}
		}
		
	}
	
	/**
	 * Create a new configuration file
	 * @param file
	 */
	private void createNewConfigurationFile(FileHandle file){
		/*this.root = new Element("config", null);
		Element floats = new Element("floats", null);
		Element integers = new Element("integers", null);
		Element strings = new Element("strings", null);
		Element booleans = new Element("booleans", null);
		this.root.addChild(floats);
		this.root.addChild(integers);
		this.root.addChild(strings);
		this.root.addChild(booleans);
		try{
			file.writeString(root.toString(), false);
		}
		catch(Exception e){
			e.printStackTrace();
		}*/
	}
	
	/**
	 * Configure for the first time the game (set default values)
	 */
	private void firstConfig(){
		
		this.floatValues.put("sfxVolume", 1.0f);
		this.floatValues.put("musicVolume", 1.0f);
		this.booleanValues.put("drawDebug", false);
		
	}
	
}

