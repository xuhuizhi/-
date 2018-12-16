public class BroadcastMessageDeal extends MessageDeal{
    BroadcastMessageDeal(int from,int to,int length,String Message)
    {
        super(from,to,length,Message);
    }
    public void deal()
    {
        for(int i=2;i<ServerThread.Threadcount;i++)
        {
            ServerSendThread.sendStr(Message,i);
        }
    }
}
