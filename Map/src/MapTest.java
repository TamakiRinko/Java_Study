import java.util.*;

public class MapTest {

    private static Map<String, Employee> staff;
    private static Map<String, Employee> treeMap;

    public static void main(String[] args) {
        staff = new HashMap<>();
        //put返回该键之前的值，第一次赋值，故返回null
        System.out.println(staff.put("244-25-5464", new Employee("Amy Lee")));
        staff.put("567-24-2546", new Employee("Harry Hacker"));
        staff.put("157-62-7935", new Employee("Cary Cooper"));
        staff.put("456-62-5527", new Employee("Francesca Cruz"));

        //挨个打印，格式：键=值
        //{157-62-7935=Cary Cooper, 144-25-5464=Amy Lee,
        // 456-62-5527=Francesca Cruz, 567-24-2546=Harry Hacker}
        System.out.println(staff.toString());

        staff.remove("567-24-2546");
        staff.replace("456-62-5527", new Employee("Francesca Hiller"));
        System.out.println(staff.get("157-62-7935"));
        //getOrDefault，有默认值返回
        System.out.println(staff.getOrDefault("157-62-1234", new Employee("None")));
        //迭代遍历
        staff.forEach((String k, Employee v)->{
            System.out.println("key = " + k + ", value = " + v);
        });
        System.out.println("");

        TreeMapTest();
    }

    public static void TreeMapTest(){
        treeMap = new TreeMap<>(staff);
        //按键的顺序迭代输出！
        treeMap.forEach((String k, Employee v)->{
            System.out.println("key = " + k + ", value = " + v);
        });
        System.out.println("");

        //按键的顺序迭代输出！
        for(Iterator<String> iter = treeMap.keySet().iterator(); iter.hasNext();){
            System.out.println(iter.next());
        }
        System.out.println("");

        //分别输出每个键即其对应的值！也按照键的排列顺序
        Set<String> keyset = treeMap.keySet();
        for(String key:keyset){
            System.out.println(key);
        }
        for(String key: treeMap.keySet()){
            System.out.println(treeMap.get(key));
        }
        System.out.println("");

        System.out.println(treeMap.containsKey("157-62-7935"));
        //以下两种均可以，会自动构造Employee对象！
        System.out.println(treeMap.containsValue("Cary Coope"));
        System.out.println(treeMap.containsValue(new Employee("Cary Coope")));
    }
}
