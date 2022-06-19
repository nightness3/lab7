package server.utility;

import java.io.IOException;

public class RecieverLoop implements Runnable {

    private Reciever reciever;

    public RecieverLoop (Reciever reciever) {
        this.reciever = reciever;
    }

    @Override
    public void run() {
        while(ServerConfig.isIsRunning()) {
            try {
                reciever.listen();
            } catch (IOException e) {
                System.out.println("Ошибка при получении данных от клиента");
            }
        }
        reciever.getForkJoinPool().shutdown();
    }
}
