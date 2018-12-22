import java.io.DataOutputStream;
import java.util.Map;

public class MessageManageSingleton {
    private static MessageManage manage;
//    private Map<Integer,DataOutputStream>out;
    private static Map<Integer,DataOutputStream>out;
    private MessageManageSingleton()
    {

    }
    public static void SetOut(Map<Integer,DataOutputStream>map){
        out=map;
    }
    public static MessageManage GetInsatance()
    {
        if(manage==null)
        {
            manage=new MessageManage(out);
        }
        return manage;
    }
}
