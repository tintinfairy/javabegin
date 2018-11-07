package myserver.handlers;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;


import myserver.Session;
import myserver.SessionCollector;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class GreetingHandler extends ClientMessageHandler{

    public GreetingHandler(Socket socket, String cmd) throws IOException {
        super(socket, cmd);
    }

    public void run() {

        try {
            JSONParser parser = new JSONParser();

            String entry = in.readUTF();
            Object obj = parser.parse(entry);
            JSONObject jsonObject = (JSONObject) obj;
            Long client_id = (Long) jsonObject.get("client_id");

            SessionCollector.getInstance().setHashMap(client_id, new Session(socket,client_id));
            Map<Long, Session> hashmap = SessionCollector.getInstance().getHashMap();

            out.writeUTF("Hello!");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
