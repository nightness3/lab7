package common.commands;

import common.Request;
import server.database.DBWorker;
import server.utility.CollectionManager;

public class RemoveCommand extends Command {

    public RemoveCommand() {
        super("remove");
    }

    @Override
    public Request execute(Request request, DBWorker dbWorker, CollectionManager cm) {
        cm.updateCollection();
        int id;
        String answer;
        try {
            id = Integer.valueOf(request.getReqCommand().getArguments().get(0));
            answer = dbWorker.removeById(id, request.getReqUser().getUsername());
            if (answer.equals("Элемент успешно добавлен в базу данных")) {
                cm.remove(request.getReqVehicle());
            }
        } catch (NumberFormatException e) {
            answer = "Ошибка в аргументах";
        }
        request.setRspAnswer(answer);
        return request;
    }

}
