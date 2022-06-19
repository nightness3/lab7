package common.commands;

import common.Request;
import common.vehicle.Vehicle;
import server.database.DBWorker;
import server.utility.CollectionManager;
import server.utility.VehicleCollection;

import java.util.TreeSet;

public class PrintDescendingCommand extends Command {

    public PrintDescendingCommand() {
        super("prind_descending");
    }

    @Override
    public Request execute(Request request, DBWorker dbWorker, CollectionManager cm) {
        cm.updateCollection();
        TreeSet<Vehicle> tree_set = new TreeSet<>(cm.getCollection());
        StringBuilder responseString = new StringBuilder();
        tree_set.stream().forEach(x -> responseString.append(x.getFuelType().toString()).append("\n"));
        String answer = responseString.toString();
        request.setRspAnswer(answer.substring(0,answer.length()-1));
        return request;
    }
}
