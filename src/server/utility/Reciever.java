package server.utility;

import common.Request;
import common.Serializer;
import server.database.DBWorker;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

public class Reciever {

    private final DBWorker dbWorker;
    private final DatagramChannel dc;
    private final Selector sc;
    private final SocketAddress addr;
    private static final int SELECTION_TIME = 1;
    private final CollectionManager cm;
    private ForkJoinPool forkJoinPool;

    public Reciever(SocketAddress addr, DBWorker dbWorker, CollectionManager cm, ForkJoinPool forkJoinPool) throws IOException {
        this.addr = addr;
        dc = DatagramChannel.open();
        sc = Selector.open();
        dc.socket().bind(addr);
        dc.configureBlocking(false);
        dc.register(sc, SelectionKey.OP_READ);
        this.dbWorker = dbWorker;
        this.cm = cm;
        this.forkJoinPool = forkJoinPool;
    }

    public Request listen() throws IOException {
        if (sc.select(SELECTION_TIME) == 0) {
            return null;
        }
        Set<SelectionKey> readyKeys = sc.selectedKeys();
        Iterator<SelectionKey> iterator = readyKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();
            if (key.isValid()) {
                if (key.isReadable()) {
                    ByteBuffer packet = ByteBuffer.allocate(4096);
                    SocketAddress socketAddress = dc.receive(packet);
                    RequestHandler task = new RequestHandler(packet, socketAddress, dc, dbWorker, forkJoinPool, cm);
                    Thread t = new Thread(task);
                    t.start();
                }
            }
        }
        return null;
    }

    public ForkJoinPool getForkJoinPool() {
        return forkJoinPool;
    }
}
