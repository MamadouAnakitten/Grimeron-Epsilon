package org.arps.Grimeron.utils;

import java.sql.*;
import java.util.Properties;
import javax.naming.*;
import javax.sql.*;

public class DBConnector {
  
    /** Uses JNDI and Datasource (preferred style).   */
    public static Connection getJNDIConnection(){
        String DATASOURCE_CONTEXT = "java:comp/env/jdbc/blah";
    
        Connection result = null;
        try 
        {
            Context initialContext = new InitialContext();
            if ( initialContext == null)
            {
                log("JNDI problem. Cannot get InitialContext.");
            }
            
            DataSource datasource = (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
            
            if (datasource != null) {
                result = datasource.getConnection();
            } else {
                log("Failed to lookup datasource.");
            }
        } catch ( NamingException ex ) {
            log("Cannot get connection: " + ex);
        } catch(SQLException ex){
            log("Cannot get connection: " + ex);
        }
        return result;
    }

    /** Uses DriverManager.  */
    public static Connection getSimpleConnection(String url, String user, String pass) {
        //See your driver documentation for the proper format of this string :
        String DB_CONN_STRING = url;
        //Provided by your driver documentation. In this case, a MySql driver is used : 
        String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
        String USER_NAME = user;
        String PASSWORD = pass;
    
        Connection result = null;
        try 
        {
            Class.forName(DRIVER_CLASS_NAME).newInstance();
        }
        catch (Exception ex){
            log("Check classpath. Cannot load db driver: " + DRIVER_CLASS_NAME);
        } 
        try 
        {
            result = DriverManager.getConnection(DB_CONN_STRING, USER_NAME, PASSWORD);
        } catch (SQLException e){
            log( "Driver loaded, but cannot connect to db: " + DB_CONN_STRING);
        }
        return result;
    }

    private static void log(Object aObject){
        System.out.println(aObject);
    }
}