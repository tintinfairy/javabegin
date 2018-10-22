package myserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.EOFException;
import java.util.Objects;

import org.json.simple.JSONArray;
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

            DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
            DataInputStream in = new DataInputStream(clientDialog.getInputStream());
            my_logger.logger.info("DataInputStream created");
            my_logger.logger.info("DataOutputStream  created");
            JSONParser parser = new JSONParser();
            String cmd = null;

            while (!clientDialog.isClosed()) {
                my_logger.logger.info("Server reading from channel");


                String entry;
                try {
                    entry = in.readUTF();

                } catch (EOFException e) {
                    break;
                }


                Object obj = parser.parse(entry);
                JSONObject jsonObject = (JSONObject) obj;
                cmd = (String) jsonObject.get("cmd");


                my_logger.logger.info("READ from clientDialog message - " + entry);


                if (Objects.equals(cmd, "get_all_courses")) {

                    String result = "{\"cmd\":\"get_all_courses\",\"user_id\":321123,\"body\":{\"page\":0,\"courses\":[{\"title\":\"course1\",\"description\":\"description1\"},{\"title\":\"course2\",\"description\":\"description2\"}]}}";
                    out.writeUTF(result);

                } else {

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
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
