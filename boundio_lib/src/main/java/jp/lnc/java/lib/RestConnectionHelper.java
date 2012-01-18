package jp.lnc.java.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.*;

import org.restlet.Client;
import org.restlet.Request;
import org.restlet.data.Form;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.representation.Representation;

public class RestConnectionHelper {

	RestConnectionHelper(String userSerial,String keyVal){
		this.keyVal = keyVal;
		this.userSerial = userSerial;
	}
	
	private final String keyVal;
	private final String userSerial;

	public String call(String tel_num,String cast){
		String url_str = "https://boundio.jp/api/vd1/" + userSerial + "/call";
		
		String ret = "";
		try{
			URL url = new URL(url_str);
			System.out.println("url:"+url_str);
			System.out.println("key:"+keyVal);
			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			OutputStream outS = urlConn.getOutputStream();
		 	String postStr = "key=" + keyVal + "&tel_to=" + tel_num + "&cast=" + cast;
		 	PrintStream printS = new PrintStream(outS);
		 	printS.print(postStr); 
	        
			InputStream inS = urlConn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inS));
			String s;
			while ((s = reader.readLine()) != null) {
				System.out.println(s);
				ret  += s+"\n";
			}
			reader.close();
		}
		catch( IOException e ){
			System.err.println( e );
		}
		return ret;
	}
	
	public String setCall(String tel_num,String cast) {
		Client client = new Client(Protocol.HTTP);
		String url = "https://boundio.jp/api/v1/"+userSerial+"/call";
		Form form = new Form();
		form.add("key", keyVal);
		form.add("tel_to", tel_num);
		form.add("cast", cast); 	
		Representation rep = form.getWebRepresentation();
		Request request = new Request(Method.POST, url, rep);
		String jsonStr;
		try {
			jsonStr = client.handle(request).getEntity().getText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonStr = e.toString();
		}
		return jsonStr;
	}
}