import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class MyServerSocket{
    public Properties prop = new Properties();
    InputStream in = MyServerSocket.class.getClassLoader().getResourceAsStream(
            "Ip.properties");
    MyServerSocket(){
        try
        {
            prop.load(in);
            ServerSocket s = new ServerSocket(Integer.valueOf(prop.getProperty("port")).intValue());
            while(true){
                Socket socket = s.accept();
                System.out.println("new socket");
                new ServerSocketThread(socket);
            }
        }
        catch (IOException e)
        {

        }
    }
}
