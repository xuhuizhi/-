import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerReceive extends Thread {
    private Socket socket;
    BufferedReader in;
    ServerReceive(Socket socket)
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
            String receiveStr=in.readLine();
            while(!receiveStr.equals("END"))
            {
                System.out.println("Client:"+receiveStr);
                receiveStr = in.readLine();
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
