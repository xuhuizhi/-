import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerSendThread extends Thread {
    private Map<Integer,DataOutputStream>out=new HashMap<Integer, DataOutputStream>();
    private MessageManage messageManage;
    Scanner sin;
    private String name;
    ServerSendThread(String name)
    {
        MessageManageSingleton.SetOut(out);
        messageManage=MessageManageSingleton.GetInsatance();
        this.name=name;
        sin = new Scanner(System.in);
        start();
    }
    public void newConnect(int ID,Socket socket)
    {
        try {
            out.put(ID,new DataOutputStream(socket.getOutputStream()));
            messageManage.SendLoginMessage(ID);
        }
        catch (IOException e)
        {

        }
    }

    public void run() {
        System.out.println("输入你要发送消息种类");
        while(sin.hasNext())
        {
            int messageType=sin.nextInt();
            messageManage.mymap.get(messageType).setMessage();
            messageManage.mymap.get(messageType).send();
            System.out.println("输入你要发送消息种类");
        }
    }
}