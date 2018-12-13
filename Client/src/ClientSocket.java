public class ClientSocket {
    static final int MAX_THREADS=40;
    static int threadCount=0;
    ClientSocket(){
        if(threadCount<40)
        {
            //System.out.println("当前客户端ID:"+(threadCount+2));
            new ClientSocketThread(threadCount+2);
            threadCount++;
        }
        else
        {
            System.out.println("当前客户端连接太多，请稍后再试");
        }
    }
}
