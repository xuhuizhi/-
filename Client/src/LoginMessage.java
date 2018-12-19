import java.io.DataInputStream;
import java.io.IOException;

public class LoginMessage {
    final static int type=3;
    public void receive(DataInputStream in)
    {
        try
        {
            String s=in.readUTF();
            System.out.println(s);
        }
        catch (IOException e)
        {

        }
    }
}
