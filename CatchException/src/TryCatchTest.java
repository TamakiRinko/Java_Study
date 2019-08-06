import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TryCatchTest {
    public static void main(String[] args) {
        String name = "test.txt";
//        readTest1.readtest(name);
//        readTest2.readtest2(name);
        TryWithResource.Try(name);
    }

}
