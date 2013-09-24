/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdvsys.ases.utils;

/**
 *
 * @author Christian
 */
public class AppProperties {
    
    private static String preprocessDirectory;
    private static String scriptsDirectory;
    

    public static String getPreprocessDirectory() {
        return preprocessDirectory;
    }

    public static void setPreprocessDirectory(String aPreprocess) {
        preprocessDirectory = aPreprocess;
    }

    public static String getScriptsDirectory() {
        return scriptsDirectory;
    }

    public static void setScriptsDirectory(String scriptsDirectory) {
        AppProperties.scriptsDirectory = scriptsDirectory;
    }
    
    
    
    
    
}
