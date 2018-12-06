public class ClientSocket {
    static final int MAX_THREADS=40;
    public void makeConnect(){
        if(ClientSocketThread.getThreadCount()<MAX_THREADS)
        {
            new ClientSocketThread();
        }
        else
        {
            System.out.println("当前接入客户端太多");
        }
    }
}
