import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReceiveThread extends Thread{
    private BufferedInputStream bin;
    private DataInputStream in;
    private Socket socket;
    private int ID;
    Message messages[]=new Message[10000];
    ClientReceiveThread(Socket socket,int id){
        messages[1]=new NormalMessage();
        messages[2]=new BroadcastMessage();
        messages[3]=new LoginMessage();
        messages[4]=new OnlineMessage();
        this.socket=socket;
        this.ID=id;
        try {
            bin = new BufferedInputStream(socket.getInputStream());
            in =new DataInputStream(bin);
        }
        catch (IOException e)
        {
            System.out.println("获得输入流失败");
        }
        start();
    }

    public void run(){
        while (true) {
            try {
                int messageType = in.readInt();
                //System.out.println("Messagetype:"+messageType);
                messages[messageType].receive(in);
            } catch (IOException e) {

            }
        }
    }
}
