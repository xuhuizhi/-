import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

public class LoginMessage extends Message{
    final static int type=3;
    public void send(int ID, Map<Integer,DataOutputStream> out)
    {
        try
        {
            out.get(ID).writeInt(type);
            out.get(ID).writeInt(ID);
        }
        catch (IOException e)
        {

        }
    }

}
