package server.utility;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class SocketWorker {

    private static InetSocketAddress addr = null;

    public InetSocketAddress getAddress(Scanner scanner) {
        if (this.addr == null) {
            System.out.print("Введите порт: ");
            this.addr = new InetSocketAddress(scanner.nextInt());
        }
        return this.addr;
    }

}
