package client.utility;

import common.commands.Command;
import common.vehicle.Vehicle;

public class VehicleWorker {
    public Vehicle getVehicle (Command command) {
        if (checkNeed(command)) {
            VehicleFactory vehicleFactory = new VehicleFactory();
            return vehicleFactory.makeVehicle();
        } else {
            return null;
        }
    }

    public boolean checkNeed (Command command) {
        String name = command.getName();
        if (name.equals("add") || name.equals("update") || name.equals("add_if_max") || name.equals("remove_lower")) {
            return true;
        }
        return false;
    }

}
