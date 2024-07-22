/* Generated by AN DISI Unibo */ 
package it.unibo.ctx_wis
import it.unibo.kactor.QakContext
import it.unibo.kactor.sysUtil
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
	//System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR");
	QakContext.createContexts(
	        "localhost", this, "wis.pl", "sysRules.pl", "ctx_wis"
	)
	//JAN Facade
	main.java.unibo.sprint3_progetto_ssgui.Sprint3ProgettoSsguiApplication.main( arrayOf<String>() );
	//JAN24 Display
}

