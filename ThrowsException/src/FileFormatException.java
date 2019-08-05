import java.io.IOException;

public class FileFormatException extends IOException {
    /**
     * 两个构造器，一个默认构造器，一个带有详细描述信息的构造器
     * 抛出异常时超类Throwable的toString方法会打印上述详细信息，调试方便
     * 通过下面的getMessage方法获得传入的详细信息
     */
    public FileFormatException(){}
    public FileFormatException(String gripe){
        super(gripe);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
