package server.utility;

import common.Request;
import common.Serializer;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.RecursiveAction;

class Task extends RecursiveAction {

    private SocketAddress socketAddress;
    private Request request;
    private DatagramChannel dc;

    public Task (Request request, SocketAddress socketAddress, DatagramChannel dc) {
        this.request = request;
        this.socketAddress = socketAddress;
        this.dc = dc;
    }

    protected void send() {
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

    @Override
    protected void compute() {

    }
}
