package com.example.lsj.mvp.reptile;

import android.util.Log;

import com.example.lsj.mvp.api.Api;
import com.example.lsj.mvp.bean.AppreciationBean;
import com.example.lsj.mvp.bean.AuthorBean;
import com.example.lsj.mvp.bean.CommentBean;
import com.example.lsj.mvp.bean.PoetryBean;
import com.example.lsj.mvp.bean.PoetryWorksBean;
import com.example.lsj.mvp.bean.VerseBean;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReptileTest {

    private final static String TAG = "ReptileTest";

    public static List<PoetryWorksBean> getPoetryItem(final String value){
        List<PoetryWorksBean> reptileItems = new ArrayList<>();
        Document doc = getDocument(value);
        Elements els = doc.select("div.cont");
        for (Element el : els){
            PoetryWorksBean reptileItem = new PoetryWorksBean();
            Elements p = el.select("p");
            if (p.size() < 2){
                continue;
            }
            String title = p.first().text();


            String url = p.get(0).getElementsByTag("a").attr("href");

            String author = p.get(1).getElementsByTag("a").get(0).text();

            String dynasty = p.get(1).getElementsByTag("a").get(1).text();

            String text = el.getElementsByClass("contson").first().text();
            String v = text.replace("？", "。");
            String ve = v.replace("！", "。");
            String verse = ve.split("。")[0];

            reptileItem.setName(title);
            reptileItem.setAuthorName(author);
            reptileItem.setDynasty(dynasty);
            reptileItem.setUrl(url);
            reptileItem.setVerse(verse+"。");
            reptileItems.add(reptileItem);
        }
        return reptileItems;
    }

    public static String getAuthorSummary(String url){
//        Log.e(TAG, "getAuthorSummary: "+ Api.GUSHIWEN +url);
        Document doc = null;
        try {
            Connection con = Jsoup
                    .connect(Api.GUSHIWEN +url);// 获取连接
            con.header("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");// 配置模拟浏览器
            Connection.Response rs = con.execute();// 获取响应
            doc = Jsoup.parse(rs.body());// 转换为Dom树
//            doc = Jsoup.connect( Api.GUSHIWEN +url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert doc != null;
        String summary = doc.getElementsByClass("main3").first().getElementsByClass("cont").get(0).getElementsByTag("p").first().text();
//        Log.e(TAG, "run-summary: " + summary);
        return summary;
    }

    public static PoetryBean getPoetry2(String url) throws IOException {
        PoetryBean poetry;
        Log.e(TAG, "getPoetry2: "+ Api.GUSHIWEN +url);
        Document poetryDoc = null;
        try {
            Connection con = Jsoup
                    .connect(Api.GUSHIWEN +url);// 获取连接
            con.header("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");// 配置模拟浏览器
            Connection.Response rs = con.execute();// 获取响应
            poetryDoc = Jsoup.parse(rs.body());// 转换为Dom树
//            poetryDoc = Jsoup.connect(Api.GUSHIWEN+url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert poetryDoc != null;
        Element element = poetryDoc.select("div.main3").first().selectFirst("div.left");
        Element cont = element.selectFirst("div.sons").selectFirst("div.cont");
        if (cont == null){
            return null;
        }

        Elements yizhu = cont.selectFirst("div.yizhu").select("img");
        if (yizhu == null && yizhu.size() < 2){
            return getPoetryVerses(url);
        }

        String id = yizhu.get(1).attr("onclick").split("\'")[1];
        poetry = getPoetryVerses(id, "yizhushang");
        if (poetry.getVerses().size()<2){
            return getPoetryVerses(url);
        }

        Element poetryElt = poetryDoc.getElementsByClass("sons").first();

        String title = cont.getElementsByTag("h1").first().text();
//        Log.e(TAG, "run-title2: " + title);
        poetry.setName(title);

        String dynasty = cont.getElementsByTag("p").get(0).getElementsByTag("a").get(0).text();
//        Log.e(TAG, "run-dynasty2: " + dynasty);
        dynasty = dynasty.replace("代", "");
        poetry.setDynasty(dynasty);
        poetry.getAuthor().setDynasty(dynasty);

        String authorName = cont.getElementsByTag("p").get(0).getElementsByTag("a").get(1).text();
//        Log.e(TAG, "run-author2: " + authorName);
        poetry.setAuthorName(authorName);
        poetry.getAuthor().setName(authorName);

        String summaryUrl =cont.getElementsByTag("p").get(0).getElementsByTag("a").get(1).attr("href");
        poetry.getAuthor().setSummary(getAuthorSummary(summaryUrl));

        Elements tagElements = poetryElt.getElementsByClass("tag");
        if (tagElements!=null && tagElements.size()>0){
            Elements tag = tagElements.first().getElementsByTag("a");
            for (int i = 0; i < tag.size() && i < 3; i++) {
                poetry.getLabels().add(tag.get(i).text());
            }
        }

        return poetry;
    }

    private static PoetryBean getPoetryVerses(String id, String value){
        PoetryBean poetry = new PoetryBean();
        AuthorBean author = new AuthorBean();
        List<VerseBean> verses = new ArrayList<>();
        List<CommentBean> commentBeans = new ArrayList<>();
        List<AppreciationBean> appreciations = new ArrayList<>();
        List<String> tags = new ArrayList<String>();
        poetry.setAuthor(author);
        poetry.setVerses(verses);
        poetry.setLabels(tags);
        poetry.setComments(commentBeans);
        poetry.setAppreciations(appreciations);

        Document doc = null;
        try {
//            Connection con = Jsoup
//                    .connect("https://so.gushiwen.cn/nocdn/ajaxshiwencont.aspx?id="+id+"&value="+value);// 获取连接
//            con.header("User-Agent",
//                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");// 配置模拟浏览器
//            Connection.Response rs = con.execute();// 获取响应
//            doc = Jsoup.parse(rs.body());// 转换为Dom树
            doc = Jsoup.connect("https://so.gushiwen.cn/nocdn/ajaxshiwencont.aspx?id="+id+"&value="+value).cookie("login", "false").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (doc == null){
            return poetry;
        }

        String docString = doc.toString().replace("<br>", "&&");
//        Log.e(TAG, "getPoetry-1: " + docString);
        String pattern = "\\(.*?\\)";
        docString = docString.replaceAll(pattern, "");
//        Log.e(TAG, "getPoetry-2: " + docString);
        doc = Jsoup.parseBodyFragment(docString);

        Elements ps = doc.select("p");
        for (int i = 0; i < ps.size()-2; i++) {
            List<CommentBean> comments = new ArrayList<>();
            String[] string = ps.get(i).text().split("&&");
//            Log.e(TAG, "getPoetry-str: " + Arrays.toString(string));

            if (string.length == 1){
                AppreciationBean a = new AppreciationBean();
                a.setText(string[0]);
                a.setSeries(String.format("%02d", i));
                appreciations.add(a);
            }
            else if (string.length>1){
                VerseBean v = new VerseBean();
                v.setText(string[0]);
                v.setTranslation(string[1]);
                v.setSeries(String.format("%02d", i));
                if (string.length > 2){
                    String[] cts = string[2].split("。");
                    for (int j = 0; j < cts.length; j++) {
                        if (!cts[j].contains("：")){
                            continue;
                        }
                        CommentBean comment = new CommentBean();
                        String[] ct = cts[j].split("：");
                        comment.setField(ct[0]);
                        comment.setText(ct[1]+"。");
                        comment.setSeries(String.format("%02d", j));
                        comments.add(comment);
                        commentBeans.add(comment);
                    }
                }
                v.setComments(comments);
                verses.add(v);
            }
        }
        return poetry;
    }

    public static PoetryBean getPoetryVerses(String url){
        PoetryBean poetry = new PoetryBean();
        AuthorBean author = new AuthorBean();
        List<VerseBean> verses = new ArrayList<>();
        List<CommentBean> comments = new ArrayList<>();
        List<AppreciationBean> appreciations = new ArrayList<>();
        List<String> tags = new ArrayList<String>();
        poetry.setAuthor(author);
        poetry.setVerses(verses);
        poetry.setComments(comments);
        poetry.setAppreciations(appreciations);
        poetry.setLabels(tags);

        Document poetryDoc = null;
        try {
            poetryDoc = Jsoup.connect(Api.GUSHIWEN+url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert poetryDoc != null;
        Element element = poetryDoc.select("div.main3").first().selectFirst("div.left");
        Element cont = element.selectFirst("div.sons").selectFirst("div.cont");
        if (cont == null){
            return null;
        }

        Element poetryElt = poetryDoc.getElementsByClass("sons").first();

        String title = cont.getElementsByTag("h1").first().text();
//        Log.e(TAG, "run-title2: " + title);
        poetry.setName(title);

        String dynasty = cont.getElementsByTag("p").get(0).getElementsByTag("a").get(0).text();
//        Log.e(TAG, "run-dynasty2: " + dynasty);
        poetry.setDynasty(dynasty);
        author.setDynasty(dynasty);

        String authorName = cont.getElementsByTag("p").get(0).getElementsByTag("a").get(1).text();
//        Log.e(TAG, "run-author2: " + authorName);
        poetry.setAuthorName(authorName);
        author.setName(authorName);

        String summaryUrl =cont.getElementsByTag("p").get(0).getElementsByTag("a").get(1).attr("href");
        author.setSummary(getAuthorSummary(summaryUrl));

        String text = cont.selectFirst("div.contson").text();
        String v = text.replace("？", "。");
        String ve = v.replace("！", "。");
        String[] verse = ve.split("。");

//        Log.e(TAG, "run-text2: " + text);

        Elements tagElements = poetryElt.getElementsByClass("tag");
        if (tagElements!=null && tagElements.size()>0){
            Elements tag = tagElements.first().getElementsByTag("a");
            for (int i = 0; i < tag.size() && i < 3; i++) {
                tags.add(tag.get(i).text());
            }
        }
//        Log.e(TAG, "run-tags: " + tags.toString());

        String id = "";
        Elements contyishang = poetryDoc.getElementsByClass("sons").get(1)
                .getElementsByClass("contyishang");
        if (contyishang != null && comments.size()>0){
            id = contyishang.get(0)
                    .getElementsByTag("div").get(1).attr("onclick")
                    .split("\'")[1];
        }
//        Log.e(TAG, "run-id: " + id);

        Document document = null;
        try {
            document = Jsoup.connect("https://so.gushiwen.cn/nocdn/ajaxfanyi.aspx?id="+id).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert document != null;
//        Log.e(TAG, "getPoetry: "+document.toString());

        Elements fys = document.getElementsByClass("contyishang");
        if (fys == null || fys.size()==0){
            for (int i = 0; i < verse.length; i++) {
                VerseBean verseBean = new VerseBean();
                verseBean.setText(verse[i]+"。");
                verses.add(verseBean);
            }
        }
        else if (fys != null && fys.size()>0){
            String fanyi = fys.first().getElementsByTag("p").get(0).text();
//            Log.e(TAG, "run-fanyi: " + fanyi);
            String f = fanyi.replace("？", "。");
            String fa = f.replace("！", "。");
            String[] fanyis = fa.split("。");
            for (int i = 0; i < verse.length && i < fanyis.length; i++) {
                VerseBean verseBean = new VerseBean();
                verseBean.setText(verse[i]+"。");
                verseBean.setTranslation(fanyis[i]+"。");
                verses.add(verseBean);
            }

            String zhushi = fys.first().getElementsByTag("p").get(1).text();
//            Log.e(TAG, "run-zhushi: " + zhushi);
            String[] zhushis = zhushi.split("。");
            for (int i = 0; i < zhushis.length; i++) {
                if (!zhushis[i].contains("：")){
                    continue;
                }
                String[] zs = zhushis[i].split("：");
                CommentBean comment = new CommentBean();
                comment.setField(zs[0]);
                comment.setText(zs[1]+"。");
                comments.add(comment);
            }
        }

        return poetry;
    }

    private static Document getDocument(String value){
        Document doc = null;
        try {
            doc = Jsoup.connect(Api.GUSHIWEN+"search.aspx/")
                    .data("value", value)
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static Document getHtml(String url, String value, String key){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Log.e(TAG, "getHtml: " + doc.toString());
        return doc;
    }
}
