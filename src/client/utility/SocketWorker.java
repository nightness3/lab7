package client.utility;

import common.User;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class SocketWorker {

    private static InetSocketAddress addr = null;

    public InetSocketAddress getAddress(Scanner scanner) {
        if (this.addr == null) {
            Console console = Console.getInstance();
            this.addr = new InetSocketAddress(console.getString(scanner, "host"), console.getInt(scanner, "port"));
        }
        return this.addr;
    }

}
