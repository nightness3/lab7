package server.utility;

import common.Request;
import common.Serializer;
import server.database.DBWorker;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.ForkJoinPool;

public class RequestHandler implements Runnable {

    private ByteBuffer dp;
    private SocketAddress sa;
    private DatagramChannel dc;
    private DBWorker dw;
    private ForkJoinPool fjp;
    private CollectionManager cm;

    public RequestHandler(ByteBuffer dp, SocketAddress sa, DatagramChannel dc, DBWorker dw, ForkJoinPool fjp, CollectionManager cm) {
        this.dp = dp;
        this.sa = sa;
        this.dc = dc;
        this.dw = dw;
        this.fjp = fjp;
        this.cm = cm;
    }

    @Override
    public void run() {
        Request request = null;
        try {
            request = Serializer.deserializeRequest(dp.array());
        } catch (IOException e) {
            System.out.println("Ошибка при получении данных от клиента");
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка при десериализации объекта");
            return;
        }
        if (!request.getRspAnswer().equals("request")) {
            boolean answer;
            if (request.getRspAnswer().equals("register")) {
                answer = dw.addUser(request.getReqUser());
            } else if (request.getRspAnswer().equals("login")) {
                answer = dw.loginUser(request.getReqUser());
            } else {
                answer = false;
            }
            String setString = answer ? "Успешная регистрация/логин" : "Ошибка при регистрации/логине";
            request.setRspAnswer(setString);
            SendTask sendTask = new SendTask(request, sa, dc);
            fjp.invoke(sendTask);
        } else {
            ExecuteTask executeTask = new ExecuteTask(request, sa, dc, dw, cm);
            fjp.invoke(executeTask);
        }
    }

    private void executeCommand (Request request, SocketAddress socketAddress, CollectionManager cm, ForkJoinPool fjp) {
        request = request.getReqCommand().execute(request, dw, cm);
        sendRequest(request, socketAddress, fjp);
    }

    private void sendRequest (Request request, SocketAddress socketAddress, ForkJoinPool fjp) {
        Task task = new Task(request, socketAddress, dc);
        fjp.invoke(task);
    }

}
