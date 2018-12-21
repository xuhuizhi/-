
import java.io.DataOutputStream;
import java.io.IOException;

public class OnlineMessage extends Message{
    final static int type=4;
    public void send(int ID)
    {
        DataOutputStream[] out=ServerSendThread.out;
        try {
            for (int i = 2; i <ServerThread.Threadcount ; i++) {
                if(i!=ID)
                {
                    String message1=ID+"号客户端已上线";
                    out[i].writeInt(type);
                    out[i].writeUTF(message1);

                }
            }
        }
        catch (IOException e)
        {

        }
    }
}
