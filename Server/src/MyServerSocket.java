import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class MyServerSocket{
    public Properties prop = new Properties();
    public static ServerSendThread serverSendThread;
    public static int Threadcount=2;
    InputStream in = MyServerSocket.class.getClassLoader().getResourceAsStream("Ip.properties");
    MyServerSocket(){
        try
        {
            prop.load(in);
            ServerSocket s = new ServerSocket(Integer.valueOf(prop.getProperty("port")).intValue());
            serverSendThread=new ServerSendThread("Server");
            while(true){
                Socket socket = s.accept();
                creatThread(socket);
            }
        }
        catch (IOException e)
        {

        }
    }
    private void creatThread(Socket socket)
    {
        new ServerReceiveThread(Threadcount,socket);
        serverSendThread.newConnect(Threadcount,socket);
        System.out.println("new Connect");
        Threadcount++;
    }
}
