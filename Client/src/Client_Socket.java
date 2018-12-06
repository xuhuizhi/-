import java.io.*;
import java.net.Socket;

class ClientSocketThread extends Thread
{
    private Socket socket;
    private int ID;
    public static int threadcount=0;
    public static int threadCount(){
        return threadcount;
    }
    ClientSocketThread() {
        ID=threadcount++;
        try {
            socket = new Socket("127.0.0.1", 8080);
        } catch (IOException e) {
            System.out.println("客户端连接失败");
        }
        start();
    }
    public void run() {
        new Client_Recive(socket);
        new Client_Send(socket);
    }
}

class Client_Send extends Thread{
    private Socket socket;
    PrintWriter out;
    BufferedReader sin;
    Client_Send(Socket socket)
    {
        this.socket=socket;
        try{
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())),true);
        }
        catch (IOException e)
        {
            System.out.println("获得输入流失败");
        }
        sin =new BufferedReader(new InputStreamReader(System.in));
        start();
    }

    public void run() {
        try {
            String Sendstr= sin.readLine();
            while(!Sendstr.equals("END")) {
                out.println(Sendstr);
                out.flush();
                System.out.println("Clint:"+Sendstr);
                Sendstr = sin.readLine();
            }
            out.println("END");
            out.close();
            socket.close();
            ClientSocketThread.threadcount--;
        }
        catch (IOException e)
        {
            System.out.println("客户端系统读入失败");
        }
    }
}

class Client_Recive extends Thread
{
    private BufferedReader in;
    private Socket socket;
    Client_Recive(Socket socket){
        this.socket=socket;
        try
        {
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e)
        {
            System.out.println("in获得失败");
        }
        start();
    }

    public void run() {
        try {
            String reciverstr=in.readLine();
            while(!reciverstr.equals("END"))
            {
                System.out.println("Server:"+reciverstr);
                reciverstr=in.readLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("Error" + e);
        }
        try {
            in.close();
            socket.close();
        }
        catch (IOException e)
        {

        }
    }
}

public class Client_Socket {
    static final int MAX_THREADS=40;
    public void make_connect(){
        if(ClientSocketThread.threadCount()<MAX_THREADS)
        {
            new ClientSocketThread();
        }
        else
        {
            System.out.println("当前接入客户端太多");
        }
    }
}
