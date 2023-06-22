import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PortScanner {

    public int getTHREADS_NUM() {
        return THREADS_NUM;
    }

    private int THREADS_NUM = 8;
    public Map<Integer, Boolean> scanPorts( int minPort, int maxPort, String ip) {
        Map<Integer, Boolean> portStatus = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(THREADS_NUM);

        for (int port = minPort; port <= maxPort; port++) {
            int finalPort = port;
            executor.execute(() -> {
                try {
                    Socket socket = new Socket(ip, finalPort);
                    socket.close();
                    portStatus.put(finalPort, true);
                } catch (IOException e) {
                    portStatus.put(finalPort, false);
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new TreeMap<>(portStatus);
    }

}
