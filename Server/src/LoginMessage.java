import java.io.DataOutputStream;
import java.io.IOException;

public class LoginMessage {
    final static int type=3;
    int ID;
    LoginMessage(int ID)
    {
        this.ID=ID;
    }
    public void send(DataOutputStream out)
    {
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
