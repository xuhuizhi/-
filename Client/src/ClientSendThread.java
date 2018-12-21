import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSendThread extends Thread {
    private static DataOutputStream out;
    Scanner sin;
    private int myID;
    Message[] messages=new Message[10000];
    ClientSendThread(int myID,Socket socket)
    {
        messages[1]=new NormalMessage();
        messages[2]=new BroadcastMessage();
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
            messages[messageType].setMessage(myID);
            messages[messageType].send(out);
        }
    }
}
