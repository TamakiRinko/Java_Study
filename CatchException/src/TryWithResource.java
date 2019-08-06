import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TryWithResource {
    /**
     * 不用关闭！try退出时，自动调用close，省去close时的异常处理
     * Java SE 7后可以使用
     * 前提：资源属于实现了AutoCloseable接口的类！
     * @param filepath  文件路径
     */
    public static void Try(String filepath) {
        //可放置多个！
        try(Scanner in = new Scanner(new FileInputStream(filepath), "UTF-8");
            PrintWriter out = new PrintWriter("out.txt")){
            while(in.hasNext()){
                String temp = in.next();
                System.out.println(temp);
                out.println(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getClass().getName());
        }
        System.out.println("Out of try_catch with resource!");
    }
}
