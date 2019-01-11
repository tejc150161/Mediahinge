package jp.mediahinge.spring.boot.app.schedule;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import jp.mediahinge.spring.boot.app.form.RSSForm;
import jp.mediahinge.spring.boot.app.get_data.GetArticle;
import jp.mediahinge.spring.boot.app.get_data.GetRSS;
import jp.mediahinge.spring.boot.app.service.CloudantArticleService;
import jp.mediahinge.spring.boot.app.service.CloudantRSSService;

@Component
public class ScheduledMethod {

	@Autowired
	private CloudantRSSService rssService;

	@Scheduled(cron = "0 * * * * *")
	@Scheduled(cron = "10 * * * * *")
	@Scheduled(cron = "20 * * * * *")
	@Scheduled(cron = "30 * * * * *")
	@Scheduled(cron = "40 * * * * *")
	@Scheduled(cron = "50 * * * * *")
	public void nobu() throws Exception{
		
		GetRSS getRSS = new GetRSS();
		getRSS.insertRSS();
		
//
//		String urlstr[] = new String[3];
//		urlstr[0] = "https://assets.wor.jp/rss/rdf/yomiuri/politics.rdf";//読売
//		urlstr[1] = "http://www3.asahi.com/rss/politics.rdf";//朝日
//		urlstr[2] = "https://mainichi.jp/rss/etc/mainichi-flash.rss";//毎日
//
//		System.out.println(urlstr[0]);
//
//		for(int i = 0; i<3; i++) {
//			SyndFeedInput input = new SyndFeedInput();
//			URL url = new URL(urlstr[i]);
//			URLConnection urlConnection;
//
//			//以下三行はローカル実行時にのみ必要な記述、デプロイ時にコメントアウト必須
//			SocketAddress addr = new InetSocketAddress("172.17.0.2", 80);
//			Proxy proxy = new Proxy(Proxy.Type.HTTP,addr);
//			urlConnection = url.openConnection(proxy);
//
//			urlConnection.setRequestProperty("User-Agent","Mozilla/5.0");
//
//			SyndFeed feed = input.build(new XmlReader(urlConnection));
//
//			RSSForm rssForm = new RSSForm();
//
//			rssForm.setType("rss");
//			if (i == 0) {
//				rssForm.setMedia("yomiuri");
//			}else if(i == 1 ) {
//				rssForm.setMedia("asahi");
//			}else {
//				rssForm.setMedia("mainichi");
//			}
//
//			// 記事リンク取得
//			for (Object obj : feed.getEntries()) {
//				SyndEntry entry = (SyndEntry) obj;
//
//				//記事URLが新規のものであった場合
//				if(rssService.findURL(entry.getLink()).size() == 0) {
//
//					rssForm.setUrl(entry.getLink());
//					rssService.persist(rssForm);
//					System.out.println("DEBUG:Successfully inserted rss data!");
//
//					//Cloudantに記事を挿入
//					GetArticle getArticle = new GetArticle();
//					getArticle.insertArticle(rssForm);
//
//					Thread.sleep(300);
//				} 
//				//記事URLが既存のものであった場合
//				else {
//					System.out.println("DEBUG:This rss is pre exists");
//					
//					//以下２行はテストメソッド
////					rssForm.setUrl(entry.getLink());
////					test_GetArticle(rssForm);
//					
//					break;
//				}
//				//entry.getLink()がURL
//			}
//			System.out.println("Finished reading " + rssForm.getMedia() + "'s rss data\n");
//
//		}
	}
	
	public void test_GetArticle(RSSForm rssForm) throws Exception{
		GetArticle getArticle = new GetArticle();
		getArticle.insertArticle(rssForm);
	}

}
