package jp.mediahinge.spring.boot.app.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.mediahinge.spring.boot.app.form.ArticleForm;

@Service
public class CloudantArticleService extends CloudantService{

    public Collection<ArticleForm> getAll(){
        List<ArticleForm> docs;
        try {
            docs = getDB().getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(ArticleForm.class);
        } catch (IOException e) {
            return null;
        }
        return docs;
    }

    public ArticleForm get(String id) {
        return getDB().find(ArticleForm.class, id);
    }

    public ArticleForm persist(ArticleForm articleForm) {
        String id = getDB().save(articleForm).getId();
        return getDB().find(ArticleForm.class, id);
    }

    public ArticleForm update(String id, ArticleForm newArticleForm) {
        ArticleForm articleForm = getDB().find(ArticleForm.class, id);
        articleForm.setText(newArticleForm.getText());
        getDB().update(articleForm);
        return getDB().find(ArticleForm.class, id);

    }

    public void delete(String id) {
        ArticleForm articleForm = getDB().find(ArticleForm.class, id);
        getDB().remove(id, articleForm.get_rev());

    }

    public int count() throws Exception {
        return getAll().size();
    }

}
