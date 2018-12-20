import java.io.DataOutputStream;
import java.io.IOException;

public class OnlineMessage {
    final static int type=4;
    int ID;
    OnlineMessage(int ID)
    {
        this.ID=ID;
    }
    public void send(DataOutputStream[] out)
    {
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
