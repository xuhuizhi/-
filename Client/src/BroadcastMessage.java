import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class BroadcastMessage extends Message{
    Scanner sin= new Scanner(System.in);
    final static int type=2;
    int from;
    String message;
    public void setMessage(int from)
    {
        this.from=from;
        System.out.println("请输入你要发送的消息内容");
        message=sin.next();
    }
    public void receive(DataInputStream in)
    {
        try {
            from=in.readInt();
            message=in.readUTF();
            deal();
        }
        catch (IOException e)
        {
            System.out.println("读取广播消息失败");
        }
    }
    public void send(DataOutputStream out)
    {
        try{
            out.writeInt(type);
            out.writeInt(from);
            out.writeUTF(message);
            System.out.println("客户端"+from+": "+message);
        }
        catch (IOException e)
        {
            System.out.println("发送广播消息失败");
        }
    }
    public void deal()
    {
        if(from==1)
        {
            System.out.println("服务器: " + message);
        }
        else {
            System.out.println("客户端" + from + ": " + message);
        }
    }
}
