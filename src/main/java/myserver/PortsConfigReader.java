package myserver;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.IOException;

public class PortsConfigReader {

    private final static int defaultPort = 3333;
    public static final PortsConfigReader INSTANCE = new PortsConfigReader();
    private int port;


    private PortsConfigReader(){
        try{
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("ports.json"));
            JSONObject jsonObject = (JSONObject) obj;
            this.port = ((Long)jsonObject.get("backend_port")).intValue();
        } catch (ParseException e){
            e.printStackTrace();
            this.port = defaultPort;
        } catch (IOException e){
            e.printStackTrace();
            this.port = defaultPort;
        }

    }
    public static PortsConfigReader getInstance(){
        return INSTANCE;
    }
    public int getPort() {
        return port;
    }
}