import java.io.IOException;

public class LoginMessage extends Message implements Observer{
    final static int type=3;
    public void send(int ID)
    {
        try
        {
            ServerSendThread.SendMessage(ID,type);
            ServerSendThread.SendMessage(ID,ID);
        }
        catch (IOException e)
        {

        }
    }

    public void Update(Object object) {
        send((int)object);
    }
}
