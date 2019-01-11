package jp.mediahinge.spring.boot.app.service;

import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsOptions;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

@Service
public class NLUService {
	
	public void test_NLU() {
		IamOptions options = new IamOptions.Builder()
				.apiKey("QaOl-WQtrefrhpHZy1XIknYvIom8WpraljZNt2Jcx-FJ")
				.build();

		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding("2018-03-16", options);

        String language = "ja";
        String text = "宗教色の強い大嘗祭（だいじょうさい）は皇室の私費で――。来春には「皇嗣（こうし）」となる秋篠宮さまの発言が波紋を広げている。「政教分離」をどう考えるべきか。皇族の発言の自由はどこまでなのか。今回の秋篠宮さまの「大嘗祭については、皇室の行事として行われるもので、ある意味での宗教色の強いもの」であって、「国費で賄うのではなく、（皇室の私費に当たる）内廷（ないてい）会計で」「身の丈に合った儀式に」とおっしゃったことは、極めて真っ当なお話をされたと思います。かつて私が小学生の頃、天皇がその年にとれた新米を伊勢神宮に奉る神嘗祭（かんなめさい）が国の祭日としてあり、やはり新穀を神々に供えられる新嘗祭（にいなめさい）がありました。学校に行って紅白のもちをもらって国民みんなでお祝いしていました。\r\n" + 
        		"\r\n" + 
        		"　どちらの祭りも、明治政府が国家統一、国民統合の手段として、皇室の行事を国民全体の行事にしたわけです。\r\n" + 
        		"\r\n" + 
        		"　それが、戦後、国民の行事ではなくなり、新嘗祭の１１月２３日は勤労感謝の日になって、なんだかわけがわからなくなりました。\r\n" + 
        		"\r\n" + 
        		"　したがって、天皇が代わられた…";

		KeywordsOptions keywords = new KeywordsOptions.Builder().build();
		EntitiesOptions entities = new EntitiesOptions.Builder().build();
		ConceptsOptions concepts = new ConceptsOptions.Builder().build();

		Features features = new Features.Builder()
				.keywords(keywords)
				.entities(entities)
				.concepts(concepts)
				.build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder()
				.features(features)
				.language(language)
				.text(text)
				.build();

		AnalysisResults response = service
				.analyze(parameters)
				.execute();
		System.out.println(response);
		
	}
}
