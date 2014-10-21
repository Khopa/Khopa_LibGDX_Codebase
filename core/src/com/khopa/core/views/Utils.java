package com.khopa.core.views;

import com.khopa.core.AGC;

/**
 * 
 * Utils function for views
 * 
 * @author Clément Perreau
 *
 */
public class Utils {

	/**
	 * Transform a position in percent to pixels on the x axis
	 */
	public static int percentToPixelW(float widthPercent){
		return (int) (widthPercent* AGC.getW());
	}
	
	/**
	 * Transform a position in percent to pixels on the y axis
	 */
	public static int percentToPixelH(float heightPercent){
		return (int) (heightPercent*AGC.getH());
	}
	
	/**
	 * Join strings
	 */
	public static String strJoin(String[] aArr, String sSep) {
	    StringBuilder sbStr = new StringBuilder();
	    for (int i = 0, il = aArr.length; i < il; i++) {
	        if (i > 0)
	            sbStr.append(sSep);
	        sbStr.append(aArr[i]);
	    }
	    return sbStr.toString();
	}
	
	
}
