public class Employee {
    String name;

    public Employee(String name) {
        this.name = name;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
