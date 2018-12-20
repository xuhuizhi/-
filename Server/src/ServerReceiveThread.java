import java.io.*;
import java.net.Socket;

class ServerReceiveThread extends Thread
{
    private DataInputStream in;
    private Socket socket;
    String messages[]=new String[50];
    ServerReceiveThread(Socket socket){
        messages[1]="NormalMessage";
        messages[2]="BroadcastMessage";
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
                try {
                    Class clz=Class.forName(messages[messageType]);
                    Message message=(Message) clz.newInstance();
                    message.receive(in);
                }
                catch (ClassNotFoundException e)
                {
                    System.out.println("没有此类消息");
                }
                catch (InstantiationException e)
                {

                }
                catch (IllegalAccessException e)
                {

                }
            }
            catch (IOException e)
            {

            }
        }
    }
}