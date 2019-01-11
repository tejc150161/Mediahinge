package jp.mediahinge.spring.boot.app.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RSSForm extends BaseForm{
	private String media;
	private String url;
	
}
