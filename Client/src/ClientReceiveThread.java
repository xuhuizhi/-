import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReceiveThread extends Thread{
    private BufferedInputStream bin;
    private DataInputStream in;
    private Socket socket;
    private int ID;
    String messages[]=new String[50];
    ClientReceiveThread(Socket socket,int id){
        messages[1]="NormalMessage";
        messages[2]="BroadcastMessage";
        messages[3]="LoginMessage";
        messages[4]="OnlineMessage";
        this.socket=socket;
        this.ID=id;
        try {
            bin = new BufferedInputStream(socket.getInputStream());
            in =new DataInputStream(bin);
        }
        catch (IOException e)
        {
            System.out.println("获得输入流失败");
        }
        start();
    }

    public void run(){
        while (true) {
            try {
                int messageType = in.readInt();
                //System.out.println("messageType:"+messageType);
                try {
                    //System.out.println(messages[messageType]);
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
            } catch (IOException e) {

            }
        }
    }
}
