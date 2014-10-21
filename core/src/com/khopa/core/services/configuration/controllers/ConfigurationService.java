package com.khopa.core.services.configuration.controllers;

import com.khopa.core.utils.PropertyDictionnary;

/**
 * 
 * Configuration manager
 * 
 * @author Clément Perreau
 *
 */
public abstract class ConfigurationService extends PropertyDictionnary {
	
	/**
	 * Singleton
	 */
	private static ConfigurationService instance;
	
	
	public ConfigurationService(){
        super();
		instance = this;
		load();
	}

	
	public abstract void save();
	
	public abstract void load();
	
	/**
	 * Get configuration singleton instance
	 * @return
	 */
	public static ConfigurationService getInstance(){
		return instance;
	}

	
	
	
	
}
