package common.commands;

import common.Request;
import common.vehicle.Vehicle;
import server.database.DBWorker;
import server.utility.CollectionManager;
import server.utility.VehicleCollection;

import java.util.LinkedHashSet;
import java.util.Set;

public class ShowCommand extends Command {

    public ShowCommand() {
        super("show");
    }

    @Override
    public Request execute(Request request, DBWorker dbWorker, CollectionManager cm) {
        StringBuilder responseString = new StringBuilder();
        cm.updateCollection();
        Set<Vehicle> collection = cm.getCollection();
        for (Vehicle x : collection) {
            responseString.append(x.toString()).append("\n");
        }
        String answer = responseString.toString();
        request.setRspAnswer(answer.substring(0,answer.length()-1));
        return request;
    }

}
