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
					var x: Int = 0;
					var y: Int = 0;
					var position: String = "";
					var job: String = "";
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						ash_level= DMIN 
						delay(500) 
						observeResource("127.0.0.2","8021","ctx_monitoring_device","sonar","ash_level")
						observeResource("localhost","8014","ctx_wis","op_robot","robot_info")
						observeResource("localhost","8014","ctx_wis","waste_storage","waste_qty")
						forward("turn_on", "turn_on(1)" ,"incinerator" ) 
						CommUtils.outgreen("$name INIZIATO")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="robotWaiting",cond=whenDispatch("waiting"))
					transition(edgeName="t01",targetState="updateWasteQty",cond=whenDispatch("waste_qty"))
					transition(edgeName="t02",targetState="startBurningPhase",cond=whenDispatch("burn_start"))
					transition(edgeName="t03",targetState="endBurningPhase",cond=whenDispatch("burn_end"))
					transition(edgeName="t04",targetState="updateAshLevel",cond=whenDispatch("ash_level"))
					transition(edgeName="t05",targetState="updateGUI",cond=whenDispatch("robot_info"))
					transition(edgeName="t06",targetState="mockDeposit",cond=whenDispatch("mock_deposit"))
					transition(edgeName="t07",targetState="mockRemove",cond=whenDispatch("mock_remove"))
				}	 
				state("updateGUI") { //this:State
					action { //it:State
						CommUtils.outblue("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						if( checkMsgContent( Term.createTerm("robot_info(X,Y,POSITION,JOB)"), Term.createTerm("robot_info(X,Y,POSITION,JOB)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
												x = payloadArg(0).toInt();
												y = payloadArg(1).toInt();
												position = payloadArg(2).toString();
												job = payloadArg(3).toString();
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="verifyConditions", cond=doswitch() )
				}	 
				state("updateAshLevel") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("ash_level(LEVEL)"), Term.createTerm("ash_level(LEVEL)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 ash_level = payloadArg(0).toInt(); 
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
						if( checkMsgContent( Term.createTerm("robot_info(X,Y,POSITION,JOB)"), Term.createTerm("robot_info(X,Y,POSITION,JOB)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
												x = payloadArg(0).toInt();
												y = payloadArg(1).toInt();
												position = payloadArg(2).toString();
												job = payloadArg(3).toString();
						}
						if( checkMsgContent( Term.createTerm("ash_level(LEVEL)"), Term.createTerm("ash_level(LEVEL)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 ash_level = payloadArg(0).toInt(); 
								if(  ash_level < DLIMT || ash_level >= DMIN  
								 ){forward("led_status", "led_status(BLINK)" ,"led" ) 
								}
						}
						if( checkMsgContent( Term.createTerm("waste_qty(QTY)"), Term.createTerm("waste_qty(QTY)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 waste_qty = payloadArg(0).toInt()/50  
								CommUtils.outmagenta("$waste_qty")
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
					 transition( edgeName="goto",targetState="verifyConditions", cond=doswitch() )
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
						CommUtils.outgreen("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						CommUtils.outmagenta("$robot_waiting,$isBurning,$waste_qty,$ash_level")
						updateResourceRep("guidata($waste_qty,$ash_level,$isBurning,$x,$y,$position,$job)" 
						)
						if(  robot_waiting && !isBurning && waste_qty > 0 && ash_level >= DLIMT 
						 ){forward("start_robot", "start_robot(1)" ,"op_robot" ) 
						 robot_waiting = false  
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t08",targetState="robotWaiting",cond=whenDispatch("waiting"))
					transition(edgeName="t09",targetState="updateWasteQty",cond=whenDispatch("waste_qty"))
					transition(edgeName="t010",targetState="startBurningPhase",cond=whenDispatch("burn_start"))
					transition(edgeName="t011",targetState="endBurningPhase",cond=whenDispatch("burn_end"))
					transition(edgeName="t012",targetState="updateAshLevel",cond=whenDispatch("ash_level"))
					transition(edgeName="t013",targetState="updateGUI",cond=whenDispatch("robot_info"))
					transition(edgeName="t014",targetState="mockDeposit",cond=whenDispatch("mock_deposit"))
					transition(edgeName="t015",targetState="mockRemove",cond=whenDispatch("mock_remove"))
				}	 
				state("mockDeposit") { //this:State
					action { //it:State
						forward("waste_in", "waste_in(1)" ,"waste_storage" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="verifyConditions", cond=doswitch() )
				}	 
				state("mockRemove") { //this:State
					action { //it:State
						request("empty_ash", "empty_ash(1)" ,"ash_storage" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="tx16",targetState="verifyConditions",cond=whenReply("ashes_taken"))
				}	 
			}
		}
} 
