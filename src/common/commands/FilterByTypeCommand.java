package common.commands;

import common.Request;
import common.vehicle.Vehicle;
import common.vehicle.VehicleType;
import server.database.DBWorker;
import server.utility.CollectionManager;
import server.utility.VehicleCollection;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class FilterByTypeCommand extends Command {

    public FilterByTypeCommand() {
        super("filter_by_type");
    }

    @Override
    public Request execute(Request request, DBWorker dbWorker, CollectionManager cm) {
        cm.updateCollection();
        VehicleType type = null;
        ArrayList<String> arguments = request.getReqCommand().getArguments();
        Set<Vehicle> vehicles = cm.getCollection();
        type = VehicleType.valueOf(arguments.get(0));

        VehicleType finalType = type;
        StringBuilder stringBuilder = new StringBuilder();
        vehicles.stream().filter(x -> x.getType() == finalType).forEach(x -> stringBuilder.append(x.toString()).append("\n"));
        String answer = stringBuilder.toString();
        request.setRspAnswer(answer.substring(0,answer.length()-2));
        return request;
    }

}
