package main.java.test;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.ColorsOut;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

public class TestInteractions {
    private final static String actorName1 = "test_manager";
    private final static String actorName2 = "test_manager_ash";
    private static Interaction connSupport;
  
/*
 *  Method to activate the system
 *  using the distribution
 */
public static void activateSystemUsingDeploy() { 
	Thread th = new Thread(){
		public void run(){
			try {
				CommUtils.outmagenta("test_manager activateSystemUsingDeploy ");
				Process p = Runtime.getRuntime().exec("./gradlew.bat run");
				Process p1 = Runtime.getRuntime().exec("./gradlew.bat runTest");
				
				showOutput(p,ColorsOut.BLACK);
				showOutput(p1,ColorsOut.BLACK);
				
			} catch ( Exception e) {
				CommUtils.outred("test_manager activate ERROR " + e.getMessage());
			}
		}
	};
	th.start();
}  

/*
 * Before all the tests
 */
	@BeforeClass
	public static void activate() {
		
		CommUtils.outmagenta("test_manager activate ");
		//activateSystemUsingGradle();
		activateSystemUsingDeploy();
	}
/*
 * After each test	
 */
	@After
	public void down() { 
		CommUtils.outcyan("end of test ");
	}


	@Test
	public void prelievoRP() {
		IApplMessage req  = CommUtils.buildRequest("tester", "start_test", "start_test(X)", actorName1);
 		try {
  			 CommUtils.outmagenta("testSystem ======================================= ");
			while( connSupport == null ) {
 				connSupport = ConnectionFactory.createClientSupport(ProtocolType.tcp, "localhost", "8018");
 				CommUtils.outcyan("testSystem another connect attempt ");
 				Thread.sleep(1000);
 			}
 			CommUtils.outcyan("CONNECTED to test_manager " + connSupport);
			IApplMessage reply = connSupport.request(req);
			CommUtils.outcyan("testSystem reply="+reply);
			String answer = reply.msgContent();
			assertEquals(answer, "successful(1)");
		} catch (Exception e) {
			CommUtils.outred("testSystem ERROR " + e.getMessage());
			fail("testRequest " + e.getMessage());
		}
	}
	@Test
	public void depositoCenere() {
		IApplMessage req  = CommUtils.buildRequest("tester", "start_test", "start_test(X)", actorName2);
 		try {
  			 CommUtils.outmagenta("testSystem ======================================= ");
			while( connSupport == null ) {
 				connSupport = ConnectionFactory.createClientSupport(ProtocolType.tcp, "localhost", "8018");
 				CommUtils.outcyan("testSystem another connect attempt ");
 				Thread.sleep(1000);
 			}
 			CommUtils.outcyan("CONNECTED to test_manager " + connSupport);
			IApplMessage reply = connSupport.request(req);
			CommUtils.outcyan("testSystem reply="+reply);
			String answer = reply.msgContent();
			assertEquals(answer, "successful(1)");
		} catch (Exception e) {
			CommUtils.outred("testSystem ERROR " + e.getMessage());
			fail("testRequest " + e.getMessage());
		}
	}
	public static void showOutput(Process proc, String color){
	    new Thread(){
	        public void run(){
	            try {
	            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
	            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
	            ColorsOut.outappl("Here is the standard output of the command:\n", color);
	            while (true){
	                String s = stdInput.readLine();
	                if ( s != null ) ColorsOut.outappl( s, color );
	            } 
	            }catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }.start();
	}
}

