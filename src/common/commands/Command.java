package common.commands;

import common.Request;
import common.Serializer;
import server.database.DBWorker;
import server.utility.CollectionManager;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Command implements Serializable {

    private String name;
    private ArrayList<String> arguments;

    public Command (String name) {
        this.name = name;
    }

    abstract public Request execute (Request request, DBWorker dbWorker, CollectionManager cm);

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public void setArguments(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    public String getName() {
        return name;
    }
}
