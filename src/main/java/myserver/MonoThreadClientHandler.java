package myserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.EOFException;
import java.util.Objects;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MonoThreadClientHandler implements Runnable {

    private static Socket clientDialog;

    Log my_logger = new Log("LogHandler.txt");

    public MonoThreadClientHandler(Socket frontend) throws IOException {
        MonoThreadClientHandler.clientDialog = frontend;

    }

    public void run() {

        try {


            DataOutputStream outFrontend = new DataOutputStream(clientDialog.getOutputStream());
            DataInputStream inFrontend = new DataInputStream(clientDialog.getInputStream());

            my_logger.logger.info("DataInputStream created");
            my_logger.logger.info("DataOutputStream  created");
            JSONParser parser = new JSONParser();
            String cmd = null;

            while (!clientDialog.isClosed()) {
                my_logger.logger.info("Server reading from channel");


                String entry;
                try {
                    entry = inFrontend.readUTF();

                } catch (EOFException e) {
                    break;
                }


                Object obj = parser.parse(entry);
                JSONObject jsonObject = (JSONObject) obj;
                cmd = (String) jsonObject.get("cmd");


                my_logger.logger.info("READ from clientDialog message - " + entry);


                if (Objects.equals(cmd, "get_all_courses")) {

                    Socket storage = new Socket("localhost", 3333);
                    DataOutputStream outStorage = new DataOutputStream(storage.getOutputStream());
                    DataInputStream inStorage = new DataInputStream(storage.getInputStream());

                    outStorage.writeUTF("{\"cmd\":\"get_all_courses\",\"body\":{}}");
                    outStorage.flush();
                    String result = inStorage.readUTF();

                    if (result != null) outStorage.writeUTF("{\"cmd\":\"close\",\"body\":{}}");
                    outStorage.flush();


                    outFrontend.writeUTF(result);

                    entry = inStorage.readUTF();
                    obj = parser.parse(entry);
                    jsonObject = (JSONObject) obj;
                    cmd = (String) jsonObject.get("cmd");
                    if (Objects.equals(cmd, "close")) {
                        outStorage.writeUTF("Socket is closed!");
                        storage.close();
                    }


                    entry = inFrontend.readUTF();
                    obj = parser.parse(entry);
                    jsonObject = (JSONObject) obj;
                    cmd = (String) jsonObject.get("cmd");
                    if (Objects.equals(cmd, "close")) {
                        outFrontend.writeUTF("{\"cmd\":\"close\",\"body\":{}}");
                        clientDialog.close();
                    }


                } else {

                    my_logger.logger.info("Server try writing to channel");
                    outFrontend.writeUTF("Server reply - " + entry + "-OK");
                }


                my_logger.logger.info("Server Wrote message to clientDialog.");

                outFrontend.flush();

            }


            my_logger.logger.info("Client disconnected");
            my_logger.logger.info("Closing connections & channels.");


            inFrontend.close();
            outFrontend.close();


            clientDialog.close();

            my_logger.logger.info("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
