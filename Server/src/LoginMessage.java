import java.io.DataOutputStream;
import java.io.IOException;

public class LoginMessage {
    final static int type=3;
    int ID;
    LoginMessage(int ID)
    {
        this.ID=ID;
    }
    public void sendToLogin(DataOutputStream out)
    {
        try
        {

            String message2="你是客户端"+ID+"号";
            //System.out.println(message2);
            out.writeInt(type);
            out.writeUTF(message2);

        }
        catch (IOException e)
        {

        }
    }
    public void sendToOthers(DataOutputStream[] out)
    {
        try {
            for (int i = 2; i <ServerThread.Threadcount ; i++) {
                if(i!=ID)
                {
                    String message1=ID+"号客户端已上线";
                    //System.out.println(message1);
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
