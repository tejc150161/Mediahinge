package jp.mediahinge.spring.boot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class GetRss {
    public static void main(String[] args) throws Exception {
    	
    	String urlstr = "http://www3.asahi.com/rss/politics.rdf";

        SyndFeedInput input = new SyndFeedInput();
        URL url = new URL(urlstr);
        URLConnection urlConnection;
        
        SocketAddress addr = new InetSocketAddress("172.17.0.2", 80);
        Proxy proxy = new Proxy(Proxy.Type.HTTP,addr);
        urlConnection = url.openConnection(proxy);

        InputStream str = urlConnection.getInputStream();
        SyndFeed feed = input.build(new XmlReader(urlConnection));

        
        // 出力ファイルの作成
        FileWriter f = new FileWriter("E:\\git\\sample.csv", false);
        PrintWriter p = new PrintWriter(new BufferedWriter(f));
        
        // 記事リンク取得
        p.print(feed.getTitle());
        p.print(",");
        for (Object obj : feed.getEntries()) {
        	SyndEntry entry = (SyndEntry) obj;
            p.print(entry.getLink());
            p.print(",");
        }

        // ファイルに書き出し閉じる
        p.close();
        System.out.println("ファイル出力完了！");
        
        
        
//		urlConnection=url.openConnection();
//		  URL feedUrl = new URL(url);
//      HttpURLConnection urlConnection = null;
//      try {
//       Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.17.0.2", 80));
//       urlConnection = (HttpURLConnection) url.openConnection(proxy);
//       urlConnection.setRequestMethod("GET");
//       urlConnection.connect();
//      } catch (Exception e) {
//       e.printStackTrace();
//      }
//        // サイトのURL
//        System.out.println(feed.getLink());

//        for (Object obj : feed.getEntries()) {
//            SyndEntry entry = (SyndEntry) obj;
//            // 記事タイトル
//            System.out.println(entry.getTitle());
//            // 記事のURL
//            System.out.println(entry.getLink());
//            // 記事の詳細
//            System.out.println(entry.getDescription().getValue());
//        }
        
    }
}