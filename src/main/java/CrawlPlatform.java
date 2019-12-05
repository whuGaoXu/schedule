
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrawlPlatform {
    private static WebDriver driver;

    private static void initDriver(){
        System.getProperties().setProperty("webdriver.chrome.driver","G:\\programmingTools\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    private static String getPageSource(String url) throws InterruptedException {

        driver.get(url);
        return driver.getPageSource();
    }

    public static Map<String, String> getAllhtmls(List<UrlOnceData> waitCrawlUrls) throws InterruptedException {
        initDriver();
        Map<String, String> urlAndhtmls = new HashMap<String, String>();
        int index = 0;
        for(UrlOnceData urlOnceData : waitCrawlUrls){
            String url = urlOnceData.getUrl();
            System.out.println("crawl " + ++index + " : " + url);
            String html = getPageSource(url);
            urlAndhtmls.put(url, html);
        }
        releaseDriver();
        return urlAndhtmls;
    }

    private static void releaseDriver(){
        driver.close();
        driver.quit();
    }

    public void Fetch(String[] args){
        System.out.println("hello world");
    }
    public static void main(String[] args) throws InterruptedException {
//        System.getProperties().setProperty("webdriver.chrome.driver","G:\\programmingTools\\chromedriver.exe");
//        //开启webDriver进程
//        WebDriver webDriver = new ChromeDriver();
//        webDriver.get("http://www.baidu.com");
//        String html = webDriver.getPageSource();
//        System.out.println(html);
//        webDriver.close();
//        webDriver.quit();
//        CrawlPlatform crawlPlatform = new CrawlPlatform();
        CrawlPlatform.initDriver();
        String html = CrawlPlatform.getPageSource("http://sports.sohu.com");
        System.out.println(html);
        CrawlPlatform.releaseDriver();
    }
}

