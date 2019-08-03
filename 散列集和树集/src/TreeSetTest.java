import java.util.*;

public class TreeSetTest {
    //树集是一个有序集合，比散列集有所改进
    public static void main(String[] args) {
        SortedSet<Item> parts = new TreeSet<>();
        //想要加入TreeSet中，则必须可以比较，须实现Comparable接口！
        //CompareTo先按id，再按name排序
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Widget", 45622));
        parts.add(new Item("Modem", 9912));
//        Iterator<Item> iter = parts.iterator();
//        while(iter.hasNext()){
//            System.out.println(iter.next().getDescription());
//       }
        //依次调用每个元素的toString方法！
        System.out.println(parts);
        System.out.println("");

        //使用特定的比较器，Comparator静态方法，按name排序；或实现Comparator接口，使用比较器；或使用lambda表达式
//        NavigableSet<Item> sortByDescription = new TreeSet<>(Comparator.comparing(Item::getDescription));
//        NavigableSet<Item> sortByDescription = new TreeSet<>(new Item());
        NavigableSet<Item> sortByDescription = new TreeSet<>((o1, o2)->{return o1.getDescription().compareTo(o2.getDescription());});
        sortByDescription.addAll(parts);
        System.out.println(sortByDescription);
//        iter = sortByDescription.iterator();
//        while(iter.hasNext()){
//            System.out.println(iter.next().getId());
//        }
    }
}
