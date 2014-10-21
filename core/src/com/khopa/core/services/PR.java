package com.khopa.core.services;

import com.khopa.core.services.graphic.impl.DefinitionDeterminer;

/**
 * Path Resolver
 * @author Clément Perreau
 */
public class PR {

    /**
     * Path Resolver for gfx resources
     * @param path New path taking the device screen into account
     */
    public static String treat(String path){
        String screenType = DefinitionDeterminer.getInstance().getScreenType().toString();
        return "data/gfx/"+screenType+"/"+path;
    }

}
