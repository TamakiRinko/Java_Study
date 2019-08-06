import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class readTest1 {
    /**
     * 测试在try/catch/finally中的代码执行过程
     * @param filename  文件路径
     */
    public static void readtest(String filename){
        // in需要在try/catch外定义，否则无法使用！
        InputStream in = null;
        String result = "Hello!";
        try {
            // 1
            in = new FileInputStream(filename);     //可能造成exception的代码
            // 2
            byte[] flush = new byte[20];
            int readlen = -1;
            while((readlen = in.read(flush)) != -1){
                String temp = new String(flush);
                result = temp + result;
            }
        } catch (FileNotFoundException e) {
            // 3
            e.printStackTrace();
            System.out.println(e.getClass().getName());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getClass().getName());
        }
//        }finally {
//            // 4
//            try {
//                //关闭in也可能抛出异常！
//                in.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println("in has been closed!");
//        }

        // 5
        //结束Try_Catch，执行下面的代码
        //当Try_Catch出现问题时，只要catch和finally没有出现问题，
        //那么在捕获异常后，会执行该处结束后的代码！
        System.out.println("Out of try_catch!");
        System.out.println(result);
    }
}
