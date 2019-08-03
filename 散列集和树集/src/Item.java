import java.util.Comparator;
import java.util.Objects;

public class Item implements Comparable<Item>, Comparator<Item> {
    //Item要与Item进行比较！！不可为Comparable<String>
    private String description;
    private int id;

    public Item() {
    }

    public Item(String name, int id) {
        this.description = name;
        this.id = id;
    }

    public String getDescription(){
        return this.description;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "[Description = " + description + ", Id = " + id + "]";
    }

    @Override
    /**
     * 记住方式！直接调用！
     */
    public int hashCode() {
        return Objects.hash(description, id);
    }

    @Override
    public int compare(Item o1, Item o2) {
        return o1.getDescription().compareTo(o2.getDescription());
    }

    @Override
    /**
     * 记住方式！！
     */
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Item other = (Item)obj;
        return (Objects.equals(description, other.description) && id == other.id);
    }

    @Override
    public int compareTo(Item o) {
        //先id后name
        int diff = Integer.compare(id, o.id);
        return diff != 0 ? diff : this.description.compareTo(o.getDescription());
    }
}
