package jp.lnc.java.lib;
import static org.junit.Assert.*;

import org.junit.Test;


public class RestConnectionHelperTest {
	String userSerial ="NNGVKWFBYTLYFAEY";
	//https://boundio.jp/api/vd1/NNGVKWFBYTLYFAEY/call 
	String key ="eSxAwZ1qzHY5Bpy6";
	
	@Test
	public void testSetCall() {

		RestConnectionHelper testConnect = new RestConnectionHelper(userSerial, key) ;
		String ret = testConnect.call("09011723745", "file(000001)");
		fail(ret);
	}

}
