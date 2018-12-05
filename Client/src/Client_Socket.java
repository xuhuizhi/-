import java.io.*;
import java.net.Socket;

class ClientSocketMultThread extends Thread
{
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader sin;
    private Socket socket;
    private static int threadcount=0;
    public static int threadCount(){
        return threadcount;
    }
    ClientSocketMultThread(){
        threadcount++;
        try{
            socket = new Socket("127.0.0.1",8080);
        }
        catch (IOException e)
        {
            System.out.println("客户端连接失败");
        }
        try
        {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            sin =new BufferedReader(new InputStreamReader(System.in));
            start();
        }
        catch (IOException e)
        {
            System.out.println("获得输入输出流失败");
            try {
                socket.close();
            }
            catch (IOException e1)
            {

            }
        }
    }

    public void run() {
        try {
            String s = sin.readLine();
            while(true) {
                if(s.equals("END")) {
                    break;
                }
                out.println(s);
                out.flush();
                System.out.println("Clint:"+s);
                System.out.println("Server: "+in.readLine());
                s = sin.readLine();
            }
            out.println("END");
            in.close();
            out.close();
            socket.close();
            threadcount--;
        }
        catch (IOException e)
        {
            System.out.println("客户端系统读入失败");
        }
    }
}

public class Client_Socket {
    static final int MAX_THREADS=40;
    public void make_connect(){
        if(ClientSocketMultThread.threadCount()<MAX_THREADS)
        {
            new ClientSocketMultThread();
        }
        else
        {
            System.out.println("当前接入客户端太多");
        }
    }
}
