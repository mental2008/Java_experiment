import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import javax.swing.text.Document;
import javax.swing.text.Element;
import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 47678 on 2017/5/26.
 */
public class Reptile {

    public static void printSource(org.jsoup.nodes.Document doc) throws Exception {

        File file = new File("sourcecode.txt");

        PrintWriter printWriter = new PrintWriter(new FileWriter(file), true);

        printWriter.println(doc.body());

    }

    public static void printContent(org.jsoup.nodes.Document doc) throws Exception {

        File file = new File("content.txt");

        PrintWriter printWriter = new PrintWriter(new FileWriter(file), true);

//        Elements links = doc.select("a");

        Elements ListDiv = doc.getElementsByAttributeValue("class","scroll_column_bd");
        printWriter.println("新闻各有态度");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mod_top_news2");
        printWriter.println("头条新闻");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mod_important_news none");
        printWriter.println("特别报道");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mod_netes_origina");
//        printWriter.println("网易独家:");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mt30 mod_news_special");
//        printWriter.println("新闻专题");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mt27 mod_high_dynamic");
//        printWriter.println("高层动态");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mt35 mod_war");
//        printWriter.println("军事航空");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mt35 mod_hot_rank clearfix");
//        printWriter.println("热点排行");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mt35 mod_money");
//        printWriter.println("财经");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mt27 mod_sports");
//        printWriter.println("体育");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mt35 mod_ent");
//        printWriter.println("娱乐");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mt25 mod_auto");
//        printWriter.println("汽车精选");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mt35 mod_house");
        printWriter.println("房产");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mt35 mod_wonderful_video");
//        printWriter.println("精彩视频");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mt35 mod_tech");
//        printWriter.println("科技");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mt27 mod_digi");
//        printWriter.println("手机数码");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mt35 mod_lady");
//        printWriter.println("女人时尚");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mt27 mod_edu");
//        printWriter.println("教育旅游");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "mt27 mod_jiankang");
//        printWriter.println("健康读书");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

        ListDiv = doc.getElementsByAttributeValue("class", "ns_area index_media");
        printWriter.println("媒体合作");
        for(org.jsoup.nodes.Element div : ListDiv) {
            Elements links = div.getElementsByTag("a");
            for(org.jsoup.nodes.Element link : links) {
                if(!link.hasText()) continue;
                printWriter.println(link.text() + "\t" + link.attr("href"));
            }
        }
        printWriter.println();

    }

    public static void printID(String url) throws Exception {

        File file = new File("ip地址.txt");
        PrintWriter printWriter = new PrintWriter(new FileWriter(file), true);

        InetAddress urlAddress = InetAddress.getByName(url);
        InetAddress localAddress = InetAddress.getLocalHost();

        printWriter.println(url + "\nip地址: " + urlAddress.getHostAddress());

        printWriter.println();

        printWriter.println("本机信息");
        printWriter.println("ip地址: " + localAddress.getHostAddress());
        printWriter.println("名称: " + localAddress.getHostName());

    }

    public static void main(String[] args) throws Exception {

        String protocol = "http://";
        String url = "news.163.com";
        org.jsoup.nodes.Document doc = Jsoup.connect((protocol + url)).timeout(3000).get();

//        System.out.println(doc.body());

        printSource(doc);

        printID(url);

        printContent(doc);

        //https://deerchao.net/tutorials/regex/regex.htm 好东西
    }

}
