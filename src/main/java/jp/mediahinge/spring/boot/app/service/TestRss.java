package jp.mediahinge.spring.boot.app.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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


//@SpringBootApplication
//@EnableScheduling
//@Component
public class TestRss {
	//	@Scheduled(cron = "5 * * * * *")
	public static void main(String[] args) throws Exception{

//		File rf = new File("E:\\git\\rss_data.csv");
//		BufferedReader br = new BufferedReader(new FileReader(rf));
//		String line;
//
//		if((line = br.readLine()) != null) {
//			System.out.println("なかみあった");
//			//csvファイルの先頭RSSだけ取得
//			String[] data = line.split(",",0);
//			//String topRss = data[1];
//			String updateRssTop = data[1];
//
//			if(topRss != updateRssTop) {
//				//更新があった時の処理
//				
//			}
//			else {
//				System.out.println("更新なし");
//			}
//		}
//
//		else {

//			String urlstr = "https://mainichi.jp/rss/etc/mainichi-flash.rss";//毎日
			String urlstr = "https://assets.wor.jp/rss/rdf/yomiuri/politics.rdf";//読売

			URL feedUrl = new URL(urlstr);
			SyndFeedInput input = new SyndFeedInput();
			URL url = new URL(urlstr);
			URLConnection urlConnection;

			//プロキシ設定
			SocketAddress addr = new InetSocketAddress("172.17.0.2", 80);
			Proxy proxy = new Proxy(Proxy.Type.HTTP,addr);
			urlConnection = url.openConnection(proxy);
			urlConnection.setRequestProperty("User-Agent","Mozilla/5.0");

			InputStream str = urlConnection.getInputStream();
			SyndFeed feed = input.build(new XmlReader(urlConnection));


			// 出力ファイルの作成
			FileWriter wf = new FileWriter("E:\\git\\rss_data.csv",true);
			PrintWriter p = new PrintWriter(new BufferedWriter(wf));

			
			// 記事リンク取得
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
			
			p.print(",");
			for (Object obj : feed.getEntries()) {
				SyndEntry entry = (SyndEntry) obj;
				p.print(entry.getLink());
				p.print(",");
			}
			p.println();
			

			
			p.close();
			System.out.println("ファイル出力完了！"); 


//			//一回目に取得したものの一番上のRSSを変数に保存
//			File ff = new File("E:\\git\\rss_data.csv");
//			BufferedReader fr = new BufferedReader(new FileReader(ff));
//			String first;
//			first = br.readLine();
//			String[] data = first.split(",",0);
//			String topRss = data[1];

			// ファイルに書き出し閉じる
  

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

