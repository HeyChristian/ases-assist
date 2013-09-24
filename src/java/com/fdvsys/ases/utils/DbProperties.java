/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdvsys.ases.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *
 * @author Christian
 */
public class DbProperties {
    
    
    
    private static String database;
    private static String dbuser;
    private static String dbpassword;
    private static String hostname;
    private static String port;
    private static String driver;

    public static String getDatabase() {
        return database;
    }

    public static void setDatabase(String database) {
        DbProperties.database = database;
    }

    public static String getDbuser() {
        return dbuser;
    }

    public static void setDbuser(String dbuser) {
        DbProperties.dbuser = dbuser;
    }

    public static String getDbpassword() {
        return dbpassword;
    }

    public static void setDbpassword(String dbpassword) {
        DbProperties.dbpassword = dbpassword;
    }

    public static String getHostname() {
        return hostname;
    }

    public static void setHostname(String hostname) {
        DbProperties.hostname = hostname;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        DbProperties.port = port;
    }

    public static String getDriver() {
        return driver;
    }

    public static void setDriver(String driver) {
        DbProperties.driver = driver;
    }

    public static String getDbUrl(){
        
        return "jdbc:mysql://" + DbProperties.hostname + ":" + DbProperties.port +"/" + DbProperties.database + "?autoReconnect=true";
        
    }
    
    
    public static Map getProperties()
    {
    
        Map properties = new HashMap();
        //Configure the internal EclipseLink connection pool
        properties.put("javax.persistence.jdbc.driver", DbProperties.getDriver());
        properties.put("javax.persistence.jdbc.url", DbProperties.getDbUrl());
        properties.put("javax.persistence.jdbc.user",DbProperties.getDbuser());
        properties.put("javax.persistence.jdbc.password", DbProperties.getDbpassword());
        return properties;
    }
    
    
    
    
    
    public  DbProperties(){
        
//        Properties prop = new Properties();
//         try {
//            
//             
//             ServletContext ctx = null;
//            InputStream configStream = ctx.getResourceAsStream("/WEB-INF/appconfig.properties");
//             
//     
//             prop.load(configStream);
//             
//             this.database      = prop.getProperty("dbname");
//             this.dbuser        = prop.getProperty("user");
//             this.dbpassword    = prop.getProperty("password");
//             this.hostname      = prop.getProperty("hostname");
//             this.port          = prop.getProperty("port");
//             
//          //   System.out.println("Database: " + GetDatabase());
//           //  System.out.println("Hostname: " + GetHostname());
//           //  System.out.println("User    : " + GetDbUser());
//             
//         } catch (IOException ex) {
//             Logger.getLogger(DbProperties.class.getName()).log(Level.SEVERE, null, ex);
//             
//             System.out.println("DONT FOUND appconfig.properties");
//         }
        
        
    }
}
