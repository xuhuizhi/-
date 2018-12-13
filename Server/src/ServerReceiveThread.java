import java.io.*;
import java.net.Socket;

class ServerReceiveThread extends Thread
{
    private DataInputStream in;
    private Socket socket;
    ServerReceiveThread(int id,Socket socket){
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
    public void getMessage(byte[] Message)
    {
        int from = (int) ((Message[3] & 0xff) | ((Message[2] & 0xff) << 8) | ((Message[1] & 0xff) << 16) | ((Message[0] & 0xff) << 24));
        int to = (int) ((Message[7] & 0xff) | ((Message[6] & 0xff) << 8) | ((Message[5] & 0xff) << 16) | ((Message[4] & 0xff) << 24));
        int length = (int) ((Message[11] & 0xff) | ((Message[10] & 0xff) << 8) | ((Message[9] & 0xff) << 16) | ((Message[8] & 0xff) << 24));
        String s = new String();
        for(int i=12;i<length+12;i++)
            s=s+(char)Message[i];
        if(to==1)
            System.out.println("Server -> Client"+to+" :"+s);
        else
           MyServerSocket.serverSendThread.SendStr(Message,to);
    }
    public void run(){
        try {
            byte[] b=new byte[200];
            while(true)
            {
                in.read(b);
                getMessage(b);
            }
        }catch (IOException e)
        {

        }
    }
}