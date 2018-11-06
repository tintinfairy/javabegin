package myserver.handlers;

import java.io.IOException;
import java.net.Socket;



public class GreetingHandler extends ClientMessageHandler{

    public GreetingHandler(Socket socket, String cmd) throws IOException {
        super(socket, cmd);
    }

    public void run() {

        try {

            out.writeUTF("Hello!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
