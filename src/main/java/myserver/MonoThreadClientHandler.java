package myserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.EOFException;


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


                switch (cmd) {
                    case ("get_all_courses"): {

                        GetAllCoursesHandler handlerGet =new GetAllCoursesHandler(clientDialog, entry);
                        handlerGet.run();
                        break;
                    }
                    case ("close"): {

                        CloseHandler closeHandler = new CloseHandler(clientDialog,entry);
                        closeHandler.run();
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
