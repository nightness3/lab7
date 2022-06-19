package common.commands;

import common.Request;
import common.vehicle.Vehicle;
import server.database.DBWorker;
import server.utility.CollectionManager;
import server.utility.VehicleCollection;

import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveLowerCommand extends Command {

    public RemoveLowerCommand() {
        super("remove_lower");
    }

    @Override
    public Request execute(Request request, DBWorker dbWorker, CollectionManager cm) {
        Vehicle newVehicle = request.getReqVehicle();
        cm.updateCollection();
        Set<Vehicle> vehicles = cm.getCollection();

        String answer;

        int sizeBegin = vehicles.size();
        vehicles.stream().filter(x -> newVehicle.compareTo(x) == -1 && x.getUsername().equals(newVehicle.getUsername())).forEach(x -> dbWorker.removeById((int)(long)x.getId(), x.getUsername()));
        vehicles.stream().filter(x -> newVehicle.compareTo(x) == -1 && x.getUsername().equals(newVehicle.getUsername())).forEach(x -> cm.remove(x));
        int sizeEnd = vehicles.size();

        if (sizeEnd >= sizeBegin) {
            answer = "Команда не нашла подходящих элементов";
        } else {
            answer = "Элементы были удалены";
        }
        request.setRspAnswer(answer);
        return request;
    }

}
