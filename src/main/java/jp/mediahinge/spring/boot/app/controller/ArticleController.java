package jp.mediahinge.spring.boot.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.mediahinge.spring.boot.app.form.ArticleForm;
import jp.mediahinge.spring.boot.app.service.CloudantArticleService;

@Controller
@RequestMapping("articles")
public class ArticleController {

	@Autowired
	CloudantArticleService service;
	
//	ArticleStore store = ArticleStoreFactory.getInstance();

	/**
	 * ModelにFormを初期セットする
	 * 
	 * @return ArticleForm
	 */
	@ModelAttribute 
	ArticleForm setUpForm() {
		return new ArticleForm();
	}

	/**
	 * 見出し表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping
	public String list(Model model) {
		System.out.println("debug:ArticleController:01");
//		store = ArticleStoreFactory.getInstance();
		if(service == null) {
			System.out.println("debug:service is null");
			System.out.println("debug:ArticleController:02");
			return "articles/persist";
		}

		List<ArticleForm> articlesList = new ArrayList<ArticleForm>();
		for (ArticleForm doc : service.getAll()) {
			String article_id = doc.getArticle_id();
			if (article_id != null){
				ArticleForm form = new ArticleForm();
				form.setArticle_id(article_id);;
				articlesList.add(form);
			}
		}
		model.addAttribute("articles", articlesList);
		System.out.println("debug:ArticleController:03");
		return "articles/persist";
	}

	@PostMapping
	public String test(Model model) {
		ArticleForm articleForm = new ArticleForm();
		articleForm.setArticle_id("2018112701");
		articleForm.setMedia("test");
		articleForm.setHeading("test");
		articleForm.setFirst_paragraph("test");
		articleForm.setText("test");
		articleForm.setUrl("test");
		articleForm.setDistribution_date("test");
		articleForm.setTopics_id("2018112701");
		System.out.println("debug:insert test data");
		service.persist(articleForm);
		System.out.println("debug:successfully insert test data");
		service.shutDown();
		System.out.println("debug:successfully shutdown  the connection");
		return "redirect:/articles";
	}
}

