import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSendThread extends Thread {
    private static DataOutputStream out;
    Scanner sin;
    private int myID;
    ClientSendThread(int myID,Socket socket)
    {
        this.myID=myID;
        try {
            sin = new Scanner(System.in);
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch (Exception e)
        {
            System.out.println("客户端获得输出流失败");
        }
        start();
    }

    public byte[] getByte(int ID,String str)
    {
        int length=str.length();
        byte[] result = new byte[12+length];
        //发送者编号
        result[0] = (byte) ((myID >> 24) & 0xFF);
        result[1] = (byte) ((myID >> 16) & 0xFF);
        result[2] = (byte) ((myID >> 8) & 0xFF);
        result[3] = (byte) (myID & 0xFF);
        //接收者编号
        result[4] = (byte) ((ID >> 24) & 0xFF);
        result[5] = (byte) ((ID >> 16) & 0xFF);
        result[6] = (byte) ((ID >> 8) & 0xFF);
        result[7] = (byte) (ID & 0xFF);
        //消息长度
        result[8] = (byte) ((length >> 24) & 0xFF);
        result[9] = (byte) ((length >> 16) & 0xFF);
        result[10] = (byte) ((length >> 8) & 0xFF);
        result[11] = (byte) (length & 0xFF);
        //消息内容
        for(int i=12;i<length+12;i++)
            result[i]=(byte) str.charAt(i-12);
        return result;
    }

    private void SendStr(String Str,int ID)
    {
        byte[] Message=getByte(ID,Str);
        try {
            out.write(Message,0,Message.length);
            out.flush();
            System.out.println(myID+"Client -> "+ID+"Client:"+Str);
        }
        catch (IOException e)
        {
            System.out.println("消息发送失败");
        }
    }

    public void run() {
        while(sin.hasNext())
        {
            int ID=sin.nextInt();
            String str = sin.nextLine();
            SendStr(str,ID);
        }
    }
}
