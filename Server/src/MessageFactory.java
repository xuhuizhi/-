public class MessageFactory {
    MessageDeal messageDeal=null;
    MessageFactory(int from,int to,int length,String str)
    {
        if (to==0) //BroadcastMessage
        {
            messageDeal=new BroadcastMessageDeal(from,to,length,str);
        }
        else
        {
            messageDeal = new NormalMessageDeal(from,to,length,str);
        }
        messageDeal.deal();
    }
}
