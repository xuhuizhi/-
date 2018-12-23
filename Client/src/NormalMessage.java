import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class NormalMessage extends Message{
    Scanner sin = new Scanner(System.in);
    final static int type=1;
    int from,to;
    String message;
    public void setMessage(int from)
    {
        this.from=from;
        System.out.println("请输入你要发送的目标编号");
        to=sin.nextInt();
        System.out.println("请输入你要发送的消息");
        message=sin.next();
    }
    public void receive(DataInputStream in)
    {
        try {
            from=in.readInt();
            to=in.readInt();
            message=in.readUTF();
            deal();
        }
        catch (IOException e)
        {
            System.out.println("读取普通消息失败");
        }
    }
    public void send()
    {
        try{
            ClientSendThread.SendMessage(type);
            ClientSendThread.SendMessage(from);
            ClientSendThread.SendMessage(to);
            ClientSendThread.SendMessage(message);
            if (to==1)
            {
                System.out.println("客户端"+from+"->服务器: "+message);
            }
            else
            {
                System.out.println("客户端"+from+"->客户端"+to+": "+message);
            }
        }
        catch (IOException e)
        {
            System.out.println("发送普遍消息失败");
        }
    }
    public void deal()
    {
        if(from==1)
        {
            System.out.println("服务器->客户端"+to+": " + message);
        }
        else {
            System.out.println("客户端" + from + "->客户端"+to+": " + message);
        }
    }
}
