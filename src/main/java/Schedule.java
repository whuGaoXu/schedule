import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
/**
 * 1.新的url
 * 2.时间到了的
 * 3.重要性排序
 * 4.根据爬虫能力进行选择
 * 选取逻辑：
 * 第一步：设置爬取能力，即每天可以爬多少数据量
 * 第二步：设置比例分成，哪个不够，另外一个则可以补齐
 * 第三步：新旧分别选取
 * */

public class Schedule {


    private static int crawlAbility = 1000; // 每天的爬取能力，也就是24h的能够爬取多少数据
    private static double newProportion = 0.5; // 默认新的占比，
    private static double oldProportion = 0.5;// 旧的占比

    private static void init(int _crawlAbility, double _newProportion){
        crawlAbility = _crawlAbility;
        newProportion = _newProportion;
        oldProportion = 1 - newProportion;
    }


    private static List<UrlOnceData> getNewUrls(List<UrlOnceData> urlsOnceData){
        List<UrlOnceData> newUrls = new ArrayList<UrlOnceData>();
        for(UrlOnceData tmp : urlsOnceData){
            if(tmp.getId()==0){
                newUrls.add(tmp);
            }
        }
        return newUrls;
    }

    private static List<UrlOnceData> getOldUrls(List<UrlOnceData> urlsOnceData){
        List<UrlOnceData> oldUrls = new ArrayList<UrlOnceData>();
        for(UrlOnceData tmp : urlsOnceData){
            if(tmp.getId()!=0){
                oldUrls.add(tmp);
            }
        }
        Collections.sort(oldUrls);
        return oldUrls;
    }


    private static List<UrlOnceData> getWaitCrawlUrls(List<UrlOnceData> oldUrls, List<UrlOnceData> newUrls){

        List<UrlOnceData> waitCrawlUrls = new ArrayList<UrlOnceData>();

        int newUrlsSize = 0;
        int oldUrlsSize = 0;
        int newUrlsCrawlAbility = (int) (crawlAbility*newProportion);
        int oldUrlsCrawlAbility = (int) (crawlAbility*oldProportion);
        if(newUrls.size()>=newUrlsCrawlAbility && oldUrls.size()>=oldUrlsCrawlAbility){
            newUrlsSize = newUrlsCrawlAbility;
            oldUrlsSize = oldUrlsCrawlAbility;
        }else if(newUrls.size()<newUrlsCrawlAbility && oldUrls.size()>=oldUrlsCrawlAbility){
            newUrlsSize = newUrlsCrawlAbility;
            oldUrlsSize = oldUrlsCrawlAbility + newUrlsCrawlAbility - newUrls.size();
        }else if(newUrls.size()>=newUrlsCrawlAbility && oldUrls.size()<oldUrlsCrawlAbility){
            newUrlsSize = newUrlsCrawlAbility + oldUrlsCrawlAbility - oldUrls.size();
            oldUrlsSize = oldUrlsCrawlAbility;
        }else{
            newUrlsSize = newUrlsCrawlAbility;
            oldUrlsSize = oldUrlsCrawlAbility;
        }

        for(int i=0; i<newUrlsSize && i<newUrls.size(); ++i){
            waitCrawlUrls.add(newUrls.get(i));
        }

        for(int i=0; i<oldUrlsSize && i<oldUrls.size(); ++i){
            waitCrawlUrls.add(oldUrls.get(i));
        }

        return waitCrawlUrls;
    }


    public static List<UrlOnceData> select(List<UrlOnceData> urlsTotal){
        init(1000, 0.5);
        List<UrlOnceData> oldUrls = getOldUrls(urlsTotal);
        List<UrlOnceData> newUrls = getNewUrls(urlsTotal);
        List<UrlOnceData> waitCrawlUrls = getWaitCrawlUrls(oldUrls, newUrls);
        return waitCrawlUrls;
    }



}

