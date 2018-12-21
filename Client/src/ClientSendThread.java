import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSendThread extends Thread {
    private static DataOutputStream out;
    Scanner sin;
    private int myID;
    ClientSendThread(int myID,Socket socket)
    {
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
            ClientReceiveThread.messageManage.mymap.get(messageType).setMessage(myID);
            ClientReceiveThread.messageManage.mymap.get(messageType).send(out);
        }
    }
}
