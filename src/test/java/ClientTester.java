import org.junit.Test;
import static org.junit.Assert.*;
import myserver.MultiThreadServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientTester {

    static Socket socket;

    public ClientTester() {
        try {
            ( new Thread(new MultiThreadServer())).start();
            socket = new Socket("localhost", 3345);
            System.out.println("Client connected to socket");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void run() {

        try (

                DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                DataInputStream ois = new DataInputStream(socket.getInputStream())) {
            System.out.println("Client oos & ois initialized");
            oos.writeUTF("Hello, server");
            oos.flush();
            System.out.println("Client wrote & start waiting for data from server...");
            System.out.println("reading...");
            String in = ois.readUTF();
            ////
            assertEquals("Server should reply correct hello message","Hello, client", in);
            /////
            System.out.println(in);

            oos.writeUTF("quit");
            oos.flush();
            System.out.println("Client wrote & start waiting for data from server...");
            System.out.println("reading...");
            String inn = ois.readUTF();
            System.out.println(inn);


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
