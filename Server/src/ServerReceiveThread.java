import java.io.*;
import java.net.Socket;

class ServerReceiveThread extends Thread
{
    private DataInputStream in;
    private Socket socket;
    Message[] messages=new Message[100000];
    ServerReceiveThread(Socket socket){
        messages[1]=new NormalMessage();
        messages[2]=new BroadcastMessage();
        this.socket=socket;
        try
        {
            in=new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));
        }
        catch (IOException e)
        {
            System.out.println("获得失败");
        }
        start();
    }
    public void run(){
        while(true)
        {
            try{
                int messageType=in.readInt();
                messages[messageType].receive(in);
            }
            catch (IOException e)
            {

            }
        }
    }
}