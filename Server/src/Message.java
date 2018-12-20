import java.io.DataInputStream;

abstract class Message  {
    abstract void setMessage();
    abstract void send();
    abstract void receive(DataInputStream in);
}
