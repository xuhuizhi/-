import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;

public class MessageManage {
    public Map<Integer,Message>mymap = new HashMap<Integer, Message>();
    private Map<Integer,DataOutputStream>out;
    public MessageManage(Map<Integer,DataOutputStream>out)
    {
        this.out=out;
        mymap.put(1,new NormalMessage(out));
        mymap.put(2,new BroadcastMessage(out));
        mymap.put(3,new LoginMessage());
        mymap.put(4,new OnlineMessage());
    }
    public void SendLoginMessage(int ID)
    {
        mymap.get(3).send(ID,out);
        mymap.get(4).send(ID,out);
        System.out.println("客户端"+ID+"连接成功");
    }

}
