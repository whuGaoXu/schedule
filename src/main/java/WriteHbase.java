import java.util.List;

/**
 *
 * 将数据写入到 本地的Json文件，对应以后的就是写Hbase
 * */

public class WriteHbase {
    private static final String urlsHbasePath = "E:\\programmingAbility\\Java\\urls\\Hbase";

    public static void writeHbase( List<UrlOnceData> crawledUrls){
        for(UrlOnceData urlOnceData:crawledUrls){
            if(urlOnceData.getId()==1){ // 第一次爬取
                writeFirstCrawl(urlOnceData);
            }else{
                writeHasCralwedBefore(urlOnceData);
            }
        }
    }

    //直接写入一个新文件
    private static void writeFirstCrawl(UrlOnceData urlOnceData){

    }

    // 先读取，然后再写入到末尾
    private  static void writeHasCralwedBefore(UrlOnceData urlOnceData){

    }
}
