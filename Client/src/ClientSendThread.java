import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSendThread extends Thread {
    private static DataOutputStream out;
    Scanner sin;
    private int myID;
    MessageManage manage= MessageManage.getInsatance();
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
    public static void SendMessage(String message) throws IOException
    {
        out.writeUTF(message);
    }
    public static void SendMessage(int message) throws IOException
    {
        out.writeInt(message);
    }

    public void run() {
        System.out.println("输入你要发送的消息种类");
        while(sin.hasNext())
        {
            int messageType=sin.nextInt();
            manage.mymap.get(messageType).setMessage(myID);
            manage.mymap.get(messageType).send();
        }
    }
}
