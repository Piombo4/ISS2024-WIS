/* Generated by AN DISI Unibo */ 
package it.unibo.ctx_monitoringdevice
import it.unibo.kactor.QakContext
import it.unibo.kactor.sysUtil
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
	//System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR");
	QakContext.createContexts(
	        "localhost", this, "sprint0_architettura_with_msg.pl", "sysRules.pl", "ctx_monitoringdevice"
	)
	//JAN Facade
	//JAN24 Display
}

