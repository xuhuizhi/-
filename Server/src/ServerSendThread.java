import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerSendThread extends Thread {
    private static DataOutputStream out[]=new DataOutputStream[45];
    Scanner sin;
    private String name;
    ServerSendThread(String name)
    {
        this.name=name;
        sin = new Scanner(System.in);
        start();
    }
    public void newConnect(int ID,Socket socket)
    {
        try {
            out[ID] = new DataOutputStream(socket.getOutputStream());
            String s = "You are the Client"+("0"+ID);
            SendStr(s,ID);
            System.out.println("客户端"+ID+"连接成功");
        }
        catch (IOException e)
        {
            System.out.println("服务器获得输出流失败");
        }
    }
    public static byte[] getByte(int ID,String str)
    {
        int length=str.length();
        byte[] result = new byte[12+length];
        //发送者编号
        result[0]=0;
        result[1]=0;
        result[2]=0;
        result[3]=1;
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
        byte[] next=str.getBytes();
        for(int i=12;i<length+12;i++)
            result[i]=(byte) str.charAt(i-12);
        return result;
    }

    public void SendStr(byte[] Message,int ID)
    {
        try {
            System.out.println("length:"+Message.length);
            out[ID].write(Message,0,Message.length);
            out[ID].flush();
//            System.out.println(name+"->"+ID+"号客户端:"+Str);
        }
        catch (IOException e)
        {
            System.out.println("转发消息失败");
        }
    }
    private void SendStr(String Str,int ID)
    {
        byte[] Message=getByte(ID,Str);
        try {
            System.out.println("length:"+Message.length);
            out[ID].write(Message,0,Message.length);
            out[ID].flush();
            System.out.println(name+"->"+ID+"Client:"+Str);
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
