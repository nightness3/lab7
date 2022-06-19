package common.commands;

import common.Request;
import common.vehicle.Vehicle;
import server.database.DBWorker;
import server.utility.CollectionManager;
import server.utility.VehicleCollection;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class InfoCommand extends Command {

    public InfoCommand() {
        super("info");
    }

    @Override
    public Request execute(Request request, DBWorker dbWorker, CollectionManager cm) {

        cm.updateCollection();
        Set<Vehicle> vehicles = cm.getCollection();
        Format formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");

        String responseString = "";

        responseString += "$ ---------------------------------------------\n";
        responseString += "$ Информация о коллекции типа Vehicle:\n";
        responseString += "$ Дата инициализации: " + formatter.format(findLowerDate(vehicles)) + "\n";
        responseString += "$ Количество элементов в коллекции: " + vehicles.size() + "\n";
        responseString += "$ ---------------------------------------------";

        request.setRspAnswer(responseString);
        return request;

    }

    private static Date findLowerDate (Set<Vehicle> vehicleSet) {

        AtomicReference<Date> date = new AtomicReference<>(new Date());

        for (Vehicle v : vehicleSet) {
            if (date.get().compareTo(v.getCreationDate()) > 0) {
                date.set(v.getCreationDate());
            }
        }

        vehicleSet.stream().filter(x -> date.get().compareTo(x.getCreationDate()) > 0).forEach(x -> date.set(x.getCreationDate()));

        return date.get();

    }

}
