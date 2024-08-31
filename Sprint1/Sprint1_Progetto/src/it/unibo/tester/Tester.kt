/* Generated by AN DISI Unibo */ 
package it.unibo.tester

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

class Tester ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						delay(500) 
						observeResource("localhost","8014","ctx_wis","ash_storage","test")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="idle", cond=doswitch() )
				}	 
				state("idle") { //this:State
					action { //it:State
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t038",targetState="idle",cond=whenDispatch("test"))
					transition(edgeName="t039",targetState="startTest",cond=whenDispatch("start_test"))
				}	 
				state("startTest") { //this:State
					action { //it:State
						forward("waste_in", "waste_in(1)" ,"waste_storage" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
				 	 		stateTimer = TimerActor("timer_startTest", 
				 	 					  scope, context!!, "local_tout_"+name+"_startTest", 40000.toLong() )  //OCT2023
					}	 	 
					 transition(edgeName="t140",targetState="testNOK",cond=whenTimeout("local_tout_"+name+"_startTest"))   
					transition(edgeName="t141",targetState="testOK",cond=whenDispatch("test"))
				}	 
				state("testOK") { //this:State
					action { //it:State
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t142",targetState="replyTestOk",cond=whenRequest("ask_test"))
				}	 
				state("replyTestOk") { //this:State
					action { //it:State
						answer("ask_test", "test_done", "test_done(1)"   )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="idle", cond=doswitch() )
				}	 
				state("testNOK") { //this:State
					action { //it:State
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t143",targetState="replyTestNOK",cond=whenRequest("ask_test"))
				}	 
				state("replyTestNOK") { //this:State
					action { //it:State
						answer("ask_test", "test_done", "test_done(0)"   )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="idle", cond=doswitch() )
				}	 
			}
		}
} 