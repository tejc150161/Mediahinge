package jp.mediahinge.spring.boot;

/**
 * Bean基底クラス
 * 
 * @author t.kawana
 *
 */
public class BaseBean {

	// エラーメッセージ
	String errorMsg;

	/**
	 * エラーメッセージ取得
	 * 
	 * @return エラーメッセージ
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * エラーメッセージ設定
	 * 
	 * @param errorMsg　エラーメッセージ
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	
}
