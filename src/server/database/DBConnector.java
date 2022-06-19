package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static String URL;
    private static String LOGIN;
    private static String PASSWORD;

    public DBConnector (String URL, String LOGIN, String PASSWORD) {
        this.URL = URL;
        this.LOGIN = LOGIN;
        this.PASSWORD = PASSWORD;
    }

    public Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Нет драйвера для подключения к базе данных");
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных");
        }
        return conn;
    }

}
