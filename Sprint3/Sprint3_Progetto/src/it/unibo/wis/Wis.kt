/* Generated by AN DISI Unibo */ 
package it.unibo.wis

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

class Wis ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		
					var ash_qty = 0
					var waste_qty = 0
					var ash_level = 0
					var isBurning: Boolean = false;
					var robot_waiting: Boolean = false;
					val DLIMT: Int = 5;
					val DMIN: Int = 25;
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						ash_level= DMIN 
						delay(500) 
						observeResource("localhost","8014","ctx_wis","waste_storage","waste_qty")
						observeResource("127.0.0.2","8021","ctx_monitoring_device","sonar","ash_level")
						forward("turn_on", "turn_on(1)" ,"incinerator" ) 
						CommUtils.outgreen("$name INIZIATO")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t01",targetState="robotWaiting",cond=whenDispatch("waiting"))
					transition(edgeName="t02",targetState="updateWasteQty",cond=whenDispatch("waste_qty"))
					transition(edgeName="t03",targetState="startBurningPhase",cond=whenDispatch("burn_start"))
					transition(edgeName="t04",targetState="endBurningPhase",cond=whenDispatch("burn_end"))
					transition(edgeName="t05",targetState="updateAshLevel",cond=whenDispatch("ash_level"))
				}	 
				state("updateAshLevel") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("ash_level(LEVEL)"), Term.createTerm("ash_level(N)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 ash_level = payloadArg(0).toInt(); 
								updateResourceRep( "guidata($waste_qty,$ash_level,$isBurning,)"  
								)
								if(  ash_level < DLIMT || ash_level >= DMIN  
								 ){forward("led_status", "led_status(BLINK)" ,"led" ) 
								}
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="verifyConditions", cond=doswitch() )
				}	 
				state("robotWaiting") { //this:State
					action { //it:State
						 robot_waiting = true  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="verifyConditions", cond=doswitch() )
				}	 
				state("updateWasteQty") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("waste_qty(X)"), Term.createTerm("waste_qty(N)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 waste_qty = payloadArg(0).toInt()/50  
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="verifyConditions", cond=doswitch() )
				}	 
				state("startBurningPhase") { //this:State
					action { //it:State
						 isBurning = true  
						forward("led_status", "led_status(ON)" ,"led" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t06",targetState="robotWaiting",cond=whenDispatch("waiting"))
					transition(edgeName="t07",targetState="updateWasteQty",cond=whenDispatch("waste_qty"))
					transition(edgeName="t08",targetState="startBurningPhase",cond=whenDispatch("burn_start"))
					transition(edgeName="t09",targetState="endBurningPhase",cond=whenDispatch("burn_end"))
				}	 
				state("endBurningPhase") { //this:State
					action { //it:State
						 isBurning = false  
						forward("led_status", "led_status(OFF)" ,"led" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="verifyConditions", cond=doswitch() )
				}	 
				state("verifyConditions") { //this:State
					action { //it:State
						if(  robot_waiting && !isBurning && waste_qty > 0 && ash_level >= DLIMT 
						 ){forward("start_robot", "start_robot(1)" ,"op_robot" ) 
						 robot_waiting = false  
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t010",targetState="robotWaiting",cond=whenDispatch("waiting"))
					transition(edgeName="t011",targetState="updateWasteQty",cond=whenDispatch("waste_qty"))
					transition(edgeName="t012",targetState="startBurningPhase",cond=whenDispatch("burn_start"))
					transition(edgeName="t013",targetState="endBurningPhase",cond=whenDispatch("burn_end"))
				}	 
			}
		}
} 