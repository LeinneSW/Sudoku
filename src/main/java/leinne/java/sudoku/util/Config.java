package leinne.java.sudoku.util;

import org.ini4j.Ini;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Config{

    private final Ini ini = new Ini();

    public void loadData(){
        try{
            ini.load(new FileInputStream("./config.ini"));
        }catch(IOException e){}
    }

    public void saveData(){
        try{
            ini.store(new FileOutputStream("./config.ini"));
        }catch(IOException e){}
    }

    public String get(String section, String key){
        return ini.get(section, key);
    }

    public String get(String section, String key, String defaultValue){
        var value = ini.get(section, key);
        return value == null ? defaultValue : value;
    }

    public boolean getBoolean(String section, String key){
        return getBoolean(section, key, false);
    }

    public boolean getBoolean(String section, String key, boolean defaultValue){
        var value = ini.get(section, key);
        return value == null ? defaultValue : Boolean.parseBoolean(value);
    }
}
