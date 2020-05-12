package cn.com.evo.cms.webMagic;

import cn.com.evo.cms.utils.HttpUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author rf
 * @date 2020/5/10
 */
public class GithubRepoPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://mp.weixin.qq.com/s?src=11&timestamp=1589197156&ver=2332&signature=8OaY*ZWqMqjalyomc-rfSDbfh1SrF9Rffb4sIUVb94P-USAz0jZQw5vFiYaAq0bnf0VnA8vYntrmOGrYEVvdiOXLG3fl4ELU-084w4frUWwsV05h9WKV*uYX-hcCiTti&new=1)").all());
    //        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("content", page.getHtml().xpath("//h1[@class='rich_media_area_primary_inner']").toString());
        if (page.getResultItems().get("content")==null){
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
//        Spider.create(new GithubRepoPageProcessor()).addUrl("https://mp.weixin.qq.com/s")
//                .addPipeline(new JsonFilePipeline("E:\\logs\\"))
//                .thread(5).run();


        String s = HttpUtil.get("https://weixin.sogou.com/weixin?type=1&query=%E5%B0%8F%E9%85%92%E7%AA%9D&ie=utf8&s_from=input&_sug_=n&_sug_type_=1&w=01015002&oq=&ri=5&sourceid=sugg&sut=0&sst0=1589201717608&lkt=0%2C0%2C0&p=40040108", "");
        System.out.println(s);
    }
}
