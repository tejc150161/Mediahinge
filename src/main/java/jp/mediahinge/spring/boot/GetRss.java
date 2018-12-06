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

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;


@SpringBootApplication
@EnableScheduling
@Component
public class GetRss {

	@Scheduled(cron = "0 * * * * *")
	public void takahata() throws Exception{

		String urlstr[] = new String[3];
		urlstr[0] = "https://assets.wor.jp/rss/rdf/yomiuri/politics.rdf";//読売
		urlstr[1] = "http://www3.asahi.com/rss/politics.rdf";//朝日
		urlstr[2] = "https://mainichi.jp/rss/etc/mainichi-flash.rss";//毎日

		for(int i = 0; i<3; i++) {
			SyndFeedInput input = new SyndFeedInput();
			URL url = new URL(urlstr[i]);
			URLConnection urlConnection;

			SocketAddress addr = new InetSocketAddress("172.17.0.2", 80);
			Proxy proxy = new Proxy(Proxy.Type.HTTP,addr);
			urlConnection = url.openConnection(proxy);
			urlConnection.setRequestProperty("User-Agent","Mozilla/5.0");

			InputStream str = urlConnection.getInputStream();
			SyndFeed feed = input.build(new XmlReader(urlConnection));


			// 出力ファイルの作成
			FileWriter f = new FileWriter("E:\\git\\rss_data.csv", true);//出力場所
			PrintWriter p = new PrintWriter(new BufferedWriter(f));

			// 文字化け回避
			String media = feed.getTitle();
			if(media.equals("ニュース速報（総合）")) {
				p.print("mainichi"); 
			}
			else if(media.equals("政治 - 朝日新聞デジタル")) {
				p.print("asahi"); 
			}
			else if(media.equals("読売新聞 政治")) {
				p.print("yomiuri"); 
			}
			// 記事リンク取得
			p.print(",");
			for (Object obj : feed.getEntries()) {
				SyndEntry entry = (SyndEntry) obj;
				p.print(entry.getLink());
				p.print(",");
			}
			p.println();

			// ファイルに書き出し閉じる
			p.close();
			System.out.println("ファイル出力完了！" + i);    
		}
	}

	@Scheduled(cron = "0 * * * * *")
	public void nobu() throws Exception{

		String urlstr[] = new String[3];
		urlstr[0] = "https://assets.wor.jp/rss/rdf/yomiuri/politics.rdf";//読売
		urlstr[1] = "http://www3.asahi.com/rss/politics.rdf";//朝日
		urlstr[2] = "https://mainichi.jp/rss/etc/mainichi-flash.rss";//毎日

		for(int i = 0; i<3; i++) {
			SyndFeedInput input = new SyndFeedInput();
			URL url = new URL(urlstr[i]);
			URLConnection urlConnection;

			SocketAddress addr = new InetSocketAddress("172.17.0.2", 80);
			Proxy proxy = new Proxy(Proxy.Type.HTTP,addr);
			urlConnection = url.openConnection(proxy);
			urlConnection.setRequestProperty("User-Agent","Mozilla/5.0");

			InputStream str = urlConnection.getInputStream();
			SyndFeed feed = input.build(new XmlReader(urlConnection));

			PrintWriter p;
			// 出力ファイルの作成
			if(i == 0) {
				FileWriter f = new FileWriter("E:\\git\\yomiuri.csv", true);//出力場所
				p = new PrintWriter(new BufferedWriter(f));
				// 記事リンク取得
				for (Object obj : feed.getEntries()) {
					SyndEntry entry = (SyndEntry) obj;
					p.print(entry.getLink());
					p.println(",");
				}
				p.println();
				// ファイルに書き出し閉じる
				p.close();
				System.out.println("ファイル出力完了！" + i);    
			}
			if(i == 1) {
				FileWriter f = new FileWriter("E:\\git\\asahi.csv", true);//出力場所
				p = new PrintWriter(new BufferedWriter(f));
				// 記事リンク取得
				for (Object obj : feed.getEntries()) {
					SyndEntry entry = (SyndEntry) obj;
					p.print(entry.getLink());
					p.println(",");
				}
				p.println();
				// ファイルに書き出し閉じる
				p.close();
				System.out.println("ファイル出力完了！" + i);    
			}
			if(i == 2) {
				FileWriter f = new FileWriter("E:\\git\\mainichi.csv", true);//出力場所
				p = new PrintWriter(new BufferedWriter(f));
				// 記事リンク取得
				for (Object obj : feed.getEntries()) {
					SyndEntry entry = (SyndEntry) obj;
					p.print(entry.getLink());
					p.println(",");
				}
				p.println();
				// ファイルに書き出し閉じる
				p.close();
				System.out.println("ファイル出力完了！" + i);    
			}

		}
	}
}

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

