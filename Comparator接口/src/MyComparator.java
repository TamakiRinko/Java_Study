import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 测试Comparator接口，熟悉lambda表达式
 * @author 史文泰
 */

public class MyComparator {
    public static void main(String[] args) {
        Person[] people = new Person[3];
//        //错误！上句仅仅是生成3个的数组，并未指向任何一个对象
//        people[0].setName("Abelard");
//        people[1].setName("David");
//        people[2].setName("Jack");
        people[0] = new Person("Abelard");
        people[1] = new Person("Ack");
        people[2] = new Person("David");

//        //按名称的长度排序
//        Arrays.sort(people, Comparator.comparingInt((Person p)->{return p.getName().length();}));
//        for(Person p:people){
//            System.out.println(p.getName());
//        }
//        System.out.println("");
//
//        //按名称的字典序，使用lambda表达式
//        Arrays.sort(people, Comparator.comparing((Person p)->{return p.getName();}));
//        for(Person p:people){
//            System.out.println(p.getName());
//        }
//        System.out.println("");
//
//        //按名称的字典序，直接使用get方法
//        Arrays.sort(people, Comparator.comparing(Person::getName));
//        for(Person p:people){
//            System.out.println(p.getName());
//        }
//        System.out.println("");
//
//        //按名称的长度排序，为comparing方法提取的键指定比较器，使用lambda表达式
////        Arrays.sort(people, Comparator.comparing(Person::getName, (s, t)->Integer.compare(s.length(), t.length())));
//        Arrays.sort(people, Comparator.comparing(Person::getName, (s, t)->{return s.length() - t.length();}));
//        for(Person p:people){
//            System.out.println(p.getName());
//        }
//        System.out.println("");

        //使用Comparator接口按名称长度排序
        Arrays.sort(people, new Person());
        for(Person p:people){
            System.out.println(p.getName());
        }
        System.out.println("");
    }
}
