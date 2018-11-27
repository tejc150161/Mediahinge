package jp.mediahinge.spring.boot;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

public class Connect_to_Cloudant {
//	@Autowired
//	private CloudantClient client;
//
//	@Autowired
//	private Database database;
	
	public void Insert_testdata(){
		try {
			//			CloudantClient client = ClientBuilder.account("b9e33c9d-6c29-4fad-9027-1502fd9aefc6-bluemix:76d0f787d8714824affbb9c562605b9d88e6100a234f6c0943d4504c292e75ba@b9e33c9d-6c29-4fad-9027-1502fd9aefc6-bluemix")
			//					.username("b9e33c9d-6c29-4fad-9027-1502fd9aefc6-bluemix")
			//					.password("76d0f787d8714824affbb9c562605b9d88e6100a234f6c0943d4504c292e75ba")
			//					.build();
			CloudantClient client = ClientBuilder.bluemix( System.getenv("VCAP_SERVICES"))
					.build();
			
			Database db = client.database("test04", true);

			ArticlesBean articlesBean = new ArticlesBean();
			articlesBean.setArticles_id("2018112701");
			articlesBean.setMedia("test");
			articlesBean.setHeading("test");
			articlesBean.setFirst_paragraph("test");
			articlesBean.setText("test");
			articlesBean.setUrl("test");
			articlesBean.setDistribution_date("test");
			articlesBean.setTopics_id("2018112701");
			db.save(articlesBean);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
