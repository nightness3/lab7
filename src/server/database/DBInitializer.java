package server.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {

    private Connection connection;

    public DBInitializer (Connection connection) {
        this.connection = connection;
    }

    public void initialize() throws SQLException {

        Statement statement = connection.createStatement();

        statement.executeUpdate("CREATE SEQUENCE IF NOT EXISTS ids START 1");

        System.out.println("Успешная инициализация/проверка ID_Sequence");

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS s335088Vehicle (" +
                "id int PRIMARY KEY, " +
                "name varchar(255), " +
                "creationDate date DEFAULT (current_date)," +
                "x bigint NOT NULL, " +
                "y bigint NOT NULL, " +
                "enginepower real NOT NULL ," +
                "numberofwheels bigint NOT NULL, " +
                "vehicletype varchar(255) NOT NULL CHECK(vehicletype = 'PLANE' or vehicletype = 'HELICOPTER' or vehicletype = 'HOVERBOARD'), " +
                "fueltype varchar(255) NOT NULL CHECK(fueltype = 'GASOLINE' or fueltype = 'NUCLEAR' or fueltype = 'PLASMA' or fueltype = 'ANTIMATTER'), " +
                "username varchar(255) NOT NULL)");

        System.out.println("Успешная инициализация/проверка s335088Vehicle");

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS s335088Users (" +
                "username varchar(255) PRIMARY KEY, " +
                "hashPassword BYTEA DEFAULT (null)" +
                ")");

        System.out.println("Успешная инициализация/проверка s335088Users");

    }

}
