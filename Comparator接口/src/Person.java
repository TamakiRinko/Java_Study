import java.util.Comparator;

public class Person implements Comparator<Person> {
    private String name;

    public Person(){}
    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    //按名称长度排序，实现Comparator接口，静态方法不用重写
    public int compare(Person o1, Person o2) {
        return o1.getName().length() - o2.getName().length();
    }
}
