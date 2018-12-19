import java.io.*;
import java.net.Socket;

class ServerReceiveThread extends Thread
{
    private DataInputStream in;
    private Socket socket;
    ServerReceiveThread(Socket socket){
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
                if(messageType==1)
                {
                    NormalMessage normalMessage=new NormalMessage();
                    normalMessage.receieve(in);
                }
                else if(messageType==2)
                {
                    BroadcastMessage broadcastMessage=new BroadcastMessage();
                    broadcastMessage.receive(in);
                }
            }
            catch (IOException e)
            {

            }
        }
    }
}