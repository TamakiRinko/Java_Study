import java.time.temporal.Temporal;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList {
    public static void main(String[] args) {
//        List<String> staff = new java.util.LinkedList<>();
//        //add方法增加到尾部
//        staff.add("Army");
//        staff.add("Bob");
//        staff.add("Carl");
//        Iterator iter = staff.iterator();
//        String first = (String) iter.next();
//        String second = (String) iter.next();
//        iter.remove();
////        for(String str: staff){
////            System.out.println(str);
////        }
//
//        //迭代器描述集合中的位置关系，依赖位置的add方法交给迭代器负责
//        //ListIterator
//        staff.add("Nancy");
//        System.out.println(staff);
//        ListIterator<String> iter2 = staff.listIterator();
//        iter2.next();
//        iter2.add("David");//添加David，同时越过Davie
//        iter2.add("Mike");//添加Mike，同时越过Mike
//        System.out.println(staff.toString());
//        System.out.println(iter2.next());//Carl
//        //remove前须有next
//        iter2.remove();//删除的也是Carl
//        System.out.println(staff);
//
//        //改变第一个元素
//        iter2 = staff.listIterator();
//        String old = iter2.next();
//        iter2.set("Holy");
//        System.out.println(staff + " " + old);
//
//        System.out.println(staff.contains("Nancy"));

        test();
    }

    public static void test(){
        List<String> a = new java.util.LinkedList<>();
        a.add("Army");
        a.add("Bob");
        a.add("Carl");
        List<String> b = new java.util.LinkedList<>();
        b.add("Dog");
        b.add("Cat");
        b.add("Mice");
        b.add("Doug");

        ListIterator<String> aIter = a.listIterator();
        ListIterator<String> bIter = b.listIterator();

        while(bIter.hasNext()){
            if(aIter.hasNext()){
                aIter.next();
            }
            aIter.add(bIter.next());    //越过add的这个
        }
        System.out.println(a);
    }
}
