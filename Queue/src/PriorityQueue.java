import java.time.LocalDate;

public class PriorityQueue {
    public static void main(String[] args) {
        //LocalDate实现了Comparable接口！
        java.util.PriorityQueue<LocalDate> pq = new java.util.PriorityQueue<>();
        pq.add(LocalDate.of(1906, 12, 9));
        pq.add(LocalDate.of(1815, 12, 10));
        pq.add(LocalDate.of(1903, 12, 3));
        pq.add(LocalDate.of(1910, 6, 22));

        System.out.println("Iterating over elements...");
        //遍历不按插入的顺序
        for(LocalDate date:pq){
            System.out.println(date);
        }
        System.out.println("Removing elements...");
        //每次删除最小的那个
        while(!pq.isEmpty()){
            System.out.println(pq.remove());
        }
    }
}
