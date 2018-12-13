import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Properties;

public class ClientSocketThread extends Thread{
    private Socket socket;
    public int ID;
    Properties prop = new Properties();
    InputStream in = ClientSocketThread.class.getClassLoader().getResourceAsStream(
            "Ip.properties");
    ClientSocketThread(int id) {
        this.ID=id;
        try {
            prop.load(in);
            socket = new Socket(prop.getProperty("host"), Integer.valueOf(prop.getProperty("port")).intValue());
        }
        catch (IOException e) {
            System.out.println("客户端连接失败");
        }
        start();
    }
    public void run() {
        new ClientReceiveThread(socket,ID);
        new ClientSendThread(ID,socket);
    }
}
