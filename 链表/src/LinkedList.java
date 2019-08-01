import java.util.Iterator;
import java.util.List;

public class LinkedList {
    public static void main(String[] args) {
        List<String> staff = new java.util.LinkedList<>();
        staff.add("Army");
        staff.add("Bob");
        staff.add("Carl");
        Iterator iter = staff.iterator();
        String first = (String) iter.next();
        String second = (String) iter.next();
        iter.remove();
        for(String str: staff){
            System.out.println(str);
        }
    }
}
