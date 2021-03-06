package com.khopa.core.utils;

import java.util.HashMap;

/**
 * This dictionnary helper class is used to store all kind of variables inside an HashMap
 *
 * NB : Not useful anymore with the new libgdx preferences class
 *
 * @author Clément Perreau
 */
@Deprecated
public class PropertyDictionnary {

    /**
     * Float values
     */
    protected HashMap<String, Float> floatValues;

    /**
     * Integer values
     */
    protected HashMap<String, Integer> integerValues;

    /**
     * String values
     */
    protected HashMap<String, String> stringValues;

    /**
     * Boolean values
     */
    protected HashMap<String, Boolean> booleanValues;

    public PropertyDictionnary(){
        floatValues = new HashMap<String, Float>();
        integerValues = new HashMap<String, Integer>();
        stringValues = new HashMap<String, String>();
        booleanValues = new HashMap<String, Boolean>();
    }

    public void setValue(String key, float value){
        floatValues.put(key, value);
    }

    public void setValue(String key, int value){
        integerValues.put(key, value);
    }

    public void setValue(String key, String value){
        stringValues.put(key, value);
    }

    public void setValue(String key, Boolean value){
        booleanValues.put(key, value);
    }

    /**
     * Return float value
     * @param key
     */
    public float getFloatValue(String key){
        if(!floatValues.containsKey(key)){
            return 0.5f;
        }
        else{
            return floatValues.get(key);
        }
    }

    /**
     * Return integer value
     * @param key
     * @return
     */
    public int getIntegerValue(String key){
        return integerValues.get(key);
    }

    /**
     * Return string value
     * @param key
     * @return
     */
    public String getStringValue(String key){
        return stringValues.get(key);
    }

    /**
     * Return boolean value
     * @param key
     * @return
     */
    public Boolean getBooleanValue(String key){
        if(!booleanValues.containsKey(key)){
            return false;
        }
        else{
            return booleanValues.get(key);
        }
    }

}
