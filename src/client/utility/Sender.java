package client.utility;

import common.Request;
import common.Serializer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Sender {

    DatagramChannel dc;

    public Sender () throws IOException {
        dc = DatagramChannel.open();
    }

    public String send(Request request, InetSocketAddress addr) {
        ByteBuffer buf;
        try {
            buf = Serializer.serializeRequest(request);
        } catch (IOException e) {
            return "Ошибка при сериализации Request";
        }
        try {
            dc.send(buf, addr);
        } catch (IOException e) {
            return "Ошибка при отправке запроса на сервер";
        }
        return recieve(dc, buf);
    }

    public String recieve (DatagramChannel dc, ByteBuffer buf) {
        Request request = null;
        buf.clear();
        buf = ByteBuffer.allocate(4096);
        try {
            dc.receive(buf);
        } catch (IOException e) {
            return "Ошибка при получении ответа от сервера";
        }
        try {
            request = (Request) Serializer.deserializeRequest(buf.array());
        } catch (IOException e) {
            return "Ошибка при десереализации объекта";
        } catch (ClassNotFoundException e) {
            return "Не существующий класс для десереализации";
        }
        return request.getRspAnswer();
    }

}
