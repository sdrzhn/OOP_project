package com.company.data;

import com.company.data.interfaces.IDataManager;

import java.sql.DriverManager;
import java.sql.Connection;

public class DataManager implements IDataManager {      //implementation
    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/medicine", "postgres", "Aluaalua2003!");      //connecting to our database medicine

            return connection;
        }
        catch (Exception e) {     //this exception will occur if doesn't connected to a database
            e.printStackTrace();
        }

        return null;
    }
}
