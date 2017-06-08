package spider;

/**
 * Created by wangpan on 2017/6/8.
 */
public class demo {
    public void demo(String url){
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true
        webClient.getOptions().setCssEnabled(false); //禁用css支持
        webClient.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常
        webClient.getOptions().setTimeout(20000);
        HtmlPage page = wc.getPage("http://www.hao123.com");
//我认为这个最重要
        String pageXml = page.asXml(); //以xml的形式获取响应文本

        /**jsoup解析文档*/
        Document doc = Jsoup.parse(pageXml, "http://cq.qq.com");
    }
}
