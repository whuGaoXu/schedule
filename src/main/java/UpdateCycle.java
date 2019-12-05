import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * 根据相似度以及上一次的更新周期，来确定下一次的更新周期
 * */

public class UpdateCycle {

    private static final double CriticalValue = 0.95; // 相似度的临界值，>=95%相似，即为一样
    private static final long ONE_DAY = 86400000;
    private static final long ONE_HOUR = 3600000;
    public static void updateCycle(Map<String, String> urlAndhtmls, List<UrlOnceData> crawledUrls) throws UnsupportedEncodingException {
        for(UrlOnceData urlOnceData : crawledUrls){
            int id = urlOnceData.getId();
            String url = urlOnceData.getUrl();
            String newHtml = urlAndhtmls.get(url);
            if(id==0){ // 这里可以进行重构
                urlOnceData.setId(1);
            }else{
                double similar = CosineSimilar.getSimilarity(newHtml, urlOnceData.getHtml());
                System.out.println(url + " 　similar : " + similar);
                long newCycle = getNewCycle(similar, urlOnceData.getRefreshCycle());
                if(newCycle != urlOnceData.getRefreshCycle()){
                    System.out.println("update cycle" + urlOnceData.getUrl() + " " + urlOnceData.getRefreshCycle() + " -> " + newCycle);
                }
                urlOnceData.setId(++id);
                urlOnceData.setRefreshCycle(newCycle);
            }

            urlOnceData.setFetchTime(Tool.getCurTime());
            urlOnceData.setHtml(newHtml);
        }

    }

    // 后续可以将其扩展
    // todo
    private static long  getNewCycle(double similar, long oldCycle){
        if(similar < CriticalValue){ // 不一样
            if(oldCycle>ONE_DAY){
                return oldCycle/2;
            }else{
                return oldCycle>ONE_HOUR? oldCycle-ONE_HOUR : ONE_HOUR;
            }
        }else{
            if(oldCycle<64*ONE_DAY){
                return oldCycle*2;
            }else{
                return oldCycle+ONE_DAY;
            }
        }
    }

}
