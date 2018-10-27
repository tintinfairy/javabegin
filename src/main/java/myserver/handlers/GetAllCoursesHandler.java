package myserver.handlers;

import org.json.simple.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;


import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class GetAllCoursesHandler extends ClientMessageHandler {

    public GetAllCoursesHandler(Socket socket, String cmd) throws IOException {
        super(socket, cmd);
    }

    public void run(){
        try {
            JSONParser parser = new JSONParser();
            Socket storage = new Socket("localhost", 3333);
            DataOutputStream outStorage = new DataOutputStream(storage.getOutputStream());
            DataInputStream inStorage = new DataInputStream(storage.getInputStream());

            outStorage.writeUTF("{\"cmd\":\"get_all_courses\",\"body\":{}}");
            outStorage.flush();
            String result = inStorage.readUTF();

            if (result != null) outStorage.writeUTF("{\"cmd\":\"close\",\"body\":{}}");
            outStorage.flush();


            out.writeUTF(result);

            String entry = inStorage.readUTF();
            Object obj = parser.parse(entry);
            JSONObject jsonObject = (JSONObject) obj;
            String cmd = (String) jsonObject.get("cmd");
            if (Objects.equals(cmd, "close")) {
                outStorage.writeUTF("Socket is closed!");
                storage.close();
            }



        } catch(IOException e){
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }


    }


}
