package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.Utils.DotEnv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContextImpl implements DBContext {

    private final DotEnv dotEnv = new DotEnv();

    @Override
    public Connection getConnection() {
        String connectionString = "jdbc:mysql://" + dotEnv.get("MYSQL_HOST")
                + "/" + dotEnv.get("MYSQL_DATABASE");
        String username = dotEnv.get("MYSQL_USERNAME");
        String password = dotEnv.get("MYSQL_PASSWORD");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(connectionString, username, password);
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
