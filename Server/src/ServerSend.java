import java.io.*;
import java.net.Socket;

public class ServerSend extends Thread {
    private static PrintWriter out;
    BufferedReader sin;
    private String name;
    ServerSend(String name,Socket socket) throws IOException
    {
        this.name=name;
        sin = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        start();
    }
    private void SendStr(String Str)
    {
        out.println(Str);
        out.flush();
        System.out.println(name+":"+Str);
    }

    public void run() {
        try {
            String sendStr= sin.readLine();

        }
        catch (IOException e)
        {
            System.out.println("客户端系统读入失败");
        }
    }
}