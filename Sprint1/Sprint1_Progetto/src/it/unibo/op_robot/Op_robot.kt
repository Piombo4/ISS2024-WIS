/* Generated by AN DISI Unibo */ 
package it.unibo.op_robot

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
import wis.util.*

class Op_robot ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		  
				var RP: Boolean = false;
				var ASH: Boolean = false;
				var T: String = ""; 
				var X = "";
				var Y = "";
				var D = "";
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outgreen("$name INIZIATO")
						solve("consult('sysRules.pl')","") //set resVar	
						solve("consult('pointPicker.pl')","") //set resVar	
						solve("consult('positions.pl')","") //set resVar	
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="engage", cond=doswitch() )
				}	 
				state("engage") { //this:State
					action { //it:State
						request("engage", "engage($MyName,300)" ,"basicrobot" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t013",targetState="waiting",cond=whenReply("engagedone"))
					transition(edgeName="t014",targetState="engage",cond=whenReply("engagerefused"))
				}	 
				state("waiting") { //this:State
					action { //it:State
						forward("waiting", "waiting(1)" ,"wis" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t015",targetState="to_ws",cond=whenDispatch("start_robot"))
				}	 
				state("to_ws") { //this:State
					action { //it:State
						 T = Position.wastein.name;  
						solve("getPoint($T,TX,TY,TDIR)","") //set resVar	
						 X = getCurSol("TX").toString();  
						 Y = getCurSol("TY").toString();  
						 D = getCurSol("TDIR").toString();  
						request("moverobot", "moverobot($X,$Y)" ,"basicrobot" )  
						delay(500) 
						forward("setdirection", "dir($D)" ,"basicrobot" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t116",targetState="withdraw_ws",cond=whenReply("moverobotdone"))
				}	 
				state("withdraw_ws") { //this:State
					action { //it:State
						delay(1500) 
						forward("get_waste", "get_waste(1)" ,"waste_storage" ) 
						 RP = true;  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="to_incinerator", cond=doswitch() )
				}	 
				state("to_incinerator") { //this:State
					action { //it:State
						 T = Position.burnin.name;  
						solve("getPoint($T,TX,TY,TDIR)","") //set resVar	
						 X = getCurSol("TX").toString();  
						 Y = getCurSol("TY").toString();  
						 D = getCurSol("TDIR").toString();  
						request("moverobot", "moverobot($X,$Y)" ,"basicrobot" )  
						delay(500) 
						forward("setdirection", "dir($D)" ,"basicrobot" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t217",targetState="ask_to_burn",cond=whenReply("moverobotdone"))
					transition(edgeName="t218",targetState="to_incinerator",cond=whenReply("moverobotfailed"))
				}	 
				state("ask_to_burn") { //this:State
					action { //it:State
						delay(1500) 
						forward("burn_in", "burn_in(1)" ,"incinerator" ) 
						 RP = false  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="go_back_home", cond=doswitch() )
				}	 
				state("go_back_home") { //this:State
					action { //it:State
						 T = Position.home.name;  
						solve("getPoint($T,TX,TY,TDIR)","") //set resVar	
						 X = getCurSol("TX").toString();  
						 Y = getCurSol("TY").toString();  
						 D = getCurSol("TDIR").toString();  
						request("moverobot", "moverobot($X,$Y)" ,"basicrobot" )  
						delay(500) 
						forward("setdirection", "dir($D)" ,"basicrobot" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t319",targetState="wait_for_burn",cond=whenReply("moverobotdone"))
					transition(edgeName="t320",targetState="go_back_home",cond=whenReply("moverobotfailed"))
				}	 
				state("wait_for_burn") { //this:State
					action { //it:State
						delay(1500) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t421",targetState="to_incinerator_burned",cond=whenDispatch("burn_end"))
				}	 
				state("to_incinerator_burned") { //this:State
					action { //it:State
						 T = Position.burnout.name;  
						solve("getPoint($T,TX,TY,TDIR)","") //set resVar	
						 X = getCurSol("TX").toString();  
						 Y = getCurSol("TY").toString();  
						 D = getCurSol("TDIR").toString();  
						request("moverobot", "moverobot($X,$Y)" ,"basicrobot" )  
						delay(500) 
						forward("setdirection", "dir($D)" ,"basicrobot" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t522",targetState="get_ash",cond=whenReply("moverobotdone"))
					transition(edgeName="t523",targetState="to_incinerator_burned",cond=whenReply("moverobotfailed"))
				}	 
				state("get_ash") { //this:State
					action { //it:State
						delay(1500) 
						forward("get_ash", "get_ash(1)" ,"incinerator" ) 
						 ASH = true  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="to_ash_storage", cond=doswitch() )
				}	 
				state("to_ash_storage") { //this:State
					action { //it:State
						 T = Position.ashout.name;  
						solve("getPoint($T,TX,TY,TDIR)","") //set resVar	
						 X = getCurSol("TX").toString();  
						 Y = getCurSol("TY").toString();  
						 D = getCurSol("TDIR").toString();  
						request("moverobot", "moverobot($X,$Y)" ,"basicrobot" )  
						delay(500) 
						forward("setdirection", "dir($D)" ,"basicrobot" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t624",targetState="ask_as",cond=whenReply("moverobotdone"))
					transition(edgeName="t625",targetState="to_ash_storage",cond=whenReply("moverobotfailed"))
				}	 
				state("ask_as") { //this:State
					action { //it:State
						delay(1500) 
						forward("deposit_ash", "deposit_ash(1)" ,"ash_storage" ) 
						 ASH = false  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="go_back_home", cond=doswitch() )
				}	 
			}
		}
} 
