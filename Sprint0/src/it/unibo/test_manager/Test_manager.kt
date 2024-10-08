/* Generated by AN DISI Unibo */ 
package it.unibo.test_manager

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

class Test_manager ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="idle", cond=doswitch() )
				}	 
				state("idle") { //this:State
					action { //it:State
						CommUtils.outblack("$name IDLE...")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="start",cond=whenRequest("start_test"))
				}	 
				state("start") { //this:State
					action { //it:State
						observeResource("127.0.0.2","8017","ctx_ashstorage","ash_storage","qty")
						forward("get_waste", "get_waste(1)" ,"waste_storage" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t01",targetState="updateQty",cond=whenDispatch("qty"))
				}	 
				state("updateQty") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("qty(N)"), Term.createTerm("qty(N)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								CommUtils.outblack("$name Quantità nell'ash storage: $N")
								answer("start_test", "reply_qty", "reply_qty(1)"   )  
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t02",targetState="updateQty",cond=whenDispatch("qty"))
				}	 
			}
		}
} 
