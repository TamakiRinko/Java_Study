import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 在Try_catch中抛出异常，目的是改变异常的类型
 */
public class ThrowsInTryCatch {
    public static void ThorwsInTry(String filename) throws Throwable {
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
        }
        //将多个异常包装成高级异常，有时只需要知道是否出问题即可，不需要知道具体问题
        catch (FileNotFoundException e) {
            //用e来初始化se，用新的异常se来包装e，可抛出高级异常se
            Throwable se = new MyException();
            se.initCause(e);
            throw se;
        } catch (IOException e) {
            //用e来初始化se，用新的异常se来包装e，可抛出高级异常se
            Throwable se = new MyException();
            se.initCause(e);
            throw se;
        }
        // 5
        //结束Try_Catch，执行下面的代码
        //当Try_Catch出现问题时，只要catch和finally没有出现问题，
        //那么在捕获异常后，会执行该处结束后的代码！
        System.out.println("Out of try_catch!");
        System.out.println(result);
    }
}
