import java.util.ArrayList;
import java.util.List;

public class NewConnentSubject {
    private List<Observer> observers = new ArrayList<Observer>();

    NewConnentSubject(){
        observers.add(new LoginMessage());
        observers.add(new OnlineMessage());
    }
    
    public void Notify(int ID)
    {
        for (int i = 0; i <observers.size() ; i++) {
            observers.get(i).Update(ID);
        }
        System.out.println("客户端"+ID+"连接成功");
    }
}
