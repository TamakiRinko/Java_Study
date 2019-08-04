import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashMapTest {

    private static Map<String, Employee> staff;

    public static void main(String[] args) {
        staff = new LinkedHashMap<>();
        System.out.println(staff.put("244-25-5464", new Employee("Amy Lee")));
        staff.put("567-24-2546", new Employee("Harry Hacker"));
        staff.put("157-62-7935", new Employee("Cary Cooper"));
        staff.put("456-62-5527", new Employee("Francesca Cruz"));
        iterTest();
    }

    public static void iterTest(){
//        InsertOrder();     //按照插入顺序
        AccessOrder();        //按照访问顺序
    }

    public static void InsertOrder(){
        /**
         * 所有的遍历均按照插入的顺序进行遍历！！accessOrder默认为0。结果均为：
         * key = 244-25-5464, value = Amy Lee
         * key = 567-24-2546, value = Harry Hacker
         * key = 157-62-7935, value = Cary Cooper
         * key = 456-62-5527, value = Francesca Cruz
         */
        Iterator<String> iter = staff.keySet().iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
        System.out.println("");
        //迭代遍历
        staff.forEach((String k, Employee v)->{
            System.out.println("key = " + k + ", value = " + v);
        });
        System.out.println("");
        //第二种迭代方式，原本最高效，现已被上一种取代
        //Entry为Map内部接口！
        for(Map.Entry<String, Employee> entry: staff.entrySet()){
            String k = entry.getKey();
            Employee v = entry.getValue();
            System.out.println("key = " + k + ", value = " + v);
        }
        System.out.println("");
        //也即可以如下表示
        Set<Map.Entry<String, Employee>> entries = staff.entrySet();
        for(Map.Entry<String, Employee> entry: entries){
            String k = entry.getKey();
            Employee v = entry.getValue();
            System.out.println("key = " + k + ", value = " + v);
        }
    }

    public static void AccessOrder(){
        /**
         * 按照访问顺序遍历，accessOrder = true
         */
        LinkedHashMap<String, Employee> lhs = new LinkedHashMap<String, Employee>(16, 0.75F, true);
        lhs.put("456-62-5527", new Employee("Francesca Cruz"));
        lhs.put("244-25-5464", new Employee("Amy Lee"));
        lhs.put("157-62-7935", new Employee("Cary Cooper"));
        lhs.put("567-24-2546", new Employee("Harry Hacker"));

        /**
         * 访问到的放到末尾！！不是开头！！
         * 456-62-5527被放到末尾
         * 结果为：
         * key = 244-25-5464, value = Amy Lee
         * key = 157-62-7935, value = Cary Cooper
         * key = 567-24-2546, value = Harry Hacker
         * key = 456-62-5527, value = Francesca Cruz
         */
        System.out.println(lhs.get("456-62-5527"));

        Iterator<String> iter = lhs.keySet().iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
        System.out.println("");
        //迭代遍历
        lhs.forEach((String k, Employee v)->{
            System.out.println("key = " + k + ", value = " + v);
        });
        System.out.println("");
        //第二种迭代方式，原本最高效，现已被上一种取代
        //Entry为Map内部接口！
        for(Map.Entry<String, Employee> entry: lhs.entrySet()){
            String k = entry.getKey();
            Employee v = entry.getValue();
            System.out.println("key = " + k + ", value = " + v);
        }
        System.out.println("");
        //也即可以如下表示
        Set<Map.Entry<String, Employee>> entries = lhs.entrySet();
        for(Map.Entry<String, Employee> entry: entries){
            String k = entry.getKey();
            Employee v = entry.getValue();
            System.out.println("key = " + k + ", value = " + v);
        }
    }

}
