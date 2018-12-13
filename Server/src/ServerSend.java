import java.io.*;
import java.net.Socket;

class ServerSend extends Thread
{
    private Socket socket;
    PrintWriter out;
    BufferedReader sin;
    ServerSend(Socket socket){
        this.socket=socket;
        try {
            out= new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            sin= new BufferedReader(new InputStreamReader(System.in));
            start();
        }
        catch (IOException e)
        {
            System.out.println("Error:"+e);
        }
    }
    public void run()
    {
        try {
            String sendStr=sin.readLine();
            while(!sendStr.equals("END"))
            {
                out.println(sendStr);
                out.flush();
                System.out.println("Server :"+sendStr);
                sendStr=sin.readLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("发送失败");
        }
        try
        {
            out.close();
            sin.close();
            socket.close();
        }
        catch (IOException e)
        {

        }

    }
}