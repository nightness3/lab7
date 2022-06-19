package common.commands;

import common.Request;
import server.database.DBWorker;
import server.utility.CollectionManager;

public class HistoryCommand extends Command {

    public HistoryCommand() {
        super("history");
    }

    @Override
    public Request execute(Request request, DBWorker dbWorker, CollectionManager cm) {
        return null;
    }

}
