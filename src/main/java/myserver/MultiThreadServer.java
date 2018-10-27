package myserver;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MultiThreadServer extends Thread implements Closeable  {

    static ExecutorService executeIt = Executors.newFixedThreadPool(2);
    boolean isClosed = false;
    private ServerSocket server;

    public MultiThreadServer() throws IOException {
        try {

            server = new ServerSocket(3345);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            while (!server.isClosed()) {

                if (br.ready()) {

                    String serverCommand = br.readLine();
                    if (serverCommand.equalsIgnoreCase("quit")) {
                        server.close();
                        break;
                    }
                }


                Socket client = server.accept();

                executeIt.execute(new MonoThreadClientHandler(client));
            }

            executeIt.shutdown();
        } catch (IOException e) {
            if (!isClosed) e.printStackTrace();

        }
    }

    @Override
    public void close() throws IOException {

        server.close();
        isClosed = true;
    }
}
