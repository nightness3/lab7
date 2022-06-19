package server;

import server.database.DBConnector;
import server.database.DBInitializer;
import server.database.DBWorker;
import server.utility.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Server {

    public static void main(String[] args) {

        SocketWorker socketWorker = new SocketWorker();
        Scanner scanner = new Scanner(System.in);
        InetSocketAddress addr = socketWorker.getAddress(scanner);
        Reciever reciever = null;
        DBConnector dbConnector = new DBConnector("jdbc:postgresql://pg:5432/studs", "s335088", "yfs612");
        Connection connection = dbConnector.getConnection();
        DBInitializer dbInitializer = new DBInitializer(connection);
        try {
            dbInitializer.initialize();
        } catch (SQLException e) {
            System.out.println("Ошибка при инициализации базы данных. Завершение программы.");
            return;
        }
        DBWorker dbWorker = null;
        try {
            dbWorker = new DBWorker(connection, "MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Ошибка при получении алгоритма шифрования. Завершение программы.");
            return;
        }

        CollectionManager cm = new CollectionManager(dbWorker);
        cm.updateCollection();

        Executor requestListener = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() / 3);
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors() / 3);

        try {
            reciever = new Reciever(addr, dbWorker, cm, forkJoinPool);
        } catch (IOException e) {
            System.out.println("Невозможно открыть соединение. Завершение программы.");
            return;
        }

        RecieverLoop recieverLoop = new RecieverLoop(reciever);
        requestListener.execute(recieverLoop);

        Scanner sc = new Scanner(System.in);
        while(ServerConfig.isIsRunning()) {
            String scan = sc.nextLine();
            if (scan.equals("exit")) {
                System.out.println("Выключаем сервер");
                return;
            } else if (scan.equals("clear")) {
                dbWorker.clearAll();
                cm.clearAll();
            }
        }

    }

}
