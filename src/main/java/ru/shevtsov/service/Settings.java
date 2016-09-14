package ru.shevtsov.service;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by dead_rabbit on 14.09.2016.
 */
public class Settings {
    private static final Settings INSTANCE = new Settings();

    private final Properties properties= new Properties();

    private Settings(){
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader().getResource("shevtsov.properties").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Settings getInstance(){return INSTANCE;}

    public String value(String key){return this.properties.getProperty(key);}
}

