package jp.mediahinge.spring.boot.app.form;

import lombok.Data;

/**
 * Cloudantに挿入するデータのForm
 */
@Data
public class BaseForm {

	private String _id;
	private String _rev;
	private String type;
}
