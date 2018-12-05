
public class Human {
    public void sleep() {
        System.out.println("Human sleep..");
    }
    public static  void doSleep(Human h){
        h.sleep();

    }//此时传递的参数是父类对象，但是实际调用时传递子类对象，就是向上转型。
    public static void main(String[] args) {
        Human h = new Male();// 向上转型
        doSleep(new Male());//此处匿名子类对象，当然实际应用时应该是用上面的向上转型公式，然后将子类对象传递进来，这样以后好在向下转型，此处没有向下转型，所以直接用了匿名类对象。
        doSleep(new Female());

    }
}

class Male extends Human {
    @Override
    public void sleep() {
        System.out.println("Male sleep..");
    }
}

class Female extends Human {
    @Override
    public void sleep() {
        System.out.println("Female sleep..");
    }

}