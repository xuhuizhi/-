import java.io.*;
import java.net.Socket;

class ServerReceiveThread extends Thread
{
    private DataInputStream in;
    private Socket socket;
    private Message message=null;
    private MessageManage messageManage=MessageManage.getInsatance();
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
                message=messageManage.getMessage(messageType);
                message.receive(in);
            }
            catch (IOException e)
            {

            }
        }
    }
}