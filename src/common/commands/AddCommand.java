package common.commands;

import common.Request;
import server.database.DBWorker;
import server.utility.CollectionManager;

public class AddCommand extends Command {

    public AddCommand() {
        super("add");
    }

    @Override
    public Request execute(Request request, DBWorker dbWorker, CollectionManager cm) {
        cm.updateCollection();
        String answer = dbWorker.addVehicle(request.getReqVehicle());
        request.setRspAnswer(answer);
        if (answer.equals("Элемент успешно добавлен в базу данных")) {
            cm.add(request.getReqVehicle());
        }
        return request;
    }

}
