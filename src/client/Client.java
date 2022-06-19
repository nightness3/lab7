package client;

import client.utility.*;
import common.Request;
import common.User;
import common.commands.Command;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        UserWrapper userWrapper = new UserWrapper();
        SocketWorker socketWorker = new SocketWorker();
        Scanner scanner = new Scanner(System.in);
        CommandParser commandParser = new CommandParser();
        VehicleWorker vehicleWorker = new VehicleWorker();
        Sender sender = null;
        try {
            sender = new Sender();
        } catch (IOException e) {
            System.out.println("Невозможно открыть соединение. Завершение программы.");
            System.exit(0);
        }

        InetSocketAddress addr = socketWorker.getAddress(scanner);
        User user = userWrapper.getUser(scanner);
        Request request = new Request();
        String answer;

        String registerFlag = Console.getInstance().getString(scanner, "регистрация - 1");
        request.setReqUser(user);
        if (registerFlag.equals("1")) {
            request.setRspAnswer("register");
        } else {
            request.setRspAnswer("login");
        }
        answer = sender.send(request, addr);
        System.out.println(answer);
        if (!answer.equals("Успешная регистрация/логин")) {
            return;
        }

        while(true) {
            String inputLine = scanner.nextLine().trim();
            ArrayList<String> splittedLine = new ArrayList<>(Arrays.asList(inputLine.split("[\\s]{1,}")));
            if (splittedLine.get(0).equals("execute_script")) {
                splittedLine.remove(0);
                ScriptReader scriptReader = new ScriptReader(sender, commandParser, splittedLine, user, vehicleWorker, addr);
                scriptReader.scriptRead();
                continue;
            }
            request = new Request();
            Command command = commandParser.parseCommand(splittedLine);
            if (command == null) {
                System.out.println("Несуществующая команда");
                continue;
            }
            request.setReqUser(user);
            request.setReqCommand(command);
            request.setReqVehicle(vehicleWorker.getVehicle(command));
            request.setRspAnswer("request");
            answer = sender.send(request, addr);
            System.out.println(answer);
        }
    }

}
