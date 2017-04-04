package com.enttax.util.config;


import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;

/**
 * Created by lcyanxi on 17-4-4.
 */
public class CompositeFactory {
    private static CompositeConfiguration configuration;

    private CompositeFactory(){}

    public static CompositeConfiguration getInstance(){
        if(configuration == null){
            synchronized (CompositeFactory.class) {
                configuration = new CompositeConfiguration();
            }
        }
        return configuration;
    }

    public static void addConfiguration(Configuration config){
        configuration = getInstance();
        configuration.addConfiguration(config);
    }

    public static String getString(String key){
        return configuration.getString(key);
    }
}
