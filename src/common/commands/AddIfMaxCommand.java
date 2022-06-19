package common.commands;

import common.Request;
import common.vehicle.Vehicle;
import server.database.DBWorker;
import server.utility.CollectionManager;
import server.utility.VehicleCollection;

import java.util.LinkedHashSet;
import java.util.Set;

public class AddIfMaxCommand extends Command {

    public AddIfMaxCommand() {
        super("add_if_max");
    }

    @Override
    public Request execute(Request request, DBWorker dbWorker, CollectionManager cm) {
        cm.updateCollection();
        Set<Vehicle> vehicles = VehicleCollection.getCollection(dbWorker);
        Vehicle ourVehicle = request.getReqVehicle();
        boolean flag = true;
        for (Vehicle v : vehicles) {
            if (ourVehicle.compareTo(v) == 1) {
                System.out.println(v.getName());
                flag = false;
            }
        }
        String answer;
        if (flag) {
            answer = dbWorker.addVehicle(ourVehicle);
            if (answer.equals("Элемент успешно добавлен в базу данных")) {
                cm.add(request.getReqVehicle());
            }
        } else {
            answer = "Элемент не оказался максимальным";
        }
        request.setRspAnswer(answer);
        return request;
    }

}
