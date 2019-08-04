import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用LinkedHashMap实现LRU算法
 * @author 史文泰
 * @param <K>   键
 * @param <V>   值
 */
public class LRU<K, V> extends LinkedHashMap<K, V> {
    private static final long serialVersionUID = 1L;        //???

    public LRU(int initialCapacity, float loadFactor, boolean accessOrder){
        super(initialCapacity, loadFactor, accessOrder);
    }

    @Override
    /**
     * 重写removeEldestEntry方法，当个数大于5时removeEldestEntry()返回true，删去最不常用的元素
     */
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size() > 5;
    }

    public static void main(String[] args) {
        LRU<Character, Integer> lru = new LRU<Character, Integer>(
                16, 0.75f, true);

        String s = "abcdefghijkl";
        //最终剩余ghijkl
        for (int i = 0; i < s.length(); i++) {
            lru.put(s.charAt(i), i);
        }
        //最后一次访问的时h
        System.out.println("LRU中key为h的Entry的值为： " + lru.get('h'));
        System.out.println("LRU的大小 ：" + lru.size());
        System.out.println("LRU ：" + lru);
        System.out.println("");

        //使用匿名内部类实现
        anonymousClass();
    }

    public static void anonymousClass(){
        /**
         * 使用匿名内部类也可以！
         */
        Map<String, Employee> cache = new LinkedHashMap<String, Employee>(
                128, 0.75F, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Employee> eldest) {
                return this.size() > 2;
            }
        };

        cache.put("244-25-5464", new Employee("Amy Lee"));
        cache.put("567-24-2546", new Employee("Harry Hacker"));
        cache.put("456-62-5527", new Employee("Francesca Cruz"));
        cache.put("157-62-7935", new Employee("Cary Cooper"));
        cache.get("456-62-5527");
        System.out.println(cache);
    }

}
