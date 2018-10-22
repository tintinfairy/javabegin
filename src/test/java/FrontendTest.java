import org.junit.Test;

import static org.junit.Assert.*;

import myserver.MultiThreadServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FrontendTest {
    @Test
    public void run() {
        try (MultiThreadServer server = new MultiThreadServer();
             Socket socket = new Socket("localhost", 3345);
             DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
             DataInputStream ois = new DataInputStream(socket.getInputStream());) {
            JSONParser parser = new JSONParser();
            String result = "";

            new Thread(server).start();

            oos.writeUTF("{\"cmd\":\"get_all_courses\",\"body\":{}}");
            oos.flush();
            String in = ois.readUTF();

            Object obj = parser.parse(in);
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject body = (JSONObject) jsonObject.get("body");
            JSONArray courses = (JSONArray) body.get("courses");

            for (Object courseObj : courses.toArray()) {
                        JSONObject course = (JSONObject) courseObj;
                        String title = (String) course.get("title");

                        String description = (String) course.get("description");
                        result+=title + " " + description + " ";
                    }


            assertEquals("Server should reply correct courses list", "course1 description1 course2 description2 ", result);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
