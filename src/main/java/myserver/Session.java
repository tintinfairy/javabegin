package myserver;
import java.net.Socket;


public class Session {
    private final Socket socket;
    private Long user_id;
    private Long client_id;

    public Session(Socket socket, Long client_id) {
        this.socket = socket;
        this.client_id = client_id;
        this.user_id = 0L;
    }
}

