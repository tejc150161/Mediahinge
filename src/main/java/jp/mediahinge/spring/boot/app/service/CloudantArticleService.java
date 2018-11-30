package jp.mediahinge.spring.boot.app.service;


import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.google.gson.JsonObject;

import jp.mediahinge.spring.boot.app.form.ArticleForm;
import jp.mediahinge.spring.boot.app.service.VCAPHelper;

@Service
public class CloudantArticleService {

    private Database db = null;
    private static final String databaseName = "test01";
    
    public CloudantArticleService(){
    	System.out.println("debug:instance");
        CloudantClient cloudant = createClient();
        if(cloudant!=null){
         db = cloudant.database(databaseName, true);
        }
    }
    
    public Database getDB(){
        return db;
    }

    private static CloudantClient createClient() {
        
        String url;

        System.out.println("DEBUG0!!!!:" + System.getenv("VCAP_SERVICES") );
        if (System.getenv("VCAP_SERVICES") != null) {
            // When running in IBM Cloud, the VCAP_SERVICES env var will have the credentials for all bound/connected services
            // Parse the VCAP JSON structure looking for cloudant.
            JsonObject cloudantCredentials = VCAPHelper.getCloudCredentials("cloudant");
            if(cloudantCredentials == null){
                System.out.println("No cloudant database service bound to this application");
                return null;
            }
            url = cloudantCredentials.get("url").getAsString();
        } else if (System.getenv("CLOUDANT_URL") != null) {
            url = System.getenv("CLOUDANT_URL");
        } else {
            System.out.println("Running locally. Looking for credentials in cloudant.properties");
            url = VCAPHelper.getLocalProperties("cloudant.properties").getProperty("cloudant_url");
            if(url == null || url.length()==0){
                System.out.println("To use a database, set the Cloudant url in src/main/resources/cloudant.properties");
                return null;
            }
        }

        try {
            System.out.println("Connecting to Cloudant");
//            CloudantClient client = ClientBuilder.url(new URL(url)).build();
            CloudantClient client = ClientBuilder.url(new URL(url)).proxyURL(new URL("http://172.17.0.2:80")).build();
            return client;
        } catch (Exception e) {
            System.out.println("Unable to connect to database");
            //e.printStackTrace();
            return null;
        }
    }
    
    public Collection<ArticleForm> getAll(){
        List<ArticleForm> docs;
        try {
            docs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(ArticleForm.class);
        } catch (IOException e) {
            return null;
        }
        return docs;
    }

    public ArticleForm get(String id) {
        return db.find(ArticleForm.class, id);
    }

    public ArticleForm persist(ArticleForm articleForm) {
        String id = db.save(articleForm).getId();
        return db.find(ArticleForm.class, id);
    }

    public ArticleForm update(String id, ArticleForm newArticleBean) {
        ArticleForm ArticleBean = db.find(ArticleForm.class, id);
        ArticleBean.setText(newArticleBean.getText());
        db.update(ArticleBean);
        return db.find(ArticleForm.class, id);

    }

    public void delete(String id) {
        ArticleForm ArticleBean = db.find(ArticleForm.class, id);
        db.remove(id, ArticleBean.get_rev());

    }

    public int count() throws Exception {
        return getAll().size();
    }

}
