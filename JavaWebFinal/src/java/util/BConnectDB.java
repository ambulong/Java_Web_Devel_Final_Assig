/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;

/**
 *
 * @author Ambulong
 */
public class BConnectDB {

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/test";
    private String user = "test";
    private String passwd = "test";

    public BConnectDB() throws Exception {
        BConfig bc = new BConfig();
        this.driver = bc.getSqlDriver();
        this.passwd = bc.getSqlPassword();
        this.url = bc.getSqlUrl();
        this.user = bc.getSqlUser();
    }

    public Connection getConnectDB() throws SQLException, Exception {
        Connection conn = null;

        Class.forName(driver);
        conn = DriverManager.getConnection(url, user, passwd);

        return conn;
    }
}
