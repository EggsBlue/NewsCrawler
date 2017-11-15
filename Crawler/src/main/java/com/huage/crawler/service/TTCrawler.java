package com.huage.crawler.service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.huage.crawler.config.NewTypeConfig;
import com.huage.crawler.entity.New;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.IocContext;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@IocBean
public class TTCrawler {

    @Test
    public void httpTest(){
        Response response = Http.get("https://www.toutiao.com/internet");
        System.out.println(response.getContent());
    }

    @Test
    public void start() throws IOException, InterruptedException {
        WebClient webClient=new WebClient(BrowserVersion.CHROME); // 实例化Web客户端
//        webClient.setCurrentWindow(new WebWindowImpl(webClient));
//        webClient.addRequestHeader("Host","www.toutiao.com");
        webClient.getCookieManager().setCookiesEnabled(true);//开启cookie管理
        webClient.getOptions().setJavaScriptEnabled(true);//开启js解析。对于变态网页，这个是必须的
        webClient.getOptions().setCssEnabled(true);//开启css解析。对于变态网页，这个是必须的。
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(true);
        webClient.getOptions().setThrowExceptionOnScriptError(true);
        //添加header
        webClient.addRequestHeader("Upgrade-Insecure-Requests","1");
        webClient.addRequestHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        webClient.addRequestHeader("Accept-Encoding","gzip, deflate, sdch, br");
        webClient.addRequestHeader("Accept-Language","zh-CN,zh;q=0.8");
        webClient.addRequestHeader("Cache-Control","max-age=0");
        webClient.addRequestHeader("Connection","keep-alive");
        webClient.addRequestHeader("Referer","https://www.toutiao.com/news_tech/");

//        HtmlPage page = webClient.getPage("https://www.toutiao.com/internet");
        Set<Cookie> cookies=null;//
        List<org.apache.http.cookie.Cookie> cos = new ArrayList();
        org.apache.http.cookie.Cookie co1 = new BasicClientCookie("CNZZDATA125","580443930-1510618242-https%253A%252F%252Fwww.toutiao.com%252F%7C1510622468");
        org.apache.http.cookie.Cookie co2 = new BasicClientCookie("UM_distinctid","15fb82d56c38c2-03209f2d577bf7-6b1b1279-1fa400-15fb82d56c4650");
        org.apache.http.cookie.Cookie co3 = new BasicClientCookie("__tasessionId","t6sorpf921510623499529");
        org.apache.http.cookie.Cookie co4 = new BasicClientCookie("_ga","GA1.2.2002011413.1510623500");
        org.apache.http.cookie.Cookie co5 = new BasicClientCookie("_gid","GA1.2.1917807570.1510623500");
        org.apache.http.cookie.Cookie co6 = new BasicClientCookie("tt_webid","66604256662");
        org.apache.http.cookie.Cookie co7 = new BasicClientCookie("uuid","\"w:0f151eb7cd86498db8aa46b9cddb40c7\"");

        cos.add(co1);
        cos.add(co2);
        cos.add(co3);
        cos.add(co4);
        cos.add(co5);
        cos.add(co6);
        cos.add(co7);

        List<Cookie> cookies1 = Cookie.fromHttpClient(cos);
        CookieManager CM = webClient.getCookieManager(); //WC = Your WebClient's name
//        cookies = CM.getCookies();//返回的Cookie在这里，下次请求的时候可能可以用上啦。
        Iterator<Cookie> iter = cookies1.iterator();
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext())
        {
////            Cookie next = iter.next();
//////            sb.append(next.getName()+"="+next.getValue()+"; ");
////            System.out.println(next.getName()+":"+next.getValue());
////            if(next.getName().equalsIgnoreCase("cna") || next.getName().equalsIgnoreCase("sca") || next.getName().equalsIgnoreCase("atpsida")){
////                CM.removeCookie(next);
////            }
            webClient.getCookieManager().addCookie(iter.next());
        }

//        while (webClient.getCookieManager().getCookies().iterator().hasNext())
//        {
//            Cookie next = iter.next();
////            sb.append(next.getName()+"="+next.getValue()+"; ");
//            System.out.println(next.getName()+":"+next.getValue());
////            if(next.getName().equalsIgnoreCase("cna") || next.getName().equalsIgnoreCase("sca") || next.getName().equalsIgnoreCase("atpsida")){
////                CM.removeCookie(next);
////            }
////            webClient.getCookieManager().addCookie(iter.next());
//        }
//        webClient.addRequestHeader("Cookie",sb.toString());
        HtmlPage page = webClient.getPage("https://www.toutiao.com/internet");
//        Thread.sleep(3000);
//        System.out.println(page.asXml());
        int count = 0;
        while(true){
//            CM = webClient.getCookieManager(); //WC = Your WebClient's name
//            cookies = CM.getCookies();//返回的Cookie在这里，下次请求的时候可能可以用上啦。
//            Iterator<Cookie> iter2 = cookies.iterator();
////        StringBuilder sb = new StringBuilder();
//            while (iter2.hasNext())
//            {
////            Cookie next = iter.next();
//////            sb.append(next.getName()+"="+next.getValue()+"; ");
////            System.out.println(next.getName()+":"+next.getValue());
////            if(next.getName().equalsIgnoreCase("cna") || next.getName().equalsIgnoreCase("sca") || next.getName().equalsIgnoreCase("atpsida")){
////                CM.removeCookie(next);
////            }
//            webClient.getCookieManager().addCookie(iter2.next());
//            }

            boolean flag = false;
            for(int i=0; i<3; i++){
                Thread.sleep(2000);
                String s = page.asText();
                if(s.indexOf("推荐中")==-1){
                    flag = true;
                    break;
                }
                continue;
            }
            System.out.println(page.asXml());
            ScriptResult result;
            if(count == 0){
                result   = page.executeJavaScript("document.documentElement.scrollTop=1000;");
            }else{
                result  = page.executeJavaScript("document.documentElement.scrollTop=document.body.scrollHeight;");
            }

            page = (HtmlPage) result.getNewPage();

//            if(flag){
//                System.out.println(page.asXml());
//                ScriptResult result = page.executeJavaScript("document.documentElement.scrollTop=document.body.scrollHeight ;");
//                HtmlPage page2 = (HtmlPage) result.getNewPage();
//                System.out.println(page2.asXml());
//            }
        }
    }

    @Test
    public void ttTest() throws Exception {
        WebClient webClient = getClient();
//        HtmlPage page = webClient.getPage("https://www.toutiao.com/internet");
//        Thread.sleep(2000);
//        System.out.println(page.asXml());
//        HtmlPage page2 = webClient.getPage("https://www.toutiao.com/internet");
//        Thread.sleep(2000);
//        System.out.println(page2.asXml());

        while(true){
            HtmlPage page = webClient.getPage("https://www.toutiao.com/internet");
            boolean flag = false;
            String s="";
            for(int i=0; i<3; i++){
                Thread.sleep(2000);
                s = page.asXml();
                if(s.indexOf("推荐中")==-1){
                    flag = true;
                    break;
                }
                continue;
            }
            System.out.println(s);

            System.out.printf("==================================");
        }
    }

    @Inject
    private Dao dao;

    void save(New newes){
        dao.insert(newes);
    }

    public void run(){
        WebClient wc = getClient();
        while(true){
            HtmlPage page = null;
            try {
                page = wc.getPage("https://www.toutiao.com/internet");
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            boolean flag = false;
            String s = "";
            for(int i=0; i<3; i++){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                s = page.asXml();
                if(s.indexOf("推荐中")==-1){
                    flag = true;
                    break;
                }
                continue;
            }
//            System.out.println(s);
            //解析页面
            List<Object> lis =page.getByXPath("//div[@class='wcommonFeed']/ul/li");
            for(Object obj : lis){
                HtmlListItem item = (HtmlListItem)obj;
//                List<Object> byXPath = item.getByXPath(".div/div[@class='normal rbox']//a");
                String title = "";
                String sourceUrl = "";
                String imgUrl = "";
                List<HtmlElement> titleItems= item.getElementsByAttribute("a", "class", "link title");
                if(titleItems!=null && titleItems.size()>0){
                    HtmlElement titleItem =item.getElementsByAttribute("a","class","link title").get(0);//获取标题元素
                    title = titleItem.asText();
//                    List<HtmlElement> aItem = item.getElementsByAttribute("a", "class", "img-wrap");
//                    if(aItem!=null && aItem.size() > 0){
//                        HtmlElement ele = aItem.get(0);
//                        DomNodeList<HtmlElement> img = ele.getElementsByTagName("img");
//                        if(img!=null && img.size()>0){
//                            HtmlElement i = img.get(0);
//                            DomNodeList<HtmlElement> img1 = i.getElementsByTagName("img");
//                            if(img1!=null && img1)
//                        }
//                    }
                }
                List<HtmlElement> imgItems= item.getByXPath("./div/div/a/img");
                if(imgItems!=null && imgItems.size()>0){
                    sourceUrl = imgItems.get(0).asText();
                }
                New news = new New();
                news.setSource(NewTypeConfig.TT);
                news.setTitle(title);
//                news.setSouceUrl(NewTypeConfig.TTURL+detailUrl);
//                news.setCreate_time(new Timestamp(System.currentTimeMillis()));
////                System.out.println(item.asXml());
////                    System.out.println(title.asXml());
//                System.out.println(titleItem.asText());
            }
            System.out.printf("==================================");
        }
    }

    private WebClient getClient(){
        WebClient webClient=new WebClient(BrowserVersion.CHROME); // 实例化Web客户端
//        webClient.setCurrentWindow(new WebWindowImpl(webClient));
//        webClient.addRequestHeader("Host","www.toutiao.com");
        webClient.getCookieManager().setCookiesEnabled(true);//开启cookie管理
        webClient.getOptions().setJavaScriptEnabled(true);//开启js解析。对于变态网页，这个是必须的
        webClient.getOptions().setCssEnabled(true);//开启css解析。对于变态网页，这个是必须的。
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(true);
        webClient.getOptions().setThrowExceptionOnScriptError(true);
        return webClient;
    }


    public Dao getDao(){
        if(dao == null){
            Ioc ioc = Mvcs.getIoc();
            dao = ioc.get(Dao.class,"dao");
        }
        return dao;
    }


}
