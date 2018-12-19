import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class NormalMessage{
    final static int type=1;
    int from,to;
    String message;
    public void setMessage(int from,int to,String message)
    {
        this.from=from;
        this.to=to;
        this.message=message;
    }
    public void receieve(DataInputStream in)
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
    public void send(DataOutputStream out)
    {
        try{
            out.writeInt(type);
            out.writeInt(from);
            out.writeInt(to);
            out.writeUTF(message);
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
