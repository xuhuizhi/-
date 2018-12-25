import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Subject {

    private class EventData {
        private ArrayList<Observer> observers;
        public Boolean notify = true;

        public EventData() {
            observers = new ArrayList<Observer>();
        }

        public void addObserver(Observer observer) {
            if (this.observers.contains(observer) == false)
                this.observers.add(observer);
        }

        public void removeObserver(Observer observer) {
            if (this.observers.contains(observer) == false)
                this.observers.remove(observer);
        }
        public void setNotify(Boolean bool)
        {
            this.notify=bool;
        }
        public void Notify(Object object)
        {
            for(int i=0;i<observers.size();i++){
                observers.get(i).Update(object);
            }
        }
    }

    private Map<String, EventData> data = null;
    private static Subject subject=new Subject();
    public static Subject getInstance(){
        return subject;
    }

    private Subject() {
        data = new HashMap<String, EventData>();
    }

    public void addListen(String eventName, Observer observer) {
        if (eventName == null || eventName == "" || observer == null) {
            return;
        }
        if (data.containsKey(eventName) == false){
            data.put(eventName,new EventData());
        }
        EventData e = data.get(eventName);
        e.addObserver(observer);
    }

    public void removeListen(String eventName, Observer observer) {
        if (eventName == null || eventName == "" || observer == null) {
            return;
        }
        if (data.containsKey(eventName) == false)
            return;
        EventData e = data.get(eventName);
        e.removeObserver(observer);
    }

    public void setNotify(String eventName, Boolean bool){
        if (eventName == null || eventName == ""||
                data.containsKey(eventName)==false) return;
        EventData e = data.get(eventName);
        e.setNotify(bool);
    }

    public void Notify(String eventName,Object object)
    {
        if (eventName == null || eventName == ""||
                data.containsKey(eventName)==false) return;
        EventData e = data.get(eventName);
        e.Notify(object);
    }
}
