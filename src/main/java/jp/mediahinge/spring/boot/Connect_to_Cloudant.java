package jp.mediahinge.spring.boot;

import java.net.URL;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Connect_to_Cloudant {
	public void Connect_to_Database(){
		try {
			URL url = new URL("https://b9e33c9d-6c29-4fad-9027-1502fd9aefc6-bluemix:76d0f787d8714824affbb9c562605b9d88e6100a234f6c0943d4504c292e75ba@b9e33c9d-6c29-4fad-9027-1502fd9aefc6-bluemix.cloudant.com");
			CloudantClient client = ClientBuilder.account("b9e33c9d-6c29-4fad-9027-1502fd9aefc6-bluemix:76d0f787d8714824affbb9c562605b9d88e6100a234f6c0943d4504c292e75ba@b9e33c9d-6c29-4fad-9027-1502fd9aefc6-bluemix")
					.username("b9e33c9d-6c29-4fad-9027-1502fd9aefc6-bluemix")
					.password("76d0f787d8714824affbb9c562605b9d88e6100a234f6c0943d4504c292e75ba")
					.build();
			Database db = client.database("test02", true);

			ArticlesBean articlesBean = new ArticlesBean();
			articlesBean.setArticles_id("2018112601");
			articlesBean.setMedia("test");
			articlesBean.setHeading("test");
			db.save(articlesBean.toString());
		}catch(Exception e) {

		}
	}
}
