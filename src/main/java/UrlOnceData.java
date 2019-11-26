public class UrlOnceData {



    private int id; // 用于记录只是第几次抓取的数据，初始值为-1
    private String url;
    private long fetchTime;
    private long refreshCycle; // 暂定以天为单位
    private byte[] html;


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

    public byte[] getHtml() {
        return html;
    }

    public void setHtml(byte[] html) {
        this.html = html;
    }



}
