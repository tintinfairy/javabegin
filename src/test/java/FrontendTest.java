import myserver.Log;
import myserver.PortsConfigReader;
import org.junit.Test;

import static org.junit.Assert.*;

import myserver.MultiThreadServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FrontendTest {
    @Test
    public void run() throws IOException, ParseException {
        MultiThreadServer server = new MultiThreadServer();
        int port = PortsConfigReader.getInstance().getPort();
        Socket socket = new Socket("localhost", port);
        ServerSocket storage = new ServerSocket(3333);
        DataOutputStream outSocket = new DataOutputStream(socket.getOutputStream());
        DataInputStream inSocket = new DataInputStream(socket.getInputStream());

        Log my_logger = new Log("LogFrontend.txt");
        JSONParser parser = new JSONParser();
        String result = "";

        new Thread(server).start();

        /*my_logger.logger.info("Client wants to get all courses");
        String cmd = "{\"cmd\":\"get_all_courses\",\"body\":{}}";
        Object obj = parser.parse(cmd);
        JSONObject jsObject = (JSONObject) obj;
        String c = (String) jsObject.get("cmd");*/

        my_logger.logger.info("Client wants to greet server");
        String cmd = "{\"cmd\":\"greeting\",\"user_id\": 1234,\"body\":{}}";
        Object obj = parser.parse(cmd);
        JSONObject jsObject = (JSONObject) obj;
        String c = (String) jsObject.get("cmd");
        switch (c) {
            case ("get_all_courses"): {
                outSocket.writeUTF(cmd);
                outSocket.flush();
                Socket backend = storage.accept();
                DataOutputStream outBackend = new DataOutputStream(backend.getOutputStream());
                DataInputStream inBackend = new DataInputStream(backend.getInputStream());

                cmd = inBackend.readUTF();
                obj = parser.parse(cmd);
                jsObject = (JSONObject) obj;
                c = (String) jsObject.get("cmd");
                if (Objects.equals(c, "get_all_courses")) {
                    my_logger.logger.info("Storage gives all courses list to server");
                    outBackend.writeUTF("{\"cmd\":\"get_all_courses\",\"user_id\":321123,\"body\":{\"page\":0,\"courses\":[{\"title\":\"course1\",\"description\":\"description1\"},{\"title\":\"course2\",\"description\":\"description2\"}]}}");
                    outBackend.flush();
                }

                String inn = inBackend.readUTF();
                obj = parser.parse(inn);
                jsObject = (JSONObject) obj;
                cmd = (String) jsObject.get("cmd");
                if (Objects.equals(cmd, "close")) {
                    outBackend.writeUTF("{\"cmd\":\"close\",\"body\":{}}");
                    backend.close();
                    storage.close();
                    my_logger.logger.info("Storage is closed");
                }

                String in = inSocket.readUTF();


                Object object = parser.parse(in);
                JSONObject jsonObject = (JSONObject) object;
                JSONObject body = (JSONObject) jsonObject.get("body");
                JSONArray courses = (JSONArray) body.get("courses");

                for (Object courseObj : courses.toArray()) {
                    my_logger.logger.info("Frontend parses courses");
                    JSONObject course = (JSONObject) courseObj;
                    String title = (String) course.get("title");

                    String description = (String) course.get("description");
                    result += title + " " + description + " ";
                }


                my_logger.logger.info("Frontend asks server to close");
                outSocket.writeUTF("{\"cmd\":\"close\",\"body\":{}}");
                outSocket.flush();
                in = inSocket.readUTF();
                obj = parser.parse(in);
                jsObject = (JSONObject) obj;
                cmd = (String) jsObject.get("cmd");

                if (Objects.equals(cmd, "close")) {

                    socket.close();
                    my_logger.logger.info("Frontend is closed");
                }
                assertEquals("Server should reply correct courses list", "course1 description1 course2 description2 ", result);

            }
            case ("greeting"): {
                outSocket.writeUTF(cmd);
                outSocket.flush();
                result = inSocket.readUTF();
                if (Objects.equals(result, "Hello!")) {

                    my_logger.logger.info("Server got the greeting");
                }
                my_logger.logger.info("Frontend asks server to close");
                outSocket.writeUTF("{\"cmd\":\"close\",\"body\":{}}");
                outSocket.flush();
                String in = inSocket.readUTF();
                obj = parser.parse(in);
                jsObject = (JSONObject) obj;
                cmd = (String) jsObject.get("cmd");

                if (Objects.equals(cmd, "close")) {

                    socket.close();
                    my_logger.logger.info("Frontend is closed");
                }
                assertEquals("Server should reply correct greeting message", "Hello!", result);


            }
        }


    }
}