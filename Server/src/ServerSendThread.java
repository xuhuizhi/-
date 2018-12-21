import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerSendThread extends Thread {
    public static DataOutputStream out[]=new DataOutputStream[45];
    Scanner sin;
    private String name;
    Message[] messages=new Message[100000];
    ServerSendThread(String name)
    {
        messages[1]=new NormalMessage();
        messages[2]=new BroadcastMessage();
        this.name=name;
        sin = new Scanner(System.in);
        start();
    }
    public void newConnect(int ID,Socket socket)
    {
        try {
            out[ID] = new DataOutputStream(socket.getOutputStream());
            System.out.println("客户端"+ID+"连接成功");
            LoginMessage loginMessage=new LoginMessage(ID);
            OnlineMessage onlineMessage=new OnlineMessage(ID);
            loginMessage.send(out[ID]);
            onlineMessage.send(out);
        }
        catch (IOException e)
        {
            System.out.println("服务器获得输出流失败");
        }
    }

    public void run() {
        System.out.println("输入你要发送消息种类");
        while(sin.hasNext())
        {
            int messageType=sin.nextInt();
            messages[messageType].setMessage();
            messages[messageType].send();
            System.out.println("输入你要发送消息种类");
        }
    }
}