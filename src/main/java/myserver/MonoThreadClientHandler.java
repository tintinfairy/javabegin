package myserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.EOFException;

public class MonoThreadClientHandler implements Runnable {

    private static Socket clientDialog;

    Log my_logger = new Log("LogHandler.txt");
    public MonoThreadClientHandler(Socket client) throws IOException {
        MonoThreadClientHandler.clientDialog = client;

    }

    public void run() {

        try {

            DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
            DataInputStream in = new DataInputStream(clientDialog.getInputStream());
            my_logger.logger.info("DataInputStream created");
            my_logger.logger.info("DataOutputStream  created");

            while (!clientDialog.isClosed()) {
                my_logger.logger.info("Server reading from channel");

                String entry;
                try {
                    entry = in.readUTF();
                } catch (EOFException e) {
                    break;
                }

                my_logger.logger.info("READ from clientDialog message - " + entry);


                switch (entry) {
                    case "quit":
                        my_logger.logger.info("Client said QUIT");
                        out.writeUTF("Server reply - " + entry + "-OK");
                        clientDialog.close();
                        break;
                    case "Hello, server":

                        out.writeUTF("Hello, client");
                        break;
                    default:
                        my_logger.logger.info("Server try writing to channel");
                        out.writeUTF("Server reply - " + entry + "-OK");
                }



                my_logger.logger.info("Server Wrote message to clientDialog.");

                out.flush();

            }


            my_logger.logger.info("Client disconnected");
            my_logger.logger.info("Closing connections & channels.");


            in.close();
            out.close();


            clientDialog.close();

            my_logger.logger.info("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
