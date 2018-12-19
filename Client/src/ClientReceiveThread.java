import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReceiveThread extends Thread{
    private BufferedInputStream bin;
    private DataInputStream in;
    private Socket socket;
    private int ID;
    ClientReceiveThread(Socket socket,int id){
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
               // System.out.println("messageType:"+messageType);
                if (messageType == 1) {
                    NormalMessage normalMessage= new NormalMessage();
                    normalMessage.receieve(in);
                }
                else if(messageType==2)
                {
                    BroadcastMessage broadcastMessage=new BroadcastMessage();
                    broadcastMessage.receieve(in);
                }
                else if(messageType==3)
                {
                    LoginMessage loginMessage=new LoginMessage();
                    loginMessage.receive(in);
                }

            } catch (IOException e) {

            }
        }
    }
}
