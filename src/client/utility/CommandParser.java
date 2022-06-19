package client.utility;

import common.commands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandParser {

    private final Map<String, Command> commands;

    public CommandParser () {
        commands = new HashMap<>();
        this.initializeMap();
    }

    public Command parseCommand (ArrayList<String> splittedLine) {
        Command command = commands.get(splittedLine.get(0));
        if (splittedLine.size() > 1) {
            splittedLine.remove(0);
            command.setArguments(splittedLine);
        }
        return command;
    }

    private void initializeMap() {
        commands.put("add", new AddCommand());
        commands.put("add_if_max", new AddIfMaxCommand());
        commands.put("clear", new ClearCommand());
        commands.put("filter_by_type", new FilterByTypeCommand());
        commands.put("help", new HelpCommand());
        commands.put("history", new HistoryCommand());
        commands.put("info", new InfoCommand());
        commands.put("max_by_number_of_wheels", new MaxByNumberOfWheelsCommand());
        commands.put("print_descending", new PrintDescendingCommand());
        commands.put("remove_by_id", new RemoveCommand());
        commands.put("remove_lower", new RemoveLowerCommand());
        commands.put("show", new ShowCommand());
        commands.put("update", new UpdateCommand());
    }

}
