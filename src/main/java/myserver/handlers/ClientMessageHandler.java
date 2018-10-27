package myserver.handlers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class ClientMessageHandler  implements Runnable {

    protected final Socket socket;
    protected DataOutputStream out;
    protected DataInputStream in;
    protected String cmd;

    public ClientMessageHandler(Socket socket, String cmd) throws IOException {
        this.socket = socket;
        this.out = new DataOutputStream(socket.getOutputStream());
        this.in = new DataInputStream(socket.getInputStream());
        this.cmd = cmd;

    }
}
