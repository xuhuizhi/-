import java.util.HashMap;
import java.util.Map;

public class MessageManage {
    public static Map<Integer,Message>mymap = new HashMap<Integer, Message>();
    public MessageManage()
    {
        mymap.put(1,new NormalMessage());
        mymap.put(2,new BroadcastMessage());
        mymap.put(3,new LoginMessage());
        mymap.put(4,new OnlineMessage());
    }
}
