package client.utility;

import common.Request;
import common.User;
import common.commands.Command;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;

public class ScriptReader {

    private Sender sender;
    private CommandParser cp;
    private ArrayList<String> line;
    private User user;
    private VehicleWorker vehicleWorker;
    private InetSocketAddress addr;

    public ScriptReader (Sender sender, CommandParser cp, ArrayList<String> line, User user, VehicleWorker vehicleWorker, InetSocketAddress addr) {
        this.sender = sender;
        this.cp = cp;
        this.line = line;
        this.user = user;
        this.vehicleWorker = vehicleWorker;
        this.addr = addr;
    }

    public void scriptRead () {
        if (line.size() < 1) {
            System.out.println("Нет аргументов для чтения скрипта");
            return;
        }
        File file = new File(line.get(0));
        if (!file.canRead()) {
            System.out.println("Файл невозможно прочитать");
            return;
        }
        String nextLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            do {
                nextLine = bufferedReader.readLine().trim();
                if (nextLine == null) return;

                ArrayList<String> splittedLine = new ArrayList<>(Arrays.asList(nextLine.split("[\\s]{1,}")));
                if (splittedLine.get(0).equals("execute_script")) {
                    splittedLine.remove(0);
                    ScriptReader scriptReader = new ScriptReader(sender, cp, splittedLine, user, vehicleWorker, addr);
                    scriptReader.scriptRead();
                    continue;
                }
                Request request = new Request();
                Command command = cp.parseCommand(splittedLine);
                if (command == null) {
                    System.out.println("Несуществующая команда");
                    continue;
                }
                request.setReqUser(user);
                request.setReqCommand(command);
                request.setReqVehicle(vehicleWorker.getVehicle(command));
                request.setRspAnswer("request");
                nextLine = sender.send(request, addr);
                System.out.println(nextLine);
            } while (true);
        } catch (FileNotFoundException e) {
            System.out.println("Файл со скриптом не найден");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла со скриптом");
        }
    }

}
