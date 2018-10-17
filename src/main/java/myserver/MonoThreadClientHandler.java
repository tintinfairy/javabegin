package myserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.EOFException;

public class MonoThreadClientHandler implements Runnable, ServerComands{

    private static Socket clientDialog;

    public MonoThreadClientHandler(Socket client) {
        MonoThreadClientHandler.clientDialog = client;
    }

    public void run() {

        try {

            DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
            DataInputStream in = new DataInputStream(clientDialog.getInputStream());
            System.out.println("DataInputStream created");
            System.out.println("DataOutputStream  created");

            while (!clientDialog.isClosed()) {
                System.out.println("Server reading from channel");

                String entry;
                try {
                    entry = in.readUTF();
                } catch (EOFException e) {
                    break;
                }

                System.out.println("READ from clientDialog message - " + entry);


                switch (entry) {
                    case "quit":
                        System.out.println("Client said QUIT");
                        out.writeUTF("Server reply - " + entry + "-OK");
                        clientDialog.close();
                        break;
                    case "Hello, server":

                        out.writeUTF("Hello, client");
                        break;
                    default:
                        System.out.println("Server try writing to channel");
                        out.writeUTF("Server reply - " + entry + "-OK");
                }



                System.out.println("Server Wrote message to clientDialog.");

                out.flush();

            }


            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");


            in.close();
            out.close();


            clientDialog.close();

            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        } /*catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }
}
