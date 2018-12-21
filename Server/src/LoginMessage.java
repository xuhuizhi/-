import java.io.DataOutputStream;
import java.io.IOException;

public class LoginMessage extends Message{
    final static int type=3;
    public void send(int ID)
    {
        DataOutputStream out=ServerSendThread.out[ID];
        try
        {
            out.writeInt(type);
            out.writeInt(ID);
        }
        catch (IOException e)
        {

        }
    }

}
