import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, FileFormatException {
        File file = new File("testException.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String result = readData(br, 100);
        System.out.println(result);
    }

    public static String readData(BufferedReader in, int leastLength) throws IOException, FileFormatException {
        int n = 0;
        char[] ch = new char[10];   //每次读10个
        int readLen = 0;
        String result = new String("");
        while(true){
            readLen = in.read(ch, 0, 10);
            if(readLen == -1){
                if(n < leastLength){
                    //未达到最低读取字节数，抛出
                    throw new FileFormatException("\n长度未达标，读取到的内容为：\n" + result);
                }
                else{
                    break;
                }
            }
            result = result + String.valueOf(ch);
            n += readLen;
        }
        return result;
    }
}
