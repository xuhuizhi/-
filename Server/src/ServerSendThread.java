import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerSendThread extends Thread {
    private static DataOutputStream out[]=new DataOutputStream[45];
    Scanner sin;
    private String name;
    ServerSendThread(String name)
    {
        this.name=name;
        sin = new Scanner(System.in);
        start();
    }
    public void newConnect(int ID,Socket socket)
    {
        try {
            out[ID] = new DataOutputStream(socket.getOutputStream());
            String s = "You are the Client"+("0"+ID);
            sendStr(s,ID);
            System.out.println("客户端"+ID+"连接成功");
        }
        catch (IOException e)
        {
            System.out.println("服务器获得输出流失败");
        }
    }

    public static void sendStr(String Str,int ID)
    {
        try {
            out[ID].writeInt(1);
            out[ID].writeInt(ID);
            out[ID].writeInt(Str.length());
            out[ID].writeUTF(Str);
        }
        catch (IOException e)
        {
            System.out.println("消息发送失败");
        }
    }

    public void run() {
        System.out.println("输入你要发送的目标编号");
        while(sin.hasNext())
        {
            int ID=sin.nextInt();
            System.out.println("输入你要发送的消息内容");
            String str = sin.nextLine();
            System.out.println("输入你要发送的目标编号");
            MessageFactory messageFactory = new MessageFactory(1,ID,str.length(),str);
        }
    }
}
