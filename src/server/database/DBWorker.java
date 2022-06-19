package server.database;

import common.User;
import common.vehicle.Vehicle;
import server.utility.HashPassword;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DBWorker {

    Connection connection;
    HashPassword hashPassword;

    public DBWorker(Connection connection, String hashAlgorithm) throws NoSuchAlgorithmException {
        this.connection = connection;
        this.hashPassword = new HashPassword(hashAlgorithm);
    }

    public synchronized ResultSet getCollection() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.takeAll.getStatement());
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Ошибка при получении коллекции");
            return null;
        }
    }

    private synchronized Integer generateId() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Statements.generateId.getStatement());
            if (resultSet.next()) {
                return resultSet.getInt("nextval");
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Ошибка при генерации Id");
            return null;
        }
    }

    public synchronized boolean loginUser(User user) {
        try {
            PreparedStatement checkStatement = connection.prepareStatement(Statements.checkUser.getStatement());
            checkStatement.setString(1, user.getUsername());
            checkStatement.setBytes(2, hashPassword.hashPassword(user.getPassword()));
            ResultSet users = checkStatement.executeQuery();
            return users.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public synchronized boolean addUser(User user) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(Statements.addUserWithPassword.getStatement());
            insertStatement.setString(1, user.getUsername());
            insertStatement.setBytes(2, hashPassword.hashPassword(user.getPassword()));
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public synchronized String addVehicle(Vehicle v) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.addVehicle.getStatement());
            setVehicleToStatement(preparedStatement, v);
            System.out.println("check");
            preparedStatement.executeUpdate();
            return "Элемент успешно добавлен в базу данных";
        } catch (SQLException e) {
            return "Элемент не получилось добавить в базу данных";
        }
    }

    public synchronized String updateById(Vehicle v, int id, String username) {
        try {
            String status = getById(id, username);
            if (!status.equals("OK")) return status;

            PreparedStatement preparedStatement = connection.prepareStatement(Statements.updateVehicle.getStatement());
            setUpdatedVehicleToStatement(preparedStatement, v, id);
            preparedStatement.executeUpdate();
            return "Элемент успешно обновлен в базе данных";
        } catch (SQLException e) {
            return "Элемент не получилось обновить в базе данных";
        }
    }

    public synchronized String removeById(int id, String username) {
        try {
            String status = getById(id, username);
            if (!status.equals("OK")) return status;

            PreparedStatement preparedStatement = connection.prepareStatement(Statements.deleteById.getStatement());
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return "Элемент был удален из базы данных";
        } catch (SQLException e) {
            return "Ошибка при удалении объекта из базы данных";
        }
    }

    public synchronized String getById(int id, String username) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(Statements.getById.getStatement());
            preparedStatement.setInt(1, id);
            ResultSet deletingStudyGroup = preparedStatement.executeQuery();

            if (!deletingStudyGroup.next())
                return "Объекта с таким айди не существует";

            if (!deletingStudyGroup.getString("username").equals(username))
                return "Вы не владелец элемента";

            return "OK";
        } catch (SQLException throwables) {
            return "Ошибка при обращении к базе данных";
        }
    }

    public synchronized String clear(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.clearAllByUser.getStatement());
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            return "Элементы пользователя " + username + " были удалены из базы данных";
        } catch (SQLException e) {
            return "Ошибка очистки базы данных";
        }

    }

    private synchronized void setVehicleToStatement (PreparedStatement stmt, Vehicle v) throws SQLException {
        Integer newId = generateId();
        if (newId == null) return;
        v.setId(newId.longValue());
        stmt.setInt(1, newId);
        stmt.setString(2, v.getName());
        stmt.setInt(3, v.getCoordinates().getX().intValue());
        stmt.setInt(4, (int) v.getCoordinates().getY());
        stmt.setDouble(5, v.getEnginePower());
        stmt.setInt(6, v.getNumberOfWheels());
        stmt.setString(7, v.getType().toString());
        stmt.setString(8, v.getFuelType().toString());
        stmt.setString(9, v.getUsername());
    }

    private synchronized void setUpdatedVehicleToStatement(PreparedStatement stmt, Vehicle v, int id) throws SQLException {
        stmt.setString(1, v.getName());
        stmt.setInt(2, v.getCoordinates().getX().intValue());
        stmt.setInt(3, (int) v.getCoordinates().getY());
        stmt.setDouble(4, v.getEnginePower());
        stmt.setInt(5, v.getNumberOfWheels());
        stmt.setString(6, v.getType().toString());
        stmt.setString(7, v.getFuelType().toString());
        stmt.setInt(8, id);
    }

}
