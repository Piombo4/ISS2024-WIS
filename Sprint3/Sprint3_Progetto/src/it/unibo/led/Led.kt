/* Generated by AN DISI Unibo */ 
package it.unibo.led

import it.unibo.kactor.*
import alice.tuprolog.*
import unibo.basicomm23.*
import unibo.basicomm23.interfaces.*
import unibo.basicomm23.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import it.unibo.kactor.sysUtil.createActor   //Sept2023

//User imports JAN2024

class Led ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		
			lateinit var p : Process	
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						delay(500) 
						CommUtils.outgreen("$name STARTS")
						 p = Runtime.getRuntime().exec("python LedOff.py");  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="updateLed",cond=whenDispatch("led_status"))
				}	 
				state("updateLed") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("led_status(X)"), Term.createTerm("led_status(X)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
											 	p.destroy()
												 if (p.isAlive()) {
									    			p.destroyForcibly();
													}
												if(payloadArg(0) == "ON"){
													
								    				p = Runtime.getRuntime().exec("python LedOn.py")
												}
												else if(payloadArg(0)== "OFF"){
													
								    				p = Runtime.getRuntime().exec("python LedOff.py")
												}
												else {
													if(payloadArg(0) == "BLINK"){
								    				p = Runtime.getRuntime().exec("python LedBlink.py")
												}
												} 
												
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t01",targetState="updateLed",cond=whenDispatch("led_status"))
				}	 
			}
		}
} 
