package server.utility;

import common.Request;
import common.Serializer;
import server.database.DBWorker;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.RecursiveAction;

public class SendTask extends RecursiveAction {

    private SocketAddress socketAddress;
    private Request request;
    private DatagramChannel dc;

    public SendTask (Request request, SocketAddress socketAddress, DatagramChannel dc) {
        this.request = request;
        this.socketAddress = socketAddress;
        this.dc = dc;
    }

    @Override
    protected void compute() {
        send();
    }

    private void send() {
        ByteBuffer buf = null;
        try {
            buf = Serializer.serializeRequest(request);
        } catch (IOException e) {
            System.out.println("Ошибка при сериализации ответа");
        }
        try {
            dc.send(buf, socketAddress);
        } catch (IOException e) {
            System.out.println("Ошибка при отправке сообщения на клиент");
        }
    }

}
