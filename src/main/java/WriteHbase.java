import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 *
 * 将数据写入到 本地的Json文件，对应以后的就是写Hbase
 * */

public class WriteHbase {
    private static final String urlsHbasePath = "E:\\programmingAbility\\Java\\urls\\Hbase";

    public static void writeHbase( List<UrlOnceData> crawledUrls) throws JsonProcessingException {
        for(UrlOnceData urlOnceData:crawledUrls){
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(urlOnceData);
            try {
                String filePath = Tool.generateFilePath("E:\\programmingAbility\\Java\\urls\\Hbase\\", urlOnceData.getUrl());
                FileOutputStream fos = new FileOutputStream(filePath);
                fos.write(json.getBytes());
                fos.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException {
        UrlOnceData urlOnceData = new UrlOnceData();
        urlOnceData.setId(1);
        urlOnceData.setUrl("test");
        urlOnceData.setFetchTime(0);
        urlOnceData.setRefreshCycle(1);
        urlOnceData.setHtml("test");

        /**
         * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。
         */
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(urlOnceData);
        System.out.println(json);

        try {
            FileOutputStream fos = new FileOutputStream("E:\\programmingAbility\\Java\\urls\\Hbase\\"+urlOnceData.getUrl());
            fos.write(json.getBytes());
            fos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        UrlOnceData test = mapper.readValue(new File("E:\\programmingAbility\\Java\\urls\\Hbase\\test"), UrlOnceData.class);
        System.out.println(test.getId());
        System.out.println(test.getUrl());
        System.out.println(test.getFetchTime());
        System.out.println(test.getRefreshCycle());
        System.out.println(test.getHtml());
    }
}
