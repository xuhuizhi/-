import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.util.Properties;

public class MyServerSocket{
    public Properties prop = new Properties();
    public static ServerThread serverThread;
    InputStream in = MyServerSocket.class.getClassLoader().getResourceAsStream("Ip.properties");
    MyServerSocket(){
        try
        {
            prop.load(in);
            ServerSocket s = new ServerSocket(Integer.valueOf(prop.getProperty("port")).intValue());
            serverThread=new ServerThread(s);
            serverThread.start();
        }
        catch (IOException e)
        {

        }
    }

}
