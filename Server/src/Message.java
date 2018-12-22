import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;

abstract class Message  {
    public void setMessage(){

    }
    public void send(){

    }
    public void send(int ID,Map<Integer,DataOutputStream>out)
    {

    }
    public void receive(DataInputStream in){

    }

}
