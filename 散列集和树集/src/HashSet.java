import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Set;

public class HashSet {
    public static void main(String[] args) {
        Set<String> words = new java.util.HashSet<>();
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner in = new Scanner(System.in);
        while(!in.hasNext("eof")){
            String word = in.next();
            words.add(word);
        }

        for(Iterator<String> iter = words.iterator(); iter.hasNext();){
//            String str = iter.next();
//            System.out.println(str);
//            System.out.println(str.hashCode());
            System.out.println(iter.next().hashCode());
        }
    }
}
