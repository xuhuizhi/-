import java.util.HashMap;
import java.util.Map;

public class MessageManage {
    public Map<Integer,Message>mymap = new HashMap<Integer, Message>();
    private static MessageManage manage=new MessageManage();
    private MessageManage()
    {
        mymap.put(1,new NormalMessage());
        mymap.put(2,new BroadcastMessage());
        mymap.put(3,new LoginMessage());
        mymap.put(4,new OnlineMessage());
    }
    public void SendLoginMessage(int ID)
    {
        mymap.get(3).send(ID);
        mymap.get(4).send(ID);
        System.out.println("客户端"+ID+"连接成功");
    }
    public static MessageManage getInsatance()
    {
        return manage;
    }
}
