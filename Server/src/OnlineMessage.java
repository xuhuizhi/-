import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

public class OnlineMessage extends Message{
    final static int type=4;
    public void send(int ID, Map<Integer,DataOutputStream> out)
    {
        try {
            for (int i = 2; i <ServerThread.Threadcount ; i++) {
                if(i!=ID)
                {
                    String message1=ID+"号客户端已上线";
                    out.get(i).writeInt(type);
                    out.get(i).writeUTF(message1);

                }
            }
        }
        catch (IOException e)
        {

        }
    }
}
