package jp.mediahinge.spring.boot;

/**
 * 記事Beanクラス
 * 
 * @author n.moriyama
 *
 */
public class ArticlesBean{
	private String _id;
	private String _rev = null;
	private String media;
	private String heading;
	private String first_paragraph;
	private String text;
	private String url;
	private String distribution_date;
	private String topics_id;

	/**
	 * 記事ID設定
	 * 
	 * @param _id 記事ID
	 */
	public void setArticles_id(String _id) {
		this._id = _id;
	}
	/**
	 * メディア設定
	 * 
	 * @param media メディア
	 */
	public void setMedia(String media) {
		this.media = media;
	}
	/**
	 * 見出し設定
	 * 
	 * @param heading 見出し
	 */
	public void setHeading(String heading) {
		this.heading = heading;
	}
	/**
	 * 第一段落設定
	 * 
	 * @param first_paragraph 第一段落
	 */
	public void setFirst_paragraph(String first_paragraph) {
		this.first_paragraph = first_paragraph;
	}
	/**
	 * 本文設定
	 * 
	 * @param text 本文
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * 記事URL設定
	 * 
	 * @param url 記事URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 配信日時設定
	 * 
	 * @param distribution_date 配信日時
	 */
	public void setDistribution_date(String distribution_date) {
		this.distribution_date = distribution_date;
	}
	/**
	 * トピックスID設定
	 * 
	 * @param topics_id トピックスID日時
	 */
	public void setTopics_id(String topics_id) {
		this.topics_id = topics_id;
	}
	public String toString() {
	    return "{ _id: " + _id + 
	    		",\nrev: " + _rev + 
	    		",\nmedia: " + media + 
	    		",\nheading: " + heading + 
	    		",\nfirst_paragraph: " + first_paragraph + 
	    		",\ntext: " + text + 
	    		",\nurl: " + url + 
	    		",\ndistribution_date: " + distribution_date + 
	    		",\ntopics_id: " + topics_id + 
	    		"\n}";
	}
}
