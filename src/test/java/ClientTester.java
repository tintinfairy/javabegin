import org.junit.Test;

import static org.junit.Assert.*;

import myserver.MultiThreadServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientTester {

    @Test
    public void run() {


        try (Socket socket = new Socket("localhost", 3345);
             DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
             DataInputStream ois = new DataInputStream(socket.getInputStream());){
            new Thread(new MultiThreadServer()).start();


            System.out.println("Client oos & ois initialized");
            oos.writeUTF("Hello, server");
            oos.flush();
            System.out.println("Client wrote & start waiting for data from server...");
            System.out.println("reading...");
            String in = ois.readUTF();

            assertEquals("Server should reply correct hello message", "Hello, client", in);
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
        } /*catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

    }


