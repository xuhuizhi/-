import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class Receive extends Thread
{
    private BufferedReader in;
    private Socket socket;
    private String name;
    Receive(Socket socket,String name){
        this.socket=socket;
        this.name=name;
        try
        {
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e)
        {
            System.out.println("获得失败");
        }
        start();
    }

    public void run(){
        try {
            String reciverStr=in.readLine();
            while(!reciverStr.equals("END"))
            {
                System.out.println(name+":"+reciverStr);
                reciverStr=in.readLine();
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