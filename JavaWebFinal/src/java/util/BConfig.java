/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Ambulong
 */
public class BConfig {
    private Properties prop;
    
    private String siteName;
    private String siteUrl;
    private String sqlDriver;
    private String sqlUrl;
    private String sqlUser;
    private String sqlPassword;
    
    public BConfig() throws FileNotFoundException, IOException{
        this.prop = new Properties();
        String packagePath = this.getClass().getResource("").getPath();
        FileInputStream fis = new FileInputStream(packagePath+"/../../../../../config.properties");
        //InputStream in = BConfig.class.getClassLoader().getResourceAsStream("/src/config.properties");
        prop.load(fis);
        
        this.siteName = prop.getProperty("sitename");
        this.siteUrl = prop.getProperty("siteurl");
        this.sqlDriver = prop.getProperty("driver");
        this.sqlPassword = prop.getProperty("password");
        this.sqlUrl = prop.getProperty("url");
        this.sqlUser = prop.getProperty("username");;
    }
    
    public String getSiteName(){
        return this.siteName;
    }
    
    public String getSiteUrl(){
        return this.siteUrl;
    }
    
    public String getSqlDriver(){
        return this.sqlDriver;
    }
    
    public String getSqlPassword(){
        return this.sqlPassword;
    }
    
    public String getSqlUrl(){
        return this.sqlUrl;
    }
    
    public String getSqlUser(){
        return this.sqlUser;
    }
}
