package myserver.handlers;

import org.json.simple.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CloseHandler extends ClientMessageHandler{


    public CloseHandler(Socket socket, String cmd) throws IOException {
        super(socket, cmd);
    }

    public void run(){

        try {

            out.writeUTF("{\"cmd\":\"close\",\"body\":{}}");
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}