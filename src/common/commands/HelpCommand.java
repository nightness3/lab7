package common.commands;

import common.Request;
import server.database.DBWorker;
import server.utility.CollectionManager;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");
    }

    @Override
    public Request execute(Request request, DBWorker dbWorker, CollectionManager cm) {
        String answer = "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "history : вывести последние 10 команд (без их аргументов)\n" +
                "max_by_number_of_wheels : вывести любой объект из коллекции, значение поля numberOfWheels которого является максимальным\n" +
                "filter_by_type type : вывести элементы, значение поля type которых равно заданному\n" +
                "print_field_descending_fuel_type : вывести значения поля fuelType всех элементов в порядке убывания";
        request.setRspAnswer(answer);
        return request;
    }

}
