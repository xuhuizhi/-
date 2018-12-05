import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class URL1 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://java.sun.com/j2se/index.jsp");
        InputStreamReader isr =new InputStreamReader(url.openStream());
        BufferedReader br = new BufferedReader(isr);
        String s;
        while((s=br.readLine())!=null)
            System.out.println("s"+s);
        //br.close();
    }
}
