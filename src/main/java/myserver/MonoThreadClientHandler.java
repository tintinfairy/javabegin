package myserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


import myserver.handlers.CloseHandler;
import myserver.handlers.GetAllCoursesHandler;
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

            Log my_logger = new Log("LogServer.txt");
            DataOutputStream outFrontend = new DataOutputStream(clientDialog.getOutputStream());
            DataInputStream inFrontend = new DataInputStream(clientDialog.getInputStream());

            JSONParser parser = new JSONParser();
            String cmd = null;

            while (!clientDialog.isClosed()) {
                String entry;
                entry = inFrontend.readUTF();
                my_logger.logger.info("Server is listening Frontend");
                Object obj = parser.parse(entry);
                JSONObject jsonObject = (JSONObject) obj;
                cmd = (String) jsonObject.get("cmd");


                switch (cmd) {
                    case ("get_all_courses"): {

                        my_logger.logger.info("Server was asked to get all courses");
                        GetAllCoursesHandler handlerGet =new GetAllCoursesHandler(clientDialog, entry);
                        handlerGet.run();
                        break;
                    }
                    case ("close"): {
                        CloseHandler closeHandler = new CloseHandler(clientDialog,entry);
                        closeHandler.run();
                        my_logger.logger.info("Server is closed");
                        break;

                    }

                    default: {
                        outFrontend.writeUTF("Server reply - " + entry + "-OK");
                        outFrontend.flush();
                    }

                }
            }

            inFrontend.close();
            outFrontend.close();


        } catch(IOException e){
            e.printStackTrace();
        } catch(ParseException e){
            e.printStackTrace();
        }
    }

}