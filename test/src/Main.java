interface HasBatteries {}
interface Waterproof {}
interface Shoots {}
interface HHHHHHHHHHHHHHHH {}

class Toy{
    Toy() {}
    Toy(int i) {}
}

class FancyToy extends Toy implements HasBatteries,Waterproof,Shoots,HHHHHHHHHHHHHHHH{
    FancyToy() {
        super(1);
    }
}


public class Main {
    static void printInfo(Class cc){
        System.out.println("Class name:"+cc.getName()+" is interface? ["+cc.isInterface()+"]");
        System.out.println("Simple name: "+cc.getSimpleName());
        System.out.println("Canonical name : "+cc.getCanonicalName());
    }
    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("FancyToy");
        } catch (ClassNotFoundException e){
            System.out.println("NOT FOUND");
        }
        printInfo(c);
        for(Class face:c.getInterfaces())
            printInfo(face);
        Class up = c.getSuperclass();
        Object obj = null;
        try {
            obj = up.newInstance();
        }
        catch (IllegalAccessException e){
            System.out.println("Illegal");
            System.exit(1);
        }
        catch (InstantiationException e){
            System.out.println("Can't access");
            System.exit(1);
        }
        printInfo(obj.getClass());
    }
}
