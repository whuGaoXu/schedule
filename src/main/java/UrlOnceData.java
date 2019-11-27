

public class UrlOnceData implements  Comparable<UrlOnceData> {



    private int id; // 用于记录只是第几次抓取的数据，初始值为-1
    private String url;
    private long fetchTime;
    private long refreshCycle; // 暂定以天为单位
    private String html;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(long fetchTime) {
        this.fetchTime = fetchTime;
    }

    public long getRefreshCycle() {
        return refreshCycle;
    }

    public void setRefreshCycle(long refreshCycle) {
        this.refreshCycle = refreshCycle;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public int compareTo(UrlOnceData urlOnceData) {
        int compareValue = 0;//后续这里可以扩展为点击数，即重要性
        if(compareValue==0){
            long fetchTimeDiff = this.getFetchTime() - urlOnceData.getFetchTime();
            if(fetchTimeDiff>0) {
                compareValue = 1;
            }else if(fetchTimeDiff==0){
                compareValue = 0;
            }else {
                compareValue = -1;
            }
        }
        return compareValue;
    }
}
