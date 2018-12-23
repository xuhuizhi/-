import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerSendThread extends Thread {
    private static Map<Integer,DataOutputStream>out=new HashMap<Integer, DataOutputStream>();
    NewConnentSubject newConnentSubject= new NewConnentSubject();
    private MessageManage messageManage;
    private Message message;
    Scanner sin;
    ServerSendThread()
    {
        messageManage=MessageManage.getInsatance();
        sin = new Scanner(System.in);
        newConnentSubject.attach(new LoginMessage());
        newConnentSubject.attach(new OnlineMessage());
        start();
    }
    public void newConnect(int ID,Socket socket)
    {
        try {
            out.put(ID,new DataOutputStream(socket.getOutputStream()));
            newConnentSubject.Notify(ID);
        }
        catch (IOException e)
        {

        }
    }
    public static void SendMessage(int id,String message) throws IOException
    {
        out.get(id).writeUTF(message);
    }
    public static void SendMessage(int id,int message) throws IOException
    {
        out.get(id).writeInt(message);
    }

    public void run() {
        System.out.println("输入你要发送消息种类");
        while(sin.hasNext())
        {
            int messageType=sin.nextInt();
            message=messageManage.getMessage(messageType);
            message.setMessage();
            message.send();
            System.out.println("输入你要发送消息种类");
        }
    }
}