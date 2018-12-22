import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class NormalMessage extends Message{
    final static int type=1;
    int from,to;
    String message;
    Scanner sin=new Scanner(System.in);
    private Map<Integer,DataOutputStream> out;
    NormalMessage(Map<Integer,DataOutputStream>out)
    {
        this.out=out;
    }
    public void setMessage()
    {
        from=1;
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
            out.get(to).writeInt(type);
            out.get(to).writeInt(from);
            out.get(to).writeInt(to);
            out.get(to).writeUTF(message);
            if(from==1)
                System.out.println("服务器->客户端"+to+": "+message);
        }
        catch (IOException e)
        {
            System.out.println("发送普遍消息失败");
        }
    }
    public void deal()
    {
        if(to==1)
        {
            System.out.println("客户端"+from+"->服务器: "+message);
        }
        else
        {
            send();
        }
    }
}
