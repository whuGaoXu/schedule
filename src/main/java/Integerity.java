import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 用于整合urlsCollection， 和库里面的url(库里面只读最后一次的记录)
 *
 * */

public class Integerity {

    private final long ONEDAY = 86400000;
    private final String urlsCollectionPath = "E:\\programmingAbility\\Java\\urls\\urlsCollection.txt";
    private final String urlsHbasePath = "E:\\programmingAbility\\Java\\urls\\Hbase";

    private List<UrlOnceData> readUrlsCollection(String path) throws IOException {
        List<UrlOnceData>  urlsCollectionDao= new ArrayList<UrlOnceData>();
        List<String> lines = Tool.readLines(path);
        for(String line:lines){
            UrlOnceData urlOnceData = new UrlOnceData();
            urlOnceData.setId(-1);
            urlOnceData.setUrl(line);
            urlOnceData.setRefreshCycle(1*ONEDAY);
            urlOnceData.setFetchTime(0);
            urlsCollectionDao.add(urlOnceData);
        }
        return urlsCollectionDao;
    }

    private List<UrlOnceData> readHbase(String path){
        List<UrlOnceData>  urlsHbaseDao= new ArrayList<UrlOnceData>();
        List<String> paths = Tool.listFiles(path);
        if(paths.isEmpty() || paths==null){
            return urlsHbaseDao;
        }else {
            //todo
            //读取最后一次的时间解析
        }
        return urlsHbaseDao;
    }

    /**
     * 求两者的并集，同时存在的以库里面的为主
     * */
    public List<UrlOnceData> union() throws IOException {
        List<UrlOnceData>  urlsUnionDao= new ArrayList<UrlOnceData>();
        List<UrlOnceData>  urlsCollectionDao = readUrlsCollection(urlsCollectionPath);
        List<UrlOnceData>  urlsHbaseDao = readHbase(urlsHbasePath);
        if(urlsHbaseDao.isEmpty() || urlsHbaseDao==null){
            return urlsCollectionDao;
        }else {
            //todo
            // 将两者取并集
        }
        return urlsUnionDao;

    }

    public static void main(String[] args) throws IOException {
//        Calendar calendar = Calendar.getInstance();
//        Date time = calendar.getTime();
//        System.out.println(time);
//        long timeInMillis = calendar.getTimeInMillis();
//        System.out.println(timeInMillis);
//        calendar.add(Calendar.DAY_OF_MONTH,1);
//        long tomorrow = calendar.getTimeInMillis();
//        System.out.println(tomorrow-timeInMillis);
        Integerity integerity = new Integerity();
        List<UrlOnceData> urlsCollection = integerity.union();
        for(UrlOnceData urlOnceData:urlsCollection){
            System.out.println(urlOnceData.getId());
            System.out.println(urlOnceData.getUrl());
            System.out.println(urlOnceData.getFetchTime());
            System.out.println(urlOnceData.getRefreshCycle());
            System.out.println();
        }
    }
}
