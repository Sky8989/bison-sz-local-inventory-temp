import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;

public class TestClass {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //新建一个文件
        File file = new File("log.txt");
        PrintStream stream = null;
        try {
            //创建文件的输出流
            stream = new PrintStream(file);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
           int a = 10/0;
        }
        catch (Exception e) {
            //将异常信息输出到指定的输出流中
            stream.print(new Date()+" ");
            e.printStackTrace(stream);
            stream.print("\n");

            //System.out.println("已保存错误信息到日志文件中");
        }
        //关闭输出流
        stream.flush();
        stream.close();

    }


}
