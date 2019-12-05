import java.io.IOException;
import java.util.List;
import java.util.Map;

/***
 * 主流程
 *
 */


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<UrlOnceData> urlsTotal = Integrity.union();
        List<UrlOnceData> waitCrawlUrls = Schedule.select(urlsTotal);
        Map<String, String> urlAndHtmls = CrawlPlatform.getAllhtmls(waitCrawlUrls);
        UpdateCycle.updateCycle(urlAndHtmls, waitCrawlUrls); // 此步骤会将信息整合到waitCrawlUrls
        WriteHbase.writeHbase(waitCrawlUrls);
    }

}
