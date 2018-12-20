import java.io.DataInputStream;
import java.io.IOException;

public class OnlineMessage extends Message{
    final static int type=4;
    public void receive(DataInputStream in)
    {
        try {
            String str=in.readUTF();
            System.out.println(str);
        }
        catch (IOException e)
        {

        }
    }
}
