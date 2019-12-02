import com.fasterxml.jackson.databind.ObjectMapper;

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

    private static final long ONEDAY = 86400000;
    private static final String urlsCollectionPath = "E:\\programmingAbility\\Java\\urls\\urlsCollection.txt";
    private static final String urlsHbasePath = "E:\\programmingAbility\\Java\\urls\\Hbase";

    private static List<UrlOnceData> readUrlsCollection(String path) throws IOException {
        List<UrlOnceData>  urlsCollectionDao= new ArrayList<UrlOnceData>();
        List<String> lines = Tool.readLines(path);
        for(String line:lines){
            if(line.isEmpty()){
                continue;
            }
            UrlOnceData urlOnceData = new UrlOnceData();
            urlOnceData.setId(0);
            if(line.contains("http://")){
                urlOnceData.setUrl(line);
            }else{
                urlOnceData.setUrl("http://" + line);
            }
            urlOnceData.setRefreshCycle(1*ONEDAY);
            urlOnceData.setFetchTime(0);
            urlsCollectionDao.add(urlOnceData);
        }
        return urlsCollectionDao;
    }

    private static List<UrlOnceData> readHbase(String path) throws IOException {
        List<UrlOnceData>  urlsHbaseDao= new ArrayList<UrlOnceData>();
        List<String> paths = Tool.listFiles(path);
        if(paths.isEmpty() || paths==null){
            return urlsHbaseDao;
        }else {
            //读取最后一次的时间解析
            for(String filepath:paths){
                ObjectMapper mapper = new ObjectMapper();
                UrlOnceData tmp = mapper.readValue(new File(filepath), UrlOnceData.class);
                urlsHbaseDao.add(tmp);
            }
        }
        return urlsHbaseDao;
    }

    /**
     * 求两者的并集，同时存在的以库里面的为主
     * */
    public static List<UrlOnceData> union() throws IOException {
        List<UrlOnceData>  urlsUnionDao= new ArrayList<UrlOnceData>();
        List<UrlOnceData>  urlsCollectionDao = readUrlsCollection(urlsCollectionPath);
        List<UrlOnceData>  urlsHbaseDao = readHbase(urlsHbasePath);
        if(urlsHbaseDao.isEmpty() || urlsHbaseDao==null){
            return urlsCollectionDao;
        }

        for(UrlOnceData urlOnceData: urlsHbaseDao){
            urlsUnionDao.add(urlOnceData);
        }
        for(UrlOnceData urlOnceDataCollection :urlsCollectionDao){
            boolean exist = false;
            for(UrlOnceData urlOnceDataHbase : urlsHbaseDao){
                if(urlOnceDataCollection.getUrl().equals(urlOnceDataHbase.getUrl())){
                    System.out.println(urlOnceDataCollection.getUrl());
                    exist = true;
                    break;
                }
            }
            if(!exist){
                System.out.println("test");
                urlsUnionDao.add(urlOnceDataCollection);
            }
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
