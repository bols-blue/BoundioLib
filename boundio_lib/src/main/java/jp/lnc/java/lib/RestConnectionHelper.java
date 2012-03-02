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
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.representation.Representation;

public class RestConnectionHelper {

	RestConnectionHelper(String userSerial,String keyVal){
		this.keyVal = keyVal;
		this.userSerial = userSerial;
		this.varsionSerial = "vd1";
	}
	
	RestConnectionHelper(String userSerial,String keyVal,String varsionSerial){
		this.keyVal = keyVal;
		this.userSerial = userSerial;
		this.varsionSerial = varsionSerial;
	}
	private final String keyVal;
	private final String userSerial;
	private final String varsionSerial;
	private Response ret;
	
	public String setCall(String tel_num,String cast) {
		Client client = new Client(Protocol.HTTP);
		String url = "https://boundio.jp/api/"+varsionSerial+"/"+userSerial+"/call";
		Form form = new Form();
		form.add("key", keyVal);
		form.add("tel_to", tel_num);
		form.add("cast", cast); 	
		Representation rep = form.getWebRepresentation();
		Request request = new Request(Method.POST, url, rep);
		String jsonStr;
		try {
			ret = client.handle(request);
			Representation tmp = ret.getEntity();
			jsonStr = tmp.getText();
			tmp.getAvailableSize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonStr = e.toString();
		}
		return jsonStr;
	}
	
}