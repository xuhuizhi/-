import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSendThread extends Thread {
    private static DataOutputStream out;
    Scanner sin;
    private int myID;
    String messages[]=new String[50];
    ClientSendThread(int myID,Socket socket)
    {
        messages[1]="NormalMessage";
        messages[2]="BroadcastMessage";
        this.myID=myID;
        try {
            sin = new Scanner(System.in);
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch (Exception e)
        {
            System.out.println("客户端获得输出流失败");
        }
        start();
    }

    public void run() {
        System.out.println("输入你要发送的消息种类");
        while(sin.hasNext())
        {
            int messageType=sin.nextInt();

            try {
                Class clz=Class.forName(messages[messageType]);
                Message message=(Message) clz.newInstance();
                message.setMessage(myID);
                message.send(out);
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
    }
}
