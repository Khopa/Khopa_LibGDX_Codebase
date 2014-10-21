package com.khopa.core.services.resources;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.khopa.core.CLT;
import com.khopa.core.services.PR;

import java.io.IOException;

/**
 * Resource Loader
 * @author Clément Perreau
 * @date 29/05/2014
 */
public class ResourceLoader {

    /**
     * Resource file path
     */
    private final static String resources = "data/meta/resources.xml";

    /**
     * Initialized ?
     */
    private static boolean initialized = false;

    /**
     * Go through the resource definition file and add the found resources to the asset manager queue
     * @param am AssetManager instance for the application
     */
    public static void resolve(AssetManager am){

        if(!initialized) init(am);

        XmlReader reader = new XmlReader();
        FileHandle file = Gdx.files.internal(resources);

        try {

            Element root = reader.parse(file);

            for(Element resourceElement: root.getChildrenByName("Resource")){
                String classname = resourceElement.getChildByName("Class").getText();
                String path      = resourceElement.getChildByName("Path").getText();
                String resolver  = resourceElement.getChildByName("Path").getAttribute("withResolver");

                if(resolver != null && resolver.equals("true")){
                    path = PR.treat(path);
                }

                try {
                    Class<?> resourceClass = Class.forName(classname);
                    am.load(path, resourceClass);
                } catch (ClassNotFoundException e) {
                    Gdx.app.error(CLT.RL, "Error loading resources, class not found : " + classname);
                    Gdx.app.error(CLT.RL, e.getMessage());
                }
            }

        } catch (IOException e) {
            Gdx.app.error(CLT.RL, "Error loading resource, IOException possibly due to XML formatting error");
            Gdx.app.error(CLT.RL, e.getMessage());
        }

    }

    public static void init(AssetManager am){
        // resister your custom loader here !!!
        initialized = true;
    }

}
