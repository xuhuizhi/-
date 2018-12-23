import java.io.IOException;

public class OnlineMessage extends Message{
    final static int type=4;
    public void send(int ID)
    {
        try {
            for (int i = 2; i <ServerThread.Threadcount ; i++) {
                if(i!=ID)
                {
                    String message=ID+"号客户端已上线";
                    ServerSendThread.SendMessage(i,type);
                    ServerSendThread.SendMessage(i,message);
                }
            }
        }
        catch (IOException e)
        {

        }
    }
}
