import java.io.DataInputStream;
import java.io.IOException;

public class LoginMessage extends Message {
    final static int type=3;
    public void receive(DataInputStream in)
    {
        try
        {
            int ID=in.readInt();
            System.out.println(ID);
        }
        catch (IOException e)
        {

        }
    }
}
