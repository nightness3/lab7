package server.database;

public enum Statements {

    addVehicle("INSERT INTO s335088Vehicle " +
            "(id, name, x, y, enginepower, numberofwheels, vehicletype, " +
            "fueltype, username) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)"),

    generateId("SELECT nextval('ids')"),

    addUserWithPassword("INSERT INTO s335088users (username, hashPassword) VALUES(?, ?)"),

    checkUser("SELECT * FROM s335088users WHERE username=? AND hashPassword=?"),

    updateVehicle("UPDATE s335088vehicle SET " +
            "name=?, x=?, y=?, enginepower=?, numberofwheels=?, vehicletype=?, " +
            "fueltype=? " +
            "WHERE id = ?"),

    getById("SELECT * FROM s335088vehicle WHERE id = ?"),

    deleteById("DELETE FROM s335088vehicle WHERE id = ?"),

    clearAllByUser("DELETE FROM s335088vehicle WHERE username = ?"),

    clearAll("DELETE FROM s335088vehicle"),

    takeAll("SELECT * FROM s335088vehicle");

    private final String statement;

    Statements(String aStatement) {
        statement = aStatement;
    }

    public String getStatement() {
        return statement;
    }

}
