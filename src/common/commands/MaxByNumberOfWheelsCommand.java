package common.commands;

import common.Request;
import common.vehicle.Vehicle;
import server.database.DBWorker;
import server.utility.CollectionManager;
import server.utility.VehicleCollection;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class MaxByNumberOfWheelsCommand extends Command {

    public MaxByNumberOfWheelsCommand() {
        super("max_by_number_of_wheels");
    }

    @Override
    public Request execute(Request request, DBWorker dbWorker, CollectionManager cm) {
        cm.updateCollection();
        AtomicLong max = new AtomicLong(-1L);
        Set<Vehicle> vehicles = cm.getCollection();

        long finalMax = max.get();
        vehicles.stream().filter(x -> x.getNumberOfWheels() > finalMax).forEach(x -> max.set(x.getNumberOfWheels()));

        StringBuilder result = new StringBuilder("");
        vehicles.stream().limit(1).filter(x -> x.getNumberOfWheels() == max.get()).forEach(x -> result.append(x.toString()));
        String answer;
        if (!result.toString().equals("")) {
            answer = result.toString();
        } else {
            answer = "Команда не нашла ни одного подходящего элемента";
        }
        request.setRspAnswer(answer.substring(0,answer.length()));
        return request;
    }
}
