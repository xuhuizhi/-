import java.util.ArrayList;
import java.util.List;

abstract class Subject {
    protected List<Observer> observers = new ArrayList<Observer>();
    public void attach(Observer observer){
        observers.add(observer);
    }
    public void detach(Observer observer){
        observers.add(observer);
    }
    abstract void Notify(int ID);
}
