package server.utility;

import common.vehicle.Coordinates;
import common.vehicle.FuelType;
import common.vehicle.Vehicle;
import common.vehicle.VehicleType;
import server.database.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class VehicleCollection {

    public synchronized static Set<Vehicle> getCollection(DBWorker dbWorker) {
        Set<Vehicle> v = new LinkedHashSet<>();
        try {
            ResultSet data = dbWorker.getCollection();
            while (data.next()) {
                Vehicle veh = new Vehicle();
                veh.setId((long) data.getInt(1));
                veh.setName(data.getString(2));
                veh.setCreationDate(data.getTimestamp(3));
                Coordinates coordinates = new Coordinates((long)data.getInt(4),(long)data.getInt(5));
                veh.setCoordinates(coordinates);
                veh.setEnginePower((float)data.getDouble(6));
                veh.setNumberOfWheels((long)data.getInt(7));
                veh.setType(VehicleType.valueOf(data.getString(8)));
                veh.setFuelType(FuelType.valueOf(data.getString(9)));
                veh.setUsername(data.getString(10));
                v.add(veh);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка получения коллекции из базы данных");
        }
        return v;
    }

}
