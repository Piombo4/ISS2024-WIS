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
	private final static String actorName1 = "tester";
	private static Interaction connSupport;

	/*
	 * Method to activate the system using the distribution
	 */
	public static void activateSystemUsingDocker() {
		Thread th = new Thread() {
			public void run() {
				try {
					CommUtils.outmagenta("test_manager activateSystemUsingDocker ");		
					Process p = Runtime.getRuntime().exec("docker-compose -f ./deployment/basicrobot.yaml up --force-recreate");
					Thread.sleep(5000);
					Process p1 = Runtime.getRuntime().exec("./build/distributions/wis-1.0/bin/wis");
					
					showOutput(p, ColorsOut.YELLOW);
					showOutput(p1, ColorsOut.YELLOW);
					
				} catch (Exception e) {
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
		activateSystemUsingDocker();
	}

	/*
	 * After each test
	 */
	@After
	public void down() {
		CommUtils.outcyan("end of test ");
		Thread th = new Thread() {
			public void run() {
				try {
					CommUtils.outmagenta("test_manager activateSystemUsingDocker ");		
					Process p = Runtime.getRuntime().exec("docker-compose -f ./deployment/basicrobot.yaml down");
					
					showOutput(p, ColorsOut.YELLOW);
					
				} catch (Exception e) {
					CommUtils.outred("test_manager activate ERROR " + e.getMessage());
				}
			}
		};
		th.start();
	}

	@Test
	public void testImmissionePacchetto() {
		IApplMessage req = CommUtils.buildDispatch("JUNITTEST", "start_test", "start_test(X)", actorName1);
		try {
			CommUtils.outmagenta("testSystem ======================================= ");
			Thread.sleep(10000);
			while (connSupport == null) {
				connSupport = ConnectionFactory.createClientSupport(ProtocolType.tcp, "localhost", "8014");
				CommUtils.outcyan("testSystem another connect attempt ");
				Thread.sleep(5000);
			}
			CommUtils.outcyan("CONNECTED to " + actorName1 + " " + connSupport);
			connSupport.forward(req);
			
			Thread.sleep(20000);
			IApplMessage reply = connSupport.request(CommUtils.buildRequest("JUNITTEST", "ask_test", "ask_test(X)", actorName1));
			CommUtils.outcyan("Asking if test ok");
			System.out.println(reply.msgContent());
			assertEquals("test_done(1)", "test_done(1)");
		} catch (Exception e) {
			CommUtils.outred("testSystem ERROR " + e.getMessage());
			fail("testRequest " + e.getMessage());
		}
	}

	public static void showOutput(Process proc, String color) {
		new Thread() {
			public void run() {
				try {
					BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
					BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
					ColorsOut.outappl("Here is the standard output of the command:\n", color);
					while (true) {
						String s = stdInput.readLine();
						if (s != null)
							ColorsOut.outappl(s, color);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
