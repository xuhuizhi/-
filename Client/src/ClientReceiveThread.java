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
    public void getMessage(byte[] Message)
    {
        int from = (int) ((Message[3] & 0xff) | ((Message[2] & 0xff) << 8) | ((Message[1] & 0xff) << 16) | ((Message[0] & 0xff) << 24));
        int to = (int) ((Message[7] & 0xff) | ((Message[6] & 0xff) << 8) | ((Message[5] & 0xff) << 16) | ((Message[4] & 0xff) << 24));
        int length = (int) ((Message[11] & 0xff) | ((Message[10] & 0xff) << 8) | ((Message[9] & 0xff) << 16) | ((Message[8] & 0xff) << 24));
        String s = new String();
        for(int i=12;i<length+12;i++)
            s=s+(char)Message[i];
        if(from==1)
            System.out.println("Server -> Client"+to+" :"+s);
        else
            System.out.println("Client"+from+" -> Client"+to+" :"+s);
    }
    public void run(){
        try{
            byte[] b=new byte[200];
            while(true)
            {
                in.read(b);
                getMessage(b);
            }

        }catch (IOException e){

        }
        try{
            bin.close();
            in.close();
        }
        catch (IOException e)
        {

        }
    }
}
