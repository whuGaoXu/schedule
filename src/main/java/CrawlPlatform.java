
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class CrawlPlatform {
    private WebDriver driver;

    public void initDriver(){
        System.getProperties().setProperty("webdriver.chrome.driver","G:\\programmingTools\\chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    public String getPageSource(String url) throws InterruptedException {

        driver.get(url);
        String html = driver.getPageSource();
        return html;
    }

    public void releaseDriver(){
        this.driver.close();
        this.driver.quit();
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
        CrawlPlatform crawlPlatform = new CrawlPlatform();
        crawlPlatform.initDriver();
        String html = crawlPlatform.getPageSource("http://www.baidu.com");
        System.out.println(html);
        crawlPlatform.releaseDriver();
    }
}

