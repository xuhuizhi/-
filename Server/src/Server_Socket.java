import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Mult extends Thread
{
    private Socket socket;
    BufferedReader in;
    PrintWriter out;
    BufferedReader sin;

    Mult(Socket socket) throws IOException
    {
        this.socket=socket;
        in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out= new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
        sin= new BufferedReader(new InputStreamReader(System.in));
        start();
    }
    public void run() {
        try{
            String s=sin.readLine();
            while(!s.equals("END"))
            {
                out.println(s);
                out.flush();
                System.out.println("Clinet: " + in.readLine());
                System.out.println("Server: "+s);
                s=sin.readLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("Error: " + e);
        }
        try {
            socket.close();
            out.close();
            in.close();
            sin.close();
        }
        catch (IOException e)
        {
            System.out.println("Error: "+e);
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
