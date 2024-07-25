/* Generated by AN DISI Unibo */ 
package it.unibo.test_manager_ash

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

class Test_manager_ash ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 var ERROR = false  
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outblack("$name IDLE...")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="start", cond=doswitch() )
				}	 
				state("start") { //this:State
					action { //it:State
						forward("deposit_ash", "deposit_ash(1)" ,"ash_storage" ) 
						request("empty_ash", "empty_ash(1)" ,"ash_storage" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
				 	 		stateTimer = TimerActor("timer_start", 
				 	 					  scope, context!!, "local_tout_"+name+"_start", 15000.toLong() )  //OCT2023
					}	 	 
					 transition(edgeName="t04",targetState="fail",cond=whenTimeout("local_tout_"+name+"_start"))   
					transition(edgeName="t05",targetState="ash_ok",cond=whenReply("ashes_taken"))
				}	 
				state("ash_ok") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("ashes_taken(LEVEL)"), Term.createTerm("ashes_taken(LEVEL)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								CommUtils.outblack("$name Quantità nel ash storage: ${payloadArg(0)}")
								if(  payloadArg(0).toInt() != 1  
								 ){ ERROR = true  
								}
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="fail", cond=doswitchGuarded({ ERROR  
					}) )
					transition( edgeName="goto",targetState="end", cond=doswitchGuarded({! ( ERROR  
					) }) )
				}	 
				state("fail") { //this:State
					action { //it:State
						answer("start_test", "fail", "fail(1)"   )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
				state("end") { //this:State
					action { //it:State
						answer("start_test", "successful", "successful(1)"   )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
			}
		}
} 