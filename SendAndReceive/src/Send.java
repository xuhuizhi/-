import java.io.*;
import java.net.Socket;

public class Send extends Thread {
    private Socket socket;
    PrintWriter out;
    BufferedReader sin;
    private String name;
    Send(Socket socket,String name)
    {
        this.name=name;
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
            String sendStr= sin.readLine();
            while(!sendStr.equals("END")) {
                out.println(sendStr);
                out.flush();
                System.out.println(name+":"+sendStr);
                sendStr = sin.readLine();
            }
            out.println("END");
            out.close();
            socket.close();
        }
        catch (IOException e)
        {
            System.out.println("客户端系统读入失败");
        }
    }
}
