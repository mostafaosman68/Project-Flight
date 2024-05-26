package com.example.project_final;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler
{
    Connection dbConnection;        //connection with database

    public Connection getDbConnection() throws ClassNotFoundException, SQLException
    {

        String connectionString ="jdbc:mysql://127.0.0.1:3306/projectfinal";        //connection with DB
        //using configs
        Class.forName("com.mysql.cj.jdbc.Driver");              //driver manager class

        dbConnection = DriverManager.getConnection(connectionString, "root", "Momen2005@");
        return dbConnection;
    }




}


