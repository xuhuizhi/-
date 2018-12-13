import java.io.IOException;
import java.net.Socket;

public class ServerSocketThread extends Thread {
    private Socket socket;
    ServerSocketThread(Socket socket) throws IOException
    {
        this.socket=socket;
        start();
    }
    public void run() {
        new (socket);
        new Receive(socket);
    }
}
