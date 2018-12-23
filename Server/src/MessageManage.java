import java.util.HashMap;
import java.util.Map;

public class MessageManage {
    private static Map<Integer,Message>mymap = new HashMap<Integer, Message>();
    private static MessageManage manage=new MessageManage();
    private MessageManage()
    {
        mymap.put(1,new NormalMessage());
        mymap.put(2,new BroadcastMessage());
        mymap.put(3,new LoginMessage());
        mymap.put(4,new OnlineMessage());
    }
    public static MessageManage getInsatance()
    {
        return manage;
    }
    public static Message getMessage(int messageType)
    {
        return mymap.get(messageType);
    }
}
