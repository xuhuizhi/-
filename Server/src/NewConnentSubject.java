
public class NewConnentSubject extends Subject{

    public void Notify(int ID)
    {
        for (int i = 0; i <observers.size() ; i++) {
            observers.get(i).Update(ID);
        }
        System.out.println("客户端"+ID+"连接成功");
    }
}
