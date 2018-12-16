public class NormalMessageDeal extends MessageDeal{
    NormalMessageDeal(int from,int to,int length,String Message)
    {
        super(from,to,length,Message);
    }
    public void deal()
    {
        ServerSendThread.sendStr(Message,to);
    }
}
