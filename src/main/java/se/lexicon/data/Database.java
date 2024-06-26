package se.lexicon.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DB_URL;
    private static final String USER;
    private static final String PASSWORD;

    static{
        DB_URL = "jdbc:mysql://localhost:3306/todoit?&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        USER = "root";
        PASSWORD = "1234";
    }

    public static Connection getConnection() {

        Connection connection = null;
        try{
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e){
                    e.printStackTrace();
        }
        return connection;
    }


}
