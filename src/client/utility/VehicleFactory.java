package client.utility;

import common.vehicle.Coordinates;
import common.vehicle.FuelType;
import common.vehicle.Vehicle;
import common.vehicle.VehicleType;

import java.util.Scanner;

public class VehicleFactory {

    public Vehicle makeVehicle () {
        Console console = Console.getInstance();
        Scanner scanner = new Scanner(System.in);
        Vehicle vehicle = new Vehicle();
        vehicle.setName(console.getString(scanner, "Name"));
        vehicle.setCoordinates(new Coordinates(console.getLong(scanner, "X"), console.getLong(scanner, "Y")));
        vehicle.setEnginePower(console.getFloat(scanner, "EnginePower"));
        vehicle.setNumberOfWheels(console.getLong(scanner, "NumberOfWheels"));
        System.out.println("1 - PLANE, 2 - HELICOPTER, 3 - HELICOPTER");
        int idVehicleType = console.getInt(scanner, "id_VehicleType");
        if (idVehicleType == 1) {
            vehicle.setType(VehicleType.PLANE);
        } else if (idVehicleType == 2) {
            vehicle.setType(VehicleType.HELICOPTER);
        } else if (idVehicleType == 3) {
            vehicle.setType(VehicleType.HELICOPTER);
        }
        System.out.println("1 - GASOLINE, 2 - NUCLEAR, 3 - PLASMA, 4 - ANTIMATTER");
        int idFuelType = console.getInt(scanner, "id_FuelType");
        if (idFuelType == 1) {
            vehicle.setFuelType(FuelType.GASOLINE);
        } else if (idFuelType == 2) {
            vehicle.setFuelType(FuelType.NUCLEAR);
        } else if (idFuelType == 3) {
            vehicle.setFuelType(FuelType.PLASMA);
        } else if (idFuelType == 4) {
            vehicle.setFuelType(FuelType.ANTIMATTER);
        }
        vehicle.setUsername(new UserWrapper().getUser(scanner).getUsername());
        return vehicle;
    }

}
