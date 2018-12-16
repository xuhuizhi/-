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
        try {
            while(true)
            {
                int from=in.readInt();
                int to = in.readInt();
                int length=in.readInt();
                String s = in.readUTF();
                if(to!=1){
                    MessageFactory messageFactory = new MessageFactory(from,to,length,s);
                }
                else{
                    System.out.println("客户端"+from+" -> 服务器 :"+s);
                }

            }
        }catch (IOException e)
        {

        }
    }
}