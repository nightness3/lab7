package common.commands;

import common.Request;
import server.database.DBWorker;
import server.utility.CollectionManager;

public class ClearCommand extends Command {

    public ClearCommand() {
        super("clear");
    }

    @Override
    public Request execute(Request request, DBWorker dbWorker, CollectionManager cm) {
        cm.updateCollection();
        String answer = dbWorker.clear(request.getReqUser().getUsername());
        cm.clear(request.getReqUser().getUsername());
        request.setRspAnswer(answer);
        return request;
    }

}
