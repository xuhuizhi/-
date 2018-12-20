import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
    public static ServerSendThread serverSendThread;
    ServerSocket serverSocket;
    public static int Threadcount=2;
    ServerThread(ServerSocket s)
    {
        serverSendThread=new ServerSendThread("Server");
        serverSocket=s;
    }

    public void run() {
        try {
            for(int i=0;i<40;i++)
            {
                Socket socket=serverSocket.accept();
                creatThread(socket);
            }
        }catch (IOException e)
        {

        }

    }
    private void creatThread(Socket socket)
    {
        new ServerReceiveThread(socket);
        serverSendThread.newConnect(Threadcount,socket);
        System.out.println("new Connect");
        Threadcount++;
    }
}
