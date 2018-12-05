class Cleanser{
    private String s = "CleanerHHH";
    public void append(String a){s+=a;}
    public void dilute(){append("dilute()");}
    public void apply() {append("apply()");}
    public void scrub() {append("scrub()");}
    public String toString() {return s;}

    public static void main(String[] args) {
        Cleanser x = new Cleanser();
        System.out.println("Cleanser's MAIN");
        x.dilute();x.apply();x.scrub();
        System.out.println(x);
    }
}
public class Detergent extends Cleanser{
    public void scrub() {
        append("Detergent scrub()");
        super.scrub();
    }
    public void foam(){append("foam()");}

    public static void main(String[] args) {
        Detergent x = new Detergent();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();
        System.out.println(x);
        System.out.println("_____________");
        Cleanser.main(args);
    }
}
