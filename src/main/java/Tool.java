import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 用于提供支撑服务
 *
 * */

public class Tool {

    public static List<String> listFiles(String path){

        List<String> filePaths = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        assert tempList != null;
        for (File value : tempList) {
            if (value.isFile()) {
                filePaths.add(value.toString());
            }
            else {
                value.isDirectory();//todo
            }
        }
        return filePaths;
    }

    public static List<String> readFile(String path) throws IOException {

        File file = new File(path);
        assert file.exists();
        assert !file.isDirectory();
        String encoding = "utf-8";
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineText = null;
        List<String> lines = new ArrayList<String>();
        while ((lineText = bufferedReader.readLine()) != null)
        {
            lines.add(lineText);
        }
        bufferedReader.close();
        read.close();
        return lines;
    }

    public static void main(String[] args) {

        List<String> filePaths = Tool.listFiles("E:\\programmingAbility\\Java\\urls");
        for(String filepath:filePaths){
            System.out.println(filepath);
        }
    }
}
