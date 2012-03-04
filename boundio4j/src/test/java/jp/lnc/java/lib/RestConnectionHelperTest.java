package jp.lnc.java.lib;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class RestConnectionHelperTest {
	String userSerial = "NNGVKWFBYTLYFAEY";
	// https://boundio.jp/api/vd1/NNGVKWFBYTLYFAEY/call
	String key = "eSxAwZ1qzHY5Bpy6";

	@Test
	public void testSetCall() {
		BoundioTelephon testConnect = new BoundioTelephon(userSerial,
				key);
		String ret = testConnect.call("09011723745", "file(000001)");
		fail(ret);
	}

	/**
	 * URI
	 */
	private static final String SUGGETS_URI = "http://bbbb.com";

	/**
	 * 
	 * 実行
	 * 
	 * @throws Exception
	 *             例外
	 */
	@Test
	public void execute() throws Exception {
		final HttpClient client = new DefaultHttpClient();

		final HttpPost post = new HttpPost(SUGGETS_URI);

		final List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("mode", "xml"));

		params.add(new BasicNameValuePair("url", "http://bbbb.com"));

		post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

		final HttpResponse response = client.execute(post);

		System.out.println(response.getStatusLine());

		System.out.println(EntityUtils.toString(response.getEntity()));

		post.abort();
	}

}
