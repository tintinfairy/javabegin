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

    public MonoThreadClientHandler(Socket frontend) throws IOException {
        MonoThreadClientHandler.clientDialog = frontend;

    }

    public void run() {

        try {


            DataOutputStream outFrontend = new DataOutputStream(clientDialog.getOutputStream());
            DataInputStream inFrontend = new DataInputStream(clientDialog.getInputStream());

            JSONParser parser = new JSONParser();
            String cmd = null;

            while (!clientDialog.isClosed()) {
                String entry;
                try {
                    entry = inFrontend.readUTF();

                } catch (EOFException e) {
                    break;
                }


                Object obj = parser.parse(entry);
                JSONObject jsonObject = (JSONObject) obj;
                cmd = (String) jsonObject.get("cmd");


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
                    outFrontend.writeUTF("Server reply - " + entry + "-OK");
                }
                outFrontend.flush();

            }

            inFrontend.close();
            outFrontend.close();


            clientDialog.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
