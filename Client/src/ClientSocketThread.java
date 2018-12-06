import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Properties;

public class ClientSocketThread extends Thread{
    private Socket socket;
    Properties prop = new Properties();
    InputStream in = ClientSocketThread.class.getClassLoader().getResourceAsStream(
            "Ip.properties");
    //private int ID;
    public static int threadCount=0;
    public static int getThreadCount(){
        return threadCount;
    }
    ClientSocketThread() {
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
        new Receive(socket,"Server");
        new Send(socket,"Client");
    }
}
