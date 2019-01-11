package jp.mediahinge.spring.boot.app.schedule;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jp.mediahinge.spring.boot.app.form.RSSForm;
import jp.mediahinge.spring.boot.app.get_data.GetArticle;
import jp.mediahinge.spring.boot.app.get_data.GetRSS;
@Component
public class ScheduledMethods {

	@Autowired
	private GetRSS getRSS;
	
	@Autowired
	private GetArticle getArticle;

	@Scheduled(cron = "0 * * * * *")
	@Scheduled(cron = "10 * * * * *")
	@Scheduled(cron = "20 * * * * *")
	@Scheduled(cron = "30 * * * * *")
	@Scheduled(cron = "40 * * * * *")
	@Scheduled(cron = "50 * * * * *")
	public void nobu() throws Exception{
		
		List<RSSForm> insertedRSSList = getRSS.insertRSS();
		
		Iterator it = insertedRSSList.iterator();
		
		while(it.hasNext()) {
			getArticle.insertArticle((RSSForm)it.next());
		}

	}
}
