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

        Elements links = doc.select("a");

        for(org.jsoup.nodes.Element link : links) {
            if(!link.hasText()) continue;
            printWriter.println(link.attr("abs:href") + " " + link.text());
//            printWriter.println(link.text());
        }

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
