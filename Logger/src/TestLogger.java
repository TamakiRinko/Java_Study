import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestLogger {
    public static void main(String[] args) {
        BasicLogger();
    }

    public static void BasicLogger(){
//        //从此处开始取消接下来所有的日志，而非仅是取消这个函数中的日志
//        Logger.getGlobal().setLevel(Level.OFF);
        Logger.getGlobal().info("File->Open menu item selected.");
        String logger = "Hello, my name is Jack, I am ";
        logger += 20;
        Logger.getGlobal().info(logger);
    }
}
