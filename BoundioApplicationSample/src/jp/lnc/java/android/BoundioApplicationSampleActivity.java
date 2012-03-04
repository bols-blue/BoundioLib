package jp.lnc.java.android;

import jp.lnc.java.android.R.id;
import jp.lnc.java.lib.BoundioTelephon;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BoundioApplicationSampleActivity extends Activity {
	// boundioのユーザ固有シリアル
	String userSerial = "9W9G3VEMASQ8VJTL";
	// boundioのユーザー固有キー
	String key = "eSxAwZ1qzHY5Bpy6";

	private BoundioTelephon telephon;
	private EditText callNomber;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		telephon = new BoundioTelephon(userSerial, key);
		Button button = (Button) findViewById(id.callButton);
		callNomber = (EditText) findViewById(id.callNomberText);
		// ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します

		button.setOnClickListener(new CallButtonListener());
	}

	private class CallButtonListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			String callNomberString = callNomber.getText().toString();
			// 通話先の電話番号と再生するファイルの指定
			String ret = telephon.call(callNomberString, "file(000001)");
			Toast.makeText(BoundioApplicationSampleActivity.this, "call "+callNomberString,
					Toast.LENGTH_SHORT).show();
			Log.d("boundio4j", ret);
		}
	}
}