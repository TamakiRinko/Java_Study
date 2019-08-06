import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class readTest2 {
    /**
     * try/catch 和 try/finally 耦合使用！
     * 统一try和finally里的异常到大的try/catch中
     * @param filename  文件名
     */
    public static void readtest2(String filename){
        // in需要在try/catch外定义，否则无法使用！
        InputStream in = null;
        String result = "Hello!";
        try {
            try {
                // 1
                in = new FileInputStream(filename);     //可能造成exception的代码
                // 2
                byte[] flush = new byte[20];
                int readlen = -1;
                while ((readlen = in.read(flush)) != -1) {
                    String temp = new String(flush);
                    result = temp + result;
                }
            }
//            finally {
//                //finally中不关闭，则捕获IOException异常
//            }
            finally {
                //3
                //finally中关闭，则捕获NullPointerException异常
                in.close();
                System.out.println("in has been closed!");
            }
        } catch (NullPointerException e){
            // 4
            //NullPointerException 继承自 Runtime Exception，
            //若不捕获，则出现在catch中未捕获代码，立刻回抛给代码调用者，不执行 5 处代码
            e.printStackTrace();
            System.out.println(e.getClass().getName());
        } catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getClass().getName());
        }
        // 5
        //上面Try_Catch出现问题，但是只要外层catch没有出现问题，
        //内部try/finally无论哪个出现问题都会执行 5 处代码！
        System.out.println("Out of try_catch!");
        System.out.println(result);
    }
}
