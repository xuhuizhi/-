import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReceiveThread extends Thread{
    private BufferedInputStream bin;
    private DataInputStream in;
    private Socket socket;
    public static MessageManage messageManage = new MessageManage();
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
                messageManage.mymap.get(messageType).receive(in);

            } catch (IOException e) {

            }
        }
    }
}
