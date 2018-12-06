import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Mult extends Thread
{
    private Socket socket;
    Mult(Socket socket) throws IOException
    {
        this.socket=socket;
        start();
    }
    public void run() {
        new Server_Send(socket);
        new Server_Receive(socket);
    }
}


class Server_Send extends Thread
{
    private Socket socket;
    PrintWriter out;
    BufferedReader sin;
    Server_Send(Socket socket){
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
            String sendstr=sin.readLine();
            while(!sendstr.equals("END"))
            {
                out.println(sendstr);
                out.flush();
                System.out.println("Server :"+sendstr);
                sendstr=sin.readLine();
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



class Server_Receive extends Thread
{
    private Socket socket;
    BufferedReader in;
    Server_Receive(Socket socket)
    {
        this.socket=socket;
        try {
            in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            start();
        }
        catch (IOException e)
        {
            System.out.println("Error:"+e);
        }
    }
    public void run() {
        try{
            String receivestr=in.readLine();
            while(!receivestr.equals("END"))
            {
                System.out.println("Client:"+receivestr);
                receivestr = in.readLine();
            }
        }
        catch (IOException e){
            System.out.println("发送失败");
        }
        try{
            in.close();
            socket.close();
        }
        catch (IOException e)
        {

        }
    }
}



public class Server_Socket{
    public static final int PORT = 8080;
    public static void main(String[] args)throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Started: "+s);
        while(true){
            Socket socket = s.accept();
            System.out.println("new socket");
            try
            {
                new Mult(socket);
            }
            catch (IOException e)
            {
                socket.close();
            }
        }
    }
}
