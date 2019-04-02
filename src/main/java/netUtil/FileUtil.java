package netUtil;

import java.io.File;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author poorguy
 * @version 0.0.1
 * @E-mail 494939649@qq.com
 * @created 2019/4/2 10:55
 */
public class FileUtil {
    public static void main(String[] args){
        List<String> files =getAllFilePathIterate (new File("C:\\test"));
        Stream.of(files).forEach(System.out::println);
    }
    //获取文件夹及其子文件夹下所有文件绝对路径
    public static List<String> getAllFilePathIterate(File dir) {
        if(!dir.isDirectory()){
            return null;
        }
        List<String> fileList = new ArrayList<>();
        Stack<File> dirStack = new Stack<>();
        dirStack.push(dir);

        File tempFile;
        while (!dirStack.isEmpty()) {
            tempFile = dirStack.pop();
            File[] files = tempFile.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    dirStack.push(file);
                } else {
                    fileList.add(file.getAbsolutePath());
                }
            }
        }
        return fileList;
    }
}
