package jp.mediahinge.spring.boot.app.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetArticleService {
	public void GetArticle(int count,String media) {
		try {
			File f = new File( media + "_rss.csv");
			BufferedReader br = new BufferedReader(new FileReader(f));

			URL url = null;
			InputStreamReader isr = null;
			String url_string = null;
			Document document = null;
			// 1行ずつCSVファイルのURLを読み込む
			while ((url_string = br.readLine()) != null) {
				document = Jsoup.connect(url_string).get();

			}
		} catch (IOException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}