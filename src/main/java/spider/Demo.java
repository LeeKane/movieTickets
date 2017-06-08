package spider;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


import java.io.IOException;

/**
 * Created by wangpan on 2017/6/8.
 */
public class Demo {
    public  boolean demo(String url){
        WebClient wc = new WebClient(BrowserVersion.FIREFOX_17);
        System.out.println(wc.getBrowserVersion());
        wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true
        wc.getOptions().setCssEnabled(false); //禁用css支持
        wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常
        wc.getOptions().setTimeout(10000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待
        HtmlPage page = null;
        try {
            page = wc.getPage(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String pageXml = page.asXml(); //以xml的形式获取响应文本
        System.out.println(pageXml);
        return false;
    }
    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.demo("https://mdianying.baidu.com/?page=movie");

    }
}
