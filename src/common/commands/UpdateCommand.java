package common.commands;

import common.Request;
import server.database.DBWorker;
import server.utility.CollectionManager;

public class UpdateCommand extends Command {

    public UpdateCommand() {
        super("add");
    }

    @Override
    public Request execute(Request request, DBWorker dbWorker, CollectionManager cm) {
        cm.updateCollection();
        int id;
        String answer;
        try {
            id = Integer.valueOf(request.getReqCommand().getArguments().get(0));
            System.out.println(id);
            answer = dbWorker.updateById(request.getReqVehicle(), id, request.getReqUser().getUsername());
            if (answer.equals("Элемент успешно обновлен в базе данных")) {
                cm.remove(request.getReqVehicle());
                request.getReqVehicle().setId((long)id);
                cm.add(request.getReqVehicle());
            }
        } catch (NumberFormatException e) {
            answer = "Ошибка в аргументах";
        }
        request.setRspAnswer(answer);
        return request;
    }

}
