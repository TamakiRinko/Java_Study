import java.util.Scanner;

public class StackTraceTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        factorial(n);
    }

    public static int factorial(int n){
        System.out.println("factorial(" + n + "):");
        //打印堆栈轨迹元素
        Throwable t = new Throwable();
        StackTraceElement[] frames = t.getStackTrace();
        for(StackTraceElement stackTraceElement: frames){
            //打印出当前调用堆栈的情况，栈越来越高！
            //显示每次切换堆栈的位置！
            System.out.println(stackTraceElement);
        }
        int r;
        if(n <= 1)  r = 1;
        else{
            r = n * factorial(n - 1);
        }
        System.out.println("return " + r);
        return r;
    }

}
