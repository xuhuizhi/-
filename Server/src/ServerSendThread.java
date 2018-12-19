import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerSendThread extends Thread {
    public static DataOutputStream out[]=new DataOutputStream[45];
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
            System.out.println("客户端"+ID+"连接成功");
            LoginMessage loginMessage=new LoginMessage(ID);
            loginMessage.sendToLogin(out[ID]);
            loginMessage.sendToOthers(out);
        }
        catch (IOException e)
        {
            System.out.println("服务器获得输出流失败");
        }
    }

    public void run() {
        System.out.println("输入你要发送的客户端");
        while(sin.hasNext())
        {
            int messageType=sin.nextInt();
            if(messageType==1)
            {
                int to=sin.nextInt();
                String str=sin.nextLine();
                NormalMessage normalMessage=new NormalMessage();
                normalMessage.setMessage(1,to,str);
                normalMessage.send(out[to]);
            }
            else if(messageType==2)
            {
                String str=sin.nextLine();
                BroadcastMessage broadcastMessage=new BroadcastMessage();
                broadcastMessage.setMessage(1,str);
                broadcastMessage.send(out);
            }
        }
    }
}
