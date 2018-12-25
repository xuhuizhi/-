import java.awt.*;
import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;

public class MessageManage {
    private static Map<Integer,Message>mymap = new HashMap<Integer, Message>();
    private static MessageManage manage=new MessageManage();
    Subject subject=Subject.getInstance();
    private MessageManage()
    {
        NormalMessage normalMessage=new NormalMessage();
        BroadcastMessage broadcastMessage = new BroadcastMessage();
        LoginMessage loginMessage= new LoginMessage();
        OnlineMessage onlineMessage = new OnlineMessage();
        mymap.put(1,normalMessage);
        mymap.put(2,broadcastMessage);
        mymap.put(3,loginMessage);
        mymap.put(4,onlineMessage);
        subject.addListen("newConnect",loginMessage);
        subject.addListen("newConnect",onlineMessage);
    }
    public static MessageManage getInsatance()
    {
        return manage;
    }
    public static void setMessage(int messageType)
    {
        mymap.get(messageType).setMessage();
    }
    public static void send(int messageType)
    {
        mymap.get(messageType).send();
    }
    public static void receive(int messageType, DataInputStream in)
    {
        mymap.get(messageType).receive(in);
    }
}