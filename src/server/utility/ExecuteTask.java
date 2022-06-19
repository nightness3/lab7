package server.utility;

import common.Request;
import common.Serializer;
import server.database.DBWorker;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.RecursiveAction;

public class ExecuteTask extends RecursiveAction {

    private SocketAddress socketAddress;
    private CollectionManager cm;
    private DBWorker dw;
    private Request request;
    private DatagramChannel dc;

    public ExecuteTask (Request request, SocketAddress socketAddress, DatagramChannel dc, DBWorker dw, CollectionManager cm) {
        this.request = request;
        this.socketAddress = socketAddress;
        this.dc = dc;
        this.dw = dw;
        this.cm = cm;
    }

    @Override
    protected void compute() {
        executeCommand(request, socketAddress, cm);
    }

    private void executeCommand (Request request, SocketAddress socketAddress, CollectionManager cm) {
        this.request = request.getReqCommand().execute(request, dw, cm);
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
