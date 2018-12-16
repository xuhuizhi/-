abstract class MessageDeal {
    int from,to,length;
    String Message;
    MessageDeal(int from,int to,int length,String Message){
        this.from=from;
        this.to=to;
        this.length=length;
        this.Message=Message;
    }
    abstract void deal();

}
