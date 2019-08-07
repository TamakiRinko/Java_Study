import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TryCatchTest {
    public static void main(String[] args) {
        String name = "test1.txt";
//        readTest1.readtest(name);
//        readTest2.readtest2(name);
//        TryWithResource.Try(name);

        try {
            ThrowsInTryCatch.ThorwsInTry(name);
        } catch (Throwable throwable) {
            //调用者可以获得高级异常，而不用知道底层到底是什么异常
            //也可以从高级异常中获得原始异常
            Throwable t = throwable.getCause();
            if(t != null){
                System.out.println("In TryCatchTest's Try/Catch!");
                t.printStackTrace();
                System.out.println(t.getClass().getName());
            }
        }

    }

}
